package fr.univtln.tperron710.requestPackage.request.received;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by toms on 01/11/15.
 */

/**
 * Contrôleur pour les requêtes reçues
 */
public class CReceivedRequestMouseAdapter extends MouseAdapter {

    /**
     * Modèle à notifier
     */
    private CRequestModel model = null;

    public CReceivedRequestMouseAdapter(CRequestModel model) {
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
                model.getReceivedRequest(row);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
