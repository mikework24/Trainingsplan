package de.mfroese.trainingsplan.logic;

import de.mfroese.trainingsplan.logic.database.DbManager;
import de.mfroese.trainingsplan.model.BodyData;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Singleton, das eine ObservableList mit Körperdaten bereitstellt und Methoden zum Sortieren der Liste implementiert.
 */
public class BodyDataHolder {
    //region Attribute
    private static BodyDataHolder instance;
    private ObservableList<BodyData> bodyDatas;
    //endregion

    //region Konstruktor
    private BodyDataHolder() {
        bodyDatas = FXCollections.observableArrayList(bodyDataToObserve ->
                new Observable[] { bodyDataToObserve.userIdProperty(), bodyDataToObserve.dateProperty(),
                        bodyDataToObserve.bodyWeightProperty(), bodyDataToObserve.bodyFatProperty(),
                        bodyDataToObserve.muscleMassProperty() });

        bodyDatas.addAll(DbManager.getInstance().readAllBodyDatas());

        bodyDatas.addListener((ListChangeListener<BodyData>) change -> {
            while (change.next()) {
                if (change.wasReplaced()) {
                    System.out.println("Listenelement ersetzt");
                } else if (change.wasUpdated()) {
                    BodyData bodyDataToUpdate = change.getList().get(change.getFrom());
                    DbManager.getInstance().update(bodyDataToUpdate);
                } else if (change.wasRemoved()) {
                    BodyData bodyDataToDelete = change.getRemoved().get(0);
                    DbManager.getInstance().delete(bodyDataToDelete);
                } else if (change.wasAdded()) {
                    BodyData bodyDataToInsert = change.getAddedSubList().get(0);
                    DbManager.getInstance().insert(bodyDataToInsert);
                } else if (change.wasPermutated()) {
                    System.out.println("Reihenfolge der Liste geändert");
                }
            }
        });
    }
    //endregion

    //region Methoden
    /**
     * Gibt die Singleton-Instanz des BodyDataHolders zurück.
     *
     * @return BodyDataHolder-Instanz
     */
    public static synchronized BodyDataHolder getInstance() {
        if (instance == null) {
            instance = new BodyDataHolder();
        }
        return instance;
    }

    /**
     * Gibt die ObservableList mit den Körperdaten zurück.
     *
     * @return ObservableList von BodyData-Objekten
     */
    public ObservableList<BodyData> getBodyDatas() {
        return bodyDatas;
    }

    /**
     * Aktualisiert ein BodyData-Objekt in der Liste und in der Datenbank.
     *
     * @param bodyData das zu aktualisierende BodyData-Objekt
     */
    public void update(BodyData bodyData) {
        int index = bodyDatas.indexOf(bodyData);
        if (index != -1) {
            bodyDatas.set(index, bodyData);
            DbManager.getInstance().update(bodyData);
        }
    }
    //endregion
}
