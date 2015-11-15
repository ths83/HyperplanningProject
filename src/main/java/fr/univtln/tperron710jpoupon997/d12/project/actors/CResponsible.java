package fr.univtln.tperron710jpoupon997.d12.project.actors;

import fr.univtln.tperron710jpoupon997.d12.project.university.*;

import java.util.*;

/**
 * Created by jeremypoupon on 12/10/15.
 */

/**
 * Entité représentant un responsable de formation
 */
public class CResponsible extends CTeacher {

    /**
     * Formations que le responsable supervise
     */
    private List<CPromotion> responsiblePromotions = null;

    public CResponsible(String firstName, String lastName, Date dateOfBirth, String login, String password,
                        int teacherId, List<String> subjects, List<CPromotion> promotions,
                        List<CPromotion> responsiblePromotions) {
        super(firstName, lastName, dateOfBirth, login, password, teacherId);
        this.subjects = subjects;
        this.promotions = promotions;
        this.responsiblePromotions = responsiblePromotions;
    }

    /**
     * Description textuelle
     * @return description textuelle
     */
    @Override
    public String toString() {
        return "CResponsible{" + super.toString() +
                "responsiblePromotions=" + responsiblePromotions +
                '}';
    }
}
