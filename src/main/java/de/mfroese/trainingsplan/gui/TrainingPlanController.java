package de.mfroese.trainingsplan.gui;

import de.mfroese.trainingsplan.gui.listview.ExerciseCellFactory;
import de.mfroese.trainingsplan.logic.ExerciseHolder;
import de.mfroese.trainingsplan.logic.PlanHolder;
import de.mfroese.trainingsplan.model.Exercise;
import de.mfroese.trainingsplan.model.ExerciseList;
import de.mfroese.trainingsplan.model.Plan;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

/**
 * Implementiert die Steuerlogik für die Training Plan-Szene.
 */
public class TrainingPlanController {
    //region Attribute
    private Plan selectedPlan;

    @FXML
    private Text planName;

    @FXML
    private List<ExerciseList> exerciseList;

    @FXML
    private ListView<Exercise> exerciseListView;
    @FXML
    private AnchorPane exerciseBox;
    @FXML
    private AnchorPane sceneContent;
    @FXML
    private AnchorPane overlay;
    @FXML
    private VBox firstExerciseInfobox;
    @FXML
    private Button btnStartTraining;
    //endregion

    //region Konstruktoren
    @FXML
    public void initialize() {
        Platform.runLater(this::updateExerciseVisibility);
    }
    //endregion

    //region Methoden
    protected void setSelectedPlan(Plan selectedPlan) {

        this.selectedPlan = selectedPlan;
        if (selectedPlan != null) {

            planName.setText(selectedPlan.getName());

            exerciseListView.setCellFactory(new ExerciseCellFactory());

            Platform.runLater(this::loadExercises);
        }
    }

    private void loadExercises() {
        exerciseListView.setItems(ExerciseHolder.getInstance().getExercises());

        exerciseListView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
                Exercise selectedExercise = exerciseListView.getSelectionModel().getSelectedItem();
                SceneManager.getInstance().switchToNewPlanExercise(selectedPlan, selectedExercise);
            }
        });

        exerciseListView.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                Exercise selectedExercise = exerciseListView.getSelectionModel().getSelectedItem();
                SceneManager.getInstance().switchToNewPlanExercise(selectedPlan, selectedExercise);
            }
        });
    }

    private void updateExerciseVisibility() {
        boolean hasPlans = !ExerciseHolder.getInstance().getExercises().isEmpty();
        firstExerciseInfobox.setDisable(hasPlans);
        firstExerciseInfobox.setVisible(!hasPlans);
        btnStartTraining.setDisable(!hasPlans);
        btnStartTraining.setVisible(hasPlans);
        exerciseBox.setDisable(!hasPlans);
        exerciseBox.setVisible(hasPlans);
    }

    @FXML
    private void editMenu() {
        sceneContent.setDisable(true);
        overlay.setDisable(false);
        overlay.setVisible(true);
    }

    @FXML
    private void closeMenu() {
        sceneContent.setDisable(false);
        overlay.setDisable(true);
        overlay.setVisible(false);
    }

    @FXML
    private void renamePlan() {
        closeMenu();
        SceneManager.getInstance().switchToNewTrainingPlan(selectedPlan);
    }

    @FXML
    private void planToArchive() {
        selectedPlan.setActive(false);
        closeMenu();
        SceneManager.getInstance().switchToTrainingPlans();
    }

    @FXML
    private void deletePlan() {
        PlanHolder.getInstance().delete(selectedPlan);
        closeMenu();
        SceneManager.getInstance().switchToTrainingPlans();
    }

    @FXML
    private void startTraining() {
        //todo Trainingsseite erstellen
        //SceneManager.getInstance().switchToTrainingPlans();
    }

    @FXML
    private void newExercise() {
        SceneManager.getInstance().switchToNewPlanExercise(selectedPlan);
    }


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
