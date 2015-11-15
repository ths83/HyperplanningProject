package fr.univtln.tperron710jpoupon997.d12.project.actors;

import fr.univtln.tperron710jpoupon997.d12.project.database.*;
import fr.univtln.tperron710jpoupon997.d12.project.university.CRoom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by jeremypoupon on 12/10/15.
 */

/**
 * Entité représentant une personne
 */
public class CPerson implements IEntity {

    /**
     * Prénom
     */
    protected String firstName = "";

    /**
     * Nom
     */
    protected String lastName = "";

    /**
     * Date de naissance
     */
    protected Date dateOfBirth = null;

    /**
     * Login
     */
    protected String login = "";

    /**
     * Mot de passe
     */
    protected String password = "";

    public CPerson(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Retourne le prénom
     * @return le prénom
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retourne le nom
     * @return le nom
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retourne le login
     * @return le login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Définit le login
     * @param login le login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retourne le mot de passe
     * @return le mot de passe
     */
    public String getPassword() {
        return password;
    }

    /**
     * Définit le mot de passe
     * @param password le mot de passe
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Rend une entité persistante
     * @param connection la connexion utilisée
     * @return vrai en cas de succès, faux sinon
     * @throws SQLException
     */
    @Override
    public boolean persist(Connection connection) throws SQLException {
        return true;
    }

    /**
     * Efface une entité persistante
     * @param connection la connexion utilisée
     * @return vrai en cas de succès, faux sinon
     * @throws SQLException
     */
    @Override
    public boolean remove(Connection connection) throws SQLException {
        return true;
    }

    /**
     * Met à jour une entité persistante
     * @param connection la connexion utilisée
     * @return vrai en cas de succès, faux sinon
     * @throws SQLException
     */
    @Override
    public boolean merge(Connection connection) throws SQLException {
        return true;
    }

    /**
     * Récupère une entité selon les paramètres
     * @param connection la connexion utilisée
     * @param login le login de l'entité
     * @param password le mot de passe de l'entité
     * @return l'entité
     * @throws SQLException
     */
    public static CPerson find(Connection connection, String login, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE person_login = ?");
        preparedStatement.setString(1, login);
        ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next())
        {
            String password1 = resultset.getString("person_password");
            if (password1.equals(password))
            {
                String firstName = resultset.getString("person_first_name");
                String lastName = resultset.getString("person_last_name");
                Date dateOfBirth = resultset.getDate("person_date_of_birth");
                int id = resultset.getInt("person_id");
                int role = resultset.getInt("person_role_id");
                CPerson person = null;
                switch (role)
                {
                    case 0:
                        person = new CStudent(firstName,lastName,dateOfBirth,login,password,id);
                        break;
                    case 1:
                        person = new CTeacher(firstName,lastName,dateOfBirth,login,password,id);
                        break;
                    case 2:
                        person = new CChiefOfDepartment(firstName,lastName,dateOfBirth,login,password,id);
                        break;
                    case 3:
                        person = new CPlanningAdministrator(firstName,lastName,dateOfBirth,login,password,id);
                        break;
                    default:
                        person = new CPerson(login,password);
                }
                return person;
            }
        }
        return null;
    }

    /**
     * Description textuelle
     * @return description textuelle
     */
    @Override
    public String toString() {
        return "CPerson{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
