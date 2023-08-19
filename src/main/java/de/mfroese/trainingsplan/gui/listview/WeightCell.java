package de.mfroese.trainingsplan.gui.listview;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Eine Zelle, die einen Gewichtswert in einer Tabelle anzeigt.
 */
public class WeightCell<S> extends TableCell<S, Double> {

    /**
     * Gibt eine Callback-Funktion zurück, die eine WeightCell für eine TableColumn erzeugt.
     *
     * @param <S> Der Typ der TableView-Zeile.
     * @return Eine Callback-Funktion zur Erzeugung einer WeightCell.
     */
    public static <S> Callback<TableColumn<S, Double>, TableCell<S, Double>> forTableColumn() {
        return param -> new WeightCell<>();
    }

    /**
     * Aktualisiert den Inhalt der Zelle.
     *
     * @param item  Der Gewichtswert der Zelle.
     * @param empty Gibt an, ob die Zelle leer ist.
     */
    @Override
    protected void updateItem(Double item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null || item == 0.0) {
            setText(null);
        } else {
            setText(String.valueOf(item) + " kg");
        }
    }
}
