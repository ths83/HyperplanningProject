package fr.univtln.tperron710.requestPackage.person.form;

import fr.univtln.tperron710.requestPackage.request.sent.CRequestSentModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by toms on 04/11/15.
 */

/**
 * Ecouteur pour le bouton d'acceptation des requêtes
 */
public class CPersonFormAnswerAcceptListener implements ActionListener {

    /**
     * Le formulaire d'administration
     */
    CPersonFormAnswerView view = null;

    /**
     * Le modèle à notifier
     */
    CRequestSentModel model = null;

    public CPersonFormAnswerAcceptListener(CPersonFormAnswerView view, CRequestSentModel model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Notifie le modèle lors d'une action (ici un clic)
     * @param e l'action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!view.getAnswerRequest().getText().equals(""))
        {
            int id = view.getRequestId();
            try {
                model.acceptRequest(id);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
