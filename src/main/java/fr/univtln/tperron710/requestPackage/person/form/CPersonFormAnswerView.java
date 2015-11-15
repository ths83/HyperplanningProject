package fr.univtln.tperron710.requestPackage.person.form;

import fr.univtln.tperron710.requestPackage.request.sent.CRequestSentModel;
import fr.univtln.tperron710jpoupon997.d12.project.EModelMessage;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by toms on 04/11/15.
 */

/**
 * Formulaire d'administration
 */
public class CPersonFormAnswerView extends JPanel implements Observer {

    /**
     * Champ de la requête
     */
    JLabel answerRequest = new JLabel();
    /**
     * Bouton de validation
     */
    JButton validateButton = new JButton("Valider");
    /**
     * Bouton de refus
     */
    JButton cancelButton = new JButton("Refuser");
    /**
     * Identifiant de la requête
     */
    int requestId = 0;

    public CPersonFormAnswerView(JTabbedPane pane, CRequestSentModel model) {
        setLayout(new GridBagLayout());
        initialize(pane, model);
    }

    /**
     * Initialisation de la vue
     * @param pane L'onglet auquel la vue est ajoutée
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


        // Requête
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        JLabel jLabel1 = new JLabel("Requête : ");
        add(jLabel1, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        add(answerRequest, gridBagConstraints);

        // Bouton d'acceptation
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        validateButton.addActionListener(new CPersonFormAnswerAcceptListener(this,model));
        add(validateButton, gridBagConstraints);

        // Bouton de refus
        gridBagConstraints.gridx = 1;
        cancelButton.addActionListener(new CPersonFormAnswerCancelListener(this,model));
        add(cancelButton, gridBagConstraints);

        pane.addTab("Administration",this);
    }

    /**
     * Retourne la requête
     * @return la requête
     */
    public JLabel getAnswerRequest() {
        return answerRequest;
    }

    /**
     * Retourne l'identifiant de la requête
     * @return l'identifiant de la requête
     */
    public int getRequestId() {
        return requestId;
    }

    /**
     * Mise à jour de la vue
     * @param o le modèle à l'origine de la notification
     * @param arg l'argument (facultatif) de la notification, influant sur l'action
     */
    @Override
    public void update(Observable o, Object arg) {
        // Si l'argument est un tableau de String, on affiche la requête
        if (arg instanceof String[])
        {
            String direction = ((String[]) arg)[1];
            if (direction.equals("received"))
            {
                String[] requestElement = ((String[]) arg)[0].split("#");
                String request = "";
                switch (requestElement[0])
                {
                    case "reporter":
                        request = "Reporter le cours du " + requestElement[1] + "/" + requestElement[2] + "/" + requestElement[3] + " prévu de " + requestElement[4] + "h" + requestElement[5] + " a " + requestElement[6] + "h" + requestElement[7] + " au " + requestElement[8] + "/" + requestElement[9] + "/" + requestElement[10] + " de " + requestElement[11] + "h" + requestElement[12] + " a " + requestElement[13] + "h" + requestElement[14];
                        break;
                    case "ajouter":
                        request = "Ajouter un cours de " + requestElement[8] + " le " + requestElement[1] + "/" + requestElement[2] + "/" + requestElement[3] + " de " + requestElement[4] + "h" + requestElement[5] + " a " + requestElement[6] + "h" + requestElement[7];
                        break;
                    case "supprimer":
                        request = "Supprimer le cours du " + requestElement[1] + "/" + requestElement[2] + "/" + requestElement[3] + " de " + requestElement[4] + "h" + requestElement[5] + " a " + requestElement[6] + "h" + requestElement[7];
                        break;
                    case "bloquer":
                        request = "Bloquer la période du " + requestElement[1] + "/" + requestElement[2] + "/" + requestElement[3] + " au " + requestElement[4] + "/" + requestElement[5] + "/" + requestElement[6];
                        break;
                    default:
                        break;
                }
                requestId = Integer.parseInt(((String[]) arg)[2]);
                answerRequest.setText(request);
            }
        }
        // Si l'argument est un message, on vide les champs
        else if (arg instanceof EModelMessage)
        {
            if (arg.equals(EModelMessage.REQUETE_MISE_A_JOUR))
            {
                answerRequest.setText("");
            }
        }
    }
}
