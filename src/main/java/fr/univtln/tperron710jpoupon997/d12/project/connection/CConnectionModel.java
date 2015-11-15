package fr.univtln.tperron710jpoupon997.d12.project.connection;

import fr.univtln.tperron710jpoupon997.d12.project.EModelMessage;
import fr.univtln.tperron710jpoupon997.d12.project.actors.*;
import fr.univtln.tperron710jpoupon997.d12.project.database.CDataSource;
import fr.univtln.tperron710jpoupon997.d12.project.main.CPlanningModel;
import fr.univtln.tperron710jpoupon997.d12.project.main.CPlanningView;

import java.sql.SQLException;
import java.util.Observable;

/**
 * Created by jeremypoupon on 16/10/15.
 */

/**
 * Le modèle de la vue de connexion
 */
public class CConnectionModel extends Observable {

    /**
     * Gestionnaire de base de données
     */
    private CDataSource dataSource = null;

    /**
     * Utilisateur
     */
    private CPerson person = null;

    public CConnectionModel(CDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CConnectionModel() {
        this(null);
    }

    /**
     * Connexion de l'utilisateur
     * @param login login de l'utilisateur
     * @param password mot de passe de l'utilisateur
     */
    public void connect(String login, String password)
    {
        // Si la connexion à la base de données est inexistante, on l'établit
        if (dataSource == null)
        {
            try {
                dataSource = new CDataSource();
            } catch (Exception e) {
                setChanged();
                notifyObservers(EModelMessage.CONNEXION_ECHOUEE);
            }
        }
        if (dataSource != null)
        {
            try {
                person = CPerson.find(dataSource.getConnection(), login, password);
                setChanged();
                if (person == null)
                    notifyObservers(EModelMessage.AUTHENTIFICATION_INCORRECTE);
                else
                {
                    if (person instanceof CStudent)
                        person = ((CStudent) person).find(dataSource.getConnection());
                    else if (person instanceof CTeacher)
                        person = ((CTeacher) person).find(dataSource.getConnection());
                    else if (person instanceof CChiefOfDepartment)
                        person = ((CChiefOfDepartment) person).find(dataSource.getConnection());
                    else if (person instanceof CPlanningAdministrator)
                        person = ((CPlanningAdministrator) person).find(dataSource.getConnection());
                    if (person == null)
                        notifyObservers(EModelMessage.AUTHENTIFICATION_INCORRECTE);
                    else
                    {
                        notifyObservers(person);
                        CPlanningModel planningModel = new CPlanningModel(person,dataSource);
                        CPlanningView planningView = new CPlanningView(planningModel);
                        planningModel.addObserver(planningView);
                    }
                }
            } catch (SQLException e) {
                setChanged();
                notifyObservers(EModelMessage.CONNEXION_ECHOUEE);
            }
        }
    }

    /**
     * Informe la vue que des champs ne sont pas remplis
     */
    public void informMissingField()
    {
        setChanged();
        notifyObservers(EModelMessage.CHAMP_VIDE);
    }
}
