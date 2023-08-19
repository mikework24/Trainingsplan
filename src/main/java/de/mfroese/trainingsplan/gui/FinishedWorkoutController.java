package de.mfroese.trainingsplan.gui;

import javafx.fxml.FXML;

/**
 * Implementiert Steuerlogik für die Trainingplaene-Szene
 */
public class FinishedWorkoutController {
    //region Konstanten
    //endregion

    //region Attribute
    //endregion

    //region Konstruktoren
    @FXML
    public void initialize() {

        //todo prüfen ob ein plan verfügbar ist

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
    private void openBodyData() {
        SceneManager.getInstance().switchToBodyData();
    }
    @FXML
    private void openSettings() {
        SceneManager.getInstance().switchToSettings();
    }
    //endregion
}
