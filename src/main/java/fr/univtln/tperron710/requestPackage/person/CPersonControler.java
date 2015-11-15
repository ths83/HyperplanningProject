package fr.univtln.tperron710.requestPackage.person;

import fr.univtln.tperron710.requestPackage.person.form.CPersonFormModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by toms on 19/10/15.
 */

/**
 * Contrôleur pour la table des contacts
 */
public class CPersonControler extends MouseAdapter {

    /**
     * Modèle à notifier
     */
    CPersonFormModel CPersonFormModel = null;

    public CPersonControler(CPersonFormModel CPersonFormModel) throws Exception {
        this.CPersonFormModel = CPersonFormModel;
    }


    /**
     * Notifie le modèle lorsqu'un clic est effectué sur la zone
     * @param e le clic
     */
    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount() == 1) {
            JTable jTable = (JTable) e.getSource();
            int row = jTable.rowAtPoint(e.getPoint());
            String login = (String)jTable.getModel().getValueAt(row,3);
            try {
                CPersonFormModel.form(login);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
