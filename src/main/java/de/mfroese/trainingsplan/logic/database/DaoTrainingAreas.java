package de.mfroese.trainingsplan.logic.database;

import de.mfroese.trainingsplan.model.TrainingAreas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO-Klasse für Trainingsbereich-Objekte.
 * Kann Trainingsbereiche aus der Datenbank auslesen.
 * Kennt die Tabellenstruktur der Trainingsbereiche in der Datenbank.
 */
public class DaoTrainingAreas implements Dao<TrainingAreas> {
    //region Konstanten
    public static final String SQL_SELECT_ALL_TRAINING_AREAS = "SELECT * FROM training_areas";
    public static final String COLUMN_ID = "pk_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMG = "img";
    //endregion

    //region Methoden
    @Override
    public List<TrainingAreas> readAll(Connection connection) {
        List<TrainingAreas> trainingAreas = new ArrayList<>();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_TRAINING_AREAS);

            while (resultSet.next()) {
                TrainingAreas trainingArea = new TrainingAreas(
                        resultSet.getInt(COLUMN_ID),
                        resultSet.getString(COLUMN_NAME),
                        resultSet.getString(COLUMN_IMG)
                );

                trainingAreas.add(trainingArea);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return trainingAreas;
    }

    @Override
    public void insert(Connection connection, TrainingAreas trainingAreas) {}

    @Override
    public void update(Connection connection, TrainingAreas trainingAreas) {}

    @Override
    public void delete(Connection connection, TrainingAreas trainingAreas) {}

    //endregion
}
