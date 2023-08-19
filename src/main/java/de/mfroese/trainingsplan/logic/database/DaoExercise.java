package de.mfroese.trainingsplan.logic.database;

import de.mfroese.trainingsplan.logic.StorageManager;
import de.mfroese.trainingsplan.model.Exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO-Klasse für Trainingsübung-Objekte.
 * Kann Übungen auslesen, speichern, ändern und löschen.
 * Kennt die Tabellenstruktur der Trainingsübung in der Datenbank.
 */
public class DaoExercise implements Dao<Exercise> {
    //region Konstanten
    public static final String SQL_SELECT_ALL_EXERCISES = "SELECT * FROM exercise WHERE fk_plan_id=?";
    public static final String COLUMN_ID = "pk_id";
    public static final String COLUMN_EXERCISE_LIST_ID = "fk_exercise_list_id";
    public static final String COLUMN_PLAN_ID = "fk_plan_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_BY_REPEAT = "by_repeat";
    public static final String COLUMN_SENTENCE = "sentence";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_REPEAT_EXERCISE = "repeat_exercise";
    public static final String COLUMN_TIME = "time";

    public static final String SQL_INSERT_EXERCISE = "INSERT INTO exercise (fk_exercise_list_id, fk_plan_id, name, position, by_repeat, sentence, weight, repeat_exercise, time) VALUES (?,?,?,?,?,?,?,?,?)";
    public static final String COL_INSERT_ID = "insert_id";
    public static final String SQL_DELETE_EXERCISE_BY_ID = "DELETE FROM exercise WHERE pk_id=?";
    public static final String SQL_UPDATE_EXERCISE_BY_ID = "UPDATE exercise SET fk_exercise_list_id=?, fk_plan_id=?, name=?, position=?, by_repeat=?, sentence=?, weight=?, repeat_exercise=?, time=? WHERE pk_id=?";
    //endregion

    //region Attribute
    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden

    @Override
    public void insert(Connection connection, Exercise exercise) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_INSERT_EXERCISE, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, exercise.getExerciseListId());
            statement.setInt(2, exercise.getPlanId());
            statement.setString(3, exercise.getName());
            statement.setInt(4, exercise.getPosition());
            statement.setBoolean(5, exercise.isByRepeat());
            statement.setInt(6, exercise.getSentence());
            statement.setDouble(7, exercise.getWeight());
            statement.setInt(8, exercise.getRepeat_exercise());
            statement.setInt(9, exercise.getTime());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            int insertId;

            if (resultSet.next()) {
                insertId = resultSet.getInt(COL_INSERT_ID);
                exercise.setId(insertId);
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
    public List<Exercise> readAll(Connection connection) {
        List<Exercise> exercises = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            int activePlanId = StorageManager.getInstance().getActivePlanId();

            statement = connection.prepareStatement(SQL_SELECT_ALL_EXERCISES);
            statement.setInt(1, activePlanId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Exercise exercise = new Exercise(
                        resultSet.getInt(COLUMN_ID),
                        resultSet.getInt(COLUMN_EXERCISE_LIST_ID),
                        resultSet.getInt(COLUMN_PLAN_ID),
                        resultSet.getString(COLUMN_NAME),
                        resultSet.getInt(COLUMN_POSITION),
                        resultSet.getBoolean(COLUMN_BY_REPEAT),
                        resultSet.getInt(COLUMN_SENTENCE),
                        resultSet.getDouble(COLUMN_WEIGHT),
                        resultSet.getInt(COLUMN_REPEAT_EXERCISE),
                        resultSet.getInt(COLUMN_TIME)
                );

                exercises.add(exercise);
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

        return exercises;
    }

    @Override
    public void update(Connection connection, Exercise exercise) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_UPDATE_EXERCISE_BY_ID);
            statement.setInt(1, exercise.getExerciseListId());
            statement.setInt(2, exercise.getPlanId());
            statement.setString(3, exercise.getName());
            statement.setInt(4, exercise.getPosition());
            statement.setBoolean(5, exercise.isByRepeat());
            statement.setInt(6, exercise.getSentence());
            statement.setDouble(7, exercise.getWeight());
            statement.setInt(8, exercise.getRepeat_exercise());
            statement.setInt(9, exercise.getTime());
            statement.setInt(10, exercise.getId());

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
    public void delete(Connection connection, Exercise exercise) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_DELETE_EXERCISE_BY_ID);
            statement.setInt(1, exercise.getId());

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
