package de.mfroese.trainingsplan.gui;

import javafx.fxml.FXML;

/**
 * Implementiert die Steuerlogik für die Statistik-Workload-Szene.
 */
public class StatisticsWorkloadController {
    //region Attribute
    //endregion

    //region Konstruktoren
    @FXML
    public void initialize() {
        // Initialisierungslogik hier einfügen
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
