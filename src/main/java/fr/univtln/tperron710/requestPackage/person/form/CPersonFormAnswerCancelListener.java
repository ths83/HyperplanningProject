package fr.univtln.tperron710.requestPackage.person.form;

import fr.univtln.tperron710.requestPackage.request.sent.CRequestSentModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by toms on 04/11/15.
 */
public class CPersonFormAnswerCancelListener implements ActionListener {

    CPersonFormAnswerView view = null;

    CRequestSentModel model = null;

    public CPersonFormAnswerCancelListener(CPersonFormAnswerView view, CRequestSentModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!view.getAnswerRequest().getText().equals(""))
        {
            int id = view.getRequestId();
            try {
                model.cancelRequest(id);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
