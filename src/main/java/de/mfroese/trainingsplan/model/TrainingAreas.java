package de.mfroese.trainingsplan.model;

/**
 * Modellklasse für Trainingsbereiche aus der realen Welt.
 */
public class TrainingAreas {

    //region Konstanten
    public static final String DEFAULT_STRING_VALUE = "noValueYet";
    public static final int DEFAULT_INT_VALUE = -1;

    //endregion

    //region Attribute
    private int id;
    private String name;
    private String img;

    //endregion

    //region Konstruktoren
    public TrainingAreas() {
        id   = DEFAULT_INT_VALUE;
        name = DEFAULT_STRING_VALUE;
        img  = DEFAULT_STRING_VALUE;
    }

    public TrainingAreas(int id, String name, String img) {
        this.id   = id;
        this.name = name;
        this.img  = img;
    }
    //endregion

    //region Methoden
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }


    @Override
    public String toString() {
        return "TrainingAreas{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
    //endregion
}
