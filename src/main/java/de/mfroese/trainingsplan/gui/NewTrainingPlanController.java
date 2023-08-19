package de.mfroese.trainingsplan.gui;

import de.mfroese.trainingsplan.logic.PlanHolder;
import de.mfroese.trainingsplan.logic.StorageManager;
import de.mfroese.trainingsplan.model.Plan;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import de.mfroese.trainingsplan.settings.AppTexts;

/**
 * Implementiert Steuerlogik für die Traeningsplan hinzufuegen-Szene
 */
public class NewTrainingPlanController {
    //region Konstanten
    final static int MIN_INPUTED_LENGHT = 3;
    //endregion

    //region Attribute
    private Plan selectedPlan;

    @FXML
    Button btnSave;
    @FXML
    TextField txtPlan;
    @FXML
    Text txtTitle;
    @FXML
    Text txtSubTitle;
    //endregion

    //region Konstruktoren
    @FXML
    public void initialize() {
        txtPlan.textProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setDisable(newValue.length() < MIN_INPUTED_LENGHT);
        });
    }
    //endregion

    //region Methoden
    protected void setSelectedPlan(Plan selectedPlan) {
        this.selectedPlan = selectedPlan;

        if (selectedPlan != null) {
            txtPlan.setText(selectedPlan.getName());
            txtTitle.setText("");
            txtSubTitle.setText(AppTexts.NEW_PLAN_NAME);
        }
    }


    @FXML
    private void cancel() {
        if (selectedPlan != null) {
            SceneManager.getInstance().switchToTrainingPlan(selectedPlan);
        }else{
            SceneManager.getInstance().switchToTrainingPlans();
        }
    }

    @FXML
    private void savePlan(){

        if (selectedPlan != null) {
            changeNameOfPlan();
        }else{
            addNewPlan();
        }
    }

    private void changeNameOfPlan(){

        //ändern des Plannamens
        selectedPlan.setName(txtPlan.getText());

        //zurück zum Plan
        SceneManager.getInstance().switchToTrainingPlan(selectedPlan);
    }

    private void addNewPlan(){

        int planSize = PlanHolder.getInstance().getPlans().size();

        //Anlegen eines neuen Planes
        Plan newPlan = new Plan(
                Integer.parseInt(StorageManager.getInstance().getUserId()),
                txtPlan.getText(),
                (planSize + 1),
                true
        );

        //Speichern des neuen Planes
        PlanHolder.getInstance().getPlans().add(newPlan);

        //Öffne die Trainingspläne
        openTrainingPlans();
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
