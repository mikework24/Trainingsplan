package de.mfroese.trainingsplan.model;

import javafx.beans.property.*;

/**
 * Modellklasse für Trainingsübung aus der realen Welt.
 * Normale Attribute wurden durch Properties ersetzt, sodass
 * die Werte der Übungseigenschaft überwachbar sind.
 * Somit können Listener registriert werden, die bei Änderungen
 * der Werte benachrichtigt werden.
 */
public class Exercise {

    //region Konstanten
    public static final String DEFAULT_STRING_VALUE = "noValueYet";
    public static final int DEFAULT_INT_VALUE = 0;
    public static final Double DEFAULT_DOUBLE_VALUE = 0.0;
    public static final boolean DEFAULT_BOOLEAN_VALUE = false;

    //endregion

    //region Attribute
    private int id;
    private IntegerProperty exerciseListId;
    private IntegerProperty planId;
    private StringProperty name;
    private IntegerProperty position;
    private BooleanProperty byRepeat;
    private IntegerProperty sentence;
    private DoubleProperty weight;
    private IntegerProperty repeat_exercise;
    private IntegerProperty time;

    //endregion

    //region Konstruktoren
    public Exercise() {
        id              = DEFAULT_INT_VALUE;
        exerciseListId  = new SimpleIntegerProperty(DEFAULT_INT_VALUE);
        planId          = new SimpleIntegerProperty(DEFAULT_INT_VALUE);
        name            = new SimpleStringProperty(DEFAULT_STRING_VALUE);
        position        = new SimpleIntegerProperty(DEFAULT_INT_VALUE);
        byRepeat        = new SimpleBooleanProperty(DEFAULT_BOOLEAN_VALUE);
        sentence        = new SimpleIntegerProperty(DEFAULT_INT_VALUE);
        weight          = new SimpleDoubleProperty(DEFAULT_DOUBLE_VALUE);
        repeat_exercise = new SimpleIntegerProperty(DEFAULT_INT_VALUE);
        time            = new SimpleIntegerProperty(DEFAULT_INT_VALUE);
    }

    public Exercise(int exerciseListId, int planId, String name, int position, boolean byRepeat,
                    int sentence, Double weight, int repeat_exercise, int time) {
        this();
        this.exerciseListId  = new SimpleIntegerProperty(exerciseListId);
        this.planId          = new SimpleIntegerProperty(planId);
        this.name            = new SimpleStringProperty(name);
        this.position        = new SimpleIntegerProperty(position);
        this.byRepeat        = new SimpleBooleanProperty(byRepeat);
        this.sentence        = new SimpleIntegerProperty(sentence);
        this.weight          = new SimpleDoubleProperty(weight);
        this.repeat_exercise = new SimpleIntegerProperty(repeat_exercise);
        this.time            = new SimpleIntegerProperty(time);
    }

    public Exercise(int id, int exerciseListId, int planId, String name, int position, boolean byRepeat,
                    int sentence, Double weight, int repeat_exercise, int time) {
        this.id              = id;
        this.exerciseListId  = new SimpleIntegerProperty(exerciseListId);
        this.planId          = new SimpleIntegerProperty(planId);
        this.name            = new SimpleStringProperty(name);
        this.position        = new SimpleIntegerProperty(position);
        this.byRepeat        = new SimpleBooleanProperty(byRepeat);
        this.sentence        = new SimpleIntegerProperty(sentence);
        this.weight          = new SimpleDoubleProperty(weight);
        this.repeat_exercise = new SimpleIntegerProperty(repeat_exercise);
        this.time            = new SimpleIntegerProperty(time);
    }

    //endregion

    //region Methoden

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExerciseListId() {
        return exerciseListId.get();
    }

    public IntegerProperty exerciseListIdProperty() {
        return exerciseListId;
    }

    public void setExerciseListId(int exerciseListId) {
        this.exerciseListId.set(exerciseListId);
    }

    public int getPlanId() {
        return planId.get();
    }

    public IntegerProperty planIdProperty() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId.set(planId);
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

    public boolean isByRepeat() {
        return byRepeat.get();
    }

    public BooleanProperty byRepeatProperty() {
        return byRepeat;
    }

    public void setByRepeat(boolean byRepeat) {
        this.byRepeat.set(byRepeat);
    }

    public int getSentence() {
        return sentence.get();
    }

    public IntegerProperty sentenceProperty() {
        return sentence;
    }

    public void setSentence(int sentence) {
        this.sentence.set(sentence);
    }

    public double getWeight() {
        return weight.get();
    }

    public DoubleProperty weightProperty() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight.set(weight);
    }

    public int getRepeat_exercise() {
        return repeat_exercise.get();
    }

    public IntegerProperty repeat_exerciseProperty() {
        return repeat_exercise;
    }

    public void setRepeat_exercise(int repeat_exercise) {
        this.repeat_exercise.set(repeat_exercise);
    }

    public int getTime() {
        return time.get();
    }

    public IntegerProperty timeProperty() {
        return time;
    }

    public void setTime(int time) {
        this.time.set(time);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", exerciseListId=" + exerciseListId.get() +
                ", planId=" + planId.get() +
                ", name=" + name.get() +
                ", position=" + position.get() +
                ", byRepeat=" + byRepeat.get() +
                ", sentence=" + sentence.get() +
                ", weight=" + weight.get() +
                ", repeat_exercise=" + repeat_exercise.get() +
                ", time=" + time.get() +
                '}';
    }

    //endregion
}
