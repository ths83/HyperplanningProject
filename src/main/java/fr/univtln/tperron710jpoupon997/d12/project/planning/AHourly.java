package fr.univtln.tperron710jpoupon997.d12.project.planning;

/**
 * Created by jeremypoupon on 12/10/15.
 */

/**
 * Entité représentant un créneau horaire
 */
public abstract class AHourly {

    /**
     * Heure de début
     */
    protected String beginHour = null;

    /**
     * Heure de fin
     */
    protected String endHour = null;

    public AHourly(String beginHour, String endHour) {
        this.beginHour = beginHour;
        this.endHour = endHour;
    }
}
