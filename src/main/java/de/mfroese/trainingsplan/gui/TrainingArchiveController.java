package de.mfroese.trainingsplan.gui;

import de.mfroese.trainingsplan.gui.listview.PlanArchiveCellFactory;
import de.mfroese.trainingsplan.logic.PlanHolder;
import de.mfroese.trainingsplan.logic.StorageManager;
import de.mfroese.trainingsplan.model.Plan;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Implementiert die Steuerlogik für die Training Archive-Szene.
 */
public class TrainingArchiveController {
    //region Attribute
    @FXML
    private ListView<Plan> planArchiveListView;
    @FXML
    private AnchorPane planArchiveBox;
    @FXML
    private VBox firstPlanArchiveInfobox;
    //endregion

    //region Konstruktoren
    @FXML
    public void initialize() {
        int userId = Integer.parseInt(StorageManager.getInstance().getUserId());
        if (userId > 0) {
            initializePlanArchiveListView();
        }
    }

    private void initializePlanArchiveListView() {
        planArchiveListView.setCellFactory(new PlanArchiveCellFactory());
        planArchiveListView.setItems(PlanHolder.getInstance().search("active=false"));

        Platform.runLater(this::updatePlanArchiveVisibility);
    }

    private void updatePlanArchiveVisibility() {
        boolean hasArchivedPlans = !PlanHolder.getInstance().search("active=false").isEmpty();
        firstPlanArchiveInfobox.setDisable(hasArchivedPlans);
        firstPlanArchiveInfobox.setVisible(!hasArchivedPlans);
        planArchiveBox.setDisable(!hasArchivedPlans);
        planArchiveBox.setVisible(hasArchivedPlans);
    }
    //endregion

    //region Methoden
    @FXML
    private void openTrainingPlans() {
        SceneManager.getInstance().switchToTrainingPlans();
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
