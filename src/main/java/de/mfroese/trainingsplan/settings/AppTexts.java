package de.mfroese.trainingsplan.settings;

/**
 * Stellt alle Ausgabetexte des Programms als Konstanten zur Verfügung
 */
public class AppTexts {
    //region Konstanten
    public static final String TRAININGSPLAN = "Trainingsplan";


    //SVG's
    public static final String SVG_SMALL_BOX = "M17.48 1.898H1.52a.568.568 0 0 0-.57.57V4.75h17.1V2.469a.578.578 0 0 0-.57-.57zM1.898 15.77c0 .73.598 1.332 1.332 1.332h12.54c.73 0 1.328-.602 1.328-1.332V5.7h-15.2zm4.75-8.168h5.704V9.5H6.648zm0 0";
    public static final String SVG_SMALL_CLIPBOARD_LIST = "M12.25 2.25H9.332C9.332 1.008 8.285 0 7 0S4.668 1.008 4.668 2.25H1.75C.785 2.25 0 3.008 0 3.938v12.374C0 17.242.785 18 1.75 18h10.5c.965 0 1.75-.758 1.75-1.688V3.938c0-.93-.785-1.687-1.75-1.687zM3.5 14.906c-.484 0-.875-.375-.875-.844 0-.468.39-.843.875-.843.484 0 .875.375.875.844 0 .468-.39.843-.875.843zm0-3.375c-.484 0-.875-.375-.875-.844 0-.468.39-.843.875-.843.484 0 .875.375.875.844 0 .468-.39.843-.875.843zm0-3.375c-.484 0-.875-.375-.875-.844 0-.468.39-.843.875-.843.484 0 .875.375.875.843 0 .47-.39.844-.875.844zM7 1.406c.484 0 .875.375.875.844s-.39.844-.875.844c-.484 0-.875-.375-.875-.844s.39-.844.875-.844zm4.668 12.938a.288.288 0 0 1-.293.281h-5.25a.288.288 0 0 1-.293-.281v-.563c0-.156.133-.281.293-.281h5.25c.16 0 .293.125.293.281zm0-3.375a.288.288 0 0 1-.293.281h-5.25a.288.288 0 0 1-.293-.281v-.563c0-.156.133-.281.293-.281h5.25c.16 0 .293.125.293.281zm0-3.375a.288.288 0 0 1-.293.281h-5.25a.288.288 0 0 1-.293-.281V7.03c0-.156.133-.281.293-.281h5.25c.16 0 .293.125.293.281zm0 0";


    //NewTrainingPlanController
    public static final String NEW_PLAN_NAME = "New Plan Name";

    //BodyDataController
    public static final String NEW_RECORD = "Neuer Datensatz";
    public static final String EDIT_RECORD = "Datensatz bearbeiten";

    public static final String DELETE = "Löschen";
    public static final String DELETE_BODY_DATA_MESSAGE = "Wollen Sie wirklich die Körperdaten löschen?";

    //NewPlanExerciseController
    public static final String SELECT_TRAINING_AREAS = "Übungsbereich Wählen";
    public static final String SELECT_EXERCISE_LIST = "Übung Wählen";
    public static final String EDIT_EXERCISE = "ÜBUNG BEARBEITEN";
    public static final String REPEATS = "WDH";
    public static final String TIME = "ZEIT";





    //StorageManager
    public static final String LOCAL_STORAGE_ERROR = "Lokaler Speicher Fehler";
    public static final String LOCAL_STORAGE_ERROR_MESSAGE = "Sie haben in diesem Verzeichnis keine Schreibrechte. " +
            "Verschieben Sie das Programmverzeichnis bitte in ein Verzeichnis, " +
            "in dem Sie auch die Schreibrechte ohne Admin besitzen.";
    //endregion

    //region Attribute
    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden
    private AppTexts() {}
    //endregion
}
