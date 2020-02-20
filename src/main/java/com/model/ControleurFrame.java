package com.model;

import com.DAO.ConnectionSingletonDAO;
import static com.DAO.ConnectionSingletonDAO.getConnection;
import com.bean.exception.ExceptionPersonnaliser;
import com.model.AffichageListeFrame;
import com.model.FormulaireFrame;
import com.model.MenuFrame;
import com.bean.metier.Client;
import com.bean.utilitaire.DomainSociete;
import com.bean.utilitaire.Interet;
import com.bean.metier.Prospect;
import com.bean.metier.Societe;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maxime
 */
public class ControleurFrame {

    // fenêtre du Menu 
    private MenuFrame menuFrame;
    // fenêtre du Formulaire 
    private FormulaireFrame formulaireFrame;
    // fenêtre d'affichage de la liste
    private AffichageListeFrame frameAfficherListe;

    // Stock le choix Client ou Prospect.
    private String selectionMenu;
    // Stock le choix de l'action à réalisé.
    private String selectionActionUser;
    // Choix user saisi dans la comboBox.
    private Societe selectComboClientProspect;

    /**
     * Ce classe permet le controle entre de l'application.
     *
     * @param menuFrame type MenuFrame
     * @param formulaireFrame type FormulaireFrame
     * @param frameAfficherListe type AffichageListeFrame
     */
    public ControleurFrame(MenuFrame menuFrame, FormulaireFrame formulaireFrame, AffichageListeFrame frameAfficherListe) {

        // ajout des frames
        this.setFrameMenu(menuFrame);
        this.setFrameFormulaire(formulaireFrame);
        this.setAfficherListe(frameAfficherListe);

        // rendre visible dès le début du programme
        this.menuFrame.setVisible(true);

        // initialisation du context
        this.initMenuFrame();

    }

    /**
     * Méthode permettant d'afficher les boutons :
     * <ul>
     * <li>Client</li>
     * <li>Prospect</li>
     * <li>Menu</li>
     * </ul>
     *
     * Le boolean champs permet de choisir si vous voulez afficher :
     * <ul>
     * <li>Ajouter</li>
     * <li>Modifier</li>
     * <li>Supprimer</li>
     * <li>Afficher</li>
     * <li>Valider</li>
     * <li>ComboBox</li>
     * </ul>
     *
     * @param champs de type boolean.
     */
    public void initMenuFrame() {
        // Affichage de la fenêtre menu
        this.menuFrame.setVisible(true);

        // masquer les fenêtres
        this.formulaireFrame.setVisible(false);
        this.frameAfficherListe.setVisible(false);

        // Titre du menu
        this.menuFrame.getLabelTitreMenuDeSelection()
                .setText("Menu de sélection");

        // Remise à zero du choix du client ou du prospect.
        this.selectComboClientProspect = null;
        this.selectionActionUser = null;

        // Bouton sélection
        this.menuFrame.getPanClientProspect().setVisible(true);
        this.menuFrame.getPanFooter().setVisible(true);

        // Masquer panneau de validation de selection
        this.menuFrame.getPanValidationUser().setVisible(false);
        this.menuFrame.getPanActionUser().setVisible(false);

        // initialisation des comboBox Domain et Intert
        this.initComboDomain();
        this.initComboInteret();

    }

    /**
     * Permet le blocage de la saisi des champs texte du formulaire. Pour bloqué
     * false, débloqué true.
     *
     * @param visibleFields de type boolean.
     */
    private void masqueChamps(boolean visibleFields) {

        // Pour chaque composant du formulaire
        for (Component composant : this.formulaireFrame.getPanFormulaire().getComponents()) {

            // si c'est un Jtext Field
            if (composant instanceof JTextField) {

                // caste pour recupérer le champs 
                JTextField jtf = (JTextField) composant;

                // le rendre inéditable 
                jtf.setEditable(visibleFields);
            }
        }
    }

    /*
     * ******************************************Getter et setter => fenêtre
     */
    /**
     * Modification de la fenêtre Menu.
     *
     * @param frameMenu de type FrameMenuMain étendu de JFrame.
     */
    public void setFrameMenu(MenuFrame frameMenu) {
        this.menuFrame = frameMenu;
    }

    /**
     * Modification de la fenêtre Formulaire.
     *
     * @param frameFormulaire de type FrameFormulair étendu de JFrame.
     */
    public void setFrameFormulaire(FormulaireFrame frameFormulaire) {
        this.formulaireFrame = frameFormulaire;
    }

    /**
     * Récupération de la fenêtere afficher Liste.
     *
     * @param frameAfficherListe de type FrameAfficherListes étendu de JFrame.
     */
    public void setAfficherListe(AffichageListeFrame frameAfficherListe) {
        this.frameAfficherListe = frameAfficherListe;
    }

    /*
     * ****************************** Évenement lié à la fenêtre du Menu *****
     */
    /**
     * Évenement du bouton Client.
     *
     * @param evt de type ActionEvent.
     */
    public void bpClientEvent(ActionEvent evt) {

        // mise a jour du choix de selection user.
        this.selectionMenu = "Menu Client";

        // visibilité des panneaux
        this.menuFrame.getPanActionUser().setVisible(true);
        this.menuFrame.getPanClientProspect().setVisible(false);

        // ajoute au titre du menu de selection le choix utilisateur.
        this.menuFrame.getLabelTitreMenuDeSelection().
                setText(this.selectionMenu);

        // trie de la liste des prospects
        Client.trieClient(Client.getListeClient());

        // création du nouveau model
        DefaultComboBoxModel model = new DefaultComboBoxModel(Client.
                getListeClient().
                toArray());

        // change le nom de la liste
        model.setSelectedItem("Liste des Clients");

        // Ajout du nouveau Model à la comboBox
        this.menuFrame.getJComboBoxListeSociete().setModel(model);
    }

    /**
     * Évenement du bouton Prospect.
     *
     * @param evt de type ActionEvent.
     */
    public void bpProspectEvent(ActionEvent evt) {

        // mise a jour du choix de selection user.
        this.selectionMenu = "Menu Prospect";

        // visibilité des panneaux 
        this.menuFrame.getPanActionUser().setVisible(true);
        this.menuFrame.getPanClientProspect().setVisible(false);

        // Ajoute au titre du menu de selection le choix utilisateur.
        this.menuFrame.getLabelTitreMenuDeSelection().
                setText(this.selectionMenu);

        // création du nouveau model
        DefaultComboBoxModel model = new DefaultComboBoxModel(Prospect.
                getListePropect().
                toArray());

        // Change le nom de la liste
        model.setSelectedItem("Liste des Prospects");

        // Trie de la liste clients
        Prospect.trieProspect(Prospect.getListePropect());

        // Ajout du nouveau Model à la comboBox
        this.menuFrame.getJComboBoxListeSociete().setModel(model);
    }

    /**
     * Évenement de la comboBox liée a la liste des clients et des prospects.
     *
     * @param evt de type ActionEvent.
     */
    public void comboBoxMenuListeEvent(ActionEvent evt) {

        // En fonction du choix de l'action choisi par l'utilisateur.
        switch (this.selectionMenu) {

            // si le choix est Client
            case "Menu Client":

                // récupération du Client dans la liste des Clients
                this.selectComboClientProspect = (Client) this.menuFrame
                        .getJComboBoxListeSociete()
                        .getSelectedItem();

                break;

            // si le choix est Prospect
            case "Menu Prospect":

                // récupération du Prospect dans la liste des Prospects
                this.selectComboClientProspect = (Prospect) this.menuFrame
                        .getJComboBoxListeSociete()
                        .getSelectedItem();
                break;
        }
    }

    /**
     * Évenement du bouton Modifier.
     *
     * @param evt de type ActionEvent.
     */
    public void bpModifierMenuEvent(ActionEvent evt) {

        // sélection du choix d'action utilisateur
        this.selectionActionUser = "Modifier";

        // masquer panneau de validation de selection
        this.menuFrame.getPanValidationUser().setVisible(true);

    }

    /**
     * Évenement du bouton Supprimer.
     *
     * @param evt de type ActionEvent.
     */
    public void bpSupprimerMenuEvent(ActionEvent evt) {

        // sélection du choix utilisateur
        this.selectionActionUser = "Supprimer";

        // masquer panneau de validation de sélection
        this.menuFrame.getPanValidationUser().setVisible(true);

    }

    /**
     * Évenement du bouton Afficher, pour affiché la liste de Clients ou des
     * Prospects
     *
     * @param evt de type ActionEvent.
     */
    public void bpAfficherMenuEvent(ActionEvent evt) {

        // sélection du choix utilisateur
        this.selectionActionUser = "Affiche_Liste";

        // initialisation du model
        this.setModelAffichageListe(
                this.iniDataModelAfficheList(this.selectionMenu),
                this.initTitreDataModelAffichageListe(this.selectionMenu));
        this.frameAfficherListe.setVisible(true);

    }

    /**
     * Évenement du bouton Ajouter
     *
     * @param evt de type ActionEvent.
     */
    public void bpAjouterMenuEvent(ActionEvent evt) {
        
        // sélection du choix utilisateur
        this.selectionActionUser = "Ajouter";

        // masquer panneau de validation de selection
        this.ajouterFormulaire(selectionMenu);

    }

    /**
     * Évenement du bouton valider de la JFrame Menu
     *
     * @param evt de type ActionEvent.
     */
    public void bpValiderMenuEvent(ActionEvent evt) {

        if (this.selectComboClientProspect == null) {
            JOptionPane.showMessageDialog(null, "Vous devez choisir dans la "
                    + "liste des clients ou prospects avant de validé ",
                    "Erreur sélection", JOptionPane.INFORMATION_MESSAGE);
        } else {

            switch (this.selectionActionUser) {

                case "Modifier":
                    this.affichageModifierSupprimer();
                    break;

                case "Supprimer":
                    this.affichageModifierSupprimer();
                    break;

            }
        }

    }

    /**
     * Évenement du bouton Menu, permet le retour au menu de sélection
     *
     * @param evt de type ActionEvent.
     */
    public void menuEvent(ActionEvent evt) {
        // initialisation du menu 
        this.initMenuFrame();
    }

    /**
     * Évenement du bouton Quitter, permet de quitter l'application.
     *
     * @param evt de type ActionEvent.
     */
    public void quitterEvent(ActionEvent evt) {
        System.exit(0);
    }

    /*
     * ************************* Évenement lié à la fenêtre du Formulaire *****
     */
    /**
     * Initialisation de la comboBox Domain.
     */
    private void initComboDomain() {
        DefaultComboBoxModel model = new DefaultComboBoxModel(DomainSociete.values());
        model.setSelectedItem("Domaine de la société");
        this.formulaireFrame.getComboDomainSt().setModel(model);
    }

    /**
     * Initialisation de la comboBox Interet.s
     */
    private void initComboInteret() {
        DefaultComboBoxModel model = new DefaultComboBoxModel(Interet.values());
        model.setSelectedItem("Intéret du Client");
        this.formulaireFrame.getComboInteret().setModel(model);
    }

    /**
     * Évenement du bouton valider de la JFrame Formulaire. En fonction du choix
     * utilisateur une action sera réalisée
     *
     * @param evt de type ActionEvent.
     */
    public void bpValiderFormulaireEvent(ActionEvent evt) {

        switch (this.selectionActionUser) {

            case "Modifier":
                this.modificationSociete();
                break;

            case "Supprimer":
                this.supprimerSociete();
                break;

            case "Ajouter":
                this.ajouterSociete();
                break;

        }
    }


    /*
     * ************************* Méthode lié à la fenêtre afficher liste *****
     */
    /**
     * Permet d'initialiser le model de données du Jtable.
     * <p>
     * Pour le model :</p>
     * <ul>
     * <li>La matrice récupère les champs du client ou du prospect. </Li>
     * <li> Le tableau de String récupère les noms  </li>
     * </ul>
     *
     * @param dataSt String matrice de données, Client ou Prospect.
     * @param titreSt String tableau champs appartenant à client ou prospect.
     */
    public void setModelAffichageListe(String[][] dataSt, String[] titreSt) {
        DefaultTableModel model = new DefaultTableModel(dataSt, titreSt) {

            // permet pas la modification
            @Override
            public boolean isCellEditable(int ligne, int colonne) {

                return false;
            }
        };

        // changement du model
        this.frameAfficherListe.getjTableAffichage().setModel(model);

    }

    /**
     * Initialisation des titre du tableau d'affichage.
     *
     * @param choix String l'utilisateur choisi d'affiché le tableau des clients
     * ou des prospects.
     * @return String tableau des entêtes du tableau.
     */
    public String[] initTitreDataModelAffichageListe(String choix) {

        String[] finaleData = new String[12];

        finaleData[0] = this.formulaireFrame.getLabelIdentifiant().getText();
        finaleData[1] = this.formulaireFrame.getLabelRaisonSocialeSt().getText();
        finaleData[2] = this.formulaireFrame.getLabelDomainSt().getText();
        finaleData[3] = this.formulaireFrame.getLabelNumeroAdresse().getText();
        finaleData[4] = this.formulaireFrame.getLabelNomDeRue().getText();
        finaleData[5] = this.formulaireFrame.getLabelCodePostale().getText();
        finaleData[6] = this.formulaireFrame.getLabelVille().getText();
        finaleData[7] = this.formulaireFrame.getLabelTelephone().getText();
        finaleData[8] = this.formulaireFrame.getLabelEmail().getText();
        finaleData[9] = this.formulaireFrame.getLabelCommentaireClient().getText();

        // si c'est client
        if (choix.equals("Menu Client")) {
            this.frameAfficherListe.getjLabelTitreTableau()
                    .setText("Tableau des Clients");
            finaleData[10] = this.formulaireFrame.getLabelChiffreAffaireSt().getText();
            finaleData[11] = this.formulaireFrame.getLabelNombreEmployer().getText();

            // si c'est prospect
        } else if (choix.equals("Menu Prospect")) {

            this.frameAfficherListe.getjLabelTitreTableau()
                    .setText("Tableau des Prospects");
            finaleData[10] = this.formulaireFrame.getLabelDateDeProspection().getText();
            finaleData[11] = this.formulaireFrame.getLabelInteret().getText();

        }
        return finaleData;
    }

    /**
     * Initialise les données du jTable soit pour un Client soit pour un
     * Prospect.
     *
     * @param chUser String choix utilisateur fait au menu du programme.
     * @return String tableau à 2 dimention avec les données de soit Client ou
     * soit prospect.
     */
    private String[][] iniDataModelAfficheList(String chUser) {

        // declaration d'une liste acceptant les liste Clients ou Prospects
        List<? extends Societe> listeSociete = null;
        // taille du tableau
        int sizeArray = 0;
        // flag entre client et prospect
        boolean client = true;
        // si c'est Client
        if (chUser.equals("Menu Client")) {

            listeSociete = Client.getListeClient();
            sizeArray = Client.getSizeListeClient();

        } else if (chUser.equals("Menu Prospect")) {
            client = false;
            listeSociete = Prospect.getListePropect();
            sizeArray = Prospect.getSizeListeProspect();
        }

        // Initialisation du tableau d'affichage
        String[][] personnelle = new String[sizeArray][12];

        // Pour chaque Client
        for (int i = 0; i < sizeArray; i++) {

            personnelle[i][0] = String.valueOf(listeSociete.get(i).getIdentifiant());
            personnelle[i][1] = listeSociete.get(i).getRaisonSociale();
            personnelle[i][2] = listeSociete.get(i).getDomainSociete();
            personnelle[i][3] = String.valueOf(listeSociete.get(i).getListAdresse().getNumeroDeRueSt());
            personnelle[i][4] = listeSociete.get(i).getListAdresse().getNomRue();
            personnelle[i][5] = listeSociete.get(i).getListAdresse().getCodePost();
            personnelle[i][6] = listeSociete.get(i).getListAdresse().getVille();
            personnelle[i][7] = listeSociete.get(i).getTelephone();
            personnelle[i][8] = listeSociete.get(i).getAdresseEmail();
            personnelle[i][9] = listeSociete.get(i).getCommentaire();

            //si client 
            if (client) {
                personnelle[i][10] = String.valueOf(Client.getListeClient().get(i).getChiffreAffaire());
                personnelle[i][11] = String.valueOf(Client.getListeClient().get(i).getNombreEmployer());

                // si prospect
            } else {

                personnelle[i][10] = Prospect.getListePropect().get(i).getDatePropect();
                personnelle[i][11] = Prospect.getListePropect().get(i).getInteresse();
            }

        }

        return personnelle;
    }

    /**
     * ****************************************************Méthode de structure
     */
    /**
     * Affichage du formulaire vide pour l'ajout d'un client ou prospect.
     *
     * @param selectionChoix String contient le titre la page sélectionné.
     */
    public void ajouterFormulaire(String selectionChoix) {

        // reaffiche les champs masqués
        this.masqueChamps(true);

        // masque le menu
        this.menuFrame.setVisible(false);

        // rend visible le formulaire.
        this.formulaireFrame.setVisible(true);

        // changer la valeur du bouton de validation pour la valeur modifier
        this.formulaireFrame.getBpValiderFormulaire().setText(this.selectionActionUser);

        // pour les client est les prospects
        this.formulaireFrame.getTxID().setEditable(false);

        // raison sociale 
        this.formulaireFrame.getTxRaison().setText("");

        // téléphone
        this.formulaireFrame.getTxTelephone().setText("");
        this.formulaireFrame.getTxTelephone().setToolTipText("Format : xx.xx.xx.xx.xx");

        // Email
        this.formulaireFrame.getTxEmail().setText("");
        this.formulaireFrame.getTxEmail().setToolTipText("Format : xxxx.xxxx@gmail.com");

        // comboBox Domain
        this.formulaireFrame.getComboDomainSt().getSelectedItem();

        // ville 
        this.formulaireFrame.getTxVille().setText("");

        // code Postale
        this.formulaireFrame.getTxCodePostale().setText("");

        // numero d'adresse
        this.formulaireFrame.getTxNumeroAd().setText("");

        // Nom de la rue 
        this.formulaireFrame.getTxNomRue().setText("");

        // les commentaires
        this.formulaireFrame.getTxCommentaire().setText("");

        // si c'est un Client affichage du context pour le client
        if (this.selectionMenu.equals("Menu Client")) {

            // visibilité des panneaux 
            this.formulaireFrame.getPanClient().setVisible(true);
            this.formulaireFrame.getPanProspect().setVisible(false);

            // titre de la page 
            this.formulaireFrame.getLabelTitreFormulaire()
                    .setText("Ajouter un Client");

            // Identifiant du nouveaux Client
            this.formulaireFrame.getTxID()
                    .setText(String.valueOf(Client.getCompteur() + 1));

            // nombre d'employé
            this.formulaireFrame.getTxNombreEmployer().setText("");

            // client Chiffre d'affaire 
            this.formulaireFrame.getTxChiffreAffaire().setText("");

            // si c'est un Propest affichage du context pour le prospect
        } else if (this.selectionMenu.equals("Menu Prospect")) {

            // visibilité des panneaux 
            this.formulaireFrame.getPanClient().setVisible(false);
            this.formulaireFrame.getPanProspect().setVisible(true);

            // titre de la page 
            this.formulaireFrame.getLabelTitreFormulaire()
                    .setText("Ajouter un Prospect");

            // Identifiant
            this.formulaireFrame.getTxID()
                    .setText(String.valueOf(Prospect.getCompteur() + 1));

            // Date de prospection
            this.formulaireFrame.getTxDatePropection().setText("");

            // ComboBox Interet
            this.formulaireFrame.getComboInteret().getSelectedItem();

        }

    }

    /**
     * Méthode d'affichage pour la Modifiction ou la Suppression des Clients ou
     * Prospects.
     */
    public void affichageModifierSupprimer() {

        // choix user pour la modification ou suppression
        Societe societe = this.selectComboClientProspect;

        // visibilité des fenêtres 
        this.formulaireFrame.setVisible(true);
        this.menuFrame.setVisible(false);

        // initialisation du titre de la page 
        String titrePage = "Titre";

        // si on modifier 
        if (this.selectionActionUser.equals("Modifier")) {

            // change le titre de la page
            titrePage = "Modification " + societe.getClass().getSimpleName();
            this.formulaireFrame.getBpValiderFormulaire().setText("Modifier");

            // réaffiche les chhamps de la page
            this.masqueChamps(true);
            // si on supprime
        } else if (this.selectionActionUser.equals("Supprimer")) {

            titrePage = "Suppression " + societe.getClass().getSimpleName();
            this.formulaireFrame.getBpValiderFormulaire().setText("Supprimer");
            this.masqueChamps(false);
        }

        // champs commun a client et prospect.
        this.formulaireFrame.getLabelTitreFormulaire().setText(titrePage);

        // identifiant
        this.formulaireFrame.getTxID().setText(String.valueOf(societe.getIdentifiant()));
        this.formulaireFrame.getTxID().setEditable(false);

        // raison sociale 
        this.formulaireFrame.getTxRaison().setText(societe.getRaisonSociale());

        // comboBox Domain
        this.formulaireFrame.getComboDomainSt()
                .setSelectedItem(DomainSociete
                        .valueOf(societe
                                .getDomainSociete()));

        // numero d'adresse
        this.formulaireFrame.getTxNumeroAd()
                .setText(String
                        .valueOf(societe
                                .getListAdresse()
                                .getNumeroDeRueSt()));

        // nom de la rue
        this.formulaireFrame.getTxNomRue()
                .setText(societe.getListAdresse().getNomRue());

        // code Postale
        this.formulaireFrame.getTxCodePostale()
                .setText(societe.getListAdresse().getCodePost());

        // ville 
        this.formulaireFrame.getTxVille().setText(societe.getListAdresse().getVille());

        // téléphone 
        this.formulaireFrame.getTxTelephone().setText(societe.getTelephone());

        // Email
        this.formulaireFrame.getTxEmail().setText(societe.getAdresseEmail());

        // commentaire
        this.formulaireFrame.getTxCommentaire()
                .setText(societe.getCommentaire());

        // si c'est un client : modification sur client
        if (societe instanceof Client) {

            // visibilté des panneaux
            this.formulaireFrame.getPanClient().setVisible(true);
            this.formulaireFrame.getPanProspect().setVisible(false);

            Client client = (Client) societe;

            // client Chiffre d'affaire 
            this.formulaireFrame.getTxChiffreAffaire()
                    .setText(String.valueOf(client.getChiffreAffaire()));

            // nombre d'employé
            this.formulaireFrame.getTxNombreEmployer()
                    .setText(String.valueOf(client.getNombreEmployer()));

            // si c'est un prospect
        } else if (societe instanceof Prospect) {
            // visibilté des panneaux
            this.formulaireFrame.getPanClient().setVisible(false);
            this.formulaireFrame.getPanProspect().setVisible(true);

            Prospect prospect = (Prospect) societe;

            // Date de prospection
            this.formulaireFrame.getTxDatePropection()
                    .setText(prospect.getDatePropect());

            // ComboBox Interet
            this.formulaireFrame.getComboInteret()
                    .setSelectedItem(Interet.valueOf(prospect.getInteresse()));
        }

    }

    /**
     * Modifie les attributs d'un client ou d'un prospect.
     */
    private void modificationSociete() {

        // visibilité des panneaux   
        try {
            // recupération du choix utilisateur.
            Societe societe = this.selectComboClientProspect;

            // raison sociale de la societe
            societe.setRaisonSociale(this.formulaireFrame.getTxRaison()
                    .getText());

            // domain de la société
            societe.setDomainSociete(this.formulaireFrame.getComboDomainSt()
                    .getSelectedItem()
                    .toString());

            // champs adresse Adresse 
            societe.getListAdresse().setNumeroDeRueSt(Integer.parseInt(
                    this.formulaireFrame
                    .getTxNumeroAd()
                    .getText()));

            societe.getListAdresse().setNomRue(
                    this.formulaireFrame
                    .getTxNomRue()
                    .getText());

            societe.getListAdresse().setCodePost(
                    this.formulaireFrame
                    .getTxCodePostale()
                    .getText());

            societe.getListAdresse().setVille(
                    this.formulaireFrame
                    .getTxVille()
                    .getText());

            societe.setTelephone(this.formulaireFrame.getTxTelephone()
                    .getText());

            societe.setAdresseEmail(this.formulaireFrame.getTxEmail()
                    .getText());

            societe.setCommentaire(this.formulaireFrame.getTxCommentaire()
                    .getText());

            // partie Client
            if (societe instanceof Client) {

                // visibilité des clients 
                this.formulaireFrame.getPanClient().setVisible(true);
                this.formulaireFrame.getPanProspect().setVisible(false);

                Client client = (Client) societe;

                // calcul ratio
                client.calculRatioClientEmployer(
                        Integer.parseInt(this.formulaireFrame
                                .getTxChiffreAffaire()
                                .getText()),
                        Integer.parseInt(this.formulaireFrame
                                .getTxNombreEmployer()
                                .getText()));

                // partie Prospect
            } else if (societe instanceof Prospect) {

                // visibilité des Prospects
                this.formulaireFrame.getPanClient().setVisible(false);
                this.formulaireFrame.getPanProspect().setVisible(true);

                // caste du choix user
                Prospect prospect = (Prospect) societe;

                prospect.setDatePropect(this.formulaireFrame
                        .getTxDatePropection()
                        .getText());

                prospect.setInteresse(this.formulaireFrame.getComboInteret()
                        .getSelectedItem()
                        .toString());
            }

            // Si la modification a été réalisée avec succès, un message sera affiché
            JOptionPane.showMessageDialog(null, "Modification effectuée sur le client", "Modification", JOptionPane.INFORMATION_MESSAGE);

            // erreur de saisi d'entier dans un champs de caractère
        } catch (NumberFormatException nbfe) {
            JOptionPane.showMessageDialog(null, "Erreur de saisi : veuillez entrer des valeurs numériques \n dans les champs demandés",
                    "Erreur de saisi Utilisateur", JOptionPane.INFORMATION_MESSAGE);

            // exception venant des classes métiers
        } catch (ExceptionPersonnaliser excePerso) {
            JOptionPane.showMessageDialog(null, excePerso.getMessage(),
                    "Erreur de saisi Utilisateur ", JOptionPane.ERROR_MESSAGE);

            // exception venant d'une erreur d'excution
        } catch (Exception excep) {
            System.out.println("Erreur d'exécution du programme"
                    + excep.getStackTrace());
        }

    }

    /**
     * Suppirme un Client ou un Prospect
     */
    private void supprimerSociete() {

        // nom de la classe selectionné par l'utilisateur 
        String nomObjetClasse = this.selectComboClientProspect.getClass().getSimpleName();

        // boite de dialogue choix oui 0 / non 1
        int choix = JOptionPane.showConfirmDialog(null, "Attention, vous allez supprimer un "
                + nomObjetClasse, "Supprimer", JOptionPane.YES_NO_OPTION);

        // si l'utilisateur supprime l'object
        if (choix == 0) {
            // suppression des objets client ou prospect
            if (this.selectionMenu.equals("Menu Client")) {
                Client.removeListeClient((Client) this.selectComboClientProspect);

            } else if (this.selectionMenu.equals("Menu Prospect")) {
                Prospect.removeListeProspect((Prospect) this.selectComboClientProspect);

            }
            // après suppression, quitte le formulaire
            this.formulaireFrame.setVisible(false);
            // initialise le menu
            this.initMenuFrame();
            // affichage du menu de selection
            this.menuFrame.setVisible(true);

        }

    }

    private void ajouterSociete() {

        Societe st = null;

        // si c'est un client
        switch (this.selectionMenu) {
            case "Menu Client":
                try {

                    // création d'un Client
                    Client client = new Client(this.formulaireFrame.getTxRaison().getText(),
                            this.formulaireFrame.getComboDomainSt()
                            .getSelectedItem().toString(),
                            Integer.parseInt(this.formulaireFrame.getTxNumeroAd().getText()),
                            this.formulaireFrame.getTxNomRue().getText(),
                            this.formulaireFrame.getTxCodePostale().getText(),
                            this.formulaireFrame.getTxVille().getText(),
                            this.formulaireFrame.getTxTelephone().getText(),
                            this.formulaireFrame.getTxEmail().getText(),
                            this.formulaireFrame.getTxCommentaire().getText(),
                            Integer.parseInt(this.formulaireFrame.getTxChiffreAffaire().getText()),
                            Integer.parseInt(this.formulaireFrame.getTxNombreEmployer().getText()));

                    // Ajoute à la liste le nouveaux Client
                    Client.addLisClient(client);

                    // Erreur de saisi d'entier dans un champs de caractère
                } catch (NumberFormatException nbfe) {
                    JOptionPane.showMessageDialog(null, "Erreur de saisi : " + nbfe.getLocalizedMessage() + "veuillez entrer des valeurs numériques dans les champs demandés",
                            "Erreur de saisi Utilisateur", JOptionPane.ERROR_MESSAGE);

                    // Exception venant des classes métiers
                } catch (ExceptionPersonnaliser excePerso) {
                    JOptionPane.showMessageDialog(null, excePerso.getMessage(),
                            "Erreur de saisi Utilisateur", JOptionPane.ERROR_MESSAGE);

                    // Exception venant d'une erreur d'excution
                } catch (Exception excep) {
                    System.out.println("Erreur d'exécution du programme"
                            + excep.getStackTrace() + "\n" + excep.getMessage() + "\n" + excep.getLocalizedMessage());
                }

                // si c'est un Prospect
                break;
            case "Menu Prospect":
                try {

                    // Création d'un prospect
                    Prospect prospect = new Prospect(this.formulaireFrame.getTxRaison().getText(),
                            this.formulaireFrame.getComboDomainSt().getSelectedItem().toString(),
                            Integer.parseInt(this.formulaireFrame.getTxNumeroAd().getText()),
                            this.formulaireFrame.getTxNomRue().getText(),
                            this.formulaireFrame.getTxCodePostale().getText(),
                            this.formulaireFrame.getTxVille().getText(),
                            this.formulaireFrame.getTxTelephone().getText(),
                            this.formulaireFrame.getTxEmail().getText(),
                            this.formulaireFrame.getTxDatePropection().getText(),
                            this.formulaireFrame.getComboInteret().getSelectedItem().toString(),
                            this.formulaireFrame.getTxCommentaire().getText());

                    // Ajoute à la liste le nouveaux Prospect.
                    Prospect.addListeProspect(prospect);

                    // Erreur de saisi d'entier dans unchamps de caractère
                } catch (NumberFormatException nbfe) {
                    JOptionPane.showMessageDialog(null, "Erreur de saisi : veuillez entrer des valeurs numériques dans les champs demandés",
                            "Erreur de saisi Utilisateur ", JOptionPane.ERROR_MESSAGE);

                    // Exception venant des classes métier
                } catch (ExceptionPersonnaliser excePerso) {
                    JOptionPane.showMessageDialog(null, excePerso.getMessage(),
                            "Erreur de saisi Utilisateur ", JOptionPane.ERROR_MESSAGE);

                    // Exception venant d'une erreur d'excution
                } catch (Exception excep) {
                    System.out.println("Erreur d'exécution du programme"
                            + excep.getStackTrace());
                }
                break;
        }

    }

    /**
     * Point d'entré du programme.
     *
     * @param agrs String
     */
    public static void main(String[] agrs) {
        // variable de teste
        Client client1 = new Client("carefour", DomainSociete.PRIVE.toString(), 10,
                "rue d en haut", "01800", "molliens-Dreuil",
                "06 98 44 20 69", "soldat.inconnue@gmail.com",
                "ils étaient quatre avant, maintenant ils sont trois et ont les connais", 100000, 10);

        Client client2 = new Client("darty", DomainSociete.PUBLIC.toString(), 50,
                "rue des boulangers", "68130", "Altkirch",
                "06.98.44.20.69", "Fabien.Zindy@gmail.com",
                "Je suis un geek, est alors ;) ", 500000, 100);

        Prospect prospect1 = new Prospect("afpa", DomainSociete.PRIVE.toString(), 20,
                "Rue de chez toi", "80000", "Amiens", "06.07.08.09.10",
                "Ghalem.maxime@gmail.com", "01/09/1982", Interet.NON.toString(), "Un commentaire de teste");

        Prospect prospect2 = new Prospect("clemessy", DomainSociete.PUBLIC.toString(), 100,
                "Rue d en haut", "68000", "colmar", "05.17.88.59.11",
                "Sylvie.Touchot@gmail.com", "10/06/1985", Interet.OUI.toString(), "Tu fait des fautes d'orthographe méme quand tu parle, alors fait gaffe ;) ");

        Client.addLisClient(client1);
        Client.addLisClient(client2);

        Prospect.addListeProspect(prospect1);
        Prospect.addListeProspect(prospect2);
        
        getConnection();

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                // instantiation des frames
                MenuFrame menuF = new MenuFrame();
                FormulaireFrame formulaireF = new FormulaireFrame();
                AffichageListeFrame affichageF = new AffichageListeFrame();

                // instantiation du controleur
                ControleurFrame control = new ControleurFrame(menuF, formulaireF, affichageF);

                // ajout du controleur au frame
                menuF.setControleur(control);
                formulaireF.setControleur(control);
                affichageF.setControleur(control);
                
                

            } 
        });
    }
}
