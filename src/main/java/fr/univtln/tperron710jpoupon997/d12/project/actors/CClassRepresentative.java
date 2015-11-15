package fr.univtln.tperron710jpoupon997.d12.project.actors;

import fr.univtln.tperron710jpoupon997.d12.project.university.*;

import java.util.*;

/**
 * Created by jeremypoupon on 12/10/15.
 */

/**
 * Entité représentant un délégué
 */
public class CClassRepresentative extends CStudent {

    public CClassRepresentative(String firstName, String lastName, Date dateOfBirth, String login, String password, int studentId, CPromotion promotion) {
        super(firstName, lastName, dateOfBirth, login, password, studentId);
        this.promotion = promotion;
    }
}
