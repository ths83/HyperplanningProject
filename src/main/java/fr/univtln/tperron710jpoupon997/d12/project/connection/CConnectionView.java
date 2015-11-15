package fr.univtln.tperron710jpoupon997.d12.project.connection;

import fr.univtln.tperron710jpoupon997.d12.project.EModelMessage;
import fr.univtln.tperron710jpoupon997.d12.project.actors.CPerson;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Hello world!
 *
 */
public class CConnectionView extends JFrame implements Observer
{
    /**
     * Contrôleur du bouton de connexion
     */
    private CConnectionButtonControler listener = null;

    /**
     * Champ contenant le login
     */
    private JTextField loginField = null;

    /**
     * Champ contenant le mot de passe
     */
    private JPasswordField passwordField = null;

    public CConnectionView(CConnectionModel model) throws HeadlessException {
        super("Première app");
        this.listener = new CConnectionButtonControler(model,this);
        createJFrame();
    }

    /**
     * Initialisation de la vue
     */
    private void createJFrame() {
        //Define frame settings
        setSize(new Dimension(1000, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) (screenSize.getWidth() - getSize().getWidth()) / 2, (int) (screenSize.getHeight() - getSize().getHeight()) / 2);
        Container container = getContentPane();
        //Create title panel
        JPanel northPanel = new JPanel(new GridLayout(2,1));
        northPanel.setBackground(new Color(255, 255, 255));
        container.add(northPanel, BorderLayout.NORTH);
        //Create title label
        JLabel title = new JLabel("Bienvenue sur l'intranet de l'université");
        title.setForeground(new Color(30,144,255));
        title.setFont(title.getFont().deriveFont(45.0f));
        Border line = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(30, 144, 255));
        Border empty = BorderFactory.createEmptyBorder(0, 50, 0, 0);
        CompoundBorder border = new CompoundBorder(line,empty);
        title.setBorder(border);
        northPanel.add(title);
        //Create subtitle label
        JLabel subTitle = new JLabel("AUTHENTIFICATION");
        subTitle.setForeground(new Color(100,100,100));
        subTitle.setFont(subTitle.getFont().deriveFont(30.0f));
        subTitle.setFont(new Font(subTitle.getFont().getFontName(), Font.BOLD, subTitle.getFont().getSize()));
        subTitle.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        subTitle.setHorizontalAlignment(SwingConstants.CENTER);
        northPanel.add(subTitle);
        //Create center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(255, 255, 255));
        container.add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(Box.createRigidArea(new Dimension(0,50)));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        //Create login panel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setMaximumSize(new Dimension(1000,75));
        loginPanel.setBackground(new Color(255, 255, 255));
        centerPanel.add(loginPanel);
        JLabel loginLabel = new JLabel("Login : ");
        loginLabel.setFont(loginLabel.getFont().deriveFont(20.0f));
        loginLabel.setFont(loginLabel.getFont().deriveFont(Font.PLAIN));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(0,250,0,0);
        loginPanel.add(loginLabel, gridBagConstraints);
        loginField = new JTextField(20);
        line = BorderFactory.createLineBorder(Color.GRAY, 1);
        empty = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        border = new CompoundBorder(line,empty);
        loginField.setBorder(line);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new Insets(0,0,0,250);
        loginPanel.add(loginField,gridBagConstraints);
        //Create password label
        JPanel passwordPanel = new JPanel(new GridBagLayout());
        passwordPanel.setMaximumSize(new Dimension(1000,50));
        passwordPanel.setBackground(new Color(255, 255, 255));
        centerPanel.add(passwordPanel);
        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(20.0f));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(0,250,0,0);
        passwordPanel.add(passwordLabel, gridBagConstraints);
        passwordField = new JPasswordField(20);
        passwordField.setBorder(border);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new Insets(0,0,0,250);
        passwordPanel.add(passwordField, gridBagConstraints);
        JButton validateButton = new JButton("Connexion");
        validateButton.setBackground(new Color(30,144,255));
        validateButton.setContentAreaFilled(false);
        validateButton.setBorderPainted(false);
        validateButton.setOpaque(true);
        validateButton.setForeground(new Color(255, 255, 255));
        validateButton.setMaximumSize(new Dimension(250,35));
        validateButton.addActionListener(listener);
        centerPanel.add(validateButton);
        //Showing frame
        setVisible(true);
    }

    /**
     * Retourne le login
     * @return le login
     */
    public JTextField getLoginField() {
        return loginField;
    }

    /**
     * Retourne le mot de passe
     * @return
     */
    public JPasswordField getPasswordField() {
        return passwordField;
    }

    /**
     * Mise à jour de la vue
     * @param o modèle à l'origine de la notification
     * @param arg argument facultatif influant sur la mise à jour
     */
    public void update(Observable o, Object arg) {
        // Si arg est une personne, on ferme la vue
        if (arg instanceof CPerson)
            close();
        //Si arg est un message d'erreur, on informe l'utilisateur
        else if (arg instanceof EModelMessage)
        {
            if (arg.equals(EModelMessage.AUTHENTIFICATION_INCORRECTE))
            {
                JOptionPane.showMessageDialog(null,"Le pseudonyme et le mot de passe saisis ne correspondent à aucun compte","Authentification incorrecte",JOptionPane.ERROR_MESSAGE);
                loginField.setText("");
                passwordField.setText("");
            }
            else if (arg.equals(EModelMessage.CHAMP_VIDE))
                JOptionPane.showMessageDialog(null,"Les champs n'ont pas tous été remplis","Erreur",JOptionPane.INFORMATION_MESSAGE);
            else if (arg.equals(EModelMessage.CONNEXION_ECHOUEE))
                JOptionPane.showMessageDialog(null,"Impossible de se connecter. Veuillez réessayer ultérieurement","Connexion impossible",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Fermeture de la vue
     */
    public void close()
    {
        setVisible(false);
        dispose();
    }
}