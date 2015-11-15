package fr.univtln.tperron710.requestPackage.person.form;

import fr.univtln.tperron710.requestPackage.request.sent.CRequestSentModel;
import fr.univtln.tperron710jpoupon997.d12.project.connection.CConnectionButtonControler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by toms on 04/11/15.
 */

/**
 * Ecouteur pour l'ajout d'un cours
 */
public class CPersonFormAddListener implements ActionListener {

    /**
     * Le formulaire
     */
    CPersonFormAddLessonView view = null;

    /**
     * Le modèle à notifier
     */
    CRequestSentModel model = null;

    public CPersonFormAddListener(CPersonFormAddLessonView view, CRequestSentModel model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Notifie le modèle en cas de clic
     * @param e  l'évènement (ici un clic)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!view.getAddFirstName().getText().equals("")
                && !view.getAddBeginHour().getText().equals("")
                && !view.getAddBeginMinutes().getText().equals("")
                && !view.getAddDay().getText().equals("")
                && !view.getAddEndHour().getText().equals("")
                && !view.getAddEndMinutes().getText().equals("")
                && !view.getAddMonth().getText().equals("")
                && !view.getAddYear().getText().equals("")
                && !view.getAddLastName().getText().equals("")
                && !view.getAddLogin().getText().equals("")
                && !view.getAddSubject().getText().equals(""))
        {
            String source = CConnectionButtonControler.getView().getLoginField().getText();
            String recipient = view.getAddLogin().getText();
            String message = "ajouter#";
            message += view.getAddDay().getText() + "#" +
                    view.getAddMonth().getText() + "#" +
                    view.getAddYear().getText() + "#" +
                    view.getAddBeginHour().getText() + "#" +
                    view.getAddBeginMinutes().getText() + "#" +
                    view.getAddEndHour().getText() + "#" +
                    view.getAddEndMinutes().getText() + "#" +
                    view.getAddSubject().getText();
            try {
                model.sendRequest(message,source,recipient);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
