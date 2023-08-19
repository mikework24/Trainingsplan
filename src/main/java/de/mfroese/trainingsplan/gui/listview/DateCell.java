package de.mfroese.trainingsplan.gui.listview;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Eine Zelle für das Anzeigen von Datumswerten in einer TableView.
 *
 * Diese Zelle wird verwendet, um Datumsdaten in einem bestimmten Format anzuzeigen.
 * Das Format wird durch den DateTimeFormatter "dd.MM.yyyy" definiert.
 */
public class DateCell<S, T> extends TableCell<S, T> {

    /**
     * Gibt einen Callback zurück, der verwendet wird, um eine Instanz der DateCell-Klasse
     * als TableCell für eine TableColumn zu erstellen.
     *
     * @return der Callback für die Erstellung der DateCell-Instanz
     */
    public static <S, T> Callback<TableColumn<S, T>, TableCell<S, T>> forTableColumn() {
        return param -> new DateCell<>();
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
        } else {
            try {
                LocalDate date = LocalDate.parse(String.valueOf(item));
                DateTimeFormatter germanFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                String germanDate = date.format(germanFormatter);
                setText(germanDate);
            } catch (Exception e) {
                setText(String.valueOf(item));
            }
        }
    }
}