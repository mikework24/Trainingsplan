package de.mfroese.trainingsplan.model;

import javafx.beans.property.*;

/**
 * Modellklasse für Trainingsübungen aus der realen Welt.
 * Normale Attribute wurden durch Properties ersetzt, sodass
 * die Werte der Übungseigenschaft überwachbar sind.
 * Somit können Listener registriert werden, die bei Änderungen
 * der Werte benachrichtigt werden.
 */
public class ExerciseList {

    //region Konstanten
    public static final String DEFAULT_STRING_VALUE = "noValueYet";
    public static final int DEFAULT_INT_VALUE = -1;
    public static final boolean DEFAULT_BOOLEAN_VALUE = false;

    //endregion

    //region Attribute
    private int id;
    private IntegerProperty trainingAreasId ;
    private StringProperty name;
    private StringProperty img;
    private BooleanProperty byRepeat;
    //endregion

    //region Konstruktoren
    public ExerciseList() {
        id              = DEFAULT_INT_VALUE;
        trainingAreasId = new SimpleIntegerProperty(DEFAULT_INT_VALUE);
        name            = new SimpleStringProperty(DEFAULT_STRING_VALUE);
        img             = new SimpleStringProperty(DEFAULT_STRING_VALUE);
        byRepeat        = new SimpleBooleanProperty(DEFAULT_BOOLEAN_VALUE);
    }

    public ExerciseList(int trainingAreasId, String name, String img, boolean byRepeat) {
        this();
        this.trainingAreasId = new SimpleIntegerProperty(trainingAreasId);
        this.name            = new SimpleStringProperty(name);
        this.img             = new SimpleStringProperty(img);
        this.byRepeat        = new SimpleBooleanProperty(byRepeat);
    }

    public ExerciseList(int id, int trainingAreasId, String name, String img, boolean byRepeat) {
        this.id              = id;
        this.trainingAreasId = new SimpleIntegerProperty(trainingAreasId);
        this.name            = new SimpleStringProperty(name);
        this.img             = new SimpleStringProperty(img);
        this.byRepeat        = new SimpleBooleanProperty(byRepeat);
    }
    //endregion

    //region Methoden

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainingAreasId() {
        return trainingAreasId.get();
    }

    public IntegerProperty trainingAreasIdProperty() {
        return trainingAreasId;
    }

    public void setTrainingAreasId(int trainingAreasId) {
        this.trainingAreasId.set(trainingAreasId);
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

    public String getImg() {
        return img.get();
    }

    public StringProperty imgProperty() {
        return img;
    }

    public void setImg(String img) {
        this.img.set(img);
    }

    public boolean isByRepeat() {
        return byRepeat.get();
    }

    public BooleanProperty byRepeatProperty() {
        return byRepeat;
    }

    public void setByRepeat(boolean byRepeat) {
        this.byRepeat.set(byRepeat);
    }


    @Override
    public String toString() {
        return "ExerciseList{" +
                "id=" + id +
                ", trainingAreasId=" + trainingAreasId.get() +
                ", name=" + name.get() +
                ", img=" + img.get() +
                ", byRepeat=" + byRepeat.get() +
                '}';
    }
//endregion
}
