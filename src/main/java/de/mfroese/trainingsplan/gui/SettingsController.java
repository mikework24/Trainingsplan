package de.mfroese.trainingsplan.gui;

import de.mfroese.trainingsplan.logic.StorageManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * Implementiert die Steuerlogik für die Einstellungen-Szene.
 */
public class SettingsController {
    //region Attribute
    @FXML
    private TextField txtBreakTimeOne;
    @FXML
    private TextField txtBreakTimeTwo;
    @FXML
    private TextField txtBreakTimeThree;
    @FXML
    private Button btnSaveBreakTimes;

    @FXML
    private CheckBox checkTakeOverWeight;
    @FXML
    private CheckBox checkTakeOverRepeat;
    @FXML
    private CheckBox checkTakeOverSentence;

    @FXML
    private TextField txtNewPassword;
    @FXML
    private TextField txtNewPasswordRepeat;
    //endregion

    //region Konstruktor
    @FXML
    public void initialize() {
        loadSavedBreakTimes();
        addBreakTimeListeners();
        loadSavedTakeOverValues();
        addTakeOverListeners();
    }
    //endregion

    //region Methoden
    private void loadSavedBreakTimes() {
        txtBreakTimeOne.setText(StorageManager.getInstance().getBreakTimeOne());
        txtBreakTimeTwo.setText(StorageManager.getInstance().getBreakTimeTwo());
        txtBreakTimeThree.setText(StorageManager.getInstance().getBreakTimeThree());
    }

    private void addBreakTimeListeners() {
        txtBreakTimeOne.textProperty().addListener((observable, oldValue, newValue) -> disableSaveBreakTimesButtonByBlank());
        txtBreakTimeTwo.textProperty().addListener((observable, oldValue, newValue) -> disableSaveBreakTimesButtonByBlank());
        txtBreakTimeThree.textProperty().addListener((observable, oldValue, newValue) -> disableSaveBreakTimesButtonByBlank());
    }

    private void loadSavedTakeOverValues() {
        checkTakeOverWeight.setSelected(StorageManager.getInstance().getTakeOverWeight());
        checkTakeOverRepeat.setSelected(StorageManager.getInstance().getTakeOverRepeat());
        checkTakeOverSentence.setSelected(StorageManager.getInstance().getTakeOverSentence());
    }

    private void addTakeOverListeners() {
        checkTakeOverWeight.selectedProperty().addListener((observable, oldValue, newValue) -> StorageManager.getInstance().saveTakeOverWeight(newValue));
        checkTakeOverRepeat.selectedProperty().addListener((observable, oldValue, newValue) -> StorageManager.getInstance().saveTakeOverRepeat(newValue));
        checkTakeOverSentence.selectedProperty().addListener((observable, oldValue, newValue) -> StorageManager.getInstance().saveTakeOverSentence(newValue));
    }

    private void disableSaveBreakTimesButtonByBlank() {
        String breakTimeOne = txtBreakTimeOne.getText();
        String breakTimeTwo = txtBreakTimeTwo.getText();
        String breakTimeThree = txtBreakTimeThree.getText();

        btnSaveBreakTimes.setDisable(breakTimeOne.isBlank() || breakTimeTwo.isBlank() || breakTimeThree.isBlank());
    }

    @FXML
    private void saveBreakTimes() {
        btnSaveBreakTimes.requestFocus();

        StorageManager.getInstance().saveBreakTimes(
                txtBreakTimeOne.getText(),
                txtBreakTimeTwo.getText(),
                txtBreakTimeThree.getText()
        );

        btnSaveBreakTimes.setDisable(true);
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                Platform.runLater(() -> btnSaveBreakTimes.setDisable(false));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    private void saveNewPassword() {
        txtNewPassword.setText("");
        txtNewPasswordRepeat.setText("");
    }

    @FXML
    private void deleteAccount() {}

    // Menübuttons zum Szenenwechsel
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
    //endregion
}
