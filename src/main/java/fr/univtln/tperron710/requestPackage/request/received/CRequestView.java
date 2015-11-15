package fr.univtln.tperron710.requestPackage.request.received;

import fr.univtln.tperron710jpoupon997.d12.project.EModelMessage;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * Created by toms on 18/10/15.
 */


/**
 * Vue contenant la liste des requêtes reçues et envoyées, ainsi que l'affichage de ces requêtes
 */
public class CRequestView extends JPanel implements Observer {

    /**
     * Panel contenant la liste des requêtes
     */
    private JPanel westPanel = null;
    /**
     * Panel contenant l'affichage des requêtes
     */
    private JPanel eastPanel = null;
    /**
     * Label contenant la description d'une requête
     */
    private JLabel description = null;
    /**
     * Table contenant les requêtes envoyées
     */
    private JTable sendedRequest = null;
    /**
     * Table contenant les requêtes reçues
     */
    private JTable receivedRequest = null;
    /**
     * Modèle observé
     */
    private CRequestModel model = null;

    public CRequestView() {
        super(new GridLayout(1,2));
        initialize();
        model = new CRequestModel(this);
        model.getRequests();
    }

    /**
     * Initialisation de la vue
     */
    public void initialize() {
        westPanel = new JPanel(new GridLayout(1,2));
        add(westPanel);
        eastPanel = new JPanel(new BorderLayout());
        add(eastPanel);
        description = new JLabel();
        description.setVerticalAlignment(JLabel.TOP);
        description.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        description.setOpaque(true);
        description.setBackground(Color.WHITE);
        eastPanel.add(description,BorderLayout.CENTER);
    }

    /**
     * Retourne le modèle observé
     * @return le modèle observé
     */
    public CRequestModel getModel() {
        return model;
    }

    /**
     * Mise à jour de la vue
     * @param o modèle origine de la notification
     * @param arg argument facultatif influant sur la mise à jour
     */
    @Override
    public void update(Observable o, Object arg) {
        // Si l'argument est un vecteur, on recharge les tables
        if (arg instanceof Vector)
        {
            westPanel.removeAll();
            Object[][] sendedRequestData = (Object[][]) ((Vector<Object>) arg).get(0);
            String[] sendedRequestTitle = (String[]) ((Vector<Object>) arg).get(1);
            sendedRequest = new JTable(sendedRequestData,sendedRequestTitle);
            sendedRequest.addMouseListener(new CSendedRequestMouseAdapter(model));
            westPanel.add(new JScrollPane(sendedRequest));
            Object[][] receivedRequestData = (Object[][]) ((Vector<Object>) arg).get(2);
            String[] receivedRequestTitle = (String[]) ((Vector<Object>) arg).get(3);
            receivedRequest = new JTable(receivedRequestData,receivedRequestTitle);
            receivedRequest.addMouseListener(new CReceivedRequestMouseAdapter(model));
            westPanel.add(new JScrollPane(receivedRequest));
            westPanel.revalidate();
            westPanel.repaint();
        }
        // Si l'argument est un tableau de String, on décortique et on affiche la requête
        else if (arg instanceof String[])
        {
            String[] requestElement = ((String[]) arg)[0].split("#");
            String request = "";
            switch (requestElement[0])
            {
                case "reporter":
                    request = "<html>Reporter le cours du " + requestElement[1] + "/" + requestElement[2] + "/" + requestElement[3] + "<br>prévu de " + requestElement[4] + "h" + requestElement[5] + " a " + requestElement[6] + "h" + requestElement[7] + "<br>au " + requestElement[8] + "/" + requestElement[9] + "/" + requestElement[10] + "<br>de " + requestElement[11] + "h" + requestElement[12] + " a " + requestElement[13] + "h" + requestElement[14] + "</html>";
                    break;
                case "ajouter":
                    request = "<html>Ajouter un cours de " + requestElement[8] + "<br>le " + requestElement[1] + "/" + requestElement[2] + "/" + requestElement[3] + "<br>de " + requestElement[4] + "h" + requestElement[5] + " a " + requestElement[6] + "h" + requestElement[7] + "</html>";
                    break;
                case "supprimer":
                    request = "<html>Supprimer le cours du " + requestElement[1] + "/" + requestElement[2] + "/" + requestElement[3] + "<br>de " + requestElement[4] + "h" + requestElement[5] + " a " + requestElement[6] + "h" + requestElement[7] + "</html>";
                    break;
                case "bloquer":
                    request = "<html>Bloquer la période du " + requestElement[1] + "/" + requestElement[2] + "/" + requestElement[3] + "<br>au " + requestElement[4] + "/" + requestElement[5] + "/" + requestElement[6] + "</html>";
                    break;
                default:
                    break;
            }
            description.setText(request);
        }
        // Si l'argument est un message, on récupère toutes les requêtes
        else if (arg instanceof EModelMessage)
        {
            if ((arg.equals(EModelMessage.REQUETE_MISE_A_JOUR)) || (arg.equals(EModelMessage.REQUETE_ENVOYEE)))
                model.getRequests();
        }
    }
}
