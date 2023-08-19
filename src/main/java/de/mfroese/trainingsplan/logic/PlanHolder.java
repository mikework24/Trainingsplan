package de.mfroese.trainingsplan.logic;

import de.mfroese.trainingsplan.logic.database.DbManager;
import de.mfroese.trainingsplan.model.Plan;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton, das eine ObservableList mit Trainingsplänen bereitstellt und Methoden zum Sortieren und Suchen der Liste implementiert.
 */
public class PlanHolder {
    //region Attribute
    private static PlanHolder instance;
    private final List<Plan> plansAll;
    private ObservableList<Plan> plans;
    private final ListChangeListener<Plan> listener;
    //endregion

    //region Konstruktor
    private PlanHolder() {
        plansAll = new ArrayList<>();
        plans = FXCollections.observableArrayList(planToObserve ->
                new Observable[] { planToObserve.userIdProperty(), planToObserve.nameProperty(),
                        planToObserve.positionProperty(), planToObserve.activeProperty() });

        plans.addAll(DbManager.getInstance().readAllPlans());
        plansAll.addAll(plans);

        sortByPosition();

        listener = change -> {
            while (change.next()) {
                if (change.wasReplaced()) {
                    System.out.println("Listenelement ersetzt");
                } else if (change.wasUpdated()) {
                    Plan planToUpdate = change.getList().get(change.getFrom());
                    DbManager.getInstance().update(planToUpdate);
                } else if (change.wasRemoved()) {
                    Plan planToDelete = change.getRemoved().get(0);
                    DbManager.getInstance().delete(planToDelete);
                    plansAll.clear();
                    plansAll.addAll(plans);
                } else if (change.wasAdded()) {
                    Plan planToInsert = change.getAddedSubList().get(0);
                    DbManager.getInstance().insert(planToInsert);
                    plansAll.clear();
                    plansAll.addAll(plans);
                } else if (change.wasPermutated()) {
                    System.out.println("Reihenfolge der Liste geändert");
                }
            }
        };

        activateListener();
    }
    //endregion

    //region Methoden
    /**
     * Gibt die Singleton-Instanz des PlanHolders zurück.
     *
     * @return PlanHolder-Instanz
     */
    public static synchronized PlanHolder getInstance() {
        if (instance == null) {
            instance = new PlanHolder();
        }
        return instance;
    }

    /**
     * Gibt die ObservableList mit den Trainingsplänen zurück.
     *
     * @return ObservableList von Plan-Objekten
     */
    public ObservableList<Plan> getPlans() {
        deactivateListener();
        plans.clear();
        plans.addAll(plansAll);
        activateListener();
        return plans;
    }

    /**
     * Aktualisiert einen Trainingsplan in der Liste und in der Datenbank.
     *
     * @param plan der zu aktualisierende Trainingsplan
     */
    public void update(Plan plan) {
        int index = plans.indexOf(plan);
        if (index != -1) {
            plans.set(index, plan);
            DbManager.getInstance().update(plan);
        }
    }

    /**
     * Löscht einen Trainingsplan aus der Liste und aus der Datenbank.
     *
     * @param plan der zu löschende Trainingsplan
     */
    public void delete(Plan plan) {
        plans.remove(plan);
        DbManager.getInstance().delete(plan);
    }

    /**
     * Gibt den Trainingsplan anhand der Position zurück.
     *
     * @param position die Position des Trainingsplans
     * @return der Trainingsplan
     */
    public Plan getPlanByPosition(int position) {
        for (Plan plan : plans) {
            if (plan.getPosition() == position) {
                return plan;
            }
        }
        return null;
    }

    /**
     * Sortiert die Trainingspläne nach ihrer Position.
     */
    public void sortByPosition() {
        plans.sort((firstPlan, secondPlan) ->
                firstPlan.getPosition() - secondPlan.getPosition());
    }

    /**
     * Aktualisiert die Trainingspläne durch erneutes Auslesen aus der Datenbank.
     *
     * @return die aktualisierte ObservableList der Trainingspläne
     */
    public ObservableList<Plan> reload() {
        deactivateListener();
        plans.clear();
        plans.addAll(DbManager.getInstance().readAllPlans());
        activateListener();
        return plans;
    }

    /**
     * Sucht nach Trainingsplänen, die den angegebenen Suchbegriff enthalten.
     *
     * @param searchString der Suchbegriff
     * @return die gefilterte ObservableList der Trainingspläne
     */
    public ObservableList<Plan> search(String searchString) {
        deactivateListener();
        plans.clear();

        if (searchString.length() > 0) {
            List<Plan> filteredList = new ArrayList<>();
            for (Plan plan : plansAll) {
                String searchTarget = plan.toString();
                if (searchTarget != null && searchTarget.toLowerCase().contains(searchString.toLowerCase())) {
                    filteredList.add(plan);
                }
            }
            plans.addAll(filteredList);
        } else {
            plans.addAll(plansAll);
        }

        activateListener();
        return plans;
    }

    private void activateListener() {
        plans.addListener(listener);
    }

    private void deactivateListener() {
        plans.removeListener(listener);
    }
    //endregion
}
