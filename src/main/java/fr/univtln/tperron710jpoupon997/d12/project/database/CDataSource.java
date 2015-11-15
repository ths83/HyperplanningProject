package fr.univtln.tperron710jpoupon997.d12.project.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jeremypoupon on 19/10/15.
 */

/**
 * Gestionnaire de connexion à la base de données
 */
public class CDataSource {

    /**
     * Connexion
     */
    private Connection connection = null;

    public CDataSource() throws Exception
    {
        connect();
    }

    /**
     * Connexion à la base de données
     */
    public boolean connect() throws Exception
    {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/jpoupon997",
                "admin", "password");
        return true;
    }

    /**
     * Déconnexion de la base de données
     * @return vrai en cas de succès, faux sinon
     */
    public boolean disconnect()
    {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Retourne la connexion
     * @return la connexion
     */
    public Connection getConnection() {
        return connection;
    }
}
