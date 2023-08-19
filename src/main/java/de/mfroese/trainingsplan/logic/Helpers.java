package de.mfroese.trainingsplan.logic;

import de.mfroese.trainingsplan.model.ComboBoxItem;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Helpers {
    //region Konstanten
    //endregion

    //region Attribute
    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden
    public static String getGermanDateFormat(String dateData) {
        try {
            LocalDate date = LocalDate.parse(String.valueOf(dateData));
            DateTimeFormatter germanFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return date.format(germanFormatter);
        } catch (Exception e) {
            return dateData;
        }
    }

    public static String getGermanDateFormat(LocalDate dateData) {
        DateTimeFormatter germanFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return dateData.format(germanFormatter);
    }

    public static String getComboBoxIdItemByName(List<ComboBoxItem> items, String name) {
        if(items != null){
            for (ComboBoxItem item : items) {
                if (item.getText().equals(name)) {
                    return item.getValue();
                }
            }
        }
        return "0";
    }

    public static ComboBoxItem getComboBoxItemByValue(List<ComboBoxItem> items, String value) {
        if(items != null){
            for (ComboBoxItem item : items) {
                if (item.getValue().equals(value)) {
                    return item;
                }
            }
            return items.get(0);
        }
        return null;
    }







    //endregion
}
