package fr.univtln.tperron710.requestPackage.person.database;

import fr.univtln.tperron710.requestPackage.person.CPersonControler;
import fr.univtln.tperron710.requestPackage.person.form.CPersonFormModel;
import fr.univtln.tperron710jpoupon997.d12.project.main.CPlanningModel;

import javax.swing.*;
import java.awt.*;


/**
 * Created by toms on 17/10/15.
 */

/**
 * Vue de la liste de contact
 */
public class CDatabaseView extends JPanel {

    /**
     * Modèle observé pour rafraichir la liste
     */
    CTablePersonModel CTablePersonModel = null;
    /**
     * Modèle à observer pour remplir les champs si un contact est sélectionné
     */
    CPersonFormModel CPersonFormModel = null;

    public CPersonFormModel getCPersonFormModel() {
        return CPersonFormModel;
    }

    public CDatabaseView(CPlanningModel model) throws Exception {
        super(new BorderLayout());
        CTablePersonModel = new CTablePersonModel(model);
    }


    public CDatabaseView(CPlanningModel model, CTablePersonModel CTablePersonModel) throws Exception {
        this(model);
         CPersonFormModel = new CPersonFormModel();
        TableView(this.CTablePersonModel, CPersonFormModel);
    }

    /**
     * Initialiser la vue
     * @param CTablePersonModel Modèle observé pour rafraichir la liste
     * @param CPersonFormModel Modèle à observer pour remplir les champs si un contact est sélectionné
     * @throws Exception
     */
    public void TableView(CTablePersonModel CTablePersonModel,
                             CPersonFormModel CPersonFormModel) throws Exception {

        setBackground(Color.WHITE);
        JTabbedPane jTabbedPane = new JTabbedPane();
        JComponent jTeacherPanel = new JPanel(new BorderLayout());
        JTable jTable2  = new JTable(CTablePersonModel.getVector(),
                CTablePersonModel.getColumnTeacherName());
        jTable2.addMouseListener(new CPersonControler(CPersonFormModel));
        JScrollPane scrollPane2 = new JScrollPane(jTable2,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jTeacherPanel.add(scrollPane2, BorderLayout.CENTER);
        jTabbedPane.addTab("Contacts", jTeacherPanel);
        add(jTabbedPane,BorderLayout.CENTER);
        setVisible(true);
    }


}
