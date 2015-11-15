package fr.univtln.tperron710jpoupon997.d12.project.database;

import fr.univtln.tperron710jpoupon997.d12.project.university.CRoom;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jeremypoupon on 10/10/15.
 */

/**
 * Entité
 */
public interface IEntity {

    /**
     * Rend l'entité persistante
     * @param connection la connexion utilisée
     * @return vrai en cas de succès, faux sinon
     * @throws SQLException
     */
    boolean persist(Connection connection) throws SQLException;

    /**
     * Efface l'entité persistante
     * @param connection la connexion utilisée
     * @return vrai en cas de succès, faux sinon
     * @throws SQLException
     */
    boolean remove(Connection connection) throws SQLException;

    /**
     * Met à jour l'entité persistante
     * @param connection la connexion utilisée
     * @return vrai en cas de succès, faux sinon
     * @throws SQLException
     */
    boolean merge(Connection connection) throws SQLException;
}
