package fr.univtln.tperron710.requestPackage.request.received;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by toms on 25/10/15.
 */

/**
 * Contrôleur pour les requêtes envoyées
 */
public class CSendedRequestMouseAdapter extends MouseAdapter {

    /**
     * Le modèle à notifier
     */
    private CRequestModel model = null;

    public CSendedRequestMouseAdapter(CRequestModel model) {
        this.model = model;
    }

    /**
     * Notifie le modèle lorsqu'un clic est effectué
     * @param e le clic
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            JTable jTable = (JTable) e.getSource();
            int row = jTable.rowAtPoint(e.getPoint());
            try {
                model.getSendedRequest(row);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
