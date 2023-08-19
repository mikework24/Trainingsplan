package de.mfroese.trainingsplan.gui.listview;

import de.mfroese.trainingsplan.model.Plan;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Eine Fabrikklasse, die Zellen für die ListView generiert.
 */
public class PlanCellFactory implements Callback<ListView<Plan>, ListCell<Plan>> {

    /**
     * Generiert eine Zelle für die ListView.
     *
     * @param planListView Die ListView, für die die Zelle generiert wird.
     * @return Eine Zelle für die ListView.
     */
    @Override
    public ListCell<Plan> call(ListView<Plan> planListView) {
        return new PlanCell();
    }
}
