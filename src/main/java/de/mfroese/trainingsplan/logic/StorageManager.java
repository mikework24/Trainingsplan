package de.mfroese.trainingsplan.logic;

import de.mfroese.trainingsplan.settings.AppTexts;
import javafx.scene.control.Alert;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Singleton, das für das Laden und Speichern von Anwendungseinstellungen zuständig ist.
 */
public class StorageManager {
    //region Konstanten
    private static final String DESTINATION_FOLDER = "data";
    private static final String RESOURCE_FILE = "app.settings";
    private static final String DESTINATION_FILE = DESTINATION_FOLDER + File.separator + RESOURCE_FILE;
    //endregion

    //region Attribute
    private static StorageManager instance;
    private final Properties properties;
    //endregion

    //region Konstruktor
    private StorageManager() {
        boolean isFirstUse = false;

        File destinationFolder = new File(DESTINATION_FOLDER);
        if (!destinationFolder.exists()) {
            if(destinationFolder.mkdir()) {
                isFirstUse = true;
            } else {
                // Fehler beim Speichern des Produkts in der Datenbank
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(AppTexts.LOCAL_STORAGE_ERROR);
                alert.setHeaderText(null);
                alert.setContentText(AppTexts.LOCAL_STORAGE_ERROR_MESSAGE);
                alert.showAndWait();
            }
        }

        properties = loadSettings();

        if(isFirstUse) {
            saveDataOnFirstUse();
        }
    }
    //endregion

    //region Methoden
    public static synchronized StorageManager getInstance() {
        if (instance == null) {
            instance = new StorageManager();
        }
        return instance;
    }

    private Properties loadSettings() {
        Properties properties = new Properties();
        File settingsFile = new File(DESTINATION_FILE);

        try {
            if (settingsFile.exists()) {
                FileInputStream fileIn = new FileInputStream(settingsFile);
                properties.load(fileIn);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        return properties;
    }

    public void saveDataOnFirstUse() {
        properties.setProperty("userId", "1");
        properties.setProperty("activePlanId", "1");
        properties.setProperty("breakTimeOne", "60");
        properties.setProperty("breakTimeTwo", "120");
        properties.setProperty("breakTimeThree", "180");
        properties.setProperty("takeOverWeight", "0");
        properties.setProperty("takeOverRepeat", "0");
        properties.setProperty("takeOverSentence", "0");
        saveData();
    }

    public void saveBreakTimes(String breakTimeOne, String breakTimeTwo, String breakTimeThree) {
        properties.setProperty("breakTimeOne", breakTimeOne);
        properties.setProperty("breakTimeTwo", breakTimeTwo);
        properties.setProperty("breakTimeThree", breakTimeThree);
        saveData();
    }

    public void saveTakeOverWeight(boolean takeOverWeight) {
        properties.setProperty("takeOverWeight", String.valueOf(takeOverWeight));
        saveData();
    }

    public void saveTakeOverRepeat(boolean takeOverRepeat) {
        properties.setProperty("takeOverRepeat", String.valueOf(takeOverRepeat));
        saveData();
    }

    public void saveTakeOverSentence(boolean takeOverSentence) {
        properties.setProperty("takeOverSentence", String.valueOf(takeOverSentence));
        saveData();
    }

    public void saveData() {
        try (FileOutputStream fileOut = new FileOutputStream(DESTINATION_FILE)) {
            properties.store(fileOut, "App Settings");
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public String getUserId() {
        return properties.getProperty("userId");
    }

    public int getActivePlanId() {
        return Integer.parseInt(properties.getProperty("activePlanId"));
    }

    public String getBreakTimeOne() {
        return properties.getProperty("breakTimeOne");
    }

    public String getBreakTimeTwo() {
        return properties.getProperty("breakTimeTwo");
    }

    public String getBreakTimeThree() {
        return properties.getProperty("breakTimeThree");
    }

    public boolean getTakeOverWeight() {
        return properties.getProperty("takeOverWeight").contains("true");
    }

    public boolean getTakeOverRepeat() {
        return properties.getProperty("takeOverRepeat").contains("true");
    }

    public boolean getTakeOverSentence() {
        return properties.getProperty("takeOverSentence").contains("true");
    }

    public void setActivePlanId(int activePlanId) {
        properties.setProperty("activePlanId", String.valueOf(activePlanId));
    }
    //endregion
}
