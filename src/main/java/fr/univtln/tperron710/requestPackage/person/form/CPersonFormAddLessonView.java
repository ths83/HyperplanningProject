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
 * View pour les requêtes d'ajout de cours
 */
public class CPersonFormAddLessonView extends JPanel implements Observer {

    /**
     * Taille par défaut des champs de texte
     */
    private int textFieldsSize = 20;
    /**
     * Taille par défaut des champs numériques
     */
    private int numericFieldsSize = 2;
    /**
     * Champ pour le prénom
     */
    JTextField addFirstName = new JTextField(textFieldsSize);
    /**
     * Champ pour le nom
     */
    JTextField addLastName = new JTextField(textFieldsSize);
    /**
     * Champ pour le login
     */
    JTextField addLogin = new JTextField(textFieldsSize);
    /**
     * Champ pour le jour
     */
    JTextField addDay = new JTextField(numericFieldsSize);
    /**
     * Champ pour le mois
     */
    JTextField addMonth = new JTextField(numericFieldsSize);
    /**
     * Champ pour l'année
     */
    JTextField addYear = new JTextField(numericFieldsSize);
    /**
     * Champ pour l'heure de début
     */
    JTextField addBeginHour = new JTextField(numericFieldsSize);
    /**
     * Champ pour les minutes de début
     */
    JTextField addBeginMinutes = new JTextField(numericFieldsSize);
    /**
     * Champ pour l'heure de fin
     */
    JTextField addEndHour = new JTextField(numericFieldsSize);
    /**
     * Champ pour les minutes de fin
     */
    JTextField addEndMinutes = new JTextField(numericFieldsSize);
    /**
     * Champ pour la matière
     */
    JTextField addSubject = new JTextField(numericFieldsSize);
    /**
     * Bouton de validation
     */
    JButton addValidate = new JButton("Demander l'ajout");

    public CPersonFormAddLessonView(JTabbedPane pane, CRequestSentModel model) {
        setLayout(new GridBagLayout());
        initialize(pane, model);
    }

    /**
     * Initialisation de la vue
     * @param pane l'onglet auquel la vue est ajoutée
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
        add(addFirstName, gridBagConstraints);

        // Nom
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        JLabel jLabel2 = new JLabel("Nom");
        add(jLabel2, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        add(addLastName, gridBagConstraints);

        // Login
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        JLabel jLabel3 = new JLabel("Login");
        add(jLabel3, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        add(addLogin, gridBagConstraints);

        // Jour
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        JLabel jLabel4 = new JLabel("Jour");
        add(jLabel4, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(addDay, gridBagConstraints);

        // Mois
        gridBagConstraints.gridx = 2;
        JLabel jLabel5 = new JLabel("Mois");
        add(jLabel5, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        add(addMonth, gridBagConstraints);

        // Année
        gridBagConstraints.gridx = 4;
        JLabel jLabel6 = new JLabel("Année");
        add(jLabel6, gridBagConstraints);

        gridBagConstraints.gridx = 5;
        add(addYear, gridBagConstraints);

        // Heure de début
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        JLabel jLabel7 = new JLabel("Heure de début");
        add(jLabel7, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(addBeginHour, gridBagConstraints);

        // Minutes de début
        gridBagConstraints.gridx = 2;
        JLabel jLabel8 = new JLabel("Minute de début");
        add(jLabel8, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        add(addBeginMinutes, gridBagConstraints);

        // Heure de fin
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        JLabel jLabel9 = new JLabel("Heure de fin");
        add(jLabel9, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(addEndHour, gridBagConstraints);

        // Minutes de fin
        gridBagConstraints.gridx = 2;
        JLabel jLabel10 = new JLabel("Minute de fin");
        add(jLabel10, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        add(addEndMinutes, gridBagConstraints);

        // Matière
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        JLabel jLabel11 = new JLabel("Matière");
        add(jLabel11, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        add(addSubject, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        addValidate.addActionListener(new CPersonFormAddListener(this,model));
        add(addValidate,gridBagConstraints);

        pane.addTab("Ajout de cours",this);
    }


    /**
     * Mise à jour de la vue
     * @param o modèle observé à l'origine du message
     * @param arg argument facultatif du modèle, influant l'action
     */
    @Override
    public void update(Observable o, Object arg) {
        // Si l'argument est un vecteur, on met à jour les champs
        if (arg instanceof Vector)
        {
            addFirstName.setText((String) ((Vector) arg).get(0));
            addLastName.setText((String) ((Vector) arg).get(1));
            addLogin.setText((String) ((Vector) arg).get(2));
        }
        // Si l'argument est un message, on vide les champs
        else if (arg instanceof EModelMessage)
        {
            if (arg.equals(EModelMessage.REQUETE_ENVOYEE))
            {
                addFirstName.setText("");
                addLastName.setText("");
                addLogin.setText("");
                addDay.setText("");
                addMonth.setText("");
                addYear.setText("");
                addBeginHour.setText("");
                addBeginMinutes.setText("");
                addEndHour.setText("");
                addEndMinutes.setText("");
                addSubject.setText("");
            }
        }
    }

    /**
     * renvoie le prénom
     * @return le prénom
     */
    public JTextField getAddFirstName() {
        return addFirstName;
    }

    /**
     * renvoie le nom
     * @return le nom
     */
    public JTextField getAddLastName() {
        return addLastName;
    }

    /**
     * renvoie le login
     * @return le login
     */
    public JTextField getAddLogin() {
        return addLogin;
    }

    /**
     * renvoie le jour
     * @return le jour
     */
    public JTextField getAddDay() {
        return addDay;
    }

    /**
     * renvoie le mois
     * @return le mois
     */
    public JTextField getAddMonth() {
        return addMonth;
    }

    /**
     * renvoie l'année
     * @return l'année
     */
    public JTextField getAddYear() {
        return addYear;
    }

    /**
     * renvoie l'heure de début
     * @return l'heure de début
     */
    public JTextField getAddBeginHour() {
        return addBeginHour;
    }

    /**
     * retourne les minutes de début
     * @return les minutes de début
     */
    public JTextField getAddBeginMinutes() {
        return addBeginMinutes;
    }

    /**
     * retourne l'heure de fin
     * @return l'heure de fin
     */
    public JTextField getAddEndHour() {
        return addEndHour;
    }

    /**
     * retourne les minutes de fin
     * @return les minutes de fin
     */
    public JTextField getAddEndMinutes() {
        return addEndMinutes;
    }

    /**
     * retourne la matière
     * @return la matière
     */
    public JTextField getAddSubject() {
        return addSubject;
    }
}
