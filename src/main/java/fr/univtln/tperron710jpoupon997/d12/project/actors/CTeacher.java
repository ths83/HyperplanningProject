package fr.univtln.tperron710jpoupon997.d12.project.actors;

import fr.univtln.tperron710jpoupon997.d12.project.database.IEntity;
import fr.univtln.tperron710jpoupon997.d12.project.university.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by jeremypoupon on 12/10/15.
 */
public class CTeacher extends CPerson {

    /**
     * Identifiant
     */
    private int teacherId = 0;

    /**
     * Matières enseignées
     */
    protected List<String> subjects = new ArrayList<>();

    /**
     * Promotions
     */
    protected List<CPromotion> promotions = new ArrayList<>();

    public CTeacher(String firstName, String lastName, Date dateOfBirth, String login, String password, int teacherId) {
        super(login, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.teacherId = teacherId;
    }

    /**
     * Retourne les matières enseignées
     * @return les matières enseignées
     */
    public List<String> getSubjects() {
        return subjects;
    }

    /**
     * Retourne les promotions
     * @return les promotions
     */
    public List<CPromotion> getPromotions() {
        return promotions;
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
     * Retourne une entité correspondant à l'id
     * @param connection la connexion utilisée
     * @return l'entité
     * @throws SQLException
     */
    public CTeacher find(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * " +
                "FROM teach_to " +
                "JOIN promotion " +
                "ON teach_to_promotion_id = promotion_id " +
                "JOIN formation " +
                "ON promotion_formation_id = formation_id " +
                "JOIN grade " +
                "ON promotion_grade_id = grade_id " +
                "JOIN department " +
                "ON formation_department_id = department_id " +
                "WHERE teach_to_teacher_id = ? ");
        preparedStatement.setInt(1, teacherId);
        ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next())
        {
            String formation = resultset.getString("formation_name");
            String grade = resultset.getString("grade_name");
            String department = resultset.getString("department_name");
            int id = resultset.getInt("promotion_id");
            promotions.add(new CPromotion(id,formation,grade,department));
        }
        preparedStatement = connection.prepareStatement
                ("SELECT * " +
                "FROM teach " +
                "JOIN subject " +
                "ON teach_subject_id = subject_id " +
                "WHERE teach_teacher_id = ? ");
        preparedStatement.setInt(1, teacherId);
        resultset = preparedStatement.executeQuery();
        while (resultset.next())
        {
            String subject = resultset.getString("subject_name");
            subjects.add(subject);
        }
        List<CPromotion> responsiblePromotions = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM PROMOTION JOIN FORMATION ON PROMOTION_FORMATION_ID = FORMATION_ID JOIN GRADE ON PROMOTION_GRADE_ID = GRADE_ID JOIN DEPARTMENT ON FORMATION_DEPARTMENT_ID = DEPARTMENT_ID WHERE PROMOTION_RESPONSIBLE_ID = ? ");
        preparedStatement.setInt(1, teacherId);
        resultset = preparedStatement.executeQuery();
        while (resultset.next())
        {
            String formation = resultset.getString("formation_name");
            String grade = resultset.getString("grade_name");
            String department = resultset.getString("department_name");
            int id = resultset.getInt("promotion_id");
            responsiblePromotions.add(new CPromotion(id, formation, grade, department));
        }
        if (responsiblePromotions.size() > 0)
            return new CResponsible(firstName,lastName,dateOfBirth,login,password,teacherId,subjects,promotions,responsiblePromotions);
        return this;
    }

    /**
     * Description textuelle
     * @return description textuelle
     */
    @Override
    public String toString() {
        return "CTeacher{" + super.toString() +
                "teacherId=" + teacherId +
                ", subjects=" + subjects +
                ", promotions=" + promotions +
                '}';
    }
}
