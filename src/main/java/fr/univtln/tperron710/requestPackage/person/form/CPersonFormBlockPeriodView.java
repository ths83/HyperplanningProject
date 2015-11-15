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
 * Formulaire de blocage de période
 */
public class CPersonFormBlockPeriodView extends JPanel implements Observer {

    /**
     * Taille standard des champs de texte
     */
    private int textFieldsSize = 20;
    /**
     * Taille des champs de texte numérique
     */
    private int numericFieldsSize = 2;
    /**
     * Champ du prénom
     */
    JTextField blockFirstName = new JTextField(textFieldsSize);
    /**
     * Champ du nom
     */
    JTextField blockLastName = new JTextField(textFieldsSize);
    /**
     * Champ du login
     */
    JTextField blockLogin = new JTextField(textFieldsSize);
    /**
     * Champ du jour de début
     */
    JTextField blockInitialDay = new JTextField(numericFieldsSize);
    /**
     * Champ du mois de début
     */
    JTextField blockInitialMonth = new JTextField(numericFieldsSize);
    /**
     * Champ de l'année de début
     */
    JTextField blockInitialYear = new JTextField(numericFieldsSize);
    /**
     * Champ du jour de fin
     */
    JTextField blockFinalDay = new JTextField(numericFieldsSize);
    /**
     * Champ du mois de fin
     */
    JTextField blockFinalMonth = new JTextField(numericFieldsSize);
    /**
     * Champ de l'année de fin
     */
    JTextField blockFinalYear = new JTextField(numericFieldsSize);
    /**
     * Bouton de validation
     */
    JButton blockValidate = new JButton("Bloquer");

    public CPersonFormBlockPeriodView(JTabbedPane pane, CRequestSentModel model) {
        setLayout(new GridBagLayout());
        initialize(pane, model);
    }

    /**
     * Initialisation de la vue
     * @param pane l'onglet auquel ajouter la vue
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
        add(blockFirstName, gridBagConstraints);

        // Nom
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        JLabel jLabel2 = new JLabel("Nom");
        add(jLabel2, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        add(blockLastName, gridBagConstraints);

        // Login
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        JLabel jLabel3 = new JLabel("Login");
        add(jLabel3, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        add(blockLogin, gridBagConstraints);

        // Jour de début
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        JLabel jLabel4 = new JLabel("Jour de début");
        add(jLabel4, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(blockInitialDay, gridBagConstraints);

        // Mois de début
        gridBagConstraints.gridx = 2;
        JLabel jLabel5 = new JLabel("Mois de début");
        add(jLabel5, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        add(blockInitialMonth, gridBagConstraints);

        // Année de début
        gridBagConstraints.gridx = 4;
        JLabel jLabel6 = new JLabel("Année de début");
        add(jLabel6, gridBagConstraints);

        gridBagConstraints.gridx = 5;
        add(blockInitialYear, gridBagConstraints);

        // Jour de fin
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        JLabel jLabel11 = new JLabel("Jour de fin");
        add(jLabel11, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        add(blockFinalDay, gridBagConstraints);

        // Mois de fin
        gridBagConstraints.gridx = 2;
        JLabel jLabel12 = new JLabel("Mois de fin");
        add(jLabel12, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        add(blockFinalMonth, gridBagConstraints);

        // Année de fin
        gridBagConstraints.gridx = 4;
        JLabel jLabel13 = new JLabel("Année de fin");
        add(jLabel13, gridBagConstraints);

        gridBagConstraints.gridx = 5;
        add(blockFinalYear, gridBagConstraints);

        // Bouton de validation
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        blockValidate.addActionListener(new CPersonFormBlockListener(this,model));
        add(blockValidate,gridBagConstraints);


        pane.addTab("Blocage de période",this);
    }

    /**
     * Mise à jour de la vue
     * @param o modèle à l'origine de la notification
     * @param arg argument facultatif influant sur l'action
     */
    @Override
    public void update(Observable o, Object arg) {
        // Si l'argument est un vecteur, on actualise les champs
        if (arg instanceof Vector)
        {
            blockFirstName.setText((String) ((Vector) arg).get(0));
            blockLastName.setText((String) ((Vector) arg).get(1));
            blockLogin.setText((String) ((Vector) arg).get(2));
        }
        // Si l'argument est un message, on vide les champs
        else if (arg instanceof EModelMessage)
        {
            if (arg.equals(EModelMessage.REQUETE_ENVOYEE))
            {
                blockFirstName.setText("");
                blockLastName.setText("");
                blockLogin.setText("");
                blockInitialDay.setText("");
                blockInitialMonth.setText("");
                blockInitialYear.setText("");
                blockFinalDay.setText("");
                blockFinalMonth.setText("");
                blockFinalYear.setText("");
            }
        }
    }

    /**
     * Retourne le prénom
     * @return le prénom
     */
    public JTextField getBlockFirstName() {
        return blockFirstName;
    }

    /**
     * Retourne le nom
     * @return le nom
     */
    public JTextField getBlockLastName() {
        return blockLastName;
    }

    /**
     * Retourne le login
     * @return le login
     */
    public JTextField getBlockLogin() {
        return blockLogin;
    }

    /**
     * Retourne le jour de début
     * @return le jour de début
     */
    public JTextField getBlockInitialDay() {
        return blockInitialDay;
    }

    /**
     * Retourne le mois de début
     * @return le mois de début
     */
    public JTextField getBlockInitialMonth() {
        return blockInitialMonth;
    }

    /**
     * Retourne l'année de début
     * @return l'année de fin
     */
    public JTextField getBlockInitialYear() {
        return blockInitialYear;
    }

    /**
     * Retourne le jour de fin
     * @return le jour de fin
     */
    public JTextField getBlockFinalDay() {
        return blockFinalDay;
    }

    /**
     * Retourne le mois de fin
     * @return le mois de fin
     */
    public JTextField getBlockFinalMonth() {
        return blockFinalMonth;
    }

    /**
     * Retourne l'année de fin
     * @return l'année de fin
     */
    public JTextField getBlockFinalYear() {
        return blockFinalYear;
    }
}
