package de.mfroese.trainingsplan.gui.listview;

import de.mfroese.trainingsplan.gui.SceneManager;
import de.mfroese.trainingsplan.model.Plan;
import de.mfroese.trainingsplan.settings.AppTexts;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

/**
 * Eine Zelle, die in der ListView angezeigt wird, um einen Trainingsplan im Archiv anzuzeigen.
 * Die Zelle enthält ein Symbol, den Namen des Trainingsplans und einen Button zum Wiederherstellen.
 */
public class PlanArchiveCell extends ListCell<Plan> {

    /**
     * Aktualisiert den Inhalt der Zelle und zeigt ihn entsprechend an.
     *
     * @param planToShow : {@link Plan} : Der anzuzeigende Trainingsplan
     * @param isEmpty : boolean : Gibt an, ob die Zelle leer sein soll
     */
    @Override
    protected void updateItem(Plan planToShow, boolean isEmpty) {
        super.updateItem(planToShow, isEmpty);

        if (isEmpty || planToShow == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Erstelle das SVG-Bild-Element
            SVGPath svgPath = new SVGPath();
            svgPath.setContent(AppTexts.SVG_SMALL_BOX);
            svgPath.getStyleClass().add("text-white");

            // Erstelle das Text-Element
            Text text = new Text();
            text.setText(" " + planToShow.getName());
            text.getStyleClass().add("text-white");
            text.setWrappingWidth(282);

            // Erstelle Buttons zum Verschieben nach oben und nach unten
            Button btnRestore = new Button("⭯");

            // Füge die CSS-Klassen zu den Buttons hinzu
            btnRestore.getStyleClass().add("btnTransp30");

            btnRestore.setOnAction(event -> {
                planToShow.setActive(true);
                SceneManager.getInstance().switchToTrainingArchive();
            });

            // Erstelle das HBox-Layout für die Zelle
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setSpacing(4);
            hbox.getChildren().addAll(svgPath, text, btnRestore);

            setGraphic(hbox);
        }
    }
}
