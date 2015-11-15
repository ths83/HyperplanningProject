package fr.univtln.tperron710.requestPackage.person.form;

import fr.univtln.tperron710.requestPackage.connection.CDatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Observable;
import java.util.Vector;

/**
 * Created by toms on 19/10/15.
 */

/**
 * Modèle permettant la mise à jour des champs
 */
public class CPersonFormModel extends Observable {

    /**
     * Prénom du contact
     */
    private String first;
    /**
     * Nom du contact
     */
    private String last;
    /**
     * Département du contact
     */
    private static String dpt;
    /**
     * Vector résultat permettant un envoi des données aux observateurs
     */
    private Vector<Object> objectVector = new Vector<Object>(7);

    /**
     * Retourne le département du contact
     * @return le département
     */
    public static String getDepartment() {
        return dpt;
    }

    /**
     * Récupère les informations du contact et les transmet aux observateurs
     * @param login le login du contact
     * @return les données du contact dans un vecteur
     * @throws Exception
     */
    public boolean form(String login) throws Exception {

        Class.forName("org.h2.Driver");
        System.setProperty("MyCompany.database.password", "password");
        System.setProperty("MyCompany.database.url", "jdbc:h2:tcp://localhost/~/jpoupon997");
        System.setProperty("MyCompany.database.user", "admin");

        PreparedStatement preparedStatement = CDatabaseManager.getConnection().prepareStatement("SELECT * FROM PERSON WHERE PERSON_LOGIN = ?");
        preparedStatement.setString(1,login);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            first = resultSet.getString("person_first_name");
            objectVector.addElement(first);
            last = resultSet.getString("person_last_name");
            objectVector.addElement(last);
            dpt = resultSet.getString("person_login");
            objectVector.addElement(dpt);
        }
        setChanged();
        notifyObservers(objectVector);
        objectVector = new Vector<Object>(3);
        return true;
    }

}
