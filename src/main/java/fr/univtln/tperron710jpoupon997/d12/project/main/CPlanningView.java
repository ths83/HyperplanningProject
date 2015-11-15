package fr.univtln.tperron710jpoupon997.d12.project.main;

import fr.univtln.tperron710.requestPackage.master.window.CWindowView;
import fr.univtln.tperron710jpoupon997.d12.project.EModelMessage;
import fr.univtln.tperron710jpoupon997.d12.project.actors.*;
import fr.univtln.tperron710jpoupon997.d12.project.planning.CLessonHourly;
import fr.univtln.tperron710jpoupon997.d12.project.planning.CLessonHourlyPlanning;
import fr.univtln.tperron710jpoupon997.d12.project.planning.CPlanning;
import fr.univtln.tperron710jpoupon997.d12.project.university.CPromotion;
import fr.univtln.tperron710jpoupon997.d12.project.university.CRoom;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by jeremypoupon on 16/10/15.
 */
public class CPlanningView extends JFrame implements Observer {

    /**
     * Modèle observé
     */
    private CPlanningModel model = null;

    /**
     * Panel du planning
     */
    private JPanel hours = new JPanel(new GridBagLayout());

    /**
     * Liste des semaines
     */
    private JComboBox dateList = null;

    /**
     * Liste des salles (et des promotions)
     */
    private JComboBox planningList = null;

    public CPlanningView(CPlanningModel model) throws HeadlessException {
        super("Planning");
        this.model = model;
        initializeFrame(model.getPerson());
    }

    /**
     * Initialisation de la vue
     * @param person l'utilisateur
     */
    private void initializeFrame(CPerson person) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        createBand(container,person,constraints);
        createCenterPanel(container,person,constraints);
        setVisible(true);
    }

    /**
     * Création du bandeau contenant les informations de l'utilisateur
     * @param container le panel contenant le bandeau
     * @param person l'utilisateur
     * @param constraints les contraintes de placement
     */
    public void createBand(Container container, CPerson person, GridBagConstraints constraints)
    {
        JPanel northPanel = new JPanel(new GridBagLayout());
        container.add(northPanel,BorderLayout.NORTH);
        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(255,255,255));
        northPanel.add(namePanel, constraints);
        namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.PAGE_AXIS));
        JLabel firstName = new JLabel(person.getFirstName());
        firstName.setBorder(BorderFactory.createEmptyBorder(5,20,0,0));
        firstName.setFont(firstName.getFont().deriveFont(40.0f));
        namePanel.add(firstName);
        JLabel lastName = new JLabel(person.getLastName());
        lastName.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
        lastName.setFont(lastName.getFont().deriveFont(30.0f));
        namePanel.add(lastName);
        JPanel gradePanel = new JPanel();
        gradePanel.setBackground(new Color(255,255,255));
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        northPanel.add(gradePanel, constraints);
        gradePanel.setLayout(new BoxLayout(gradePanel,BoxLayout.Y_AXIS));
        JLabel formationLabel = new JLabel();
        formationLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 20));
        formationLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        formationLabel.setFont(formationLabel.getFont().deriveFont(40.0f));
        gradePanel.add(formationLabel);
        JLabel departmentLabel = new JLabel();
        departmentLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        departmentLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        departmentLabel.setFont(departmentLabel.getFont().deriveFont(30.0f));
        gradePanel.add(departmentLabel);
        if (person instanceof CStudent)
        {
            formationLabel.setText(((CStudent) person).getPromotion().getGrade() + " " + ((CStudent) person).getPromotion().getFormation());
            departmentLabel.setText(((CStudent) person).getPromotion().getDepartment());
        }
        else if (person instanceof CTeacher)
        {
            String subjects = "";
            for (String s : ((CTeacher) person).getSubjects())
                subjects = subjects + s + ", ";
            subjects = subjects.substring(0,subjects.length()-2);
            formationLabel.setText(subjects);
            String promotions = "";
            for (CPromotion p : ((CTeacher) person).getPromotions())
                promotions = promotions + p.getGrade() + " " + p.getFormation() + ", ";
            if (promotions.length() > 2)
                promotions = promotions.substring(0,promotions.length()-2);
            else if (promotions.length() == 0)
                promotions = " ";
            departmentLabel.setText(promotions);
        }
        else if (person instanceof CChiefOfDepartment)
        {
            formationLabel.setText(" ");
            departmentLabel.setText(((CChiefOfDepartment) person).getDepartment());
        }
        else
        {
            formationLabel.setText(" ");
            departmentLabel.setText(" ");
        }
    }

    /**
     * Création du panel central
     * @param container le panel contenant le panel central
     * @param person l'utilisateur
     * @param constraints les contraintes de placement
     */
    public void createCenterPanel(Container container, CPerson person, GridBagConstraints constraints)
    {
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(true);
        centerPanel.setBackground(Color.WHITE);
        container.add(centerPanel,BorderLayout.CENTER);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(tabbedPane.getFont().deriveFont(15.0f));
        centerPanel.add(tabbedPane);
        createPlanningPanel(person,constraints,tabbedPane);
        JPanel requestPanel = new JPanel(new BorderLayout());
        requestPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2));
        if (person instanceof CStudent)
        {
            if (person instanceof CClassRepresentative)
            {
                try {
                    tabbedPane.addTab("Requêtes",new CWindowView(model));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            try {
                tabbedPane.addTab("Requêtes",new CWindowView(model));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Création du panel du planning
     * @param person l'utilisateur
     * @param constraints les contraintes de placement
     * @param tabbedPane le panel dans lequel sera ajouté le panel
     */
    public void createPlanningPanel(CPerson person, GridBagConstraints constraints, JTabbedPane tabbedPane)
    {
        JPanel planningManagerPanel = new JPanel(new BorderLayout());
        tabbedPane.addTab("Planning",planningManagerPanel);
        createPlanningHeader(person, planningManagerPanel);
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 0.1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(0,10,0,10);
        JPanel planningPanel = new JPanel(new BorderLayout());
        planningManagerPanel.add(planningPanel);
        createDays(constraints,planningPanel);
        planningPanel.add(hours,BorderLayout.CENTER);
        createPlanning();
    }

    /**
     * Création des en-têtes du planning
     * @param person l'utilisateur
     * @param planningManagerPanel le panel dans lequel seront placés les en-têtes
     */
    public void createPlanningHeader(CPerson person, JPanel planningManagerPanel)
    {
        JPanel planningHeaderPanel = new JPanel(new BorderLayout());
        planningHeaderPanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        planningManagerPanel.add(planningHeaderPanel, BorderLayout.NORTH);
        JPanel planningListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        planningHeaderPanel.add(planningListPanel,BorderLayout.WEST);
        JLabel planningListLabel = new JLabel("Emploi du temps : ");
        planningListLabel.setFont(planningListLabel.getFont().deriveFont(15.0f));
        planningListLabel.setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
        planningListPanel.add(planningListLabel);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.MONTH,Calendar.AUGUST);
        calendar.set(Calendar.WEEK_OF_MONTH,1);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        int beginDate = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.set(Calendar.MONTH,Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH,31);
        int lastWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        while (lastWeek == 1)
        {
            calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-1);
            lastWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        }
        int currentIndex = 0;
        String monday, friday = "";
        Vector<String> dates = new Vector<>();
        for (int i = beginDate ; i < lastWeek+1 ; i++)
        {
            calendar.set(Calendar.WEEK_OF_YEAR,i);
            calendar.set(Calendar.DAY_OF_WEEK, 2);
            monday = dateFormat.format(calendar.getTime());
            calendar.set(Calendar.DAY_OF_WEEK,7);
            friday = dateFormat.format(calendar.getTime());
            dates.add("Semaine " + i + " : du " + monday + " au " + friday);
            if (i == currentWeek)
                currentIndex = dates.size()-1;
        }
        calendar.set(Calendar.YEAR,2016);
        for (int i = 1 ; i < beginDate ; i++)
        {
            calendar.set(Calendar.WEEK_OF_YEAR,i);
            calendar.set(Calendar.DAY_OF_WEEK, 2);
            monday = dateFormat.format(calendar.getTime());
            calendar.set(Calendar.DAY_OF_WEEK, 7);
            friday = dateFormat.format(calendar.getTime());
            dates.add("Semaine " + i + " : du " + monday + " au " + friday);
            if (i == currentWeek)
                currentIndex = dates.size()-1;
        }
        dateList = new JComboBox(dates);
        dateList.addItemListener(new CDateListControler(this,model));
        planningListPanel.add(dateList);
        Vector<String> rooms = new Vector<>();
        for (CRoom room : model.getRoomList())
            rooms.add(room.getBuilding() + room.getNumero());
        rooms.sort((o1, o2) -> o1.compareTo(o2));
        if (person instanceof CStudent)
        {
            rooms.add(0,"Promotion");
        }
        else if (person instanceof CTeacher)
        {
            int i = 0;
            for (CPromotion promotion : ((CTeacher) person).getPromotions())
            {
                rooms.add(i,promotion.getGrade() + " " + promotion.getFormation());
                i++;
            }
        }
        else if (person instanceof CChiefOfDepartment)
        {
            int i = 0;
            for (CPromotion promotion : ((CChiefOfDepartment) person).getPromotions())
            {
                rooms.add(i,promotion.getGrade() + " " + promotion.getFormation());
                i++;
            }
        }
        else if (person instanceof CPlanningAdministrator)
        {
            int i = 0;
            for (CPromotion promotion : ((CPlanningAdministrator) person).getPromotions())
            {
                rooms.add(i,promotion.getGrade() + " " + promotion.getFormation());
                i++;
            }
        }
        planningList = new JComboBox(rooms);
        planningList.addItemListener(new CPlanningListControler(this,model));
        planningListPanel.add(planningList);
        if (rooms.size() > 0)
        {
            planningList.setSelectedIndex(0);
        }
        dateList.setSelectedIndex(currentIndex);
        JPanel buttonPanel = new JPanel(new BorderLayout());
        planningHeaderPanel.add(buttonPanel,BorderLayout.EAST);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,50));
        JButton disconnectButton = new JButton("Déconnexion");
        disconnectButton.addActionListener(new CPlanningDisconnectListener(model));
        buttonPanel.add(disconnectButton,BorderLayout.CENTER);
    }

    /**
     * Création des jours de la semaine
     * @param constraints les contraintes de placement
     * @param planningPanel le panel dans lequel seront ajoutés les jours
     */
    public void createDays(GridBagConstraints constraints, JPanel planningPanel)
    {
        JPanel daysPanel = new JPanel(new GridBagLayout());
        planningPanel.add(daysPanel,BorderLayout.WEST);
        daysPanel.add(new JLabel("Jour"),constraints);
        constraints.weighty = 1;
        constraints.gridy++;
        daysPanel.add(new JLabel("Lundi"),constraints);
        constraints.gridy++;
        daysPanel.add(new JLabel("Mardi"),constraints);
        constraints.gridy++;
        daysPanel.add(new JLabel("Mercredi"),constraints);
        constraints.gridy++;
        daysPanel.add(new JLabel("Jeudi"),constraints);
        constraints.gridy++;
        daysPanel.add(new JLabel("Vendredi"),constraints);
        constraints.gridy++;
        daysPanel.add(new JLabel("Samedi"),constraints);
        constraints.gridy++;
    }

    /**
     * Création des heures
     * @param constraints les contraintes de placement
     */
    public void createHours(GridBagConstraints constraints)
    {
        constraints.insets = new Insets(0,0,0,0);
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.anchor = GridBagConstraints.SOUTHWEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 0.1;
        for (int i = 0 ; i < 22 ; i++)
        {
            if (i%2 == 0)
            {
                hours.add(new JLabel(((i/2)+8) + "h"),constraints);
                constraints.gridx++;
            }
            else
            {
                hours.add(new JLabel(Integer.toString(30)),constraints);
                constraints.gridx++;
            }
        }
    }

    /**
     * Création du planning
     */
    public void createPlanning()
    {
        hours.removeAll();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 0.1;
        constraints.anchor = GridBagConstraints.LINE_START;
        createHours(constraints);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weighty = 1;
        CLessonHourlyPlanning hourlyPlanning = null;
        if (model.getPlanning() == null)
        {
            hourlyPlanning = new CLessonHourlyPlanning();
        }
        else
        {
            hourlyPlanning = (CLessonHourlyPlanning) model.getPlanning();
        }
        JLabel hour;
        if (hourlyPlanning.getHourlies().size() == 0)
        {
            for (int i = 0 ; i < 6 ; i++)
            {
                constraints.gridx = 0;
                for (int j = 0 ; j < 22 ; j++)
                {
                    hour = new JLabel("<html><br><br><br><br><br></html>");
                    hour.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                    hours.add(hour, constraints);
                    constraints.gridx++;
                }
                constraints.gridy++;
            }
        }
        else
        {
            for (int i = 0 ; i < 6 ; i++)
            {
                constraints.gridx = 0;
                if (hourlyPlanning.getHourlies().get(i).size() == 0)
                {
                    constraints.gridy = i + 1;
                    for (int j = 0 ; j < 22 ; j++)
                    {
                        hour = new JLabel("<html><br><br><br><br><br></html>");
                        hour.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                        hours.add(hour, constraints);
                        constraints.gridx++;
                    }
                }
                else
                {
                    constraints.gridx = 0;
                    constraints.gridy = i+1;
                    hourlyPlanning.getHourlies();
                    List<CLessonHourly> hourlies = hourlyPlanning.getHourlies().get(i);
                    int currentHour = 8;
                    int currentMinute = 0;
                    for (CLessonHourly hourly : hourlies)
                    {
                        String[] beginHourParameters = hourly.getLesson().getBeginHour().split(":");
                        int lessonBeginHour = Integer.parseInt(beginHourParameters[0]);
                        int lessonBeginMinute = Integer.parseInt(beginHourParameters[1]);
                        String[] endHourParameters = hourly.getLesson().getEndHour().split(":");
                        int lessonEndHour = Integer.parseInt(endHourParameters[0]);
                        int lessonEndMinute = Integer.parseInt(endHourParameters[1]);
                        if (lessonBeginHour > currentHour)
                        {
                            for (int j = 0 ; j < (lessonBeginHour-currentHour)*2 ; j++)
                            {
                                constraints.gridwidth = 1;
                                hour = new JLabel();
                                hour.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                                hours.add(hour, constraints);
                                constraints.gridx++;
                            }
                            currentHour = lessonBeginHour;
                        }
                        if (lessonBeginMinute != currentMinute)
                        {
                            constraints.gridwidth = 1;
                            hour = new JLabel();
                            hour.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                            hours.add(hour, constraints);
                            constraints.gridx++;
                            if (currentMinute == 30)
                                currentMinute = 0;
                            else
                                currentMinute = 30;
                        }
                        int duration = 0;
                        if (currentMinute == lessonEndMinute)
                        {
                            duration = (lessonEndHour-currentHour)*2;
                            if (lessonEndMinute != 0)
                                duration++;
                        }
                        else
                        {
                            duration = ((lessonEndHour-currentHour)*2)-1;
                            if (lessonEndMinute != 0)
                                duration++;
                        }
                        currentHour = lessonEndHour;
                        currentMinute = lessonEndMinute;
                        constraints.gridwidth = duration;
                        hour = new JLabel("<html><div style=\\\"text-align: center>" + hourly.getLesson().getSubject() + "<br>" + hourly.getLesson().getTeacher() + "<br>" + hourly.getLesson().getPromotion().getGrade() + " " + hourly.getLesson().getPromotion().getFormation() + "<br>" + hourly.getLesson().getClassroom().getBuilding() + hourly.getLesson().getClassroom().getNumero() + "<br>" + hourly.getLesson().getLessonType().toString() + "</div></html>",SwingConstants.CENTER);
                        hour.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                        switch (hourly.getLesson().getLessonType())
                        {
                            case MagistralLesson:
                                hour.setBackground(Color.RED);
                                break;
                            case Exercises:
                                hour.setBackground(Color.CYAN);
                                break;
                            case PracticalWorking:
                                hour.setBackground(Color.GREEN);
                                break;
                        }
                        hour.setOpaque(true);
                        hours.add(hour, constraints);
                        constraints.gridx += duration;
                        if (hourlies.indexOf(hourly) == hourlies.size()-1)
                        {
                            if (currentMinute == 0)
                            {
                                for (int j = 0 ; j < (19-currentHour)*2 ; j++)
                                {
                                    constraints.gridwidth = 1;
                                    hour = new JLabel();
                                    hour.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                                    hours.add(hour, constraints);
                                    constraints.gridx++;
                                }
                            }
                            else
                            {
                                for (int j = 0 ; j < (19-currentHour)*2-1 ; j++)
                                {
                                    constraints.gridwidth = 1;
                                    hour = new JLabel();
                                    hour.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                                    hours.add(hour, constraints);
                                    constraints.gridx++;
                                }
                            }
                        }
                    }
                }
            }
        }
        hours.revalidate();
        hours.repaint();
    }

    /**
     * Retourne la liste des dates
     * @return la liste des dates
     */
    public JComboBox getDateList() {
        return dateList;
    }

    /**
     * Retourne la liste des plannings (et des promotions)
     * @return la liste des plannings (et des promotions)
     */
    public JComboBox getPlanningList() {
        return planningList;
    }

    /**
     * Mise à jour de la vue
     * @param o le modèle d'origine de la notification
     * @param arg argument facultatif influant sur la mise à jour
     */
    @Override
    public void update(Observable o, Object arg) {
        // Si l'argument est un planning, on l'affiche
        if (arg instanceof CPlanning)
            createPlanning();
        // Si l'argument est un message, on ferme la fenêtre
        if (arg instanceof EModelMessage)
        {
            if (arg == EModelMessage.DECONNEXION)
                close();
        }
    }

    /**
     * Fermeture de la fenêtre
     */
    public void close()
    {
        setVisible(false);
        dispose();
    }
}
