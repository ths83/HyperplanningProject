package fr.univtln.tperron710jpoupon997.d12.project.main;

import fr.univtln.tperron710jpoupon997.d12.project.EModelMessage;
import fr.univtln.tperron710jpoupon997.d12.project.actors.*;
import fr.univtln.tperron710jpoupon997.d12.project.connection.CConnectionModel;
import fr.univtln.tperron710jpoupon997.d12.project.connection.CConnectionView;
import fr.univtln.tperron710jpoupon997.d12.project.database.CDataSource;
import fr.univtln.tperron710jpoupon997.d12.project.database.CEntityManager;
import fr.univtln.tperron710jpoupon997.d12.project.database.IEntity;
import fr.univtln.tperron710jpoupon997.d12.project.planning.AHourly;
import fr.univtln.tperron710jpoupon997.d12.project.planning.CLessonHourly;
import fr.univtln.tperron710jpoupon997.d12.project.planning.CLessonHourlyPlanning;
import fr.univtln.tperron710jpoupon997.d12.project.planning.CPlanning;
import fr.univtln.tperron710jpoupon997.d12.project.university.CRoom;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jeremypoupon on 20/10/15.
 */

/**
 * Modèle de la vue principale
 */
public class CPlanningModel extends Observable {

    /**
     * Utilisateur
     */
    private CPerson person = null;

    /**
     * Liste des salles et des promotions
     */
    private List<CRoom> roomList = new ArrayList<>();

    /**
     * Gestionnaire de connexions
     */
    private CDataSource dataSource = null;

    /**
     * Planning courant
     */
    private CPlanning planning = null;

    public CPlanningModel(CPerson person, CDataSource dataSource) {
        try {
            this.person = person;
            this.dataSource = dataSource;
            if (person instanceof CStudent)
            {
                roomList = CRoom.findByDepartment(dataSource.getConnection(),((CStudent) person).getPromotion().getDepartment());
            }
            else if (person instanceof CChiefOfDepartment)
            {
                roomList = CRoom.findByDepartment(dataSource.getConnection(), ((CChiefOfDepartment) person).getDepartment());
            }
            else if (person instanceof CPlanningAdministrator)
            {
                roomList = CRoom.findAll(dataSource.getConnection());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne l'utilisateur
     * @return l'utilisateur
     */
    public CPerson getPerson() {
        return person;
    }

    /**
     * Retourne la liste des salles et des promotions
     * @return la liste des salles et des promotions
     */
    public List<CRoom> getRoomList() {
        return roomList;
    }

    /**
     * Retourne le planning
     * @return le planning
     */
    public CPlanning getPlanning() {
        return planning;
    }

    /**
     * Définit le planning d'une promotion selon la semaine et la salle (ou promotion)
     * @param week la semaine
     * @param promotionId la salle (ou promotion)
     */
    public void setPromotionPlanning (Date week, int promotionId)
    {
        try {
            String[] dates = new String[6];
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(week);
            for (int i = 2 ; i < 8 ; i++)
            {
                calendar.set(Calendar.DAY_OF_WEEK,i);
                Date date = calendar.getTime();
                dates[i-2] = dateFormat.format(date);
            }
            planning = new CLessonHourlyPlanning();
            ((CLessonHourlyPlanning) planning).setHourlies(CLessonHourly.findByDatesAndPromotion(dataSource.getConnection(),dates,promotionId));
            setChanged();
            notifyObservers(planning);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Définit le planning d'une salle selon une semaine et une salle
     * @param firstDayOfWeek semaine
     * @param classRoomNumero numero de la salle
     * @param classRoomBuilding bâtiment de la salle
     */
    public void setClassroomPlanning (Date firstDayOfWeek, int classRoomNumero, String classRoomBuilding)
    {
        try {
            String[] dates = new String[6];
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(firstDayOfWeek);
            for (int i = 2 ; i < 8 ; i++)
            {
                calendar.set(Calendar.DAY_OF_WEEK,i);
                Date date = calendar.getTime();
                dates[i-2] = dateFormat.format(date);
            }
            planning = new CLessonHourlyPlanning();
            ((CLessonHourlyPlanning) planning).setHourlies(CLessonHourly.findByDatesAndClassRoom(dataSource.getConnection(),dates,classRoomNumero,classRoomBuilding));
            setChanged();
            notifyObservers(planning);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deconnexion de l'utilisateur
     */
    public void disconnect()
    {
        setChanged();
        notifyObservers(EModelMessage.DECONNEXION);
        CConnectionModel model = new CConnectionModel();
        CConnectionView view = new CConnectionView(model);
        model.addObserver(view);
    }
}
