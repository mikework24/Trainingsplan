package de.mfroese.trainingsplan.gui.listview;

import de.mfroese.trainingsplan.model.Exercise;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Eine Fabrikklasse, die Zellen für die ListView generiert.
 */
public class ExerciseCellFactory implements Callback<ListView<Exercise>, ListCell<Exercise>> {

    /**
     * Generiert eine Zelle für die ListView.
     *
     * @param exerciseListView Die ListView, für die die Zelle generiert wird.
     * @return Eine Zelle für die ListView.
     */
    @Override
    public ListCell<Exercise> call(ListView<Exercise> exerciseListView) {
        return new ExerciseCell();
    }
}
