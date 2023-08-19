package de.mfroese.trainingsplan.logic.database;

import de.mfroese.trainingsplan.model.User;

import java.sql.Connection;
import java.util.List;

public class DaoUser implements Dao<User> {
    //region Konstanten
    //endregion

    //region Attribute
    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden

    @Override
    public void insert(Connection connection, User object) {

    }

    @Override
    public List<User> readAll(Connection connection) {
        return null;
    }

    @Override
    public void update(Connection connection, User object) {

    }

    @Override
    public void delete(Connection connection, User object) {

    }

    //endregion
}
