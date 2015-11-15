package fr.univtln.tperron710.requestPackage.person.form;

import fr.univtln.tperron710.requestPackage.request.sent.CRequestSentModel;
import fr.univtln.tperron710jpoupon997.d12.project.EModelMessage;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * Created by toms on 04/11/15.
 */

/**
 * Formulaire de report de cours
 */
public class CPersonFormReportLessonView extends JPanel implements Observer {

    /**
     * Taille standard des champs de texte
     */
    private int textFieldsSize = 20;
    /**
     * Taille des champs numériques
     */
    private int numericFieldsSize = 2;
    /**
     * Champ de texte contenant le prénom
     */
    JTextField reportFirstName = new JTextField(textFieldsSize);
    /**
     * Champ de texte contenant le nom
     */
    JTextField reportLastName = new JTextField(textFieldsSize);
    /**
     * Champ de texte contenant le login
     */
    JTextField reportLogin = new JTextField(textFieldsSize);
    /**
     * Champ de texte contenant le jour initial
     */
    JTextField reportInitialDay = new JTextField(numericFieldsSize);
    /**
     * Champ de texte contenant le mois initial
     */
    JTextField reportInitialMonth = new JTextField(numericFieldsSize);
    /**
     * Champ de texte contenant l'année initiale
     */
    JTextField reportInitialYear = new JTextField(numericFieldsSize);
    /**
     * Champ de texte contenant le l'heure de début initiale
     */
    JTextField reportInitialBeginHour = new JTextField(numericFieldsSize);
    /**
     * Champ de texte contenant les minutes de début initiales
     */
    JTextField reportInitialBeginMinutes = new JTextField(numericFieldsSize);
    /**
     * Champ de texte contenant l'heure de fin initiale
     */
    JTextField reportInitialEndHour = new JTextField(numericFieldsSize);
    /**
     * Champ de texte contenant les minutes de fin initiales
     */
    JTextField reportInitialEndMinutes = new JTextField(numericFieldsSize);
    /**
     * Champ de texte contenant le jour final
     */
    JTextField reportFinalDay = new JTextField(numericFieldsSize);
    /**
     * Champ de texte contenant le mois final
     */
    JTextField reportFinalMonth = new JTextField(numericFieldsSize);
    /**
     * Champ de texte contenant l'année finale
     */
    JTextField reportFinalYear = new JTextField(numericFieldsSize);
    /**
     * Champ de texte contenant l'heure de début finale
     */
    JTextField reportFinalBeginHour = new JTextField(numericFieldsSize);
    /**
     * Champ de texte contenant les minutes de début finales
     */
    JTextField reportFinalBeginMinutes = new JTextField(numericFieldsSize);
    /**
     * Champ de texte contenant l'heure de fin finale
     */
    JTextField reportFinalEndHour = new JTextField(numericFieldsSize);
    /**
     * Champ de texte contenant les minutes de fin finales
     */
    JTextField reportFinalEndMinutes = new JTextField(numericFieldsSize);
    /**
     * Bouton de validation
     */
    JButton reportValidate = new JButton("Demander le report");

    public CPersonFormReportLessonView(JTabbedPane pane, CRequestSentModel model) {
        setLayout(new GridBagLayout());
        initialize(pane, model);
    }

    /**
     * Initialise la vue
     * @param pane onglet auquel sera ajouté la vue
     * @param model le modèle observé
     */
    public void initialize(JTabbedPane pane, CRequestSentModel model) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;


        // Prénom
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        JLabel jLabel1 = new JLabel("Prénom");
        add(jLabel1, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        add(reportFirstName, gridBagConstraints);

        // Nom
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        JLabel jLabel2 = new JLabel("Nom");
        add(jLabel2, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        add(reportLastName, gridBagConstraints);

        // Login
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        JLabel jLabel3 = new JLabel("Login");
        add(jLabel3, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        add(reportLogin, gridBagConstraints);

        // Jour initial
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        JLabel jLabel4 = new JLabel("Jour initial");
        add(jLabel4, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(reportInitialDay, gridBagConstraints);

        // Mois initial
        gridBagConstraints.gridx = 2;
        JLabel jLabel5 = new JLabel("Mois initial");
        add(jLabel5, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        add(reportInitialMonth, gridBagConstraints);

        // Année initiale
        gridBagConstraints.gridx = 4;
        JLabel jLabel6 = new JLabel("Année initiale");
        add(jLabel6, gridBagConstraints);

        gridBagConstraints.gridx = 5;
        add(reportInitialYear, gridBagConstraints);

        // Heure de début initiale
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        JLabel jLabel7 = new JLabel("Heure de début initiale");
        add(jLabel7, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(reportInitialBeginHour, gridBagConstraints);

        // Minutes de début initiales
        gridBagConstraints.gridx = 2;
        JLabel jLabel8 = new JLabel("Minute de début initiale");
        add(jLabel8, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        add(reportInitialBeginMinutes, gridBagConstraints);

        // Heure de fin initiale
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        JLabel jLabel9 = new JLabel("Heure de fin initiale");
        add(jLabel9, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(reportInitialEndHour, gridBagConstraints);

        // Minutes de fin initiales
        gridBagConstraints.gridx = 2;
        JLabel jLabel10 = new JLabel("Minute de fin initiale");
        add(jLabel10, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        add(reportInitialEndMinutes, gridBagConstraints);

        // Jour final
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        JLabel jLabel11 = new JLabel("Jour final");
        add(jLabel11, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(reportFinalDay, gridBagConstraints);

        // Mois final
        gridBagConstraints.gridx = 2;
        JLabel jLabel12 = new JLabel("Mois final");
        add(jLabel12, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        add(reportFinalMonth, gridBagConstraints);

        // Année finale
        gridBagConstraints.gridx = 4;
        JLabel jLabel13 = new JLabel("Année finale");
        add(jLabel13, gridBagConstraints);

        gridBagConstraints.gridx = 5;
        add(reportFinalYear, gridBagConstraints);

        // Heure de début finale
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        JLabel jLabel14 = new JLabel("Heure de début finale");
        add(jLabel14, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(reportFinalBeginHour, gridBagConstraints);

        // Minutes de début finales
        gridBagConstraints.gridx = 2;
        JLabel jLabel15 = new JLabel("Minute de début finale");
        add(jLabel15, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        add(reportFinalBeginMinutes, gridBagConstraints);

        // Heure de fin finale
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        JLabel jLabel16 = new JLabel("Heure de fin finale");
        add(jLabel16, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(reportFinalEndHour, gridBagConstraints);

        // Minutes de fin finales
        gridBagConstraints.gridx = 2;
        JLabel jLabel17 = new JLabel("Minute de fin finale");
        add(jLabel17, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        add(reportFinalEndMinutes, gridBagConstraints);

        // Bouton de validation
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        reportValidate.addActionListener(new CPersonFormReportListener(this, model));
        add(reportValidate,gridBagConstraints);

       pane.addTab("Report de cours",this);
    }

    /**
     * Retourne le bouton de validation
     * @return le bouton de validation
     */
    public JButton getReportValidate() {
        return reportValidate;
    }

    /**
     * Retourne les minutes de fin finales
     * @return les minutes de fin finales
     */
    public JTextField getReportFinalEndMinutes() {
        return reportFinalEndMinutes;
    }

    /**
     * Retourne l'heure de fin finale
     * @return l'heure de fin finale
     */
    public JTextField getReportFinalEndHour() {
        return reportFinalEndHour;
    }

    /**
     * Retourne les minutes de début finales
     * @return les minutes de début finales
     */
    public JTextField getReportFinalBeginMinutes() {
        return reportFinalBeginMinutes;
    }

    /**
     * Retourne l'heure de début finale
     * @return l'heure de début finale
     */
    public JTextField getReportFinalBeginHour() {
        return reportFinalBeginHour;
    }

    /**
     * Retourne l'année finale
     * @return l'année finale
     */
    public JTextField getReportFinalYear() {
        return reportFinalYear;
    }

    /**
     * Retourne le mois final
     * @return le mois final
     */
    public JTextField getReportFinalMonth() {
        return reportFinalMonth;
    }

    /**
     * Retourn le jour final
     * @return le jour final
     */
    public JTextField getReportFinalDay() {
        return reportFinalDay;
    }

    /**
     * Retourne les minutes de fin initiales
     * @return les minutes de fin initiales
     */
    public JTextField getReportInitialEndMinutes() {
        return reportInitialEndMinutes;
    }

    /**
     * Retourne l'heure de fin initiale
     * @return l'heure de fin initiale
     */
    public JTextField getReportInitialEndHour() {
        return reportInitialEndHour;
    }

    /**
     * Retourne les minutes de début initiales
     * @return les minutes de début initiales
     */
    public JTextField getReportInitialBeginMinutes() {
        return reportInitialBeginMinutes;
    }

    /**
     * Retourne l'heure de début initiale
     * @return l'heure de début initiale
     */
    public JTextField getReportInitialBeginHour() {
        return reportInitialBeginHour;
    }

    /**
     * Retourne l'année initiale
     * @return l'année initiale
     */
    public JTextField getReportInitialYear() {
        return reportInitialYear;
    }

    /**
     * Retourne le mois initial
     * @return le mois initial
     */
    public JTextField getReportInitialMonth() {
        return reportInitialMonth;
    }

    /**
     * Retourne le jour initial
     * @return le jour initial
     */
    public JTextField getReportInitialDay() {
        return reportInitialDay;
    }

    /**
     * Retourne le login
     * @return le login
     */
    public JTextField getReportLogin() {
        return reportLogin;
    }

    /**
     * Retourne le nom
     * @return le nom
     */
    public JTextField getReportLastName() {
        return reportLastName;
    }

    /**
     * Retourne le prénom
     * @return le prénom
     */
    public JTextField getReportFirstName() {
        return reportFirstName;
    }

    /**
     * Mise à jour de la vue
     * @param o modèle à l'origine de la notification
     * @param arg argument facultatif influant sur la mise à jour
     */
    @Override
    public void update(Observable o, Object arg) {
        // Si l'argument est un vecteur, on met à jour les champs
        if (arg instanceof Vector)
        {
            reportFirstName.setText((String) ((Vector) arg).get(0));
            reportLastName.setText((String) ((Vector) arg).get(1));
            reportLogin.setText((String) ((Vector) arg).get(2));
        }
        // Si l'argument est un message, on vide les champs
        else if (arg instanceof EModelMessage)
        {
            if (arg.equals(EModelMessage.REQUETE_ENVOYEE))
            {
                reportFirstName.setText("");
                reportLastName.setText("");
                reportLogin.setText("");
                reportInitialDay.setText("");
                reportInitialMonth.setText("");
                reportInitialYear.setText("");
                reportInitialBeginHour.setText("");
                reportInitialBeginMinutes.setText("");
                reportInitialEndHour.setText("");
                reportInitialEndMinutes.setText("");
                reportFinalDay.setText("");
                reportFinalMonth.setText("");
                reportFinalYear.setText("");
                reportFinalBeginHour.setText("");
                reportFinalBeginMinutes.setText("");
                reportFinalEndHour.setText("");
                reportFinalEndMinutes.setText("");
            }
        }
    }
}
