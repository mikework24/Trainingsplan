package de.mfroese.trainingsplan;

import de.mfroese.trainingsplan.gui.SceneManager;
import de.mfroese.trainingsplan.settings.AppTexts;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.getInstance().setAndConfigureMainStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}