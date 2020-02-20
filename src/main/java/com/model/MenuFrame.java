package com.model;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Cette Classe permet d'affiché le menu principale
 *
 * @author Maxime
 */
public class MenuFrame extends javax.swing.JFrame {

    // ControleurFrame des Frames
    private ControleurFrame controleur;

    /**
     * Constructeur du menu contextuelle
     */
    public MenuFrame() {
        // Initialisation des composants
        initComponents();
    }
    
    /**
     * Modifie le controleur
     *
     * @param controleur de type ControleurFrame.
     */
    public void setControleur(ControleurFrame controleur) {
        this.controleur = controleur;
    }



    /**
     * Accesseur sur le titre du Menu
     *
     * @return Objet JLabel du Titre du Menu
     */
    public JLabel getLabelTitreMenuDeSelection() {
        return this.labelTitreMenu;
    }

    /**
     * Accesseur sur la comboBox contenant la liste des client ou des prospect.
     *
     * @return Objet de type JComboBox.
     */
    public JComboBox getJComboBoxListeSociete() {
        return this.jComboBoxListeSociete;
    }

    public JButton getBpAfficheListe() {
        return bpAfficheListe;
    }

 

    public JButton getBpMenu() {
        return bpMenu;
    }


    public JPanel getPanClientProspect() {
        return PanClientProspect;
    }

    public void setPanClientProspect(JPanel PanClientProspect) {
        this.PanClientProspect = PanClientProspect;
    }

    public JPanel getPanActionUser() {
        return panActionUser;
    }

    public void setPanActionUser(JPanel panActionUser) {
        this.panActionUser = panActionUser;
    }

    public JPanel getPanFooter() {
        return panFooter;
    }

    public void setPanFooter(JPanel panFooter) {
        this.panFooter = panFooter;
    }

    public JPanel getPanMenuDeSelection() {
        return panMenuDeSelection;
    }

    public void setPanMenuDeSelection(JPanel panMenuDeSelection) {
        this.panMenuDeSelection = panMenuDeSelection;
    }

    public JPanel getPanValidationUser() {
        return panValidationUser;
    }

    public void setPanValidationUser(JPanel panValidationUser) {
        this.panValidationUser = panValidationUser;
    }

    /**
     * **********************************************************Evenement
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        panMenuDeSelection = new javax.swing.JPanel();
        labelTitreMenu = new javax.swing.JLabel();
        panFooter = new javax.swing.JPanel();
        bpMenu = new javax.swing.JButton();
        bpQuitterMenuSelection = new javax.swing.JButton();
        PanClientProspect = new javax.swing.JPanel();
        bpClient = new javax.swing.JButton();
        bpProspect = new javax.swing.JButton();
        panActionUser = new javax.swing.JPanel();
        bpModifier = new javax.swing.JButton();
        bpSupprime = new javax.swing.JButton();
        bpAfficheListe = new javax.swing.JButton();
        bpAjouter = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        panValidationUser = new javax.swing.JPanel();
        jComboBoxListeSociete = new javax.swing.JComboBox();
        bpValiderMenu = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("frameSelectionUser"); // NOI18N
        setResizable(false);

        panMenuDeSelection.setMaximumSize(null);

        labelTitreMenu.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        labelTitreMenu.setText("Menu de sélection");
        labelTitreMenu.setName("MenuDeSelection"); // NOI18N

        javax.swing.GroupLayout panMenuDeSelectionLayout = new javax.swing.GroupLayout(panMenuDeSelection);
        panMenuDeSelection.setLayout(panMenuDeSelectionLayout);
        panMenuDeSelectionLayout.setHorizontalGroup(
            panMenuDeSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panMenuDeSelectionLayout.createSequentialGroup()
                .addGap(0, 203, Short.MAX_VALUE)
                .addComponent(labelTitreMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panMenuDeSelectionLayout.setVerticalGroup(
            panMenuDeSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panMenuDeSelectionLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(labelTitreMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bpMenu.setText("Menu");
        bpMenu.setName("menu"); // NOI18N
        bpMenu.setPreferredSize(new java.awt.Dimension(90, 32));
        bpMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpMenuActionPerformed(evt);
            }
        });

        bpQuitterMenuSelection.setText("Quitter");
        bpQuitterMenuSelection.setActionCommand("QuitterMenuSelection");
        bpQuitterMenuSelection.setName("quitter"); // NOI18N
        bpQuitterMenuSelection.setPreferredSize(new java.awt.Dimension(90, 32));
        bpQuitterMenuSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpQuitterMenuSelectionPanneau1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panFooterLayout = new javax.swing.GroupLayout(panFooter);
        panFooter.setLayout(panFooterLayout);
        panFooterLayout.setHorizontalGroup(
            panFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panFooterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bpMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bpQuitterMenuSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panFooterLayout.setVerticalGroup(
            panFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panFooterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bpQuitterMenuSelection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bpMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bpQuitterMenuSelection.getAccessibleContext().setAccessibleName("QuitterMenuSelection");
        bpQuitterMenuSelection.getAccessibleContext().setAccessibleDescription("");

        bpClient.setText("Client");
        bpClient.setActionCommand("ClientMenuSelection");
        bpClient.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bpClient.setMaximumSize(new java.awt.Dimension(100, 32));
        bpClient.setMinimumSize(new java.awt.Dimension(100, 32));
        bpClient.setName("client"); // NOI18N
        bpClient.setPreferredSize(new java.awt.Dimension(100, 32));
        bpClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpClientActionPerformed(evt);
            }
        });

        bpProspect.setText("Prospect");
        bpProspect.setMaximumSize(new java.awt.Dimension(100, 32));
        bpProspect.setMinimumSize(new java.awt.Dimension(100, 32));
        bpProspect.setPreferredSize(new java.awt.Dimension(100, 32));
        bpProspect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpProspectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanClientProspectLayout = new javax.swing.GroupLayout(PanClientProspect);
        PanClientProspect.setLayout(PanClientProspectLayout);
        PanClientProspectLayout.setHorizontalGroup(
            PanClientProspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanClientProspectLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bpClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bpProspect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanClientProspectLayout.setVerticalGroup(
            PanClientProspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanClientProspectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanClientProspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bpProspect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bpClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        bpModifier.setText("Modifier");
        bpModifier.setMaximumSize(new java.awt.Dimension(100, 32));
        bpModifier.setMinimumSize(new java.awt.Dimension(100, 32));
        bpModifier.setName("modifier"); // NOI18N
        bpModifier.setPreferredSize(new java.awt.Dimension(100, 32));
        bpModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpModifierActionPerformed(evt);
            }
        });

        bpSupprime.setText("Supprimer");
        bpSupprime.setMaximumSize(new java.awt.Dimension(100, 32));
        bpSupprime.setMinimumSize(new java.awt.Dimension(100, 32));
        bpSupprime.setName("supprimer"); // NOI18N
        bpSupprime.setPreferredSize(new java.awt.Dimension(100, 32));
        bpSupprime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpSupprimeActionPerformed(evt);
            }
        });

        bpAfficheListe.setText("Affiche liste ");
        bpAfficheListe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpAfficheListeActionPerformed(evt);
            }
        });

        bpAjouter.setText("Ajouter");
        bpAjouter.setMaximumSize(new java.awt.Dimension(100, 32));
        bpAjouter.setName("ajouter"); // NOI18N
        bpAjouter.setPreferredSize(new java.awt.Dimension(100, 32));
        bpAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpAjouterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panActionUserLayout = new javax.swing.GroupLayout(panActionUser);
        panActionUser.setLayout(panActionUserLayout);
        panActionUserLayout.setHorizontalGroup(
            panActionUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panActionUserLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bpModifier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bpSupprime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bpAfficheListe, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bpAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(panActionUserLayout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panActionUserLayout.setVerticalGroup(
            panActionUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panActionUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panActionUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bpModifier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bpSupprime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bpAfficheListe, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bpAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jComboBoxListeSociete.setSelectedItem(jComboBoxListeSociete);
        jComboBoxListeSociete.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBoxListeSociete.setMinimumSize(new java.awt.Dimension(80, 25));
        jComboBoxListeSociete.setName("comboBoxClient"); // NOI18N
        jComboBoxListeSociete.setPreferredSize(new java.awt.Dimension(82, 32));
        jComboBoxListeSociete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxListeSocieteActionPerformed(evt);
            }
        });

        bpValiderMenu.setText("Valider");
        bpValiderMenu.setPreferredSize(new java.awt.Dimension(90, 32));
        bpValiderMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpValiderMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panValidationUserLayout = new javax.swing.GroupLayout(panValidationUser);
        panValidationUser.setLayout(panValidationUserLayout);
        panValidationUserLayout.setHorizontalGroup(
            panValidationUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panValidationUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panValidationUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panValidationUserLayout.createSequentialGroup()
                        .addComponent(jComboBoxListeSociete, 0, 281, Short.MAX_VALUE)
                        .addGap(74, 74, 74))
                    .addGroup(panValidationUserLayout.createSequentialGroup()
                        .addComponent(bpValiderMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panValidationUserLayout.setVerticalGroup(
            panValidationUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panValidationUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxListeSociete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(bpValiderMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jComboBoxListeSociete.getAccessibleContext().setAccessibleName("");
        jComboBoxListeSociete.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filler7, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(filler4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(128, 128, 128)
                                        .addComponent(filler6, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(filler5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(PanClientProspect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panMenuDeSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(filler8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panValidationUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panActionUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panMenuDeSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(PanClientProspect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(panActionUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(panValidationUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(filler8, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filler5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(filler4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Buoton pour quitter le programme.
     *
     * @param evt de type ActionEvent
     */
    private void bpQuitterMenuSelectionPanneau1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpQuitterMenuSelectionPanneau1ActionPerformed
        controleur.quitterEvent(evt);
    }//GEN-LAST:event_bpQuitterMenuSelectionPanneau1ActionPerformed

    /**
     * Sélection du choix client.
     *
     * @param evt du type ActionEvent
     */
    private void bpClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpClientActionPerformed
        controleur.bpClientEvent(evt);

    }//GEN-LAST:event_bpClientActionPerformed
    /**
     * Événement du bouton Menu
     *
     * @param evt de type ActionEvent
     */
    private void bpMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpMenuActionPerformed
        controleur.menuEvent(evt);

    }//GEN-LAST:event_bpMenuActionPerformed
    /**
     * Événement du bouton ajouter.
     *
     * @param evt de type ActionEvent
     */
    private void bpAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpAjouterActionPerformed
        controleur.bpAjouterMenuEvent(evt);
    }//GEN-LAST:event_bpAjouterActionPerformed
    /**
     * Bouton modifier, Action d'affichageModifSuppClientProsp pour modifié un
     * client / prospect.
     *
     * @param evt de type ActionEvent
     */
    private void bpModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpModifierActionPerformed
        controleur.bpModifierMenuEvent(evt);
    }//GEN-LAST:event_bpModifierActionPerformed
    /**
     * Initialise la comboBox en fonction du choix de l'action.
     *
     * @param evt de type ActionEvent
     */
    private void jComboBoxListeSocieteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxListeSocieteActionPerformed
        this.controleur.comboBoxMenuListeEvent(evt);
    }//GEN-LAST:event_jComboBoxListeSocieteActionPerformed

    /**
     * Bouton supprimer, Action d'affichageModifSuppClientProsp pour supprimé un
     * client / prospect.
     *
     * @param evt de type ActionEvent
     */
    private void bpSupprimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpSupprimeActionPerformed
        controleur.bpSupprimerMenuEvent(evt);
    }//GEN-LAST:event_bpSupprimeActionPerformed
    /**
     * Bouton valider de la fenêtre principale.
     *
     * @param evt de type ActionEvent.
     */
    private void bpValiderMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpValiderMenuActionPerformed
        controleur.bpValiderMenuEvent(evt);
    }//GEN-LAST:event_bpValiderMenuActionPerformed

    private void bpAfficheListeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpAfficheListeActionPerformed
        controleur.bpAfficherMenuEvent(evt);
    }//GEN-LAST:event_bpAfficheListeActionPerformed

    private void bpProspectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpProspectActionPerformed
        controleur.bpProspectEvent(evt);
    }//GEN-LAST:event_bpProspectActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanClientProspect;
    private javax.swing.JButton bpAfficheListe;
    private javax.swing.JButton bpAjouter;
    private javax.swing.JButton bpClient;
    private javax.swing.JButton bpMenu;
    private javax.swing.JButton bpModifier;
    private javax.swing.JButton bpProspect;
    private javax.swing.JButton bpQuitterMenuSelection;
    private javax.swing.JButton bpSupprime;
    private javax.swing.JButton bpValiderMenu;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBoxListeSociete;
    private javax.swing.JLabel labelTitreMenu;
    private javax.swing.JPanel panActionUser;
    private javax.swing.JPanel panFooter;
    private javax.swing.JPanel panMenuDeSelection;
    private javax.swing.JPanel panValidationUser;
    // End of variables declaration//GEN-END:variables

}
