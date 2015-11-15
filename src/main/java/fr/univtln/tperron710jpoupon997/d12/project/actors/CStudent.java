package fr.univtln.tperron710jpoupon997.d12.project.actors;

import fr.univtln.tperron710jpoupon997.d12.project.university.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jeremypoupon on 12/10/15.
 */

/**
 * Entité représentant un étudiant
 */
public class CStudent extends CPerson {

    /**
     * Identifiant
     */
    protected int studentId = 0;

    /**
     * Promotion
     */
    protected CPromotion promotion = null;

    public CStudent(String firstName, String lastName, Date dateOfBirth, String login, String password, int studentId) {
        super(login, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.studentId = studentId;
    }

    /**
     * Retourne l'identifiant
     * @return l'identifiant
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Retourne la promotion
     * @return la promotion
     */
    public CPromotion getPromotion() {
        return promotion;
    }

    /**
     * Définit la promotion
     * @param promotion la promotion
     */
    public void setPromotion(CPromotion promotion) {
        this.promotion = promotion;
    }

    /**
     * Rend une entité persistante
     * @param connection la connexion utilisée
     * @return vrai en cas de succès, faux sinon
     * @throws SQLException
     */
    public boolean persist(Connection connection) throws SQLException {
        return true;
    }

    /**
     * Efface une entité persistante
     * @param connection la connexion utilisée
     * @return vrai en cas de succès, faux sinon
     * @throws SQLException
     */
    public boolean remove(Connection connection) throws SQLException {
        return true;
    }

    /**
     * Met à jour une entité persistante
     * @param connection la connexion utilisée
     * @return vrai en cas de succès, faux sinon
     * @throws SQLException
     */
    public boolean merge(Connection connection) throws SQLException {
        return true;
    }

    /**
     * Retourne une entité correspndant à l'id
     * @param connection la connexion utilisée
     * @return l'etudiant
     * @throws SQLException
     */
    public CStudent find(Connection connection) throws SQLException
    {
        boolean isRepresentative = false;
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * " +
                "FROM student_promotion " +
                "JOIN promotion " +
                "ON student_promotion_promotion_id = promotion_id " +
                "JOIN formation " +
                "ON promotion_formation_id = formation_id " +
                "JOIN department " +
                "ON formation_department_id = department_id " +
                "JOIN grade ON promotion_grade_id = grade_id " +
                "WHERE student_promotion_student_id = ? ");
        preparedStatement.setInt(1, getStudentId());
        ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next())
        {
            String formation = resultset.getString("formation_name");
            String grade = resultset.getString("grade_name");
            String department = resultset.getString("department_name");
            int id = resultset.getInt("promotion_id");
            isRepresentative = resultset.getBoolean("student_promotion_is_representative");
            setPromotion(new CPromotion(id,formation,grade,department));
        }
        if (isRepresentative)
            return new CClassRepresentative(firstName,lastName,dateOfBirth,login,password,studentId,promotion);
        return this;
    }

    /**
     * Description textuelle
     * @return description textuelle
     */
    @Override
    public String toString() {
        return "CStudent{" + super.toString() +
                "promotion=" + promotion +
                ", studentId=" + studentId +
                '}';
    }
}
