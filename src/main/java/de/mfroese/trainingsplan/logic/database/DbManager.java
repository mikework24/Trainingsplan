package de.mfroese.trainingsplan.logic.database;

import de.mfroese.trainingsplan.model.Exercise;
import de.mfroese.trainingsplan.model.ExerciseList;
import de.mfroese.trainingsplan.model.Plan;
import de.mfroese.trainingsplan.model.TrainingAreas;
import de.mfroese.trainingsplan.model.BodyData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Baut Verbindung zu Datenbanken auf und hat Zugriff
 * auf die jeweiligen DAO-Objekte aller Modellklassen
 * des Projektes.
 */
public class DbManager {
    //region Konstanten
    private static final String CONNECTION_API_PREFIX = "jdbc:mariadb://";
    private static final String SERVER_IP = "localhost";
    private static final String DB_NAME = "/fitnessplan";
    private static final String DB_USER_NAME = "root";
    private static final String DB_USER_PASSWORD = "";
    private static final String CONNECTION_URL = CONNECTION_API_PREFIX + SERVER_IP + DB_NAME;
    //endregion

    //region Attribute
    private static DbManager instance;

    private DaoExercise daoExercise;
    private DaoExerciseList daoExerciseList;
    private DaoPlan daoPlan;
    private DaoTrainingAreas daoTrainingAreas;
    private DaoBodyData daoBodyData;

    private Connection connection;
    //endregion

    //region Konstruktoren
    private DbManager() {
        daoExercise = new DaoExercise();
        daoExerciseList = new DaoExerciseList();
        daoPlan = new DaoPlan();
        daoTrainingAreas = new DaoTrainingAreas();
        daoBodyData = new DaoBodyData();
    }
    //endregion

    //region Methoden
    /**
     * Gibt eine Instanz des DbManager zurück (Singleton-Muster).
     *
     * @return Die DbManager-Instanz.
     */
    public static synchronized DbManager getInstance() {
        if (instance == null) instance = new DbManager();
        return instance;
    }

    /**
     * Stellt die Verbindung zur Datenbank her und gibt die Connection-Instanz zurück.
     *
     * @return Die Connection-Instanz.
     */
    private Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(CONNECTION_URL, DB_USER_NAME, DB_USER_PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * Fügt ein Objekt in die Datenbank ein.
     *
     * @param object Das einzufügende Objekt.
     */
    public void insert(Object object) {
        if (object instanceof Exercise exercise) {daoExercise.insert(getConnection(), exercise);
        } else if (object instanceof ExerciseList exerciseList) {daoExerciseList.insert(getConnection(), exerciseList);
        } else if (object instanceof Plan plan) {daoPlan.insert(getConnection(), plan);
        } else if (object instanceof BodyData bodyData) {daoBodyData.insert(getConnection(), bodyData);
        } else { System.out.println("Objekt kann nicht eingefügt werden!"); }
    }

    /**
     * Liest alle Übungen aus der Datenbank.
     *
     * @return Eine Liste mit allen Übungen.
     */
    public List<Exercise> readAllExercises() {
        return daoExercise.readAll(getConnection());
    }

    /**
     * Liest alle Übungslisten aus der Datenbank.
     *
     * @return Eine Liste mit allen Übungslisten.
     */
    public List<ExerciseList> readAllExercisesList() {
        return daoExerciseList.readAll(getConnection());
    }

    /**
     * Liest alle Pläne aus der Datenbank.
     *
     * @return Eine Liste mit allen Plänen.
     */
    public List<Plan> readAllPlans() {
        return daoPlan.readAll(getConnection());
    }

    /**
     * Liest alle Trainingsbereiche aus der Datenbank.
     *
     * @return Eine Liste mit allen Trainingsbereichen.
     */
    public List<TrainingAreas> readAllTrainingAreas() {
        return daoTrainingAreas.readAll(getConnection());
    }

    /**
     * Liest alle Körperdaten aus der Datenbank.
     *
     * @return Eine Liste mit allen Körperdaten.
     */
    public List<BodyData> readAllBodyDatas() {
        return daoBodyData.readAll(getConnection());
    }

    /**
     * Aktualisiert ein Objekt in der Datenbank.
     *
     * @param object Das zu aktualisierende Objekt.
     */
    public void update(Object object) {
        if (object instanceof Exercise exercise) {daoExercise.update(getConnection(), exercise);
        } else if (object instanceof ExerciseList exerciseList) {daoExerciseList.update(getConnection(), exerciseList);
        } else if (object instanceof Plan plan) {daoPlan.update(getConnection(), plan);
        } else if (object instanceof BodyData bodyData) {daoBodyData.update(getConnection(), bodyData);
        } else { System.out.println("Objekt kann nicht aktualisiert werden!"); }
    }

    /**
     * Löscht ein Objekt aus der Datenbank.
     *
     * @param object Das zu löschende Objekt.
     */
    public void delete(Object object) {
        if (object instanceof Exercise exercise) {daoExercise.delete(getConnection(), exercise);
        } else if (object instanceof ExerciseList exerciseList) {daoExerciseList.delete(getConnection(), exerciseList);
        } else if (object instanceof Plan plan) {daoPlan.delete(getConnection(), plan);
        } else if (object instanceof BodyData bodyData) {daoBodyData.delete(getConnection(), bodyData);
        } else { System.out.println("Objekt kann nicht gelöscht werden!"); }
    }
    //endregion
}
