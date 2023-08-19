package de.mfroese.trainingsplan.logic;

import de.mfroese.trainingsplan.logic.database.DbManager;
import de.mfroese.trainingsplan.model.TrainingAreas;

import java.util.List;

/**
 * Singleton, das die Liste der Übungsbereiche bereitstellt.
 * Implementiert Methoden zum Sortieren der Liste.
 */
public class TrainingAreasHolder {
    //region Attribute
    private static TrainingAreasHolder instance;
    private List<TrainingAreas> trainingAreas;
    //endregion

    //region Konstruktor
    private TrainingAreasHolder() {
        trainingAreas = DbManager.getInstance().readAllTrainingAreas();
    }
    //endregion

    //region Methoden
    public static synchronized TrainingAreasHolder getInstance() {
        if (instance == null) {
            instance = new TrainingAreasHolder();
        }
        return instance;
    }

    public List<TrainingAreas> getTrainingAreas() {
        return trainingAreas;
    }
    //endregion
}
