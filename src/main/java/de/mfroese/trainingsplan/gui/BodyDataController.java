package de.mfroese.trainingsplan.gui;

import de.mfroese.trainingsplan.gui.listview.DateCell;
import de.mfroese.trainingsplan.gui.listview.PercentCell;
import de.mfroese.trainingsplan.gui.listview.WeightCell;
import de.mfroese.trainingsplan.logic.BodyDataHolder;
import de.mfroese.trainingsplan.logic.Helpers;
import de.mfroese.trainingsplan.logic.StorageManager;
import de.mfroese.trainingsplan.model.BodyData;
import de.mfroese.trainingsplan.settings.AppTexts;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Diese Klasse implementiert die Steuerlogik für die Trainingspläne-Szene.
 * Sie ist verantwortlich für die Anzeige, Bearbeitung und Speicherung von Körperdaten
 * sowie das Zeichnen von Diagrammen basierend auf den gespeicherten Daten.
 */
public class BodyDataController {
    @FXML
    private AnchorPane sceneContent;
    @FXML
    private AnchorPane overlay;
    @FXML
    private TableView<BodyData> bodyDataTableView;
    @FXML
    private TableColumn<BodyData, String> dateColumn;
    @FXML
    private TableColumn<BodyData, Double> weightColumn;
    @FXML
    private TableColumn<BodyData, Double> fatColumn;
    @FXML
    private TableColumn<BodyData, Double> muscleColumn;
    @FXML
    private LineChart<String, Number> bodyDataLineChart;
    @FXML
    private Button btnChartWeight;
    @FXML
    private Button btnChartFat;
    @FXML
    private Button btnChartMuscle;
    @FXML
    private Text txtDate;
    @FXML
    private TextField txtBodyWeight;
    @FXML
    private TextField txtBodyFat;
    @FXML
    private TextField txtMuscleMass;
    @FXML
    private Button btnSaveBodyData;
    @FXML
    private Text overlayTitle;
    @FXML
    private Button btnDeleteBodyData;

    private List<BodyData> bodyDataList;
    private NumberAxis leftAxis;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            setupNumericInputListener(txtBodyWeight);
            setupNumericInputListener(txtBodyFat);
            setupNumericInputListener(txtMuscleMass);

            dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
            weightColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBodyWeight()).asObject());
            fatColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBodyFat()).asObject());
            muscleColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getMuscleMass()).asObject());

            dateColumn.setCellFactory(DateCell.forTableColumn());
            weightColumn.setCellFactory(WeightCell.forTableColumn());
            fatColumn.setCellFactory(PercentCell.forTableColumn());
            muscleColumn.setCellFactory(PercentCell.forTableColumn());

            bodyDataTableView.setItems(BodyDataHolder.getInstance().getBodyDatas());

            bodyDataTableView.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
                    editBodyData();
                }
            });

            bodyDataTableView.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    editBodyData();
                }
            });

            leftAxis = (NumberAxis) bodyDataLineChart.getYAxis();
            bodyDataLineChart.getStyleClass().add("white-lines-chart");
            showChartWeight();
        });
    }

    private void setupNumericInputListener(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String enteredValue = newValue.replace(",", ".");
            String filteredValue = enteredValue.replaceAll("[^0-9.]", "");
            if (filteredValue.matches("^\\d*\\.?\\d*$")) {
                textField.setText(filteredValue);
            } else {
                textField.setText(oldValue);
            }

            disableSaveButton();
        });
    }

    @FXML
    private void showChartWeight() {
        showChart("weight");
    }

    @FXML
    private void showChartFat() {
        showChart("fat");
    }

    @FXML
    private void showChartMuscle() {
        showChart("muscle");
    }

    private void showChart(String chartType) {
        btnChartWeight.getStyleClass().setAll("rGray");
        btnChartFat.getStyleClass().setAll("rGray");
        btnChartMuscle.getStyleClass().setAll("rGray");

        switch (chartType) {
            case "weight" -> btnChartWeight.getStyleClass().setAll("rBlue");
            case "fat" -> btnChartFat.getStyleClass().setAll("rBlue");
            case "muscle" -> btnChartMuscle.getStyleClass().setAll("rBlue");
            default -> {
            }
        }

        double diagramMinValue = Double.MAX_VALUE;
        double diagramMaxValue = Double.MIN_VALUE;

        String chartName = "";
        Function<BodyData, Double> getValueFunction = null;

        switch (chartType) {
            case "weight" -> {
                chartName = "Gewicht";
                getValueFunction = BodyData::getBodyWeight;
            }
            case "fat" -> {
                chartName = "Körperfett";
                getValueFunction = BodyData::getBodyFat;
            }
            case "muscle" -> {
                chartName = "Muskelmasse";
                getValueFunction = BodyData::getMuscleMass;
            }
            default -> {
            }
        }

        XYChart.Series<String, Number> chartSeries = new XYChart.Series<>();
        chartSeries.setName(chartName);

        bodyDataList = BodyDataHolder.getInstance().getBodyDatas();

        for (BodyData bodyData : bodyDataList) {
            assert getValueFunction != null;
            double bodyValue = getValueFunction.apply(bodyData);
            if (bodyValue > 0) {
                chartSeries.getData().add(new XYChart.Data<>(Helpers.getGermanDateFormat(bodyData.getDate()), bodyValue));
                diagramMinValue = Math.min(diagramMinValue, bodyValue);
                diagramMaxValue = Math.max(diagramMaxValue, bodyValue);
            }
        }

        leftAxis.setUpperBound(diagramMaxValue + 2);
        leftAxis.setLowerBound(diagramMinValue - 2);

        bodyDataLineChart.getData().setAll(chartSeries);
    }

    private void showOverlay() {
        sceneContent.setDisable(true);
        overlay.setDisable(false);
        overlay.setVisible(true);
    }

    @FXML
    private void closeOverlay() {
        sceneContent.setDisable(false);
        overlay.setDisable(true);
        overlay.setVisible(false);
    }

    @FXML
    private void addBodyData() {
        overlayTitle.setText(AppTexts.NEW_RECORD);
        btnDeleteBodyData.setDisable(true);
        btnDeleteBodyData.setVisible(false);

        txtDate.setText(Helpers.getGermanDateFormat(LocalDate.now()));

        txtBodyWeight.setText("");
        txtBodyFat.setText("");
        txtMuscleMass.setText("");

        showOverlay();
    }

    private void editBodyData() {
        overlayTitle.setText(AppTexts.EDIT_RECORD);
        btnDeleteBodyData.setDisable(false);
        btnDeleteBodyData.setVisible(true);

        BodyData selectedBodyData = bodyDataTableView.getSelectionModel().getSelectedItem();

        txtDate.setText(Helpers.getGermanDateFormat(selectedBodyData.getDate()));

        if (selectedBodyData.getBodyWeight() > 0) {
            txtBodyWeight.setText(String.valueOf(selectedBodyData.getBodyWeight()));
        }
        if (selectedBodyData.getBodyFat() > 0) {
            txtBodyFat.setText(String.valueOf(selectedBodyData.getBodyFat()));
        }
        if (selectedBodyData.getMuscleMass() > 0) {
            txtMuscleMass.setText(String.valueOf(selectedBodyData.getMuscleMass()));
        }

        showOverlay();
    }

    @FXML
    private void disableSaveButton() {
        String bodyWeight = txtBodyWeight.getText();
        String bodyFat = txtBodyFat.getText();
        String muscleMass = txtMuscleMass.getText();

        btnSaveBodyData.setDisable(bodyWeight.isBlank() && bodyFat.isBlank() && muscleMass.isBlank());
    }

    @FXML
    private void saveNewBodyData() {
        if (txtBodyWeight.getText().isEmpty()) {
            txtBodyWeight.setText("0.0");
        }
        if (txtBodyFat.getText().isEmpty()) {
            txtBodyFat.setText("0.0");
        }
        if (txtMuscleMass.getText().isEmpty()) {
            txtMuscleMass.setText("0.0");
        }

        BodyData selectedBodyData = bodyDataTableView.getSelectionModel().getSelectedItem();
        if (overlayTitle.getText().equals(AppTexts.NEW_RECORD)) {
            BodyData newBodyData = new BodyData(
                    Integer.parseInt(StorageManager.getInstance().getUserId()),
                    LocalDate.now().toString(),
                    Double.parseDouble(txtBodyWeight.getText()),
                    Double.parseDouble(txtBodyFat.getText()),
                    Double.parseDouble(txtMuscleMass.getText())
            );

            BodyDataHolder.getInstance().getBodyDatas().add(newBodyData);

        } else if (selectedBodyData != null) {
            if (!isEqual(String.valueOf(selectedBodyData.getBodyWeight()), txtBodyWeight.getText())) {
                selectedBodyData.setBodyWeight(Double.parseDouble(txtBodyWeight.getText()));
            }
            if (!isEqual(String.valueOf(selectedBodyData.getBodyFat()), txtBodyFat.getText())) {
                selectedBodyData.setBodyFat(Double.parseDouble(txtBodyFat.getText()));
            }
            if (!isEqual(String.valueOf(selectedBodyData.getMuscleMass()), txtMuscleMass.getText())) {
                selectedBodyData.setMuscleMass(Double.parseDouble(txtMuscleMass.getText()));
            }
        }
        bodyDataTableView.setItems(BodyDataHolder.getInstance().getBodyDatas());
        bodyDataTableView.refresh();

        bodyDataList = BodyDataHolder.getInstance().getBodyDatas();
        showChartWeight();

        closeOverlay();
    }

    private boolean isEqual(String oldValue, String newValue) {
        return oldValue.equals(newValue);
    }

    @FXML
    private void deleteBodyData() {
        BodyData selectedBodyData = bodyDataTableView.getSelectionModel().getSelectedItem();

        if (selectedBodyData != null) {
            Alert confirmDeletionAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDeletionAlert.setTitle(AppTexts.DELETE);
            confirmDeletionAlert.setHeaderText(AppTexts.DELETE_BODY_DATA_MESSAGE);
            confirmDeletionAlert.setContentText(selectedBodyData.toString());

            Optional<ButtonType> optional = confirmDeletionAlert.showAndWait();

            if (optional.isPresent() && optional.get() == ButtonType.OK) {
                BodyDataHolder.getInstance().getBodyDatas().remove(selectedBodyData);

                bodyDataList = BodyDataHolder.getInstance().getBodyDatas();
                showChartWeight();

                closeOverlay();
            }
        }
    }

    @FXML
    private void openTrainingPlans() {
        SceneManager.getInstance().switchToTrainingPlans();
    }

    @FXML
    private void openStatisticsExercise() {
        SceneManager.getInstance().switchToStatisticsExercise();
    }

    @FXML
    private void openFinishedWorkout() {
        SceneManager.getInstance().switchToFinishedWorkout();
    }

    @FXML
    private void openSettings() {
        SceneManager.getInstance().switchToSettings();
    }
}