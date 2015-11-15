package fr.univtln.tperron710jpoupon997.d12.project.planning;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jeremypoupon on 22/10/15.
 */

/**
 * Planning de cours
 */
public class CLessonHourlyPlanning extends CPlanning {

    /**
     * Liste des créneaux
     */
    private List<List<CLessonHourly>> hourlies = new ArrayList<>();

    /**
     * Retourne les créneaux
     * @return les créneaux
     */
    public List<List<CLessonHourly>> getHourlies() {
        return hourlies;
    }

    /**
     * Définit les créneaux
     * @param hourlies les créneaux
     */
    public void setHourlies(List<List<CLessonHourly>> hourlies) {
        this.hourlies = hourlies;
    }
}
