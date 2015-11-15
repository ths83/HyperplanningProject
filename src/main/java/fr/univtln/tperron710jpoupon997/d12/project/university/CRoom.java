package fr.univtln.tperron710jpoupon997.d12.project.university;

import fr.univtln.tperron710jpoupon997.d12.project.database.IEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeremypoupon on 21/10/15.
 */
public class CRoom implements IEntity {

    private int numero;

    private String building;

    private String department;

    public CRoom(int numero, String building, String department) {
        this.numero = numero;
        this.building = building;
        this.department = department;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean persist(Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean remove(Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean merge(Connection connection) throws SQLException {
        return false;
    }

    public CRoom find(Connection connection) throws SQLException {
        return null;
    }

    public static List<CRoom> findByDepartment(Connection connection, String department) throws SQLException {
        List<CRoom> rooms = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ROOM JOIN BUILDING ON ROOM_BUILDING_ID = BUILDING_ID JOIN DEPARTMENT ON BUILDING_DEPARTMENT_ID = DEPARTMENT_ID WHERE DEPARTMENT_NAME = ? ");
        preparedStatement.setString(1, department);
        ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next())
        {
            int numero = resultset.getInt("ROOM_NUMERO");
            String building = resultset.getString("BUILDING_ID");
            rooms.add(new CRoom(numero,building,department));
        }
        return rooms;
    }

    public static List<CRoom> findAll(Connection connection) throws SQLException {
        List<CRoom> rooms = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ROOM JOIN BUILDING ON ROOM_BUILDING_ID = BUILDING_ID JOIN DEPARTMENT ON BUILDING_DEPARTMENT_ID = DEPARTMENT_ID");
        ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next())
        {
            int numero = resultset.getInt("ROOM_NUMERO");
            String building = resultset.getString("BUILDING_ID");
            String department = resultset.getString("DEPARTMENT_NAME");
            rooms.add(new CRoom(numero,building,department));
        }
        return rooms;
    }

    @Override
    public String toString() {
        return "CRoom{" +
                "numero=" + numero +
                ", building='" + building + '\'' +
                ", department=" + department +
                '}';
    }
}
