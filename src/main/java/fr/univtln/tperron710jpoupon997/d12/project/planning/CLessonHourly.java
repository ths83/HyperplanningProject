package fr.univtln.tperron710jpoupon997.d12.project.planning;

import fr.univtln.tperron710jpoupon997.d12.project.database.IEntity;
import fr.univtln.tperron710jpoupon997.d12.project.lessons.CClassroom;
import fr.univtln.tperron710jpoupon997.d12.project.lessons.CLesson;
import fr.univtln.tperron710jpoupon997.d12.project.university.CPromotion;
import fr.univtln.tperron710jpoupon997.d12.project.university.ELessonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeremypoupon on 12/10/15.
 */

/**
 * Entité représentant un créneau horaire de cours
 */
public class CLessonHourly extends AHourly implements IEntity {

    /**
     * Cours enseigné
     */
    private CLesson lesson = null;

    public CLessonHourly(CLesson lesson) {
        super(lesson.getBeginHour(), lesson.getEndHour());
        this.lesson = lesson;
    }

    /**
     * Retourne le cours
     * @return le cours
     */
    public CLesson getLesson() {
        return lesson;
    }

    /**
     * Rend une entité persistante
     * @param connection la connexion utilisée
     * @return vrai en cas de succès, faux sinon
     * @throws SQLException
     */
    @Override
    public boolean persist(Connection connection) throws SQLException {
        return false;
    }

    /**
     * Efface une entité persistante
     * @param connection la connexion utilisée
     * @return vrai en cas de succès, faux sinon
     * @throws SQLException
     */
    @Override
    public boolean remove(Connection connection) throws SQLException {
        return false;
    }

    /**
     * Met à jour une entité persistante
     * @param connection la connexion utilisée
     * @return vrai en cas de succès, faux sinon
     * @throws SQLException
     */
    @Override
    public boolean merge(Connection connection) throws SQLException {
        return false;
    }

    /**
     * Retourne un tableau de créneaux
     * @param connection la connexion utilisée
     * @param dates un jour de la semaine choisie
     * @param promotion la promotion choisie
     * @return le planning de la promotion choisie pour la date choisie
     * @throws SQLException
     */
    public static List<List<CLessonHourly>> findByDatesAndPromotion(Connection connection, String[] dates, int promotion) throws SQLException
    {
        List<List<CLessonHourly>> lessons = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CLASS JOIN PROMOTION ON CLASS_PROMOTION_ID = PROMOTION_ID JOIN FORMATION ON PROMOTION_FORMATION_ID = FORMATION_ID JOIN GRADE ON PROMOTION_GRADE_ID = GRADE_ID JOIN SUBJECT ON CLASS_SUBJECT_ID = SUBJECT_ID JOIN PERSON ON CLASS_TEACHER_ID = PERSON_ID JOIN ROOM ON CLASS_ROOM_ID = ROOM_ID WHERE CLASS_DATE = ? AND CLASS_PROMOTION_ID = ? ");
        preparedStatement.setInt(2,promotion);
        for (int i = 0 ; i < dates.length ; i++)
        {
            lessons.add(new ArrayList<>());
            preparedStatement.setString(1, dates[i]);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next())
            {
                String date = resultset.getString("class_date");
                String beginHour = resultset.getString("class_begin_hour");
                String endHour = resultset.getString("class_end_hour");
                String subject = resultset.getString("subject_name");
                String teacher = resultset.getString("person_last_name");
                teacher = teacher + " " + resultset.getString("person_first_name");
                int id =  resultset.getInt("promotion_id");
                String formation = resultset.getString("formation_name");
                String grade = resultset.getString("grade_name");
                int classRoomNumero = resultset.getInt("room_numero");
                String classRoomBuilding = resultset.getString("room_building_id");
                int lessonType = resultset.getInt("class_type_id");
                ELessonType eLessonType = null;
                switch (lessonType)
                {
                    case 0:
                        eLessonType = ELessonType.MagistralLesson;
                        break;
                    case 1:
                        eLessonType = ELessonType.Exercises;
                        break;
                    case 2:
                        eLessonType = ELessonType.PracticalWorking;
                        break;
                }
                lessons.get(i).add(new CLessonHourly(new CLesson(new CClassroom(classRoomBuilding, classRoomNumero, 0), beginHour, endHour, date, subject, teacher, new CPromotion(id, formation, grade, null), eLessonType)));
            }
        }
        return lessons;
    }

    /**
     * Retourne un tableau de créneaux
     * @param connection la connexion utilisée
     * @param dates un jour de la semaine choisie
     * @param classRoomNumero le numero de la salle choisie
     * @param classRoomBuilding le bâtiment de la salle choisie
     * @return le planning de la promotion choisie pour la date choisie
     * @throws SQLException
     */
    public static List<List<CLessonHourly>> findByDatesAndClassRoom(Connection connection, String[] dates, int classRoomNumero, String classRoomBuilding) throws SQLException
    {
        List<List<CLessonHourly>> lessons = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CLASS JOIN PROMOTION ON CLASS_PROMOTION_ID = PROMOTION_ID JOIN FORMATION ON PROMOTION_FORMATION_ID = FORMATION_ID JOIN GRADE ON PROMOTION_GRADE_ID = GRADE_ID JOIN SUBJECT ON CLASS_SUBJECT_ID = SUBJECT_ID JOIN PERSON ON CLASS_TEACHER_ID = PERSON_ID JOIN ROOM ON CLASS_ROOM_ID = ROOM_ID WHERE CLASS_DATE = ? AND ROOM_NUMERO = ? AND ROOM_BUILDING_ID = ?");
        preparedStatement.setInt(2,classRoomNumero);
        preparedStatement.setString(3,classRoomBuilding);
        for (int i = 0 ; i < dates.length ; i++)
        {
            lessons.add(new ArrayList<>());
            preparedStatement.setString(1, dates[i]);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next())
            {
                String date = resultset.getString("class_date");
                String beginHour = resultset.getString("class_begin_hour");
                String endHour = resultset.getString("class_end_hour");
                String subject = resultset.getString("subject_name");
                String teacher = resultset.getString("person_last_name");
                teacher = teacher + " " + resultset.getString("person_first_name");
                int id =  resultset.getInt("promotion_id");
                String formation = resultset.getString("formation_name");
                String grade = resultset.getString("grade_name");
                int lessonType = resultset.getInt("class_type_id");
                ELessonType eLessonType = null;
                switch (lessonType)
                {
                    case 0:
                        eLessonType = ELessonType.MagistralLesson;
                        break;
                    case 1:
                        eLessonType = ELessonType.Exercises;
                        break;
                    case 2:
                        eLessonType = ELessonType.PracticalWorking;
                        break;
                }
                lessons.get(i).add(new CLessonHourly(new CLesson(new CClassroom(classRoomBuilding,classRoomNumero,0),beginHour,endHour,date,subject,teacher,new CPromotion(id,formation,grade,null),eLessonType)));
            }
        }
        return lessons;
    }

    /**
     * Description textuelle
     * @return description textuelle
     */
    @Override
    public String toString() {
        return "CLessonHourly{" +
                "lesson=" + lesson +
                '}' + "\n";
    }
}
