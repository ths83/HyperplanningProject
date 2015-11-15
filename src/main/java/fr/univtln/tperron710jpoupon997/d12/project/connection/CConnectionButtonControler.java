package fr.univtln.tperron710jpoupon997.d12.project.connection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jeremypoupon on 15/10/15.
 */

/**
 * Contrôleur du bouton de connexion
 */
public class CConnectionButtonControler implements ActionListener {

    /**
     * Modèle à notifier
     */
    private CConnectionModel model = null;

    /**
     * Vue d'origine de la notification
     */
    private static CConnectionView view = null;

    public CConnectionButtonControler(CConnectionModel model, CConnectionView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Retourne le modèle
     * @return le modèle
     */
    public CConnectionModel getModel() {
        return model;
    }

    /**
     * Définit le modèle
     * @param model le modèle
     */
    public void setModel(CConnectionModel model) {
        this.model = model;
    }

    /**
     * Retourne la vue
     * @return la vue
     */
    public static CConnectionView getView() {
        return view;
    }

    /**
     * Définit la vue
     * @param view la vue
     */
    public void setView(CConnectionView view) {
        this.view = view;
    }

    /**
     * Notifie le modèle lorsqu'une action (ici un clic) est effectuée
     * @param e l'action
     */
    public void actionPerformed(ActionEvent e) {
        String login = view.getLoginField().getText();
        String password = String.valueOf(view.getPasswordField().getPassword());
        if (!login.equals("") && !password.equals(""))
            model.connect(login, password);
        else
            model.informMissingField();
    }

}
