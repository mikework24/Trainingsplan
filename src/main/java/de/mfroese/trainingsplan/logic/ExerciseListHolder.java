package de.mfroese.trainingsplan.logic;

import de.mfroese.trainingsplan.logic.database.DbManager;
import de.mfroese.trainingsplan.model.ExerciseList;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Singleton, das eine ObservableList mit Übungslisten bereitstellt und Methoden zum Sortieren der Liste implementiert.
 */
public class ExerciseListHolder {
    //region Attribute
    private static ExerciseListHolder instance;
    private ObservableList<ExerciseList> exercisesList;
    private final ListChangeListener<ExerciseList> listener;
    //endregion

    //region Konstruktor
    private ExerciseListHolder() {

        exercisesList = FXCollections.observableArrayList(exerciseListToObserve ->
                new Observable[] { exerciseListToObserve.trainingAreasIdProperty(), exerciseListToObserve.nameProperty(),
                        exerciseListToObserve.imgProperty(), exerciseListToObserve.byRepeatProperty() });

        exercisesList.addAll(DbManager.getInstance().readAllExercisesList());

        listener = change -> {
            while (change.next()) {
                if (change.wasReplaced()) {
                    System.out.println("Listenelement ersetzt");
                } else if (change.wasUpdated()) {
                    ExerciseList exerciseListToUpdate = change.getList().get(change.getFrom());
                    DbManager.getInstance().update(exerciseListToUpdate);
                } else if (change.wasRemoved()) {
                    ExerciseList exerciseListToDelete = change.getRemoved().get(0);
                    DbManager.getInstance().delete(exerciseListToDelete);
                } else if (change.wasAdded()) {
                    ExerciseList exerciseListToInsert = change.getAddedSubList().get(0);
                    DbManager.getInstance().insert(exerciseListToInsert);
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
     * Gibt die Singleton-Instanz des ExerciseListHolders zurück.
     *
     * @return ExerciseListHolder-Instanz
     */
    public static synchronized ExerciseListHolder getInstance() {
        if (instance == null) {
            instance = new ExerciseListHolder();
        }
        return instance;
    }

    /**
     * Gibt die ObservableList mit den Übungslisten zurück.
     *
     * @return ObservableList von ExerciseList-Objekten
     */
    public ObservableList<ExerciseList> getExercisesList() {
        return exercisesList;
    }

    /**
     * Aktualisiert eine Übungsliste in der Liste und in der Datenbank.
     *
     * @param exerciseList die zu aktualisierende Übungsliste
     */
    public void update(ExerciseList exerciseList) {
        int index = exercisesList.indexOf(exerciseList);
        if (index != -1) {
            exercisesList.set(index, exerciseList);
            DbManager.getInstance().update(exerciseList);
        }
    }

    private void activateListener() {
        exercisesList.addListener(listener);
    }

    private void deactivateListener() {
        exercisesList.removeListener(listener);
    }
    //endregion


}
