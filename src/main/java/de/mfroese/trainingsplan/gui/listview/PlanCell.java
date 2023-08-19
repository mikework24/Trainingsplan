package de.mfroese.trainingsplan.gui.listview;

import de.mfroese.trainingsplan.logic.PlanHolder;
import de.mfroese.trainingsplan.model.Plan;
import de.mfroese.trainingsplan.settings.AppTexts;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.ListCell;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

/**
 * Definiert eine Zelle, wie sie in der ListView angezeigt werden soll, um Trainingspläne anzuzeigen.
 */
public class PlanCell extends ListCell<Plan> {

    /**
     * Aktualisiert den Inhalt einer Zelle und zeigt ihn in der implementierten Art und Weise an.
     * Die Zellen werden von der Zellenfabrik erstellt.
     *
     * @param planToShow : {@link Plan} : Anzuzeigender Trainingsplan
     * @param isEmpty : boolean : Gibt an, ob die Zelle leer sein soll
     */
    @Override
    protected void updateItem(Plan planToShow, boolean isEmpty) {
        super.updateItem(planToShow, isEmpty);

        // Wenn die Zelle leer ist oder das anzuzeigende Objekt nicht richtig initialisiert ist.
        if (isEmpty || planToShow == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Erstelle das SVG-Bild-Element
            SVGPath svgPath = new SVGPath();
            svgPath.setContent(AppTexts.SVG_SMALL_CLIPBOARD_LIST);
            svgPath.getStyleClass().add("text-white");

            // Erstelle das Text-Element
            Text text = new Text();
            text.setText(" " + planToShow.getName());
            text.getStyleClass().add("text-white");
            text.setWrappingWidth(250);

            // Erstelle Buttons zum Verschieben nach oben und nach unten
            Button btnMoveUp = new Button("▲");
            Button btnMoveDown = new Button("▼");

            // Füge die CSS-Klassen zu den Buttons hinzu
            btnMoveUp.getStyleClass().add("btnTransp30");
            btnMoveDown.getStyleClass().add("btnTransp30");

            // Deaktiviere nicht benötigte Buttons für das erste und letzte Listenelement
            if (getIndex() == 0) {
                btnMoveUp.setDisable(true);
                btnMoveUp.setVisible(false);
            }
            if (getIndex() == getListView().getItems().size() - 1) {
                btnMoveDown.setDisable(true);
                btnMoveDown.setVisible(false);
            }

            // Definiere die Aktionen für die Buttons
            btnMoveUp.setOnAction(event -> {
                int currentPosition = planToShow.getPosition();
                int newPosition = currentPosition - 1;
                Plan otherPlan = PlanHolder.getInstance().getPlanByPosition(newPosition);

                // Tausche die Positionen
                otherPlan.setPosition(planToShow.getPosition());
                planToShow.setPosition(newPosition);

                PlanHolder.getInstance().sortByPosition();
            });

            btnMoveDown.setOnAction(event -> {
                int currentPosition = planToShow.getPosition();
                int newPosition = currentPosition + 1;
                Plan otherPlan = PlanHolder.getInstance().getPlanByPosition(newPosition);

                // Tausche die Positionen
                otherPlan.setPosition(planToShow.getPosition());
                planToShow.setPosition(newPosition);

                PlanHolder.getInstance().sortByPosition();
            });

            // Erstelle das HBox-Layout für die Zelle
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setSpacing(4);

            // Füge die Elemente zur HBox hinzu
            if (getIndex() == getListView().getItems().size() - 1) {
                hbox.getChildren().addAll(svgPath, text, btnMoveDown, btnMoveUp);
            } else {
                hbox.getChildren().addAll(svgPath, text, btnMoveUp, btnMoveDown);
            }

            // Setze das HBox-Layout als grafisches Element der Zelle
            setGraphic(hbox);
        }
    }
}
