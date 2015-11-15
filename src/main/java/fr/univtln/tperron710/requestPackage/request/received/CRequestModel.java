package fr.univtln.tperron710.requestPackage.request.received;

import fr.univtln.tperron710.requestPackage.connection.CDatabaseManager;
import fr.univtln.tperron710jpoupon997.d12.project.connection.CConnectionButtonControler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Observable;
import java.util.Vector;

/**
 * Created by toms on 05/11/15.
 */

/**
 * Modèle pour la table des requêtes
 */
public class CRequestModel extends Observable {

    /**
     * La vue observatrice
     */
    private CRequestView view = null;

    public CRequestModel(CRequestView view) {
        this.view = view;
        addObserver(view);
    }

    /**
     * Récupère toutes les requêtes (reçues et envoyées) par l'utilisateur
     */
    public void getRequests()
    {
        try {
            Class.forName("org.h2.Driver");
            System.setProperty("MyCompany.database.password", "password");
            System.setProperty("MyCompany.database.url", "jdbc:h2:tcp://localhost/~/jpoupon997");
            System.setProperty("MyCompany.database.user", "admin");
            Vector<Object> result = new Vector<>(4);
            PreparedStatement preparedStatement = CDatabaseManager.getConnection().prepareStatement("SELECT * FROM MESSAGE WHERE MESSAGE_FROM = ? ORDER BY MESSAGE_ID ASC");
            preparedStatement.setString(1, CConnectionButtonControler.getView().getLoginField().getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            Vector<Object[]> objects = new Vector<>();
            while (resultSet.next()){
                String receiver = resultSet.getString("message_to");
                int state = resultSet.getInt("message_answer");
                String stateString = "";
                switch (state)
                {
                    case 0:
                        stateString = "non traitée";
                        break;
                    case 1:
                        stateString = "acceptée";
                        break;
                    case -1:
                        stateString = "refusée";
                        break;
                }
                objects.add(new Object[]{receiver,stateString});
            }
            Object[][] sendedData = new Object[objects.size()][];
            for (int i = 0 ; i < objects.size() ; i++)
            {
                sendedData[i] = objects.get(i);
            }
            result.add(sendedData);
            result.add(new String[]{"Destinataire","Etat"});
            preparedStatement = CDatabaseManager.getConnection().prepareStatement("SELECT * FROM MESSAGE WHERE MESSAGE_TO = ? ORDER BY MESSAGE_ID DESC");
            preparedStatement.setString(1, CConnectionButtonControler.getView().getLoginField().getText());
            resultSet = preparedStatement.executeQuery();
            objects = new Vector<>();
            while (resultSet.next()){
                String sender = resultSet.getString("message_from");
                int state = resultSet.getInt("message_answer");
                String stateString = "";
                switch (state)
                {
                    case 0:
                        stateString = "non traitée";
                        break;
                    case 1:
                        stateString = "acceptée";
                        break;
                    case -1:
                        stateString = "refusée";
                        break;
                }
                objects.add(new Object[]{sender,stateString});
            }
            Object[][] receivedData = new Object[objects.size()][];
            for (int i = 0 ; i < objects.size() ; i++)
            {
                receivedData[i] = objects.get(i);
            }
            result.add(receivedData);
            result.add(new String[]{"Expéditeur","Etat"});
            setChanged();
            notifyObservers(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère une requête envoyée sélectionnée dans la table
     * @param row la ligne de la requête
     */
    public void getSendedRequest(int row)
    {
        try {
            Class.forName("org.h2.Driver");
            System.setProperty("MyCompany.database.password", "password");
            System.setProperty("MyCompany.database.url", "jdbc:h2:tcp://localhost/~/jpoupon997");
            System.setProperty("MyCompany.database.user", "admin");
            PreparedStatement preparedStatement = CDatabaseManager.getConnection().prepareStatement("SELECT * FROM MESSAGE WHERE MESSAGE_FROM = ? ORDER BY MESSAGE_ID ASC");
            preparedStatement.setString(1, CConnectionButtonControler.getView().getLoginField().getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            String result = "";
            int id = 0;
            int counter = 0;
            while (resultSet.next()){
                if (counter < row)
                    counter++;
                else
                {
                    result = resultSet.getString("message_string");
                    id = resultSet.getInt("message_id");
                    break;
                }
            }
            String[] results = new String[]{result,"sended",Integer.toString(id)};
            setChanged();
            notifyObservers(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère une requête reçue sélectionnée dans la table
     * @param row la ligne de la requête
     */
    public void getReceivedRequest(int row)
    {
        try {
            Class.forName("org.h2.Driver");
            System.setProperty("MyCompany.database.password", "password");
            System.setProperty("MyCompany.database.url", "jdbc:h2:tcp://localhost/~/jpoupon997");
            System.setProperty("MyCompany.database.user", "admin");
            PreparedStatement preparedStatement = CDatabaseManager.getConnection().prepareStatement("SELECT * FROM MESSAGE WHERE MESSAGE_TO = ? ORDER BY MESSAGE_ID DESC");
            preparedStatement.setString(1, CConnectionButtonControler.getView().getLoginField().getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            String result = "";
            int id = 0;
            int counter = 0;
            while (resultSet.next()){
                if (counter < row)
                    counter++;
                else
                {
                    result = resultSet.getString("message_string");
                    id = resultSet.getInt("message_id");
                    break;
                }
            }
            String[] results = new String[]{result,"received", Integer.toString(id)};
            setChanged();
            notifyObservers(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
