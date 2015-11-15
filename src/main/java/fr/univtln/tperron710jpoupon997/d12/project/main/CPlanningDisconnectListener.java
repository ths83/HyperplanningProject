package fr.univtln.tperron710jpoupon997.d12.project.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jeremypoupon on 03/11/15.
 */

/**
 * Contrôleur sur le bouton de déconnexion
 */
public class CPlanningDisconnectListener implements ActionListener {

    /**
     * Le modèle à notifier
     */
    private CPlanningModel model = null;

    public CPlanningDisconnectListener(CPlanningModel model) {
        this.model = model;
    }

    /**
     * Retourne le modèle
     * @return le modèle
     */
    public CPlanningModel getModel() {
        return model;
    }

    /**
     * Notifie le modèle lorsqu'une action (ici un clic) est effectuée
     * @param e l'action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        model.disconnect();
    }
}
