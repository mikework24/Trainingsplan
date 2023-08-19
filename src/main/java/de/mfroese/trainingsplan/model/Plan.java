package de.mfroese.trainingsplan.model;

import javafx.beans.property.*;

/**
 * Modellklasse für Trainingspläne aus der realen Welt.
 * Normale Attribute wurden durch Properties ersetzt, sodass
 * die Werte der Planeigenschaften überwachbar sind.
 * Somit können Listener registriert werden, die bei Änderungen
 * der Werte benachrichtigt werden.
 */
public class Plan {

    //region Konstanten
    public static final String DEFAULT_STRING_VALUE = "noValueYet";
    public static final int DEFAULT_INT_VALUE = -1;
    public static final boolean DEFAULT_BOOLEAN_VALUE = false;
    //endregion

    //region Attribute
    private int id;
    private IntegerProperty userId;
    private StringProperty name;
    private IntegerProperty position;
    private BooleanProperty active;
    //endregion

    //region Konstruktoren
    public Plan() {
        id       = DEFAULT_INT_VALUE;
        userId   = new SimpleIntegerProperty(DEFAULT_INT_VALUE);
        name     =  new SimpleStringProperty(DEFAULT_STRING_VALUE);
        position = new SimpleIntegerProperty(DEFAULT_INT_VALUE);
        active   = new SimpleBooleanProperty(DEFAULT_BOOLEAN_VALUE);
    }

    public Plan(int userId, String name, int position, boolean active) {
        this();
        this.userId   = new SimpleIntegerProperty(userId);
        this.name     = new SimpleStringProperty(name);
        this.position = new SimpleIntegerProperty(position);
        this.active   = new SimpleBooleanProperty(active);
    }

    public Plan(int id, int userId, String name, int position, boolean active) {
        this.id       = id;
        this.userId   = new SimpleIntegerProperty(userId);
        this.name     = new SimpleStringProperty(name);
        this.position = new SimpleIntegerProperty(position);
        this.active   = new SimpleBooleanProperty(active);
    }

    //endregion

    //region Methoden
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId.get();
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getPosition() {
        return position.get();
    }

    public IntegerProperty positionProperty() {
        return position;
    }

    public void setPosition(int position) {
        this.position.set(position);
    }

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", userId=" + userId.get() +
                ", name=" + name.get() +
                ", position=" + position.get() +
                ", active=" + active.get() +
                '}';
    }
    //endregion
}
