package fr.univtln.tperron710jpoupon997.d12.project.database;

import javax.activation.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeremypoupon on 10/10/15.
 */

/**
 * Gestionnaire d'entité
 * @param <T> le type de l'entité
 */
public class CEntityManager<T extends IEntity> {

    /**
     * Gestionnaire de connexion
     */
    private CDataSource dataSource = null;

    public CEntityManager(CDataSource cDataSource) {
        dataSource = cDataSource;
    }

    /**
     * Rend une entité persistante
     * @param IEntity l'entité
     * @return vrai en cas de succès, faux sinon
     */
    public boolean persist(IEntity IEntity)
    {
        try {
            IEntity.persist(dataSource.getConnection());
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Efface une entité persistante
     * @param IEntity l'entité
     * @return vrai en cas de succès, faux sinon
     */
    public boolean remove(IEntity IEntity)
    {
        try {
            IEntity.remove(dataSource.getConnection());
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Met à jour une entité persistante
     * @param IEntity l'entité
     * @return vrai en cas de succès, faux sinon
     */
    public boolean merge(IEntity IEntity)
    {
        try {
            IEntity.merge(dataSource.getConnection());
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
