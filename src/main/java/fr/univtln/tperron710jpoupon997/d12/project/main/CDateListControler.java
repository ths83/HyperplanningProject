package fr.univtln.tperron710jpoupon997.d12.project.main;

import fr.univtln.tperron710jpoupon997.d12.project.actors.CChiefOfDepartment;
import fr.univtln.tperron710jpoupon997.d12.project.actors.CPlanningAdministrator;
import fr.univtln.tperron710jpoupon997.d12.project.actors.CStudent;
import fr.univtln.tperron710jpoupon997.d12.project.actors.CTeacher;
import fr.univtln.tperron710jpoupon997.d12.project.university.CPromotion;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jeremypoupon on 28/10/15.
 */

/**
 * Contrôleur pour la liste des dates
 */
public class CDateListControler implements ItemListener {

    /**
     * Modèle à notifier
     */
    private CPlanningModel model = null;

    /**
     * Vue d'où vient la notification
     */
    private CPlanningView view = null;

    public CDateListControler(CPlanningView view, CPlanningModel model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Notifie le modèle lorsqu'une action (ici un changement d'élément) est effectuée
     * @param e l'action
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED)
        {
            Object o = e.getItem();
            if (o instanceof String)
            {
                String[] strings = ((String) o).split(" ");
                String[] dateElements = strings[4].split("/");
                Calendar calendar = Calendar.getInstance();
                calendar.set(Integer.parseInt(dateElements[2]),Integer.parseInt(dateElements[1])-1,Integer.parseInt(dateElements[0]));
                Date date = calendar.getTime();
                Object item = view.getPlanningList().getSelectedItem();
                if (item instanceof String)
                {
                    if (model.getPerson() instanceof CStudent)
                    {
                        if (item.equals("Promotion"))
                            model.setPromotionPlanning(date,((CStudent) model.getPerson()).getPromotion().getId());
                        else
                        {
                            String building = "";
                            String numero = "";
                            for (char c : ((String) item).toCharArray())
                            {
                                if (Character.isDigit(c))
                                    numero = numero + c;
                                else
                                    building = building + c;
                            }
                            model.setClassroomPlanning(date,Integer.parseInt(numero),building);
                        }
                    }
                    else if (model.getPerson() instanceof CTeacher)
                    {
                        String[] promotionElements = ((String) item).split(" ");
                        for (CPromotion p : ((CTeacher) model.getPerson()).getPromotions())
                        {
                            String formation = "";
                            if (promotionElements.length > 2)
                            {
                                for (int i = 1 ; i < promotionElements.length ; i++)
                                {
                                    if (i < 2)
                                        formation += promotionElements[i];
                                    else
                                        formation += " " + promotionElements[i];
                                }
                            }
                            else
                                formation = promotionElements[1];
                            if ((p.getGrade().equals(promotionElements[0])) && (p.getFormation().equals(formation)))
                                model.setPromotionPlanning(date,p.getId());
                        }
                    }
                    else if (model.getPerson() instanceof CChiefOfDepartment)
                    {
                        String[] promotionElements = ((String) item).split(" ");
                        if (promotionElements.length > 1)
                        {
                            for (CPromotion p : ((CChiefOfDepartment) model.getPerson()).getPromotions())
                            {
                                if ((p.getGrade().equals(promotionElements[0])) && (p.getFormation().equals(promotionElements[1])))
                                    model.setPromotionPlanning(date,p.getId());
                            }
                        }
                        else
                        {
                            String building = "";
                            String numero = "";
                            for (char c : ((String) item).toCharArray())
                            {
                                if (Character.isDigit(c))
                                    numero = numero + c;
                                else
                                    building = building + c;
                            }
                            model.setClassroomPlanning(date,Integer.parseInt(numero),building);
                        }
                    }
                    else if (model.getPerson() instanceof CPlanningAdministrator)
                    {
                        String[] promotionElements = ((String) item).split(" ");
                        if (promotionElements.length > 1)
                        {
                            for (CPromotion p : ((CPlanningAdministrator) model.getPerson()).getPromotions())
                            {
                                if ((p.getGrade().equals(promotionElements[0])) && (p.getFormation().equals(promotionElements[1])))
                                    model.setPromotionPlanning(date,p.getId());
                            }
                        }
                        else
                        {
                            String building = "";
                            String numero = "";
                            for (char c : ((String) item).toCharArray())
                            {
                                if (Character.isDigit(c))
                                    numero = numero + c;
                                else
                                    building = building + c;
                            }
                            model.setClassroomPlanning(date,Integer.parseInt(numero),building);
                        }
                    }
                }
            }
        }
    }
}
