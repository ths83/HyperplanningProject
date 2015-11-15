package fr.univtln.tperron710jpoupon997.d12.project.actors;

import fr.univtln.tperron710jpoupon997.d12.project.university.CPromotion;
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
 * Entité représentant un administrateur
 */
public class CPlanningAdministrator extends CPerson {

    /**
     * Identifiant
     */
    private int PAId = 0;

    /**
     * Promotions
     */
    private List<CPromotion> promotions = null;

    /**
     * Salles
     */
    private List<CRoom> rooms = null;

    public CPlanningAdministrator(String firstName, String lastName, Date dateOfBirth, String login, String password, int PAId) {
        super(login, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.PAId = PAId;
    }

    /**
     * Retourne les promotions
     * @return les promotions
     */
    public List<CPromotion> getPromotions() {
        return promotions;
    }

    /**
     * Retourne les salles
     * @return les salles
     */
    public List<CRoom> getRooms() {
        return rooms;
    }

    /**
     * Retourne l'entité avec ses informations remplies
     * @param connection la connexion utilisée
     * @return l'objet avec ses attributs remplis grâce aux requêtes
     * @throws SQLException
     */
    public CPlanningAdministrator find(Connection connection) throws SQLException {
        List<CPromotion> promotions = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PROMOTION JOIN FORMATION ON PROMOTION_FORMATION_ID  = FORMATION_ID JOIN GRADE ON PROMOTION_GRADE_ID = GRADE_ID JOIN DEPARTMENT ON FORMATION_DEPARTMENT_ID = DEPARTMENT_ID");
        ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next())
        {
            String formation = resultset.getString("formation_name");
            String grade = resultset.getString("grade_name");
            String department = resultset.getString("department_name");
            int id = resultset.getInt("promotion_id");
            promotions.add(new CPromotion(id,formation,grade,department));
        }
        this.promotions = promotions;
        List<CRoom> rooms = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM ROOM JOIN BUILDING ON ROOM_BUILDING_ID = BUILDING_ID JOIN DEPARTMENT ON BUILDING_DEPARTMENT_ID = DEPARTMENT_ID");
        resultset = preparedStatement.executeQuery();
        while (resultset.next())
        {
            int numero = resultset.getInt("room_numero");
            String building = resultset.getString("room_building_id");
            String department = resultset.getString("department_name");
            rooms.add(new CRoom(numero,building,department));
        }
        this.rooms = rooms;
        return this;
    }

}
