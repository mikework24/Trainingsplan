package de.mfroese.trainingsplan.gui;

import de.mfroese.trainingsplan.Main;
import de.mfroese.trainingsplan.model.Exercise;
import de.mfroese.trainingsplan.model.Plan;
import de.mfroese.trainingsplan.settings.AppTexts;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Singleton, das für das Wechseln von Szenen zuständig ist.
 * Es enthält eine Referenz auf das Hauptfenster der Applikation,
 * über das die Szenen ausgetauscht werden können.
 */
public class SceneManager {
    //region Attribute
    private static SceneManager instance;
    private Stage mainStage;
    //endregion

    //region Konstruktor
    private SceneManager() {}
    //endregion

    //region Methoden
    public static synchronized SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void setAndConfigureMainStage(Stage stage) {
        mainStage = stage;
        mainStage.setTitle(AppTexts.TRAININGSPLAN);
        switchToTrainingPlans();
    }

    /**
     * Lädt die MasterSzene und wechselt zu ihr.
     */
    public void switchToTrainingPlans() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("training-plans-view.fxml"));
            Scene trainingPlansScene = new Scene(fxmlLoader.load());
            switchScene(trainingPlansScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToTrainingPlan(Plan selectedPlan) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("training-plan-view.fxml"));
            Scene trainingPlanScene = new Scene(fxmlLoader.load());

            TrainingPlanController trainingPlanController = fxmlLoader.getController();
            trainingPlanController.setSelectedPlan(selectedPlan);

            switchScene(trainingPlanScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToTrainingPlan() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("training-plan-view.fxml"));
            Scene trainingPlanScene = new Scene(fxmlLoader.load());

            switchScene(trainingPlanScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToNewTrainingPlan() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("new-training-plan-view.fxml"));
            Scene newTrainingPlanScene = new Scene(fxmlLoader.load());
            switchScene(newTrainingPlanScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToNewTrainingPlan(Plan selectedPlan) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("new-training-plan-view.fxml"));
            Scene newTrainingPlanScene = new Scene(fxmlLoader.load());

            NewTrainingPlanController newTrainingPlanController = fxmlLoader.getController();
            newTrainingPlanController.setSelectedPlan(selectedPlan);

            switchScene(newTrainingPlanScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToNewPlanExercise(Plan selectedPlan) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("new-plan-exercise-view.fxml"));
            Scene newPlanExerciseScene = new Scene(fxmlLoader.load());

            NewPlanExerciseController newPlanExerciseController = fxmlLoader.getController();
            newPlanExerciseController.setSelectedPlan(selectedPlan);

            switchScene(newPlanExerciseScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToNewPlanExercise(Plan selectedPlan, Exercise selectedExercise) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("new-plan-exercise-view.fxml"));
            Scene newPlanExerciseScene = new Scene(fxmlLoader.load());

            NewPlanExerciseController newPlanExerciseController = fxmlLoader.getController();
            newPlanExerciseController.setSelectedPlanAndExercise(selectedPlan, selectedExercise);

            switchScene(newPlanExerciseScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToTrainingArchive() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("training-archive.fxml"));
            Scene trainingArchiveScene = new Scene(fxmlLoader.load());
            switchScene(trainingArchiveScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToStatisticsExercise() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("statistics-exercise-view.fxml"));
            Scene statisticsExerciseScene = new Scene(fxmlLoader.load());
            switchScene(statisticsExerciseScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToStatisticsWorkload() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("statistics-workload-view.fxml"));
            Scene statisticsWorkloadScene = new Scene(fxmlLoader.load());
            switchScene(statisticsWorkloadScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToFinishedWorkout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("finished-workout-view.fxml"));
            Scene finishedWorkoutScene = new Scene(fxmlLoader.load());
            switchScene(finishedWorkoutScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToBodyData() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("body-data-view.fxml"));
            Scene bodyDataScene = new Scene(fxmlLoader.load());
            switchScene(bodyDataScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToSettings() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("settings-view.fxml"));
            Scene settingsScene = new Scene(fxmlLoader.load());
            switchScene(settingsScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchScene(Scene scene) {
        mainStage.setScene(scene);
        mainStage.show();
    }
    //endregion
}
