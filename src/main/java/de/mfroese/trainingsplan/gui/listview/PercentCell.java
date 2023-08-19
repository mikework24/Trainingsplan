package de.mfroese.trainingsplan.gui.listview;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Eine Zelle für das Anzeigen von Prozentwerten in einer TableView.
 *
 * Diese Zelle wird verwendet, um Double-Werte als Prozentsätze anzuzeigen.
 * Der Wert wird mit einem Prozentzeichen am Ende formatiert.
 */
public class PercentCell<S> extends TableCell<S, Double> {

    /**
     * Gibt einen Callback zurück, der verwendet wird, um eine Instanz der PercentCell-Klasse
     * als TableCell für eine TableColumn zu erstellen.
     *
     * @return der Callback für die Erstellung der PercentCell-Instanz
     */
    public static <S> Callback<TableColumn<S, Double>, TableCell<S, Double>> forTableColumn() {
        return param -> new PercentCell<>();
    }

    @Override
    protected void updateItem(Double item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null || item == 0.0) {
            setText(null);
        } else {
            setText(String.valueOf(item) + "%");
            getStyleClass().add("percent-cell");
        }
    }
}
