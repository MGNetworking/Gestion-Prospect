package com.model;

import com.metier.Societe;

import javax.swing.*;

import com.metier.Societe.TypeSociete;

public class ActionButtonSelectionSociete implements java.awt.event.ActionListener {

    private MenuFrame menuFrame;
    private ControleurFrame controleurFrame;

    public ActionButtonSelectionSociete(MenuFrame frame, ControleurFrame controFrame) {
        this.menuFrame = frame;
        this.controleurFrame = controFrame;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) throws RuntimeException {

        JButton bp = (JButton) e.getSource();   // récupération de l'évenement.
        String nombp = (String) bp.toString();  // récupération du nom du bouton
        String choix = null;                    // choix du client ou du prospect

        // contexte de choix Client
        this.menuFrame.getPanActionUser().setVisible(true);
        this.menuFrame.getPanClientProspect().setVisible(false);

        // aiguillage par le nom du bouton client ou propect
        if (TypeSociete.CLIENT.equals(nombp)) {
            choix = Societe.TypeSociete.CLIENT.toString();

        } else if (TypeSociete.PROSPECT.equals(nombp)) {
            choix = TypeSociete.PROSPECT.toString();
        }

        // Initialisation des composants
        if (choix != null) {
            this.menuFrame.getLabelTitreMenuDeSelection().setText(choix);

            // initialisation du model
            DefaultComboBoxModel model = new DefaultComboBoxModel(
                this.controleurFrame.getListeChoixSociete(choix).toArray());

            model.setSelectedItem("Liste des " + choix);
            this.menuFrame.getJComboBoxListeSociete().setModel(model);

        } else {
            throw new RuntimeException("Erreur dans l'évenment du bouton client / prospect ");
        }

    }
}
