package com.model;

import com.model.MenuFrame.Action;

import javax.swing.*;


public class ActionValidationFormulaire implements java.awt.event.ActionListener {

    private MenuFrame menuFrame;

    ActionValidationFormulaire(MenuFrame frame){
        this.menuFrame = frame;

    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {


        if (this.menuFrame.getChoixComboBoxClientProspect() == null) {
            JOptionPane.showMessageDialog(null, "Vous devez choisir dans la "
                    + "liste des clients ou prospects avant de validé ",
                "Erreur sélection", JOptionPane.INFORMATION_MESSAGE);
        } else {

            // choix = modifier / supprimer
            if (this.menuFrame.equals(Action.MODIFICATION)) {

                new FormulaireFrame(this.menuFrame.getChoixComboBoxClientProspect(), Action.MODIFICATION);
                this.menuFrame.dispose();     //  libaire les resources de la Frame menu

            } else if (this.menuFrame.getMemoModifSup().equals(Action.SUPPRESSION)) {

                new FormulaireFrame(this.menuFrame.getChoixComboBoxClientProspect(), Action.SUPPRESSION);
                this.menuFrame.dispose();     //  libaire les resources de la Frame menu
            }


        }

    }
}
