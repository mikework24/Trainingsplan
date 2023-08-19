package de.mfroese.trainingsplan.logic.database;

import de.mfroese.trainingsplan.logic.StorageManager;
import de.mfroese.trainingsplan.model.BodyData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO-Klasse für Körperdaten-Objekte.
 * Kann Körperdaten auslesen, speichern, ändern und löschen.
 * Kennt die Tabellenstruktur der Körperdaten in der Datenbank.
 */
public class DaoBodyData implements Dao<BodyData> {
    //region Konstanten
    public static final String SQL_SELECT_ALL_BODY_DATA = "SELECT * FROM body_data WHERE fk_user_id=?";
    public static final String COLUMN_ID = "pk_id";
    public static final String COLUMN_USER_ID = "fk_user_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_FAT = "fat";
    public static final String COLUMN_MUSCLE = "muscle";
    public static final String SQL_INSERT_BODY_DATA = "INSERT INTO body_data (fk_user_id, date, weight, fat, muscle) VALUES (?,?,?,?,?)";
    public static final String COL_INSERT_ID = "insert_id";
    public static final String SQL_DELETE_BODY_DATA_BY_ID = "DELETE FROM body_data WHERE pk_id=?";
    public static final String SQL_UPDATE_BODY_DATA_BY_ID = "UPDATE body_data SET fk_user_id=?, date=?, weight=?, fat=?, muscle=? WHERE pk_id=?";
    //endregion

    //region Attribute
    int userId = 0;
    //endregion

    //region Konstruktoren
    DaoBodyData() {
        userId = Integer.parseInt(StorageManager.getInstance().getUserId());
    }
    //endregion

    //region Methoden

    @Override
    public void insert(Connection connection, BodyData bodyData) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_INSERT_BODY_DATA, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, bodyData.getUserId());
            statement.setString(2, bodyData.getDate());
            statement.setDouble(3, bodyData.getBodyWeight());
            statement.setDouble(4, bodyData.getBodyFat());
            statement.setDouble(5, bodyData.getMuscleMass());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            int insertId;

            if (resultSet.next()) {
                insertId = resultSet.getInt(COL_INSERT_ID);
                bodyData.setId(insertId);
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
    public List<BodyData> readAll(Connection connection) {
        List<BodyData> bodyDatas = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_BODY_DATA);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                BodyData bodyData = new BodyData(
                        resultSet.getInt(COLUMN_ID),
                        resultSet.getInt(COLUMN_USER_ID),
                        resultSet.getString(COLUMN_DATE),
                        resultSet.getDouble(COLUMN_WEIGHT),
                        resultSet.getDouble(COLUMN_FAT),
                        resultSet.getDouble(COLUMN_MUSCLE)
                );

                bodyDatas.add(bodyData);
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

        return bodyDatas;
    }

    @Override
    public void update(Connection connection, BodyData bodyData) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_UPDATE_BODY_DATA_BY_ID);
            statement.setInt(1, bodyData.getUserId());
            statement.setString(2, bodyData.getDate());
            statement.setDouble(3, bodyData.getBodyWeight());
            statement.setDouble(4, bodyData.getBodyFat());
            statement.setDouble(5, bodyData.getMuscleMass());
            statement.setInt(6, bodyData.getId());

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
    public void delete(Connection connection, BodyData bodyData) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_DELETE_BODY_DATA_BY_ID);
            statement.setInt(1, bodyData.getId());

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
