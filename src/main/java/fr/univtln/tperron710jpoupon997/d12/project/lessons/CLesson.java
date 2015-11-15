package fr.univtln.tperron710jpoupon997.d12.project.lessons;

import fr.univtln.tperron710jpoupon997.d12.project.actors.*;
import fr.univtln.tperron710jpoupon997.d12.project.database.IEntity;
import fr.univtln.tperron710jpoupon997.d12.project.university.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by jeremypoupon on 12/10/15.
 */

/**
 * Cours
 */
public class CLesson {

    /**
     * Salle de cours
     */
    private CClassroom classroom = null;

    /**
     * Heure de début
     */
    private String beginHour = "";

    /**
     * Heure de fin
     */
    private String endHour = "";

    /**
     * Date
     */
    private String date = "";

    /**
     * Matière
     */
    private String subject = "";

    /**
     * Professeur
     */
    private String teacher = "";

    /**
     * Promotion
     */
    private CPromotion promotion = null;

    /**
     * Type de cours
     */
    private ELessonType lessonType = null;

    public CLesson(CClassroom classroom, String beginHour, String endHour, String date, String subject,
                   String teacher, CPromotion promotion, ELessonType lessonType) {
        this.classroom = classroom;
        this.beginHour = beginHour;
        this.endHour = endHour;
        this.date = date;
        this.subject = subject;
        this.teacher = teacher;
        this.promotion = promotion;
        this.lessonType = lessonType;
    }

    /**
     * Retourne la salle de cours
     * @return la salle de cours
     */
    public CClassroom getClassroom() {
        return classroom;
    }

    /**
     * Retourne l'heure de début
     * @return l'heure de début
     */
    public String getBeginHour() {
        return beginHour;
    }

    /**
     * Retourne l'heure de fin
     * @return l'heure de fin
     */
    public String getEndHour() {
        return endHour;
    }

    /**
     * Retourne la date
     * @return la date
     */
    public String getDate() {
        return date;
    }

    /**
     * Définit la date
     * @param date la date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Retourne la matière
     * @return la matière
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Retourne le professeur
     * @return le professeur
     */
    public String getTeacher() {
        return teacher;
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
     * Retourne le type de cours
     * @return le type de cours
     */
    public ELessonType getLessonType() {
        return lessonType;
    }

    /**
     * Retourne la description textuelle
     * @return description textuelle
     */
    @Override
    public String toString() {
        return "CLesson{" +
                "classroom=" + classroom +
                ", beginHour='" + beginHour + '\'' +
                ", endHour='" + endHour + '\'' +
                ", date='" + date + '\'' +
                ", subject=" + subject +
                ", teacher=" + teacher +
                ", promotion=" + promotion +
                ", lessonType=" + lessonType +
                '}';
    }
}
