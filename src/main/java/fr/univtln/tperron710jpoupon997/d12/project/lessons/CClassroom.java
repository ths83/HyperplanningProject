package fr.univtln.tperron710jpoupon997.d12.project.lessons;

/**
 * Created by jeremypoupon on 12/10/15.
 */

/**
 * Salle de cours
 */
public class CClassroom {

    /**
     * Bâtiment
     */
    private String building = "";

    /**
     * Numéro
     */
    private int numero = 0;

    /**
     * Capacité
     */
    private int capacity = 0;

    public CClassroom(String building, int numero, int capacity) {
        this.building = building;
        this.numero = numero;
        this.capacity = capacity;
    }

    /**
     * Retourne le bâtiment
     * @return le bâtiment
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Retourne le numéro
     * @return le numéro
     */
    public int getNumero() {
        return numero;
    }

}
