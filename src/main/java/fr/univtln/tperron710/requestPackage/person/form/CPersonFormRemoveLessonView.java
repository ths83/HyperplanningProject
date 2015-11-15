package fr.univtln.tperron710.requestPackage.person.form;

import fr.univtln.tperron710.requestPackage.request.sent.CRequestSentModel;
import fr.univtln.tperron710jpoupon997.d12.project.EModelMessage;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * Created by toms on 02/11/15.
 */

/**
 * Formulaire de suppression de cours
 */
public class CPersonFormRemoveLessonView extends JPanel implements Observer {

    /**
     * Taille standard des champs de texte
     */
    private int textFieldsSize = 20;
    /**
     * Taille standard des champs numériques
     */
    private int numericFieldsSize = 2;
    /**
     * Champ contenant le prénom
     */
    JTextField removeFirstName = new JTextField(textFieldsSize);
    /**
     * Champ contenant le nom
     */
    JTextField removeLastName = new JTextField(textFieldsSize);
    /**
     * Champ contenant le login
     */
    JTextField removeLogin = new JTextField(textFieldsSize);
    /**
     * Champ contenant le jour
     */
    JTextField removeDay = new JTextField(numericFieldsSize);
    /**
     * Champ contenant le mois
     */
    JTextField removeMonth = new JTextField(numericFieldsSize);
    /**
     * Champ contenant l'année
     */
    JTextField removeYear = new JTextField(numericFieldsSize);
    /**
     * Champ contenant l'heure de début
     */
    JTextField removeBeginHour = new JTextField(numericFieldsSize);
    /**
     * Champ contenant les minutes de début
     */
    JTextField removeBeginMinutes = new JTextField(numericFieldsSize);
    /**
     * Champ contenant l'heure de fin
     */
    JTextField removeEndHour = new JTextField(numericFieldsSize);
    /**
     * Champ contenant les minutes de fin
     */
    JTextField removeEndMinutes = new JTextField(numericFieldsSize);
    /**
     * Bouton de validation
     */
    JButton removeValidate = new JButton("Demander la suppression");

    public CPersonFormRemoveLessonView(JTabbedPane pane, CRequestSentModel model) {
        setLayout(new GridBagLayout());
        initialize(pane, model);
    }

    /**
     * Initialisation de la vue
     * @param pane onglet auquel sera ajoutée la vue
     * @param model modèle observé
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
        add(removeFirstName, gridBagConstraints);

        // Nom
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        JLabel jLabel2 = new JLabel("Nom");
        add(jLabel2, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        add(removeLastName, gridBagConstraints);

        // Login
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        JLabel jLabel3 = new JLabel("Login");
        add(jLabel3, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        add(removeLogin, gridBagConstraints);

        // Jour
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        JLabel jLabel4 = new JLabel("Jour");
        add(jLabel4, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(removeDay, gridBagConstraints);

        // Mois
        gridBagConstraints.gridx = 2;
        JLabel jLabel5 = new JLabel("Mois");
        add(jLabel5, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        add(removeMonth, gridBagConstraints);

        // Année
        gridBagConstraints.gridx = 4;
        JLabel jLabel6 = new JLabel("Année");
        add(jLabel6, gridBagConstraints);

        gridBagConstraints.gridx = 5;
        add(removeYear, gridBagConstraints);

        // Heure de début
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        JLabel jLabel7 = new JLabel("Heure de début");
        add(jLabel7, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(removeBeginHour, gridBagConstraints);

        // Minute de début
        gridBagConstraints.gridx = 2;
        JLabel jLabel8 = new JLabel("Minute de début");
        add(jLabel8, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        add(removeBeginMinutes, gridBagConstraints);

        // Heure de fin
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        JLabel jLabel9 = new JLabel("Heure de fin");
        add(jLabel9, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(removeEndHour, gridBagConstraints);

        // Minutes de fin
        gridBagConstraints.gridx = 2;
        JLabel jLabel10 = new JLabel("Minute de fin");
        add(jLabel10, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        add(removeEndMinutes, gridBagConstraints);

        // Bouton de validation
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        removeValidate.addActionListener(new CPersonFormRemoveListener(this,model));
        add(removeValidate,gridBagConstraints);

        pane.addTab("Suppression de cours",this);
    }


    /**
     * Mise à jour des champs
     * @param o modèle à l'origine de la notification
     * @param arg argument facultatif influant sur l'action
     */
    @Override
    public void update(Observable o, Object arg) {
        // Si l'argument est un vecteur, on remplit les champs
        if (arg instanceof Vector)
        {
            removeFirstName.setText((String) ((Vector) arg).get(0));
            removeLastName.setText((String) ((Vector) arg).get(1));
            removeLogin.setText((String) ((Vector) arg).get(2));
        }
        // Si l'argument est un message, on vide les champs
        else if (arg instanceof EModelMessage)
        {
            if (arg.equals(EModelMessage.REQUETE_ENVOYEE))
            {
                removeFirstName.setText("");
                removeLastName.setText("");
                removeLogin.setText("");
                removeDay.setText("");
                removeMonth.setText("");
                removeYear.setText("");
                removeBeginHour.setText("");
                removeBeginMinutes.setText("");
                removeEndHour.setText("");
                removeEndMinutes.setText("");
            }
        }
    }

    /**
     * Retourne le prénom
     * @return le prénom
     */
    public JTextField getRemoveFirstName() {
        return removeFirstName;
    }

    /**
     * Retourne le nom
     * @return le nom
     */
    public JTextField getRemoveLastName() {
        return removeLastName;
    }

    /**
     * Retourne le login
     * @return le login
     */
    public JTextField getRemoveLogin() {
        return removeLogin;
    }

    /**
     * Retourne le jour
     * @return le jour
     */
    public JTextField getRemoveDay() {
        return removeDay;
    }

    /**
     * Retourne le mois
     * @return le mois
     */
    public JTextField getRemoveMonth() {
        return removeMonth;
    }

    /**
     * Retourne l'année
     * @return l'année
     */
    public JTextField getRemoveYear() {
        return removeYear;
    }

    /**
     * Retourne l'heure de début
     * @return l'heure de début
     */
    public JTextField getRemoveBeginHour() {
        return removeBeginHour;
    }

    /**
     * Retourne les minutes de début
     * @return les minutes de début
     */
    public JTextField getRemoveBeginMinutes() {
        return removeBeginMinutes;
    }

    /**
     * Retourne l'heure de fin
     * @return l'heure de fin
     */
    public JTextField getRemoveEndHour() {
        return removeEndHour;
    }

    /**
     * Retourne les minutes de fin
     * @return les minutes de fin
     */
    public JTextField getRemoveEndMinutes() {
        return removeEndMinutes;
    }
}
