package fr.univtln.tperron710.requestPackage.person.database;

import fr.univtln.tperron710.requestPackage.connection.CDatabaseManager;
import fr.univtln.tperron710jpoupon997.d12.project.actors.CChiefOfDepartment;
import fr.univtln.tperron710jpoupon997.d12.project.actors.CPlanningAdministrator;
import fr.univtln.tperron710jpoupon997.d12.project.actors.CResponsible;
import fr.univtln.tperron710jpoupon997.d12.project.actors.CStudent;
import fr.univtln.tperron710jpoupon997.d12.project.main.CPlanningModel;

import java.sql.*;
import java.util.Observable;
import java.util.Vector;

/**
 * Created by toms on 17/10/15.
 */

/**
 * Modèle pour générer la table des contacts
 */
public class CTablePersonModel extends Observable{

    /**
     * Vecteur contenant les données à envoyer
     */
    Vector<Object> vector = new Vector<Object>(7);
    /**
     * Vector contenant les en-tête de colonnes
     */
    Vector<Object> columnTeacherName = new Vector<Object>(7);
    /**
     * Modèle utilisé pour obtenir les informations
     */
    CPlanningModel parentModel = null;

    /**
     * Obtenir les données
     * @return les données
     */
    public Vector<Object> getVector() {
        return vector;
    }

    /**
     * Obtenir les
     * @return table header vector
     */
    public Vector<Object> getColumnTeacherName() {
        return columnTeacherName;
    }

    public CTablePersonModel(CPlanningModel model) throws Exception {
        parentModel = model;
        teacherDatabaseDisplayModel();
    }

    /**
     * Initialiser la liste des contacts
     * @return vrai en cas de succès, faux sinon
     * @throws Exception
     */
    public boolean teacherDatabaseDisplayModel() throws Exception {

        Class.forName("org.h2.Driver");
        System.setProperty("MyCompany.database.password", "password");
        System.setProperty("MyCompany.database.url", "jdbc:h2:tcp://localhost/~/jpoupon997");
        System.setProperty("MyCompany.database.user", "admin");
        Statement statement = CDatabaseManager.getConnection().createStatement();

        /**
         *create table
         */

        columnTeacherName.addElement("Prénom");
        columnTeacherName.addElement("Nom");
        columnTeacherName.addElement("Date de naissance");

        columnTeacherName.addElement("Login");
        columnTeacherName.addElement("Rôle");
        if (parentModel.getPerson() instanceof CStudent)
        {
            Vector<Object> objects = new Vector<>(5);
            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM PERSON WHERE PERSON_ROLE_ID = 0 OR PERSON_ROLE_ID = 1 ORDER BY PERSON_ROLE_ID ASC");
            while (resultSet1.next()){
                String firstname = resultSet1.getString("person_first_name");
                objects.addElement(firstname);
                String lastname = resultSet1.getString("person_last_name");
                objects.addElement(lastname);
                Date birthdate = resultSet1.getDate("person_date_of_birth");
                objects.addElement(birthdate);
                String login = resultSet1.getString("person_login");
                objects.addElement(login);
                int role = resultSet1.getInt("person_role_id");
                String roleText = "";
                switch (role)
                {
                    case 0:
                        roleText = "Etudiant";
                        break;
                    case 1:
                        roleText = "Professeur";
                        break;
                    case 2:
                        roleText = "Chef de Département";
                        break;
                    case 3:
                        roleText = "Administrateur";
                        break;
                }
                objects.add(roleText);
                vector.addElement(objects);
                objects = new Vector<>(5);
            }
        }
        else if ((parentModel.getPerson() instanceof CChiefOfDepartment) || (parentModel.getPerson() instanceof CResponsible) || (parentModel.getPerson() instanceof CPlanningAdministrator))
        {
            Vector<Object> objects = new Vector<Object>(5);
            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM PERSON ORDER BY PERSON_ROLE_ID ASC");
            while (resultSet1.next()){
                String firstname = resultSet1.getString("person_first_name");
                objects.addElement(firstname);
                String lastname = resultSet1.getString("person_last_name");
                objects.addElement(lastname);
                Date birthdate = resultSet1.getDate("person_date_of_birth");
                objects.addElement(birthdate);
                String login = resultSet1.getString("person_login");
                objects.addElement(login);
                int role = resultSet1.getInt("person_role_id");
                String roleText = "";
                switch (role)
                {
                    case 0:
                        roleText = "Etudiant";
                        break;
                    case 1:
                        roleText = "Professeur";
                        break;
                    case 2:
                        roleText = "Chef de Département";
                        break;
                    case 3:
                        roleText = "Administrateur";
                        break;
                }
                objects.add(roleText);
                vector.addElement(objects);
                objects = new Vector<>(5);
            }
        }
        else
        {
            Vector<Object> objects = new Vector<Object>(5);
            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM PERSON WHERE PERSON_ROLE_ID = 0 OR PERSON_ROLE_ID = 1 OR PERSON_ROLE_ID = 2 ORDER BY PERSON_ROLE_ID ASC");
            while (resultSet1.next()){
                String firstname = resultSet1.getString("person_first_name");
                objects.addElement(firstname);
                String lastname = resultSet1.getString("person_last_name");
                objects.addElement(lastname);
                Date birthdate = resultSet1.getDate("person_date_of_birth");
                objects.addElement(birthdate);
                String login = resultSet1.getString("person_login");
                objects.addElement(login);
                int role = resultSet1.getInt("person_role_id");
                String roleText = "";
                switch (role)
                {
                    case 0:
                        roleText = "Etudiant";
                        break;
                    case 1:
                        roleText = "Professeur";
                        break;
                    case 2:
                        roleText = "Chef de Département";
                        break;
                    case 3:
                        roleText = "Administrateur";
                        break;
                }
                objects.add(roleText);
                vector.addElement(objects);
                objects = new Vector<>(5);
            }
        }
        Vector<Vector> objects1 = new Vector<Vector>(2);
        objects1.addElement(columnTeacherName);
        objects1.addElement(vector);
        setChanged();
        notifyObservers(objects1);
        return true;
    }


}
