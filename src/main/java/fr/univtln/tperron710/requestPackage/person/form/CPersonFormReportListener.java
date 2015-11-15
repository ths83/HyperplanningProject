package fr.univtln.tperron710.requestPackage.person.form;

import fr.univtln.tperron710.requestPackage.request.sent.CRequestSentModel;
import fr.univtln.tperron710jpoupon997.d12.project.connection.CConnectionButtonControler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by toms on 04/11/15.
 */
public class CPersonFormReportListener implements ActionListener {

    CPersonFormReportLessonView view = null;

    CRequestSentModel model = null;

    public CPersonFormReportListener(CPersonFormReportLessonView view, CRequestSentModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!view.getReportFinalBeginHour().getText().equals("")
                && !view.getReportFinalBeginMinutes().getText().equals("")
                && !view.getReportFinalDay().getText().equals("")
                && !view.getReportFinalEndHour().getText().equals("")
                && !view.getReportFinalEndMinutes().getText().equals("")
                && !view.getReportFinalMonth().getText().equals("")
                && !view.getReportFinalYear().getText().equals("")
                && !view.getReportFirstName().getText().equals("")
                && !view.getReportInitialBeginHour().getText().equals("")
                && !view.getReportInitialBeginMinutes().getText().equals("")
                && !view.getReportInitialDay().getText().equals("")
                && !view.getReportInitialEndHour().getText().equals("")
                && !view.getReportInitialEndMinutes().getText().equals("")
                && !view.getReportInitialMonth().getText().equals("")
                && !view.getReportInitialYear().getText().equals("")
                && !view.getReportLastName().getText().equals("")
                && !view.getReportLogin().getText().equals(""))
        {
            String source = CConnectionButtonControler.getView().getLoginField().getText();
            String recipient = view.getReportLogin().getText();
            String message = "reporter#";
            message += view.getReportInitialDay().getText() + "#" +
                    view.getReportInitialMonth().getText() + "#" +
                    view.getReportInitialYear().getText() + "#" +
                    view.getReportInitialBeginHour().getText() + "#" +
                    view.getReportInitialBeginMinutes().getText() + "#" +
                    view.getReportInitialEndHour().getText() + "#" +
                    view.getReportInitialEndMinutes().getText() + "#" +
                    view.getReportFinalDay().getText() + "#" +
                    view.getReportFinalMonth().getText() + "#" +
                    view.getReportFinalYear().getText() + "#" +
                    view.getReportFinalBeginHour().getText() + "#" +
                    view.getReportFinalBeginMinutes().getText() + "#" +
                    view.getReportFinalEndHour().getText() + "#" +
                    view.getReportFinalEndMinutes().getText();
            try {
                model.sendRequest(message,source,recipient);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
