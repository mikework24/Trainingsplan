package de.mfroese.trainingsplan.logic;

import de.mfroese.trainingsplan.logic.database.DbManager;
import de.mfroese.trainingsplan.model.Exercise;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Singleton, das eine ObservableList mit Übungen bereitstellt und Methoden zum Sortieren der Liste implementiert.
 */
public class ExerciseHolder {
    //region Attribute
    private static ExerciseHolder instance;
    private ObservableList<Exercise> exercises;
    private final ListChangeListener<Exercise> listener;
    //endregion

    //region Konstruktor
    private ExerciseHolder() {
        exercises = FXCollections.observableArrayList(exerciseToObserve ->
                new Observable[] { exerciseToObserve.exerciseListIdProperty(), exerciseToObserve.planIdProperty(),
                        exerciseToObserve.nameProperty(), exerciseToObserve.positionProperty(),
                        exerciseToObserve.byRepeatProperty(), exerciseToObserve.sentenceProperty(),
                        exerciseToObserve.weightProperty(), exerciseToObserve.repeat_exerciseProperty(),
                        exerciseToObserve.timeProperty() });

        exercises.addAll(DbManager.getInstance().readAllExercises());

        sortByPosition();

        listener = change -> {
            while (change.next()) {
                if (change.wasReplaced()) {
                    System.out.println("Listenelement ersetzt");
                } else if (change.wasUpdated()) {
                    Exercise exerciseToUpdate = change.getList().get(change.getFrom());
                    DbManager.getInstance().update(exerciseToUpdate);
                } else if (change.wasRemoved()) {
                    Exercise exerciseToDelete = change.getRemoved().get(0);
                    DbManager.getInstance().delete(exerciseToDelete);
                } else if (change.wasAdded()) {
                    Exercise exerciseToInsert = change.getAddedSubList().get(0);
                    DbManager.getInstance().insert(exerciseToInsert);
                } else if (change.wasPermutated()) {
                    System.out.println("Reihenfolge der Liste geändert");
                }
            }
        };

        activateListener();
    }
    //endregion

    //region Methoden
    /**
     * Gibt die Singleton-Instanz des ExerciseHolders zurück.
     *
     * @return ExerciseHolder-Instanz
     */
    public static synchronized ExerciseHolder getInstance() {
        if (instance == null) {
            instance = new ExerciseHolder();
        }
        return instance;
    }

    /**
     * Gibt die ObservableList mit den Übungen zurück.
     *
     * @return ObservableList von Exercise-Objekten
     */
    public ObservableList<Exercise> getExercises() {
        return exercises;
    }

    /**
     * Aktualisiert ein Exercise-Objekt in der Liste und in der Datenbank.
     *
     * @param exercise das zu aktualisierende Exercise-Objekt
     */
    public void update(Exercise exercise) {
        int index = exercises.indexOf(exercise);
        if (index != -1) {
            exercises.set(index, exercise);
            DbManager.getInstance().update(exercise);
        }
    }

    /**
     * Lädt die Übungen neu aus der Datenbank und aktualisiert die Liste.
     *
     * @return ObservableList von Exercise-Objekten
     */
    public ObservableList<Exercise> reload() {
        deactivateListener();
        exercises.clear();
        exercises.addAll(DbManager.getInstance().readAllExercises());
        activateListener();
        return exercises;
    }


    /**
     * Gibt die größte Position in der Liste der Übungen zurück.
     *
     * @return die größte Position als int-Wert
     */
    public int getMaxPosition() {
        int maxPosition = 0;
        for (Exercise exercise : exercises) {
            int position = exercise.getPosition();
            if (position > maxPosition) {
                maxPosition = position;
            }
        }
        return maxPosition;
    }

    /**
     * Gibt den Übungen anhand der Position zurück.
     *
     * @param position die Position der Übungen
     * @return der Übung
     */
    public Exercise getExerciseByPosition(int position) {
        for (Exercise exercise : exercises) {
            if (exercise.getPosition() == position) {
                return exercise;
            }
        }
        return null;
    }

    /**
     * Sortiert die Übungen nach ihrer Position.
     */
    public void sortByPosition() {
        exercises.sort((firstPlan, secondPlan) ->
                firstPlan.getPosition() - secondPlan.getPosition());
    }

    private void activateListener() {
        exercises.addListener(listener);
    }

    private void deactivateListener() {
        exercises.removeListener(listener);
    }

    //endregion
}
