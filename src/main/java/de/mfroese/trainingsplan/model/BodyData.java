package de.mfroese.trainingsplan.model;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Modellklasse für Körperdaten aus der realen Welt.
 * Normale Attribute wurden durch Properties ersetzt, sodass
 * die Werte der Planeigenschaften überwachbar sind.
 * Somit können Listener registriert werden, die bei Änderungen
 * der Werte benachrichtigt werden.
 */
public class BodyData {

    //region Konstanten
    public static final String DEFAULT_STRING_VALUE = "noValueYet";
    public static final int DEFAULT_INT_VALUE = 0;
    public static final Double DEFAULT_DOUBLE_VALUE = 0.0;
    //endregion

    //region Attribute
    private int id;
    private IntegerProperty userId;
    private StringProperty date;
    private DoubleProperty bodyWeight;
    private DoubleProperty bodyFat;
    private DoubleProperty muscleMass;
    //endregion

    //region Konstruktoren
    public BodyData() {
        id         = DEFAULT_INT_VALUE;
        userId     = new SimpleIntegerProperty(DEFAULT_INT_VALUE);
        date       = new SimpleStringProperty(DEFAULT_STRING_VALUE);
        bodyWeight = new SimpleDoubleProperty(DEFAULT_DOUBLE_VALUE);
        bodyFat    = new SimpleDoubleProperty(DEFAULT_DOUBLE_VALUE);
        muscleMass = new SimpleDoubleProperty(DEFAULT_DOUBLE_VALUE);
    }

    public BodyData(int userId, String date, Double bodyWeight, Double bodyFat, Double muscleMass) {
        this();
        this.userId     = new SimpleIntegerProperty(userId);
        this.date       = new SimpleStringProperty(date);
        this.bodyWeight = new SimpleDoubleProperty(bodyWeight);
        this.bodyFat    = new SimpleDoubleProperty(bodyFat);
        this.muscleMass = new SimpleDoubleProperty(muscleMass);
    }

    public BodyData(int id, int userId, String date, Double bodyWeight, Double bodyFat, Double muscleMass) {
        this.id              = id;
        this.userId     = new SimpleIntegerProperty(userId);
        this.date       = new SimpleStringProperty(date);
        this.bodyWeight = new SimpleDoubleProperty(bodyWeight);
        this.bodyFat    = new SimpleDoubleProperty(bodyFat);
        this.muscleMass = new SimpleDoubleProperty(muscleMass);
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

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public Double getBodyWeight() {
        return bodyWeight.get();
    }

    public DoubleProperty bodyWeightProperty() {
        return bodyWeight;
    }

    public void setBodyWeight(double bodyWeight) {
        this.bodyWeight.set(bodyWeight);
    }

    public Double getBodyFat() {
        return bodyFat.get();
    }

    public DoubleProperty bodyFatProperty() {
        return bodyFat;
    }

    public void setBodyFat(double bodyFat) {
        this.bodyFat.set(bodyFat);
    }

    public Double getMuscleMass() {
        return muscleMass.get();
    }

    public DoubleProperty muscleMassProperty() {
        return muscleMass;
    }

    public void setMuscleMass(double muscleMass) {
        this.muscleMass.set(muscleMass);
    }

    @Override
    public String toString() {
        return "BodyData{" +
                "id=" + id +
                ", userId=" + userId.get() +
                ", date=" + date.get() +
                ", bodyWeight=" + bodyWeight.get() +
                ", bodyFat=" + bodyFat.get() +
                ", muscleMass=" + muscleMass.get() +
                '}';
    }
    //endregion
}
