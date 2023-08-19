module de.mfroese.trainingsplan {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mariadb.jdbc;
    requires java.sql;

    opens de.mfroese.trainingsplan to javafx.fxml;
    exports de.mfroese.trainingsplan;
    opens de.mfroese.trainingsplan.gui to javafx.fxml;
    exports de.mfroese.trainingsplan.gui;
}