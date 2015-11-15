package fr.univtln.tperron710.requestPackage.person.form;

import fr.univtln.tperron710.requestPackage.request.sent.CRequestSentModel;
import fr.univtln.tperron710jpoupon997.d12.project.connection.CConnectionButtonControler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by toms on 04/11/15.
 */

/**
 * Ecouteur pour le blocage de période
 */
public class CPersonFormBlockListener implements ActionListener {

    /**
     * Formulaire de blocage de période
     */
    CPersonFormBlockPeriodView view = null;

    /**
     * Modèle à notifier
     */
    CRequestSentModel model = null;

    public CPersonFormBlockListener(CPersonFormBlockPeriodView view, CRequestSentModel model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Notifie le modèle si une action (ici un clic) est effectuée
     * @param e l'action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!view.getBlockFinalDay().getText().equals("")
                && !view.getBlockFinalMonth().getText().equals("")
                && !view.getBlockFinalYear().getText().equals("")
                && !view.getBlockFirstName().getText().equals("")
                && !view.getBlockInitialDay().getText().equals("")
                && !view.getBlockInitialMonth().getText().equals("")
                && !view.getBlockInitialYear().getText().equals("")
                && !view.getBlockLastName().getText().equals("")
                && !view.getBlockLogin().getText().equals(""))
        {
            String source = CConnectionButtonControler.getView().getLoginField().getText();
            String recipient = view.getBlockLogin().getText();
            String message = "bloquer#";
            message += view.getBlockInitialDay().getText() + "#" +
                    view.getBlockInitialMonth().getText() + "#" +
                    view.getBlockInitialYear().getText() + "#" +
                    view.getBlockFinalDay().getText() + "#" +
                    view.getBlockFinalMonth().getText() + "#" +
                    view.getBlockFinalYear().getText();
            try {
                model.sendRequest(message,source,recipient);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
