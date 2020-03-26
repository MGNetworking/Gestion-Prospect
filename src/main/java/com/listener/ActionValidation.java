package com.listener;

import com.controleur.ControleurFrame;
import com.model.FormulaireFrame;
import com.model.MenuFrame;
import com.model.MenuFrame.Action;

import javax.swing.*;


public class ActionValidation implements java.awt.event.ActionListener {

    private MenuFrame menuFrame;
    private ControleurFrame controleur;

    /**
     * Cette evenement gére la validation de la sélection dans la frame du Menu principale
     *
     * @param frame
     */
    public ActionValidation(MenuFrame frame, ControleurFrame controleur) {
        this.menuFrame = frame;
        this.controleur = controleur;

    }

    /**
     * Evenement du bouton supprimer ou modifier
     *
     * @param e de type ActionEvent
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {

        // si aucun choix n'est fait dans la comboBox
        if (this.menuFrame.getChoixComboBoxClientProspect() == null) {
            JOptionPane.showMessageDialog(null, "Vous devez choisir dans la "
                    + "liste des clients ou prospects avant de validé ",
                "Erreur sélection", JOptionPane.INFORMATION_MESSAGE);

        } else {

            // choix = modifier / supprimer
            if (this.menuFrame.getMemoModifSup().equals(Action.MODIFICATION.getAction())) {

                new FormulaireFrame(this.menuFrame.getChoixComboBoxClientProspect(),
                    Action.MODIFICATION, this.controleur, this.menuFrame.getMemoireClientProspect());


            } else if (this.menuFrame.getMemoModifSup().equals(Action.SUPPRESSION.getAction())) {

                new FormulaireFrame(this.menuFrame.getChoixComboBoxClientProspect(),
                    Action.SUPPRESSION, this.controleur, this.menuFrame.getMemoireClientProspect());


            }
            this.menuFrame.dispose();     //  libaire les resources de la Frame menu
        }

    }
}
