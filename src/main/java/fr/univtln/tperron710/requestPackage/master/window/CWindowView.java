package fr.univtln.tperron710.requestPackage.master.window;

import fr.univtln.tperron710.requestPackage.person.database.CDatabaseView;
import fr.univtln.tperron710.requestPackage.request.received.CRequestView;
import fr.univtln.tperron710.requestPackage.person.database.CTablePersonModel;
import fr.univtln.tperron710.requestPackage.person.form.CPersonFormView;
import fr.univtln.tperron710.requestPackage.request.sent.CRequestSentModel;
import fr.univtln.tperron710jpoupon997.d12.project.main.CPlanningModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by toms on 18/10/15.
 */

/**
 * Onglet principal pour les requêtes
 */
public class CWindowView extends JPanel{

    /**
     * Vue de la table des contacts
     */
    CDatabaseView CDatabaseView = null;

    public CWindowView(CPlanningModel model) throws Exception {
        CTablePersonModel CTablePersonModel = new CTablePersonModel(model);
        CDatabaseView = new CDatabaseView(model, CTablePersonModel);
        CRequestView requestReceivedView = new CRequestView();
        CRequestSentModel CRequestSentModel = new CRequestSentModel();
        CRequestSentModel.addObserver(requestReceivedView);
        CPersonFormView CPersonFormView = new CPersonFormView(CRequestSentModel, requestReceivedView, model);
        View(CDatabaseView, CPersonFormView, requestReceivedView);
        CDatabaseView.getCPersonFormModel().addObserver(CPersonFormView);
    }

    /**
     * Initialisation de la vue
     * @param CDatabaseView table des contacts à ajouter
     * @param CPersonFormView formulaire des requêtes
     * @param requestReceivedView liste des requêtes
     */
    public void View(CDatabaseView CDatabaseView, CPersonFormView CPersonFormView, CRequestView requestReceivedView){

        setLayout(new GridLayout(1,2));
        JPanel westPanel = new JPanel(new BorderLayout());
        JPanel eastPanel = new JPanel(new BorderLayout());
        westPanel.add(CDatabaseView, BorderLayout.CENTER);
        eastPanel.add(CPersonFormView,BorderLayout.NORTH);
        eastPanel.add(requestReceivedView,BorderLayout.CENTER);
        add(westPanel);
        add(eastPanel);
        setVisible(true);
    }
}
