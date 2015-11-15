package fr.univtln.tperron710.requestPackage.person.form;

import fr.univtln.tperron710.requestPackage.request.sent.CRequestSentModel;
import fr.univtln.tperron710jpoupon997.d12.project.connection.CConnectionButtonControler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by toms on 01/11/15.
 */
public class CPersonFormRemoveListener implements ActionListener {

    CPersonFormRemoveLessonView view = null;

    CRequestSentModel model = null;

    public CPersonFormRemoveListener(CPersonFormRemoveLessonView view, CRequestSentModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!view.getRemoveFirstName().getText().equals("")
                && !view.getRemoveBeginHour().getText().equals("")
                && !view.getRemoveBeginMinutes().getText().equals("")
                && !view.getRemoveDay().getText().equals("")
                && !view.getRemoveEndHour().getText().equals("")
                && !view.getRemoveEndMinutes().getText().equals("")
                && !view.getRemoveMonth().getText().equals("")
                && !view.getRemoveYear().getText().equals("")
                && !view.getRemoveLastName().getText().equals("")
                && !view.getRemoveLogin().getText().equals(""))
        {
            String source = CConnectionButtonControler.getView().getLoginField().getText();
            String recipient = view.getRemoveLogin().getText();
            String message = "supprimer#";
            message += view.getRemoveDay().getText() + "#" +
                    view.getRemoveMonth().getText() + "#" +
                    view.getRemoveYear().getText() + "#" +
                    view.getRemoveBeginHour().getText() + "#" +
                    view.getRemoveBeginMinutes().getText() + "#" +
                    view.getRemoveEndHour().getText() + "#" +
                    view.getRemoveEndMinutes().getText();
            try {
                model.sendRequest(message,source,recipient);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
