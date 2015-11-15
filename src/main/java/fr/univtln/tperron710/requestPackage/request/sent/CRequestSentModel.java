package fr.univtln.tperron710.requestPackage.request.sent;

import fr.univtln.tperron710.requestPackage.connection.CDatabaseManager;
import fr.univtln.tperron710jpoupon997.d12.project.EModelMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Observable;

/**
 * Created by toms on 22/10/15.
 */

/**
 * Modèle pour l'envoi de requêtes
 */
public class CRequestSentModel extends Observable {

    /**
     * Envoi la requête à la base de données
     * @param message la requête à envoyer
     * @param source l'expéditeur de la requête
     * @param recipient le destinataire de la requête
     * @return vrai en cas de succès, faux sinon
     */
    public boolean sendRequest(String message, String source, String recipient) {
        try {
            Class.forName("org.h2.Driver");
            System.setProperty("MyCompany.database.password", "password");
            System.setProperty("MyCompany.database.url", "jdbc:h2:tcp://localhost/~/jpoupon997");
            System.setProperty("MyCompany.database.user", "admin");

            Statement statement = CDatabaseManager.getConnection().createStatement();

            ResultSet set = statement.executeQuery("SELECT MAX(message_id) as maxi FROM message ");

            int lastIndex = 0;

            while (set.next()) {
                lastIndex = set.getInt("maxi");
            }

            PreparedStatement preparedStatement =
                    CDatabaseManager.getConnection().prepareStatement
                            ("INSERT INTO message values (?,?,?,?,0)");
            preparedStatement.setInt(1, lastIndex+1);
            preparedStatement.setString(2, message);
            preparedStatement.setString(3, source);
            preparedStatement.setString(4, recipient);

            preparedStatement.executeUpdate();
            setChanged();
            notifyObservers(EModelMessage.REQUETE_ENVOYEE);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            setChanged();
            notifyObservers(EModelMessage.CONNEXION_ECHOUEE);
            return false;
        }
    }

    /**
     * Définit une requête comme étant validée
     * @param id l'identifiant de la requête
     * @return vrai en cas de succès, faux sinon
     */
    public boolean acceptRequest(int id) {

        try {
            Class.forName("org.h2.Driver");
            System.setProperty("MyCompany.database.password", "password");
            System.setProperty("MyCompany.database.url", "jdbc:h2:tcp://localhost/~/jpoupon997");
            System.setProperty("MyCompany.database.user", "admin");

            PreparedStatement preparedStatement = CDatabaseManager.getConnection().prepareStatement("SELECT * FROM message WHERE message_id = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            String request = "";
            while (resultSet.next()) {
                request = resultSet.getString("message_string");
            }

            preparedStatement =
                    CDatabaseManager.getConnection().prepareStatement
                            ("UPDATE MESSAGE SET MESSAGE_ANSWER = 1 WHERE MESSAGE_STRING = ?");
            preparedStatement.setString(1, request);

            preparedStatement.executeUpdate();
            setChanged();
            notifyObservers(EModelMessage.REQUETE_MISE_A_JOUR);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            setChanged();
            notifyObservers(EModelMessage.CONNEXION_ECHOUEE);
            return false;
        }
    }

    /**
     * Définit une requête comme étant refusée
     * @param id l'identifiant de la requête
     * @return vrai en cas de succès, faux sinon
     */
    public boolean cancelRequest(int id) {

        try {
            Class.forName("org.h2.Driver");
            System.setProperty("MyCompany.database.password", "password");
            System.setProperty("MyCompany.database.url", "jdbc:h2:tcp://localhost/~/jpoupon997");
            System.setProperty("MyCompany.database.user", "admin");

            PreparedStatement preparedStatement = CDatabaseManager.getConnection().prepareStatement("SELECT * FROM message WHERE message_id = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            String request = "";
            while (resultSet.next()){
                request = resultSet.getString("message_string");
            }

            preparedStatement =
                    CDatabaseManager.getConnection().prepareStatement
                            ("UPDATE MESSAGE SET MESSAGE_ANSWER = -1 WHERE MESSAGE_STRING = ?");
            preparedStatement.setString(1, request);

            preparedStatement.executeUpdate();
            setChanged();
            notifyObservers(EModelMessage.REQUETE_MISE_A_JOUR);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            setChanged();
            notifyObservers(EModelMessage.CONNEXION_ECHOUEE);
            return false;
        }
    }
}
