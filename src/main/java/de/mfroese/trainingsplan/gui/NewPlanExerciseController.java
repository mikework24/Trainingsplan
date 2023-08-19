package de.mfroese.trainingsplan.gui;

import de.mfroese.trainingsplan.logic.*;
import de.mfroese.trainingsplan.model.*;
import de.mfroese.trainingsplan.settings.AppTexts;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementiert Steuerlogik für die Übung-Szene
 */
public class NewPlanExerciseController {

    private Plan selectedPlan;
    private Exercise selectedExercise;
    private boolean isByRepeat = true;
    private List<ComboBoxItem> trainingAreasOptions;
    private List<TrainingAreas> trainingAreaList;
    private List<ComboBoxItem> exerciseListOptions;
    private List<ExerciseList> exercisesList;
    private List<ExerciseList> filteredExercisesList;

    @FXML
    private Text txtTitle;
    @FXML
    private ComboBox<ComboBoxItem> comboTrainingAreas;
    @FXML
    private ComboBox<ComboBoxItem> comboExerciseList;
    @FXML
    private TextField txtNote;
    @FXML
    private Button btnByRepeat;
    @FXML
    private Button btnByTime;
    @FXML
    private Text repeatOrTime;
    @FXML
    private TextField txtSentence;
    @FXML
    private TextField txtWeight;
    @FXML
    private TextField txtRepeatOrTime;
    @FXML
    private Button btnDeleteExercise;
    @FXML
    private Button btnSave;

    @FXML
    public void initialize() {
        // Lade Trainingsbereiche und Übungslisten
        trainingAreaList = TrainingAreasHolder.getInstance().getTrainingAreas();
        exercisesList = ExerciseListHolder.getInstance().getExercisesList();
        filteredExercisesList = new ArrayList<>();

        // Füge Validierungs-Listener zu den Textfeldern hinzu
        addIntegerInputListener(txtSentence);
        addDoubleInputListener(txtWeight);
        addIntegerInputListener(txtRepeatOrTime);

        Platform.runLater(() -> {
            // Initialisiere Optionen für Trainingsbereichsauswahl
            trainingAreasOptions = new ArrayList<>();
            trainingAreasOptions.add(new ComboBoxItem("0", AppTexts.SELECT_TRAINING_AREAS));
            for (TrainingAreas trainingAreas : trainingAreaList) {
                trainingAreasOptions.add(new ComboBoxItem(String.valueOf(trainingAreas.getId()), trainingAreas.getName()));
            }
            comboTrainingAreas.getItems().setAll(trainingAreasOptions);
            comboTrainingAreas.setVisibleRowCount(Math.min(trainingAreasOptions.size(), 10));
            comboTrainingAreas.setValue(trainingAreasOptions.get(0));

            // Listener für Trainingsbereichsauswahl
            comboTrainingAreas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String selectedExercisesListId = Helpers.getComboBoxIdItemByName(trainingAreasOptions, newValue.getText());
                filteredExercisesList.clear();
                for (ExerciseList exerciseList : exercisesList) {
                    if (String.valueOf(exerciseList.getTrainingAreasId()).equals(selectedExercisesListId)) {
                        filteredExercisesList.add(exerciseList);
                    }
                }
                reloadExerciseListOptions();
            });

            if (selectedExercise != null) {
                // Vorhandene Übung wird geladen
                for (ExerciseList exerciseList : exercisesList) {
                    if (exerciseList.getId() == selectedExercise.getExerciseListId()) {
                        comboTrainingAreas.setValue(Helpers.getComboBoxItemByValue(trainingAreasOptions, String.valueOf(exerciseList.getTrainingAreasId())));
                    }
                }
                loadSelectedExercise();
            } else {
                setByRepeat();
            }

            reloadExerciseListOptions();
        });
    }

    protected void setSelectedPlan(Plan selectedPlan) {
        this.selectedPlan = selectedPlan;
    }

    protected void setSelectedPlanAndExercise(Plan selectedPlan, Exercise selectedExercise) {
        this.selectedPlan = selectedPlan;
        this.selectedExercise = selectedExercise;
    }

    private void reloadExerciseListOptions() {
        // Aktualisiere Optionen für Übungslistenauswahl
        exerciseListOptions = new ArrayList<>();
        exerciseListOptions.add(new ComboBoxItem("0", AppTexts.SELECT_EXERCISE_LIST));
        for (ExerciseList exerciseList : filteredExercisesList) {
            exerciseListOptions.add(new ComboBoxItem(String.valueOf(exerciseList.getId()), exerciseList.getName()));
        }
        comboExerciseList.getItems().setAll(exerciseListOptions);
        comboExerciseList.setVisibleRowCount(Math.min(exerciseListOptions.size() + 1, 10));

        if (selectedExercise != null) {
            comboExerciseList.setValue(Helpers.getComboBoxItemByValue(exerciseListOptions, String.valueOf(selectedExercise.getExerciseListId())));
        } else {
            comboExerciseList.setValue(exerciseListOptions.get(0));
        }
    }

    private void loadSelectedExercise() {
        // Übungsdetails werden geladen
        txtTitle.setText(AppTexts.EDIT_EXERCISE);

        comboExerciseList.setValue(Helpers.getComboBoxItemByValue(trainingAreasOptions, String.valueOf(selectedExercise.getExerciseListId())));

        btnDeleteExercise.setVisible(true);
        btnDeleteExercise.setDisable(false);

        txtNote.setText(selectedExercise.getName());

        if (selectedExercise.isByRepeat()) {
            setByRepeat();
        } else {
            setByTime();
        }

        txtSentence.setText(String.valueOf(selectedExercise.getSentence()));
        txtWeight.setText(String.valueOf(selectedExercise.getWeight()));
    }

    @FXML
    private void setByRepeat() {
        // Setze Übungstyp auf Wiederholungen
        isByRepeat = true;
        btnByRepeat.getStyleClass().setAll("bBlue");
        btnByTime.getStyleClass().setAll("bGray");
        repeatOrTime.setText(AppTexts.REPEATS);
        if (selectedExercise != null) txtRepeatOrTime.setText(String.valueOf(selectedExercise.getRepeat_exercise()));
    }

    @FXML
    private void setByTime() {
        // Setze Übungstyp auf Zeit
        isByRepeat = false;
        btnByRepeat.getStyleClass().setAll("bGray");
        btnByTime.getStyleClass().setAll("bBlue");
        repeatOrTime.setText(AppTexts.TIME);
        if (selectedExercise != null) txtRepeatOrTime.setText(String.valueOf(selectedExercise.getTime()));
    }

    private void addDoubleInputListener(TextField textField) {
        // Validierungs-Listener für Gleitkommazahlen
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String enteredValue = newValue.replace(",", ".");
            String filteredValue = enteredValue.replaceAll("[^0-9.]", "");
            if (filteredValue.matches("^\\d*\\.?\\d*$")) {
                textField.setText(filteredValue);
            } else {
                textField.setText(oldValue);
            }
        });
    }

    private void addIntegerInputListener(TextField textField) {
        // Validierungs-Listener für Ganzzahlen
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String filteredValue = newValue.replaceAll("[^0-9]", "");
            textField.setText(filteredValue);
        });
    }

    @FXML
    private void cancel() {
        SceneManager.getInstance().switchToTrainingPlan(selectedPlan);
    }

    @FXML
    private void saveExercise() {
        String exerciseListId = comboExerciseList.getValue().getValue();

        int repeatExercise = 0;
        int time = 0;
        if (isByRepeat) repeatExercise = Integer.parseInt(txtRepeatOrTime.getText());
        else time = Integer.parseInt(txtRepeatOrTime.getText());

        if (selectedExercise == null) {
            // Neue Übung wird erstellt
            Exercise newExercise = new Exercise(
                    Integer.parseInt(exerciseListId),
                    selectedPlan.getId(),
                    txtNote.getText(),
                    ExerciseHolder.getInstance().getMaxPosition() + 1,
                    isByRepeat,
                    Integer.parseInt(txtSentence.getText()),
                    Double.parseDouble(txtWeight.getText()),
                    repeatExercise,
                    time
            );

            ExerciseHolder.getInstance().getExercises().add(newExercise);

        } else {
            // Vorhandene Übung wird aktualisiert
            selectedExercise.setExerciseListId(Integer.parseInt(exerciseListId));
            selectedExercise.setName(txtNote.getText());
            selectedExercise.setByRepeat(isByRepeat);
            selectedExercise.setSentence(Integer.parseInt(txtSentence.getText()));
            selectedExercise.setWeight(Double.parseDouble(txtWeight.getText()));

            if (isByRepeat) selectedExercise.setRepeat_exercise(Integer.parseInt(txtRepeatOrTime.getText()));
            else selectedExercise.setTime(Integer.parseInt(txtRepeatOrTime.getText()));
        }

        cancel();
    }

    @FXML
    private void deleteExercise() {
        ExerciseHolder.getInstance().getExercises().remove(selectedExercise);

        cancel();
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
}