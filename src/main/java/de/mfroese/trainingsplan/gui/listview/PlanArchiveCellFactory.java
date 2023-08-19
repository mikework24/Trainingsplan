package de.mfroese.trainingsplan.gui.listview;

import de.mfroese.trainingsplan.model.Plan;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Eine Fabrikklasse, die Zellen für die ListView generiert, um Trainingspläne im Archiv anzuzeigen.
 */
public class PlanArchiveCellFactory implements Callback<ListView<Plan>, ListCell<Plan>> {

    /**
     * Erzeugt eine neue Zelle für die ListView.
     *
     * @param planListView : {@link ListView<Plan>} : Die ListView, für die die Zelle erzeugt wird
     * @return : {@link ListCell<Plan>} : Die erzeugte Zelle
     */
    @Override
    public ListCell<Plan> call(ListView<Plan> planListView) {
        return new PlanArchiveCell();
    }
}
