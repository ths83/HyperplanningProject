package fr.univtln.tperron710.requestPackage.person.form;

import fr.univtln.tperron710.requestPackage.request.received.CRequestView;
import fr.univtln.tperron710.requestPackage.request.sent.CRequestSentModel;
import fr.univtln.tperron710jpoupon997.d12.project.actors.CChiefOfDepartment;
import fr.univtln.tperron710jpoupon997.d12.project.actors.CPlanningAdministrator;
import fr.univtln.tperron710jpoupon997.d12.project.actors.CResponsible;
import fr.univtln.tperron710jpoupon997.d12.project.main.CPlanningModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * Created by toms on 19/10/15.
 */

/**
 * Vue principale pour l'envoi des requêtes
 */
public class CPersonFormView extends JPanel implements Observer{

    /**
     * Vue pour le report de cours
     */
    private CPersonFormReportLessonView reportLessonView = null;
    /**
     * Vue pour l'ajout de cours
     */
    private CPersonFormAddLessonView addLessonView = null;
    /**
     * Vue pour la suppression de cours
     */
    private CPersonFormRemoveLessonView removeLessonView = null;
    /**
     * Vue pour le blocage de période
     */
    private CPersonFormBlockPeriodView blockPeriodView = null;
    /**
     * Vue pour la réponse aux requêtes
     */
    private CPersonFormAnswerView answerView = null;
    /**
     * Modèle observé
     */
    private CPlanningModel model = null;

    public CPersonFormView(CRequestSentModel CRequestSentModel, CRequestView view, CPlanningModel model) throws Exception {
        this.model = model;
        setLayout(new BorderLayout());
        JTabbedPane jTabbedPane = new JTabbedPane();
        // On crée les vues selon le rôle de la personne connectée
        if (!(model.getPerson() instanceof CPlanningAdministrator))
        {
            reportLessonView = new CPersonFormReportLessonView(jTabbedPane, CRequestSentModel);
            CRequestSentModel.addObserver(reportLessonView);
            addLessonView = new CPersonFormAddLessonView(jTabbedPane, CRequestSentModel);
            CRequestSentModel.addObserver(addLessonView);
            removeLessonView = new CPersonFormRemoveLessonView(jTabbedPane, CRequestSentModel);
            CRequestSentModel.addObserver(removeLessonView);
            if ((model.getPerson() instanceof CResponsible) || (model.getPerson() instanceof CChiefOfDepartment))
            {
                if (model.getPerson() instanceof CChiefOfDepartment)
                {
                    blockPeriodView = new CPersonFormBlockPeriodView(jTabbedPane, CRequestSentModel);
                    CRequestSentModel.addObserver(blockPeriodView);
                }
                answerView = new CPersonFormAnswerView(jTabbedPane, CRequestSentModel);
                view.getModel().addObserver(answerView);
                CRequestSentModel.addObserver(answerView);
            }
        }
        else
        {
            answerView = new CPersonFormAnswerView(jTabbedPane, CRequestSentModel);
            view.getModel().addObserver(answerView);
            CRequestSentModel.addObserver(answerView);
        }
        this.add(jTabbedPane,BorderLayout.CENTER);
        this.setBackground(Color.white);
    }

    /**
     * Mise à jour de la vue
     * @param o modèle à l'origine de cette notificatin
     * @param arg argument facultatif influant sur la mise à jour
     */
    public void update(Observable o, Object arg) {
        // Si l'argument est un vecteur, on met à jour les vues
        if (arg instanceof Vector){
            if (!(model.getPerson() instanceof CPlanningAdministrator))
            {
                reportLessonView.update(null,arg);
                addLessonView.update(null,arg);
                removeLessonView.update(null,arg);
                if ((model.getPerson() instanceof CResponsible) || (model.getPerson() instanceof CChiefOfDepartment))
                {
                    if (model.getPerson() instanceof CChiefOfDepartment)
                        blockPeriodView.update(null,arg);
                }
            }
        }
    }
 }
