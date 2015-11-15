package fr.univtln.tperron710.requestPackage.connection;
/**
 * Created by toms on 11/10/15.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Gestionnaire de base de données
 */
public class CDatabaseManager {

    /**
     * Liste de connexions
     */
    private static final Queue<Connection> freeConnections = new LinkedList<Connection>();

    /**
     * Compteur de connexion
     */
    private static final int numberOfInitialConnections = 5;

    /**
     * Propriété mot de passe pour la connexion
     */
    private static final String password = System
            .getProperty("MyCompany.database.password");

    /**
     * Propriété URL pour la connexion
     */
    private static final String url = System
            .getProperty("MyCompany.database.url");

    /**
     * Propriété utilisateur pour la connexion
     */
    private static final String user = System
            .getProperty("MyCompany.database.user");

    /**
     * Initialisation de la connexion
     */
    static {
        for (int i = 0; i < numberOfInitialConnections; i++) {
            try {
                freeConnections.add(DriverManager.getConnection(url, user,
                        password));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retourne une connexion
     * @return la connexion
     * @throws SQLException the SQL exception
     */
    public static synchronized Connection getConnection() throws SQLException {
        Connection connection = null;
        if (freeConnections.isEmpty()) {
            connection = DriverManager.getConnection(url, user, password);
        } else {
            connection = freeConnections.remove();
        }
        return connection;
    }

    /**
     * Effacer une connexion
     * @param connection la connexion
     */
    public static synchronized void releaseConnection(Connection connection) {
        if (freeConnections.size() < numberOfInitialConnections) {
            freeConnections.add(connection);
        } else {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}