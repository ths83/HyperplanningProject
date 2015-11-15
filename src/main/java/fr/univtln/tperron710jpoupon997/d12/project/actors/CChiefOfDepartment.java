package fr.univtln.tperron710jpoupon997.d12.project.actors;

import fr.univtln.tperron710jpoupon997.d12.project.university.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by jeremypoupon on 12/10/15.
 */

/**
 * Entité représentant un chef de département
 */
public class CChiefOfDepartment extends CPerson {

    /**
     * Identifiant
     */
    private int CDId = 0;

    /**
     * Département
     */
    private String department = null;

    /**
     * Promotions
     */
    private List<CPromotion> promotions = new ArrayList<>();

    public CChiefOfDepartment(String firstName, String lastName, Date dateOfBirth, String login, String password,
                              int CDId) {
        super(login, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.CDId = CDId;
    }

    /**
     * Retourne l'identifiant
     * @return l'identifiant
     */
    public int getCDId() {
        return CDId;
    }

    /**
     * Retourne le département
     * @return le département
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Retourne les promotions
     * @return les promotions
     */
    public List<CPromotion> getPromotions() {
        return promotions;
    }

    /**
     * Retourne le chef de département correspondant à l'id
     * @param connection la connection utilisée pour les requêtes
     * @return le chef de département
     * @throws SQLException
     */
    public CChiefOfDepartment find(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * " +
                "FROM DEPARTMENT " +
                "WHERE DEPARTMENT_CHIEF_ID = ? ");
        preparedStatement.setInt(1, getCDId());
        ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next())
        {
            String department = resultset.getString("department_name");
            this.department = department;
        }
        List<CPromotion> promotions = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM PROMOTION JOIN FORMATION ON PROMOTION_FORMATION_ID = FORMATION_ID JOIN DEPARTMENT ON FORMATION_DEPARTMENT_ID = DEPARTMENT_ID JOIN GRADE ON PROMOTION_GRADE_ID = GRADE_ID WHERE DEPARTMENT_NAME = ? ");
        preparedStatement.setString(1, department);
        resultset = preparedStatement.executeQuery();
        while (resultset.next())
        {
            int id = resultset.getInt("promotion_id");
            String grade = resultset.getString("grade_name");
            String formation = resultset.getString("formation_name");
            promotions.add(new CPromotion(id,formation,grade,department));
        }
        this.promotions = promotions;
        return this;
    }

    /**
     * Description textuelle d'un chef de département
     * @return description textuelle d'un chef de département
     */
    @Override
    public String toString() {
        return "CChiefOfDepartment{" + super.toString() +
                "CDId=" + CDId +
                ", department='" + department + '\'' +
                '}';
    }
}
