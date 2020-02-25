package com.model;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.metier.Client;
import com.metier.Prospect;
import com.metier.Societe;
import com.model.MenuFrame.Action;
import com.metier.Societe.TypeSociete;

import java.util.List;

/**
 * Cette classe permet l'affichage de la liste des clients ou des prospect.
 *
 * @author Maxime
 */
public class AffichageListeFrame extends javax.swing.JFrame {

    private String choixMemoireClientProspect;

    /**
     * Constructeur
     */
    public AffichageListeFrame(String memoireClientProspect) {

        this.setVisible(true);
        this.choixMemoireClientProspect = memoireClientProspect;

        initComponents();

        this.setModelAffichageListe(
            iniDataModelAfficheList(this.choixMemoireClientProspect),
            initTitreDataModelAffichageListe(this.choixMemoireClientProspect));

    }

    /**
     * Permet d'initialiser le model de données du Jtable.
     * <p>
     * Pour le model :</p>
     * <ul>
     * <li>La matrice récupère les champs du client ou du prospect. </Li>
     * <li> Le tableau de String récupère les noms  </li>
     * </ul>
     *
     * @param dataSt  String matrice de données, Client ou Prospect.
     * @param titreSt String tableau champs appartenant à client ou prospect.
     */
    public void setModelAffichageListe(String[][] dataSt, String[] titreSt) {

        DefaultTableModel model = new DefaultTableModel(dataSt, titreSt) {

            @Override // permet pas la modification
            public boolean isCellEditable(int ligne, int colonne) {

                return false;
            }
        };

        // changement du model
        this.panTbAffichageListe.setModel(model);

    }

    /**
     * Initialisation des titre du tableau d'affichage.
     *
     * @param choix String l'utilisateur choisi d'affiché le tableau des clients
     *              ou des prospects.
     * @return String tableau des entêtes du tableau.
     */
    public String[] initTitreDataModelAffichageListe(String choix) {

        String[] finaleData = new String[12];

        finaleData[0] = "Identifiant";
        finaleData[1] = "RaisonSociale";
        finaleData[2] = "Domain";
        finaleData[3] = "N° adresse";
        finaleData[4] = "Nom de rue";
        finaleData[5] = "Code postale";
        finaleData[6] = "Ville";
        finaleData[7] = "Téléphone";
        finaleData[8] = "Email";
        finaleData[9] = "Commentaire";

        // si c'est client
        if (choix.equals(Societe.TypeSociete.CLIENT.getTypeSociete())) {
            this.jLabelTitreTableau.setText("Tableau des Clients");
            finaleData[10] = "Chiffre d'affaire";
            finaleData[11] = "Nombre employer";

            // si c'est prospect
        } else if (choix.equals(TypeSociete.PROSPECT.getTypeSociete())) {

            this.jLabelTitreTableau.setText("Tableau des Prospects");
            finaleData[10] = "Date de prospection";
            finaleData[11] = "Interet";

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
        if (chUser.equals(TypeSociete.CLIENT.getTypeSociete())) {

            // TODO fair methode vers controleur
            listeSociete = Client.getListeClient();
            sizeArray = Client.getSizeListeClient();

        } else if (chUser.equals(TypeSociete.PROSPECT.getTypeSociete())) {
            client = false;
            // TODO fair methode vers controleur
            listeSociete = Prospect.getListePropect();
            sizeArray = Prospect.getSizeListeProspect();
        }

        // Initialisation du tableau d'affichage
        String[][] personnelle = new String[sizeArray][12];

        // Pour chaque Client
        for (int i = 0; i < sizeArray; i++) {

            personnelle[i][0] = String.valueOf(listeSociete.get(i).getIdentifiant());
            personnelle[i][1] = listeSociete.get(i).getRaisonSociale();
            personnelle[i][2] = listeSociete.get(i).getDomainSociete().toString();
            personnelle[i][3] = String.valueOf(listeSociete.get(i).getAdresse().getNumeroDeRueSt());
            personnelle[i][4] = listeSociete.get(i).getAdresse().getNomRue();
            personnelle[i][5] = listeSociete.get(i).getAdresse().getCodePost();
            personnelle[i][6] = listeSociete.get(i).getAdresse().getVille();
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
                personnelle[i][11] = Prospect.getListePropect().get(i).getInteresse().toString();
            }

        }

        return personnelle;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "Convert2Lambda"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTableauAffichage = new javax.swing.JPanel();
        jLabelTitreTableau = new javax.swing.JLabel();
        panFooterAffichageListe = new javax.swing.JPanel();
        bpMenu = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panTbAffichageListe = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("affichageFrame");
        setResizable(false);

        jLabelTitreTableau.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabelTitreTableau.setText("Titre du Tableau");

        javax.swing.GroupLayout jPanelTableauAffichageLayout = new javax.swing.GroupLayout(jPanelTableauAffichage);
        jPanelTableauAffichage.setLayout(jPanelTableauAffichageLayout);
        jPanelTableauAffichageLayout.setHorizontalGroup(
            jPanelTableauAffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelTableauAffichageLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelTitreTableau, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTableauAffichageLayout.setVerticalGroup(
            jPanelTableauAffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelTableauAffichageLayout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jLabelTitreTableau, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addContainerGap())
        );

        bpMenu.setText("Menu");
        bpMenu.setPreferredSize(new java.awt.Dimension(80, 32));
        bpMenu.addActionListener(new ActionRetourMenu(this));

        jButton1.setText("Quitter");
        jButton1.setPreferredSize(new java.awt.Dimension(80, 32));
        jButton1.addActionListener(new ActionQuitter());

        javax.swing.GroupLayout panFooterAffichageListeLayout = new javax.swing.GroupLayout(panFooterAffichageListe);
        panFooterAffichageListe.setLayout(panFooterAffichageListeLayout);
        panFooterAffichageListeLayout.setHorizontalGroup(
            panFooterAffichageListeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panFooterAffichageListeLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bpMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        panFooterAffichageListeLayout.setVerticalGroup(
            panFooterAffichageListeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panFooterAffichageListeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panFooterAffichageListeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bpMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panTbAffichageListe.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{

            },
            new String[]{

            }
        ));
        panTbAffichageListe.setPreferredSize(new java.awt.Dimension(1920, 750));
        jScrollPane1.setViewportView(panTbAffichageListe);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(512, 512, 512)
                            .addComponent(jPanelTableauAffichage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(311, 752, Short.MAX_VALUE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(panFooterAffichageListe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelTableauAffichage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(panFooterAffichageListe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(171, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bpMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabelTitreTableau;
    private javax.swing.JPanel jPanelTableauAffichage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panFooterAffichageListe;
    private javax.swing.JTable panTbAffichageListe;
    // End of variables declaration//GEN-END:variables
}
