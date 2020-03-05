package com.model;

import com.controleur.ControleurFrame;
import com.exception.ExceptionDAO;
import com.exception.ExceptionPersonnaliser;
import com.listener.ActionQuitter;
import com.listener.ActionRetourMenu;
import com.listener.ActionValideFormulaire;
import com.metier.Client;
import com.metier.Prospect;
import com.metier.Societe;
import com.metier.Societe.DomainSociete;
import com.model.MenuFrame.Action;
import com.metier.Societe.TypeSociete;
import com.metier.Prospect.Interet;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Cette classe permet la gestion du formulaire de modification de suppréssion
 * est de l'ajout soit d'un client soit d'un prospect.
 *
 * @author Maxime
 */
public class FormulaireFrame extends javax.swing.JFrame {

    private String actionTypeMenu;
    private Societe societe;
    private String memoirechoixClientProspect;
    private ControleurFrame controleur;

    /**
     * Constructeur pour la modification, suppression et l'ajout d'un client ou d'un prospect
     *
     * @param societe        de type Societe
     * @param actionTypeMenu de type Enumeration
     */
    public FormulaireFrame(Societe societe, Action actionTypeMenu, ControleurFrame controleur, String memoirechoixClientProspect) {
        initComponents();
        this.societe = societe;
        this.actionTypeMenu = actionTypeMenu.getAction();
        this.controleur = controleur;
        this.memoirechoixClientProspect = memoirechoixClientProspect;

        initComboDomain();
        initComboInteret();

        this.affichageModifierSupprimer();
        this.setVisible(true);
    }

    /**
     * renvoi l'action fait par l'utilisateur.
     * <ul>
     * <li>MODIFICATION</li>
     * <li>SUPPRESSION</li>
     * <li>AJOUT</li>
     * <li>AFFICHAGE_LISTE</li>
     * </ul>
     *
     * @return une enumeration Action
     * @see com.model.MenuFrame.Action
     */
    public String getActionTypeMenu() {
        return this.actionTypeMenu;
    }

/*    *//**
     * renvoie le choix d'un client ou d'un prospect.
     *
     * @return de type string
     *//*
    public String getMemoireChoixClientProspect() {
        return this.memoirechoixClientProspect;
    }*/

    /**
     * renvoi un objet de type Societe
     *
     * @return de type String
     */
    public Societe getSociete() {
        return this.societe;
    }

    /**
     * Permet le blocage de la saisi des champs texte du formulaire. Pour bloqué
     * false, débloqué true.
     *
     * @param editableFields de type boolean.
     */
    private void masqueChamps(boolean editableFields) {

        for (Component component : this.panFormulaire.getComponents()) {  // Pour chaque composant du formulaire

            if (component instanceof JTextField) {                        // si c'est un Jtext Field
                JTextField jtf = (JTextField) component;                  // caste pour recupérer le champs
                jtf.setEditable(editableFields);                                   // le rendre inéditable
            }
        }
        // Element seule prospect et client a masqué
        this.txDatePropection.setEnabled(editableFields);
        this.txNombreEmployer.setEnabled(editableFields);
        this.txChiffreAffaire.setEnabled(editableFields);
    }

    /**
     * Initialisation de la comboBox Domain.
     */
    private void initComboDomain() {
        DefaultComboBoxModel model = new DefaultComboBoxModel(DomainSociete.values());
        //model.setSelectedItem("Domaine de la société");
        this.comboDomainSt.setModel(model);
    }

    /**
     * Initialisation de la comboBox Interet.s
     */
    private void initComboInteret() {
        DefaultComboBoxModel model = new DefaultComboBoxModel(Interet.values());
        model.setSelectedItem("Intéret du Client");
        this.comboInteresset.setModel(model);
    }

    /**
     * Affichage du formulaire vide pour l'ajout d'un client ou prospect.
     *
     * @param selectionChoix String contient le titre la page sélectionné.
     */
    public void affichageAjout() {

        // affiche les champs masqués
        this.masqueChamps(true);
        this.bpValiderFormulaire.setText(this.actionTypeMenu);
        this.txID.setEditable(false);
        this.txTelephone.setToolTipText("Format : xx.xx.xx.xx.xx");
        this.txEmail.setToolTipText("Format : xxxx.xxxx@gmail.com");


        // si c'est un Client affichage du context pour le client
        if (this.memoirechoixClientProspect.equals(TypeSociete.CLIENT)) {

            // visibilité des panneaux
            this.panClient.setVisible(true);
            this.panProspect.setVisible(false);
            this.labelTitreFormulaire.setText("Ajouter d'un " + TypeSociete.CLIENT);
            this.txID.setText("No values");

            // si c'est un Propest affichage du context pour le prospect
        } else if (this.memoirechoixClientProspect.equals(TypeSociete.CLIENT)) {

            // visibilité des panneaux
            this.panClient.setVisible(false);
            this.panProspect.setVisible(true);
            this.labelTitreFormulaire.setText("Ajouter d'un " + TypeSociete.PROSPECT);
            this.txID.setText("No values");
            this.comboInteresset.getSelectedItem();

        }

    }

    /**
     * Méthode d'affichage pour la Modifiction ou la Suppression des Clients ou
     * Prospects.
     */
    public void affichageModifierSupprimer() {


        // choix user pour la modification ou suppression
        Societe societe = this.societe;
        String titrePage = "Titre";

        // si on modifier

        if (Action.MODIFICATION.getAction().equals(this.actionTypeMenu)) {

            this.masqueChamps(true); // réaffiche les champs de la page
            // change le titre de la page et valeur du bouton
            titrePage = Action.MODIFICATION.toString() + " : " + societe.getClass().getSimpleName();
            this.bpValiderFormulaire.setText(Action.MODIFICATION.toString());


            // si on supprime
        } else if (Action.SUPPRESSION.getAction().equals(this.actionTypeMenu)) {

            this.masqueChamps(false); // réaffiche les chhamps de la page
            titrePage = Action.SUPPRESSION.toString() + " : " + societe.getClass().getSimpleName();
            this.bpValiderFormulaire.setText(Action.SUPPRESSION.toString());

        }

        // champs commun au client et prospect.
        this.labelTitreFormulaire.setText(titrePage);
        this.txID.setText(String.valueOf(societe.getIdentifiant()));
        this.txID.setEditable(false);
        this.txRaison.setText(societe.getRaisonSociale());
        this.comboDomainSt.setSelectedItem(DomainSociete.valueOf(societe.getDomainSociete().toString()));
        this.txNumeroAd.setText(String.valueOf(societe.getAdresse().getNumeroDeRueSt()));
        this.txNomRue.setText(societe.getAdresse().getNomRue());
        this.txCodePostale.setText(societe.getAdresse().getCodePost());
        this.txVille.setText(societe.getAdresse().getVille());
        this.txTelephone.setText(societe.getTelephone());
        this.txEmail.setText(societe.getEmail());
        this.txCommentaire.setText(societe.getCommentaire());

        // si c'est un client : modification sur client
        if (societe instanceof Client) {

            Client client = (Client) societe;

            // visibilté des panneaux
            this.panClient.setVisible(true);
            this.panProspect.setVisible(false);

            this.txChiffreAffaire.setText(String.valueOf(client.getChiffreAffaire()));
            this.txNombreEmployer.setText(String.valueOf(client.getNombreEmployer()));

            // si c'est un prospect
        } else if (societe instanceof Prospect) {

            Prospect prospect = (Prospect) societe;

            // visibilté des panneaux
            this.panClient.setVisible(false);
            this.panProspect.setVisible(true);

            this.txDatePropection.setText(prospect.getDatePropect());
            this.comboInteresset.setSelectedItem(Interet.valueOf(prospect.getInteresse().toString()));
        }

    }

    /**
     * Cette méthode valide les informations du formulaire client ou prospect
     * si true ajoute
     * si false modifi
     *
     * @param action de type boolean
     */
    public void ajouterModifierSociete(boolean action) {

        // Création d'un prospect temporaire
        Client client = new Client();
        Prospect prospect = new Prospect();

        System.out.println("Debut ");
        try {
            // choix du client
            if (this.memoirechoixClientProspect.equals(TypeSociete.CLIENT.getTypeSociete())) {
                System.out.println("client passage ");
                // passage des valeurs du client
                client.setIdentifiant(Integer.parseInt(this.txID.getText()));
                client.setRaisonSociale(this.txRaison.getText().trim());
                client.setTelephone(this.txTelephone.getText().trim());
                client.setEmail(this.txEmail.getText().trim());
                client.setCommentaire(this.txCommentaire.getText().trim());
                client.setDomainSociete(DomainSociete.valueOf(this.comboDomainSt.getSelectedItem().toString()));

                client.setAdresseSt(Integer.parseInt(this.txNumeroAd.getText().trim()),
                        this.txNomRue.getText().trim(),
                        this.txCodePostale.getText().trim(),
                        this.txVille.getText().trim());

                client.calculRatioClientEmployer(
                        Integer.parseInt(this.txChiffreAffaire.getText().trim()),
                        Integer.parseInt(this.txNombreEmployer.getText().trim()));

                // si c'est ajouter client
                if (action == true) {
                    // boite de dialogue propose la sauvegarde choix oui 0 / non 1
                    int choix = JOptionPane.showConfirmDialog(null,
                            "southaitez vous ajouter ce client ",
                            "Ajouter",
                            JOptionPane.YES_NO_OPTION);

                    if (choix == 0) {

                        // Ajoute à la base de données le client
                        boolean retourSGBD = this.controleur.addSocieteControleur(client);

                        if (retourSGBD == true) {
                            JOptionPane.showMessageDialog(null,
                                    "La création à était réalisé avec succés",
                                    "Transaction SGBD",
                                    JOptionPane.INFORMATION_MESSAGE);

                            new MenuFrame(this.controleur);
                            this.dispose();
                            client = null;

                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "échec de l'ajoute du Client",
                                    "Transaction SGBD",
                                    JOptionPane.ERROR_MESSAGE);
                        }


                    }

                    // si c'est modifier client
                } else if (action == false) {
                    System.out.println("Client modification : ");
                    // boite de dialogue propose la sauvegarde choix oui 0 / non 1
                    int choix = JOptionPane.showConfirmDialog(null,
                            "southaitez vous modifié ce client ",
                            "Modification",
                            JOptionPane.YES_NO_OPTION);

                    if (choix == 0) {

                        // Modifier à la base de données le client
                        boolean retourSGBD = this.controleur.updateSocieteControleur(client);

                        if (retourSGBD == true) {
                            JOptionPane.showMessageDialog(null,
                                    "La modification à était réalisé avec succés",
                                    "Transaction SGBD",
                                    JOptionPane.INFORMATION_MESSAGE);

                            new MenuFrame(this.controleur);
                            this.dispose();
                            client = null;

                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "échec de la modification du Client",
                                    "Transaction SGBD",
                                    JOptionPane.ERROR_MESSAGE);

                        }

                    }
                }


                // choix du Prospect
            } else if (this.memoirechoixClientProspect.equals(TypeSociete.PROSPECT.getTypeSociete())) {

                // passage des valeurs du prospect
                prospect.setIdentifiant(Integer.parseInt(this.txID.getText()));
                prospect.setRaisonSociale(this.txRaison.getText().trim());
                prospect.setTelephone(this.txTelephone.getText().trim());
                prospect.setEmail(this.txEmail.getText());
                prospect.setCommentaire(this.txCommentaire.getText());
                prospect.setDomainSociete(DomainSociete.valueOf(this.comboDomainSt.getSelectedItem().toString()));

                prospect.setAdresseSt(Integer.parseInt(this.txNumeroAd.getText()),
                        this.txNomRue.getText().trim(),
                        this.txCodePostale.getText().trim(),
                        this.txVille.getText().trim());

                prospect.setDatePropect(this.txDatePropection.getText());
                prospect.setInteresse(Interet.valueOf(this.comboInteresset.getSelectedItem().toString()));

                // ajouter prospect
                if (action == true) {
                    // boite de dialogue propose la sauvegarde choix oui 0 / non 1
                    int choix = JOptionPane.showConfirmDialog(null,
                            "southaitez vous ajouter ce prospect "
                            , "Ajouter", JOptionPane.YES_NO_OPTION);

                    if (choix == 0) {

                        // Ajoute à la base de données le prospect
                        boolean retourSGBD = this.controleur.addSocieteControleur(prospect);

                        if (retourSGBD == true) {// permet d'affiché un message
                            JOptionPane.showMessageDialog(null,
                                    "La création a était réalisé avec succés",
                                    "Transaction SGBD", JOptionPane.INFORMATION_MESSAGE);

                            new MenuFrame(this.controleur);
                            this.dispose();
                            prospect = null;

                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "échec de l'ajoute du Prospect",
                                    "Transaction SGBD", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    // modification prospect
                } else if (action == false) {

                    // boite de dialogue propose la sauvegarde choix oui 0 / non 1
                    int choix = JOptionPane.showConfirmDialog(null,
                            "southaitez vous modifié ce prospect "
                            , "Modification", JOptionPane.YES_NO_OPTION);

                    if (choix == 0) {

                        // Modifie le prospect
                        boolean retourSGBD = this.controleur.updateSocieteControleur(prospect);

                        if (retourSGBD == true) {// permet d'affiché un message
                            JOptionPane.showMessageDialog(null,
                                    "La modification a était réalisé avec succés",
                                    "Transaction SGBD", JOptionPane.INFORMATION_MESSAGE);

                            new MenuFrame(this.controleur);
                            this.dispose();
                            prospect = null;

                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "échec de la modification du Prospect",
                                    "Transaction SGBD", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

            }

        } catch (NumberFormatException nub) {
            JOptionPane.showMessageDialog(null,
                    "Vous entré des valeurs numériques dans le champs numero d'adresse .\n" +
                            "veuillez entré une nouvelle valeur numerique.",
                    "ERREUR de saisi : Adresse",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (ExceptionPersonnaliser exp) {

            // exception sur la classe adresse
            switch (exp.getIndicationAdresse()) {

                case NUMERO_RUE_INF_0: {
                    JOptionPane.showMessageDialog(null,
                            "Le numero de la rue ne peut pas étre inférieur ou égale à 0.\n" +
                                    "veuillez entré une nouvelle valeurs.",
                            "ERREUR de saisi : numero de rue",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case EMPTY_NOM_RUE: {
                    JOptionPane.showMessageDialog(null,
                            "Le nom de la rue doit étre renseigner.\n" +
                                    "veuillez renseigner le noms de la rue.",
                            "ERREUR de saisi : nom de la rue",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case MATCH_NOM_RUE: {
                    JOptionPane.showMessageDialog(null,
                            "Le nom de la rue doit ne pas comporté de chiffre.\n" +
                                    "Veuillez renseigner un autre noms de rue.",
                            "ERREUR de saisi : nom de la rue",
                            JOptionPane.INFORMATION_MESSAGE);

                    break;
                }
                case MACTH_CD_POSTALE: {
                    JOptionPane.showMessageDialog(null,
                            "Le nom de la rue ne doit pas comporte de chiffre.\n" +
                                    "Veuillez renseigner un autre noms de rue.",
                            "ERREUR de saisi : le code postale",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case EMPTY_CD_POSTALE: {
                    JOptionPane.showMessageDialog(null,
                            "Le code postale doit étre renseigné.\n" +
                                    "Veuillez renseigner le code postale.",
                            "ERREUR de saisi : le code postale",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case MATCH_NOM_VILLE: {
                    JOptionPane.showMessageDialog(null,
                            "Le noms de la ville doit comporter un minimum lettre.\n" +
                                    "Veuillez renseigner correctement le nom de la ville.",
                            "ERREUR de saisi : le nom de la ville",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

            }
            // exception sur la Societe
            switch (exp.getIndicationSt()) {
                case EMPTY_EMAIL: {
                    JOptionPane.showMessageDialog(null,
                            "L'adresse email n'est pas saisi.\n" +
                                    "Veuillez saisir l'adresse email.",
                            "ERREUR de saisi : L'adresse email",
                            JOptionPane.INFORMATION_MESSAGE);

                    break;
                }
                case MATCH_EMAIL: {
                    JOptionPane.showMessageDialog(null,
                            "L'adresse email présente une anomalie, elle n'est pas conforme " +
                                    "au standart attendur.\n" +
                                    "Veuillez resaisir l'adresse email.",
                            "ERREUR de saisi : L'adresse email",
                            JOptionPane.INFORMATION_MESSAGE);

                    break;
                }
                case EMPTY_RAISONSOCIALE: {
                    JOptionPane.showMessageDialog(null,
                            "La raison sociale est vide" +
                                    "Veuillez saisir la raison sociale",
                            "ERREUR de saisi : La raison sociale",
                            JOptionPane.INFORMATION_MESSAGE);

                    break;
                }

                case DOMAIN_PRIVE_PUBLIC: {
                    System.err.format("Erreur sur la comboBox domain Prive ou Public \n" +
                            exp.getStackTrace() + exp.getMessage());
                    break;
                }

                case EMPTY_TELEPHONE: {
                    JOptionPane.showMessageDialog(null,
                            "Le numéro de téléphone n'est pas renseigné" +
                                    "Veuillez saisir le numéro de téléphone",
                            "ERREUR de saisi : le numéro de téléphone",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case MATCH_TELEPHONE: {
                    JOptionPane.showMessageDialog(null,
                            "Le numéro de téléphone n'est pas conforme au standart attendu" +
                                    "Veuillez resaisir le numéro de téléphone",
                            "ERREUR de saisi : le numéro de téléphone",
                            JOptionPane.INFORMATION_MESSAGE);

                    break;
                }

            }
            // exception sur la client
            switch (exp.getIndicationCl()) {
                case CALCUL_RATIO: {
                    JOptionPane.showMessageDialog(null,
                            "Le calcul du ratio chiffre d'affaire nombre d'employés et inferieur a 0" +
                                    "Veuillez resaisir l'un des champs correspondant " +
                                    "au chiffre d'affaire et nombre d'employés",
                            "ERREUR de saisi : le calcul du ratio",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
            // exception sur la classe Prospect
            switch (exp.getIndicationPs()) {
                case EMPTY_DATE: {
                    JOptionPane.showMessageDialog(null,
                            "La date n'a pas était saisi" +
                                    "Veuillez saisir la date",
                            "ERREUR de saisi : La date",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case MACTH_DATE: {
                    JOptionPane.showMessageDialog(null,
                            "La date ne correspond pas au format attendu" +
                                    "Veuillez resaisir la date",
                            "ERREUR de saisi : La date",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case INTERET_OUI_NON: {
                    System.err.format("ERREUR sur la comboBox Interet" +
                            exp.getStackTrace() + exp.getMessage());
                    break;
                }
            }

        } catch (SQLException sql) {// venant de l'insertion dans la base de données

            System.err.format("SQL Error [State: %s]\n Message : %s",
                    sql.getSQLState(), sql.getMessage());

        } catch (Exception excep) { // Exception venant d'une erreur d'execution

            System.err.format("Erreur d'exécution du programme"+ excep.getStackTrace()+ " : " +excep.getMessage());


        } finally { // fin de l'insertion du nouveau Prospect ou client

            // supression des références des l'objets
            client = null;
            prospect = null;

        }
    }


    /**
     * Supprime un Client ou un Prospect
     */
    public void supprimerSociete() {

        // nom de la classe selectionnée par l'utilisateur
        String nomObjetClasse = this.societe.getClass().getSimpleName();

        // boite de dialogue choix oui 0 / non 1
        int choix = JOptionPane.showConfirmDialog(null, "Attention, vous allez supprimer un "
                + nomObjetClasse, "Supprimer", JOptionPane.YES_NO_OPTION);

        // si l'utilisateur supprime l'object
        if (choix == 0) {

            try {
                // suppression des objets clients ou prospects
                if (this.societe instanceof Client) {

                    Boolean message = this.controleur.deleteSocieteControle((Client) this.societe);
                    this.dispose();                       // après suppression, quitte le formulaire
                    new MenuFrame(this.controleur);       // creation d'un Frame Menu

                } else if (this.societe instanceof Prospect) {

                    this.controleur.deleteSocieteControle((Prospect) this.societe);
                    this.dispose();                       // après suppression, quitte le formulaire
                    new MenuFrame(this.controleur);       // creation d'un Frame Menu

                }
            } catch (SQLException sql) {
                System.err.format("SQL Error [State: %s]\n Message : %s",
                        sql.getSQLState(), sql.getMessage());

            } catch (Exception ex) {
                System.err.format("SQL Error [State: %s]\n Message : %s",
                        ex.getStackTrace(), ex.getMessage());
            }

        }
    }

    private void initComponents() {

        panTitre = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        labelTitreFormulaire = new javax.swing.JLabel();
        panProspect = new javax.swing.JPanel();
        labelDateDeProspection = new javax.swing.JLabel();
        txDatePropection = new javax.swing.JTextField();
        labelInteret = new javax.swing.JLabel();
        comboInteresset = new javax.swing.JComboBox();
        panClient = new javax.swing.JPanel();
        labelNombreEmployer = new javax.swing.JLabel();
        txNombreEmployer = new javax.swing.JTextField();
        labelChiffreAffaireSt = new javax.swing.JLabel();
        txChiffreAffaire = new javax.swing.JTextField();
        panFormulaire = new javax.swing.JPanel();
        labelIdentifiant = new javax.swing.JLabel();
        txID = new javax.swing.JTextField();
        labelRaisonSocialeSt = new javax.swing.JLabel();
        txRaison = new javax.swing.JTextField();
        labelTelephone = new javax.swing.JLabel();
        txTelephone = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        txEmail = new javax.swing.JTextField();
        labelDomainSt = new javax.swing.JLabel();
        comboDomainSt = new javax.swing.JComboBox();
        labelVille = new javax.swing.JLabel();
        txVille = new javax.swing.JTextField();
        labelCodePostale = new javax.swing.JLabel();
        txCodePostale = new javax.swing.JTextField();
        labelNumeroAdresse = new javax.swing.JLabel();
        txNumeroAd = new javax.swing.JTextField();
        labelNomDeRue = new javax.swing.JLabel();
        txNomRue = new javax.swing.JTextField();
        panFooter = new javax.swing.JPanel();
        bpMenuFormulaire = new javax.swing.JButton();
        bpQuitterFormulaire = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelCommentaireClient = new javax.swing.JLabel();
        txCommentaire = new java.awt.TextArea();
        bpValiderFormulaire = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("FrameModiSupp"); // NOI18N

        panTitre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panTitre.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        panTitre.setPreferredSize(new java.awt.Dimension(700, 600));

        labelTitreFormulaire.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        labelTitreFormulaire.setText("Titre");

        javax.swing.GroupLayout panTitreLayout = new javax.swing.GroupLayout(panTitre);
        panTitre.setLayout(panTitreLayout);
        panTitreLayout.setHorizontalGroup(
                panTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panTitreLayout.createSequentialGroup()
                                .addGroup(panTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panTitreLayout.createSequentialGroup()
                                                .addGap(346, 346, 346)
                                                .addComponent(filler4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(filler13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panTitreLayout.createSequentialGroup()
                                                .addGroup(panTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panTitreLayout.createSequentialGroup()
                                                                .addGap(9, 9, 9)
                                                                .addComponent(labelTitreFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(panTitreLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(filler5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filler9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(filler12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(filler6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(filler11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(filler7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(230, 230, 230))
        );
        panTitreLayout.setVerticalGroup(
                panTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panTitreLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panTitreLayout.createSequentialGroup()
                                .addComponent(filler5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panTitreLayout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panTitreLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelTitreFormulaire)))
                                .addGroup(panTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panTitreLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(filler9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(51, 51, 51)
                                                .addComponent(filler6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(panTitreLayout.createSequentialGroup()
                                                .addGap(499, 499, 499)
                                                .addGroup(panTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(filler4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(filler11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(panTitreLayout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(filler12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panTitreLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panTitreLayout.createSequentialGroup()
                                                .addComponent(filler7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panTitreLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(filler13, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(251, 251, 251))))
        );

        labelDateDeProspection.setText("Date de prospection :");

        txDatePropection.setPreferredSize(new java.awt.Dimension(80, 30));

        labelInteret.setText("Interet :");

        comboInteresset.setSelectedItem(comboInteresset);
        comboInteresset.setPreferredSize(new java.awt.Dimension(80, 30));

        javax.swing.GroupLayout panProspectLayout = new javax.swing.GroupLayout(panProspect);
        panProspect.setLayout(panProspectLayout);
        panProspectLayout.setHorizontalGroup(
                panProspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panProspectLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panProspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(labelDateDeProspection)
                                        .addComponent(txDatePropection, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                        .addComponent(labelInteret)
                                        .addComponent(comboInteresset, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panProspectLayout.setVerticalGroup(
                panProspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panProspectLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelDateDeProspection)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txDatePropection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelInteret)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboInteresset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        labelNombreEmployer.setText("Nombre d'employés :");

        txNombreEmployer.setPreferredSize(new java.awt.Dimension(80, 30));

        labelChiffreAffaireSt.setText("Chiffre d'affaire :");

        txChiffreAffaire.setPreferredSize(new java.awt.Dimension(80, 30));

        javax.swing.GroupLayout panClientLayout = new javax.swing.GroupLayout(panClient);
        panClient.setLayout(panClientLayout);
        panClientLayout.setHorizontalGroup(
                panClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panClientLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelNombreEmployer)
                                        .addComponent(txNombreEmployer, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelChiffreAffaireSt)
                                        .addComponent(txChiffreAffaire, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(98, Short.MAX_VALUE))
        );
        panClientLayout.setVerticalGroup(
                panClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panClientLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelNombreEmployer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txNombreEmployer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelChiffreAffaireSt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txChiffreAffaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(7, Short.MAX_VALUE))
        );

        labelIdentifiant.setText("Identifiant :");

        txID.setPreferredSize(new java.awt.Dimension(80, 30));

        labelRaisonSocialeSt.setText("Raison Sociale ");

        txRaison.setMinimumSize(new java.awt.Dimension(80, 30));
        txRaison.setName("champsNom"); // NOI18N
        txRaison.setPreferredSize(new java.awt.Dimension(80, 30));

        labelTelephone.setText("Téléphone :");

        txTelephone.setMinimumSize(new java.awt.Dimension(80, 30));
        txTelephone.setName("email"); // NOI18N
        txTelephone.setPreferredSize(new java.awt.Dimension(80, 30));

        labelEmail.setText("Email :");

        txEmail.setMinimumSize(new java.awt.Dimension(80, 30));
        txEmail.setName("ville"); // NOI18N
        txEmail.setPreferredSize(new java.awt.Dimension(80, 30));

        labelDomainSt.setText("Domain :");

        comboDomainSt.setSelectedItem(comboDomainSt);

        labelVille.setText("Ville :");

        txVille.setMinimumSize(new java.awt.Dimension(80, 30));
        txVille.setName("telephone"); // NOI18N
        txVille.setPreferredSize(new java.awt.Dimension(80, 30));

        labelCodePostale.setText("Code Postale :");

        txCodePostale.setMinimumSize(new java.awt.Dimension(80, 30));
        txCodePostale.setName("codePostale"); // NOI18N
        txCodePostale.setPreferredSize(new java.awt.Dimension(80, 30));

        labelNumeroAdresse.setText("Numéro adresse :");

        txNumeroAd.setMinimumSize(new java.awt.Dimension(80, 30));
        txNumeroAd.setName("numeroRue"); // NOI18N
        txNumeroAd.setPreferredSize(new java.awt.Dimension(80, 30));

        labelNomDeRue.setText("Nom de rue :");

        txNomRue.setMinimumSize(new java.awt.Dimension(80, 30));
        txNomRue.setName("nomRue"); // NOI18N
        txNomRue.setPreferredSize(new java.awt.Dimension(80, 30));

        javax.swing.GroupLayout panFormulaireLayout = new javax.swing.GroupLayout(panFormulaire);
        panFormulaire.setLayout(panFormulaireLayout);
        panFormulaireLayout.setHorizontalGroup(
                panFormulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panFormulaireLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panFormulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panFormulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(comboDomainSt, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(labelDomainSt)
                                                .addComponent(labelVille)
                                                .addComponent(txVille, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(panFormulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panFormulaireLayout.createSequentialGroup()
                                                                .addGroup(panFormulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(labelTelephone)
                                                                        .addGroup(panFormulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(txTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(panFormulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(labelEmail)
                                                                                        .addComponent(txEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addGap(1, 1, 1))
                                                        .addComponent(txRaison, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(labelIdentifiant)
                                                .addComponent(txID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(labelRaisonSocialeSt))
                                        .addGroup(panFormulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panFormulaireLayout.createSequentialGroup()
                                                        .addComponent(labelCodePostale)
                                                        .addGap(105, 105, 105))
                                                .addGroup(panFormulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txCodePostale, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txNumeroAd, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(labelNumeroAdresse)
                                                        .addComponent(txNomRue, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(labelNomDeRue))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panFormulaireLayout.setVerticalGroup(
                panFormulaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panFormulaireLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelIdentifiant)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(labelRaisonSocialeSt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txRaison, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelTelephone)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelEmail)
                                .addGap(3, 3, 3)
                                .addComponent(txEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelDomainSt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboDomainSt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelVille)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txVille, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelCodePostale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txCodePostale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelNumeroAdresse)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txNumeroAd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelNomDeRue)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txNomRue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bpMenuFormulaire.setText("Menu");
        bpMenuFormulaire.setMaximumSize(new java.awt.Dimension(69, 32));
        bpMenuFormulaire.setMinimumSize(new java.awt.Dimension(69, 32));
        bpMenuFormulaire.setName("menu"); // NOI18N
        bpMenuFormulaire.setPreferredSize(new java.awt.Dimension(90, 32));
        bpMenuFormulaire.addActionListener(new ActionRetourMenu(this));

        bpQuitterFormulaire.setText("Quitter");
        bpQuitterFormulaire.setName("quitter"); // NOI18N
        bpQuitterFormulaire.setPreferredSize(new java.awt.Dimension(90, 32));
        bpQuitterFormulaire.addActionListener(new ActionQuitter());

        javax.swing.GroupLayout panFooterLayout = new javax.swing.GroupLayout(panFooter);
        panFooter.setLayout(panFooterLayout);
        panFooterLayout.setHorizontalGroup(
                panFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panFooterLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bpMenuFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bpQuitterFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
        );
        panFooterLayout.setVerticalGroup(
                panFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panFooterLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bpMenuFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bpQuitterFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelCommentaireClient.setText("Commentaire :");

        bpValiderFormulaire.setText(this.actionTypeMenu);
        bpValiderFormulaire.setMaximumSize(new java.awt.Dimension(69, 32));
        bpValiderFormulaire.setMinimumSize(new java.awt.Dimension(69, 32));
        bpValiderFormulaire.setName("valider"); // NOI18N
        bpValiderFormulaire.addActionListener(new ActionValideFormulaire(this, this.controleur));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelCommentaireClient)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(txCommentaire, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(139, 139, 139)
                                                .addComponent(bpValiderFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelCommentaireClient)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txCommentaire, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bpValiderFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(panFooter, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(panFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addGap(6, 6, 6)
                                                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(panClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(panProspect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panFormulaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(panProspect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(panClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                                .addComponent(panFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54))
        );

        pack();
    }

    private javax.swing.JButton bpMenuFormulaire;
    private javax.swing.JButton bpQuitterFormulaire;
    private javax.swing.JButton bpValiderFormulaire;
    private javax.swing.JComboBox comboDomainSt;
    private javax.swing.JComboBox comboInteresset;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelChiffreAffaireSt;
    private javax.swing.JLabel labelCodePostale;
    private javax.swing.JLabel labelCommentaireClient;
    private javax.swing.JLabel labelDateDeProspection;
    private javax.swing.JLabel labelDomainSt;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelIdentifiant;
    private javax.swing.JLabel labelInteret;
    private javax.swing.JLabel labelNomDeRue;
    private javax.swing.JLabel labelNombreEmployer;
    private javax.swing.JLabel labelNumeroAdresse;
    private javax.swing.JLabel labelRaisonSocialeSt;
    private javax.swing.JLabel labelTelephone;
    private javax.swing.JLabel labelTitreFormulaire;
    private javax.swing.JLabel labelVille;
    private javax.swing.JPanel panClient;
    private javax.swing.JPanel panFooter;
    private javax.swing.JPanel panFormulaire;
    private javax.swing.JPanel panProspect;
    private javax.swing.JPanel panTitre;
    private javax.swing.JTextField txChiffreAffaire;
    private javax.swing.JTextField txCodePostale;
    private java.awt.TextArea txCommentaire;
    private javax.swing.JTextField txDatePropection;
    private javax.swing.JTextField txEmail;
    private javax.swing.JTextField txID;
    private javax.swing.JTextField txNomRue;
    private javax.swing.JTextField txNombreEmployer;
    private javax.swing.JTextField txNumeroAd;
    private javax.swing.JTextField txRaison;
    private javax.swing.JTextField txTelephone;
    private javax.swing.JTextField txVille;
}
