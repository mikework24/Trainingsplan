package de.mfroese.trainingsplan.gui;

import de.mfroese.trainingsplan.gui.listview.PlanCellFactory;
import de.mfroese.trainingsplan.logic.ExerciseHolder;
import de.mfroese.trainingsplan.logic.PlanHolder;
import de.mfroese.trainingsplan.logic.StorageManager;
import de.mfroese.trainingsplan.model.Plan;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Implementiert die Steuerlogik für die Training Plans-Szene.
 */
public class TrainingPlansController {
    //region Attribute
    @FXML
    private ListView<Plan> planListView;
    @FXML
    private AnchorPane planBox;
    @FXML
    private VBox firstPlanInfobox;
    //endregion

    //region Konstruktoren
    @FXML
    public void initialize() {
        int userId = Integer.parseInt(StorageManager.getInstance().getUserId());
        if (userId > 0) {
            initializePlanListView();
            initializeFirstPlanInfobox();
        }
    }

    private void initializePlanListView() {
        planListView.setCellFactory(new PlanCellFactory());
        planListView.setItems(PlanHolder.getInstance().search("active=true"));

        planListView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
                Plan selectedPlan = planListView.getSelectionModel().getSelectedItem();
                switchToTrainingPlan(selectedPlan);
            }
        });

        planListView.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                Plan selectedPlan = planListView.getSelectionModel().getSelectedItem();
                switchToTrainingPlan(selectedPlan);
            }
        });
    }

    private void initializeFirstPlanInfobox() {
        boolean hasActivePlans = !PlanHolder.getInstance().search("active=true").isEmpty();
        firstPlanInfobox.setDisable(hasActivePlans);
        firstPlanInfobox.setVisible(!hasActivePlans);
        planBox.setDisable(!hasActivePlans);
        planBox.setVisible(hasActivePlans);
    }

    private void switchToTrainingPlan(Plan selectedPlan) {
        StorageManager.getInstance().setActivePlanId(selectedPlan.getId());
        ExerciseHolder.getInstance().reload();
        SceneManager.getInstance().switchToTrainingPlan(selectedPlan);
    }
    //endregion

    //region Methoden
    @FXML
    private void openTrainingArchive() {
        SceneManager.getInstance().switchToTrainingArchive();
    }

    @FXML
    private void openNewTrainingPlan() {
        SceneManager.getInstance().switchToNewTrainingPlan();
    }

    @FXML
    private void openStatisticsExercise() {
        SceneManager.getInstance().switchToStatisticsExercise();
    }

    @FXML
    private void openFinishedWorkout() {
        SceneManager.getInstance().switchToFinishedWorkout();
    }

    @FXML
    private void openBodyData() {
        SceneManager.getInstance().switchToBodyData();
    }

    @FXML
    private void openSettings() {
        SceneManager.getInstance().switchToSettings();
    }
    //endregion
}
