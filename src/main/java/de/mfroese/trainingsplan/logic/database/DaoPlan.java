package de.mfroese.trainingsplan.logic.database;

import de.mfroese.trainingsplan.logic.StorageManager;
import de.mfroese.trainingsplan.model.Plan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO-Klasse für Trainingsplan-Objekte.
 * Kann Trainingspläne auslesen, speichern, ändern und löschen.
 * Kennt die Tabellenstruktur der Trainingspläne in der Datenbank.
 */
public class DaoPlan implements Dao<Plan> {
    //region Konstanten
    public static final String SQL_SELECT_ALL_PLANS = "SELECT * FROM plan WHERE fk_user_id=?";
    public static final String COLUMN_ID = "pk_id";
    public static final String COLUMN_USER_ID = "fk_user_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_ACTIVE = "active";
    public static final String SQL_INSERT_PLAN = "INSERT INTO plan (fk_user_id, name, position, active) VALUES (?,?,?,1)";
    public static final String COL_INSERT_ID = "insert_id";
    public static final String SQL_DELETE_PLAN_BY_ID = "DELETE FROM plan WHERE pk_id=?";
    public static final String SQL_UPDATE_PLAN_BY_ID = "UPDATE plan SET fk_user_id=?, name=?, position=?, active=? WHERE pk_id=?";
    //endregion

    //region Attribute
    int userId = 0;
    //endregion

    //region Konstruktoren
    DaoPlan(){
        userId = Integer.parseInt(StorageManager.getInstance().getUserId());
    }
    //endregion

    //region Methoden

    @Override
    public void insert(Connection connection, Plan plan) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_INSERT_PLAN, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, plan.getUserId());
            statement.setString(2, plan.getName());
            statement.setInt(3, plan.getPosition());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            int insertId;

            if (resultSet.next()) {
                insertId = resultSet.getInt(COL_INSERT_ID);
                plan.setId(insertId);
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
    public List<Plan> readAll(Connection connection) {
        List<Plan> plans = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_PLANS);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Plan plan = new Plan(
                        resultSet.getInt(COLUMN_ID),
                        resultSet.getInt(COLUMN_USER_ID),
                        resultSet.getString(COLUMN_NAME),
                        resultSet.getInt(COLUMN_POSITION),
                        resultSet.getBoolean(COLUMN_ACTIVE)
                );

                plans.add(plan);
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

        return plans;
    }

    @Override
    public void update(Connection connection, Plan plan) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_UPDATE_PLAN_BY_ID);
            statement.setInt(1, plan.getUserId());
            statement.setString(2, plan.getName());
            statement.setInt(3, plan.getPosition());
            statement.setBoolean(4, plan.isActive());
            statement.setInt(5, plan.getId());

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
    public void delete(Connection connection, Plan plan) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_DELETE_PLAN_BY_ID);
            statement.setInt(1, plan.getId());

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
