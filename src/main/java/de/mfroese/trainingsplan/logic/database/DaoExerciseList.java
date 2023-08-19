package de.mfroese.trainingsplan.logic.database;

import de.mfroese.trainingsplan.model.ExerciseList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO-Klasse für Trainingsübung-Listen-Objekte.
 * Kann Trainingsübungslisten auslesen, speichern, ändern und löschen.
 * Kennt die Tabellenstruktur der Trainingsübung in der Datenbank.
 */
public class DaoExerciseList implements Dao<ExerciseList> {
    //region Konstanten
    public static final String SQL_SELECT_ALL_EXERCISES_LIST = "SELECT * FROM exercise_list";
    public static final String COLUMN_ID = "pk_id";
    public static final String COLUMN_TRAINING_AREAS_ID = "fk_training_areas_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMG = "img";
    public static final String COLUMN_BY_REPEAT = "by_repeat";
    public static final String SQL_INSERT_EXERCISE_LIST = "INSERT INTO exercise_list (fk_training_areas_id, name, img, by_repeat) VALUES (?,?,?,?)";
    public static final String COL_INSERT_ID = "insert_id";
    public static final String SQL_DELETE_EXERCISE_LIST_BY_ID = "DELETE FROM exercise_list WHERE pk_id=?";
    public static final String SQL_UPDATE_EXERCISE_LIST_BY_ID = "UPDATE exercise_list SET fk_training_areas_id=?, name=?, img=?, by_repeat=? WHERE pk_id=?";
    //endregion

    //region Attribute
    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden

    @Override
    public void insert(Connection connection, ExerciseList exerciseList) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_INSERT_EXERCISE_LIST, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, exerciseList.getTrainingAreasId());
            statement.setString(2, exerciseList.getName());
            statement.setString(3, exerciseList.getImg());
            statement.setBoolean(4, exerciseList.isByRepeat());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            int insertId;

            if (resultSet.next()) {
                insertId = resultSet.getInt(COL_INSERT_ID);
                exerciseList.setId(insertId);
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
    }

    @Override
    public List<ExerciseList> readAll(Connection connection) {
        List<ExerciseList> exercisesList = new ArrayList<>();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_EXERCISES_LIST);

            while (resultSet.next()) {
                ExerciseList exerciseList = new ExerciseList(
                        resultSet.getInt(COLUMN_ID),
                        resultSet.getInt(COLUMN_TRAINING_AREAS_ID),
                        resultSet.getString(COLUMN_NAME),
                        resultSet.getString(COLUMN_IMG),
                        resultSet.getBoolean(COLUMN_BY_REPEAT)
                );

                exercisesList.add(exerciseList);
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

        return exercisesList;
    }

    @Override
    public void update(Connection connection, ExerciseList exerciseList) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_UPDATE_EXERCISE_LIST_BY_ID);
            statement.setInt(1, exerciseList.getTrainingAreasId());
            statement.setString(2, exerciseList.getName());
            statement.setString(3, exerciseList.getImg());
            statement.setBoolean(4, exerciseList.isByRepeat());
            statement.setInt(5, exerciseList.getId());

            statement.executeUpdate();

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
    }

    @Override
    public void delete(Connection connection, ExerciseList exerciseList) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_DELETE_EXERCISE_LIST_BY_ID);
            statement.setInt(1, exerciseList.getId());

            statement.executeUpdate();

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
    }

    //endregion
}
