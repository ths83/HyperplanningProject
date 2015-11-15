package fr.univtln.tperron710jpoupon997.d12.project.university;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeremypoupon on 12/10/15.
 */
public class CPromotion {

    private String formation = "";

    private String grade = "";

    private String department = "";

    private int id = 0;

    public CPromotion(int id, String formation, String grade, String department) {
        this.id = id;
        this.formation = formation;
        this.grade = grade;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "CPromotion{" +
                "formation='" + formation + '\'' +
                ", grade='" + grade + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

}
