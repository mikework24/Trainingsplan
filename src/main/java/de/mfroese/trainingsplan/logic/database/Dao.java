package de.mfroese.trainingsplan.logic.database;

import java.sql.Connection;
import java.util.List;

/**
 * DAO - Datenzugriffsobjekt, das Zugriff auf bestimmte Daten bereitstellt.
 * Stellt CRUD-Operationen zur Verfügung.
 *
 * @param <T> Der Typ des Interfaces / der Modellklasse, für die das Interface implementiert wird.
 */
public interface Dao<T> {

    /**
     * Fügt ein Objekt in die Datenbank ein.
     *
     * @param connection Die Verbindung zur Datenbank.
     * @param object     Das Objekt einer bestimmten Klasse.
     */
    void insert(Connection connection, T object);

    /**
     * Liest alle Objekte aus der Datenbank aus.
     *
     * @param connection Die Verbindung zur Datenbank.
     * @return Eine Liste von Objekten.
     */
    List<T> readAll(Connection connection);

    /**
     * Aktualisiert ein Objekt in der Datenbank.
     *
     * @param connection Die Verbindung zur Datenbank.
     * @param object     Das Objekt einer bestimmten Klasse.
     */
    void update(Connection connection, T object);

    /**
     * Löscht ein Objekt aus der Datenbank.
     *
     * @param connection Die Verbindung zur Datenbank.
     * @param object     Das Objekt einer bestimmten Klasse.
     */
    void delete(Connection connection, T object);
}
