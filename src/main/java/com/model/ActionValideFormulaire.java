package com.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.model.MenuFrame.Action;

import javax.swing.*;

public class ActionValideFormulaire implements ActionListener {

    private FormulaireFrame formulaireFrame;

    ActionValideFormulaire(FormulaireFrame frame) {
        this.formulaireFrame = frame;
    }

    /**
     * Cette Evenement gére l'action sur le bouton valide de la frame Formulaire/
     *
     * @param e de type ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String nomBp = (String) ((JButton) e.getSource()).getText();

        if (nomBp.equals(Action.MODIFICATION.getAction())) {
            this.formulaireFrame.modificationSociete();

        } else if (nomBp.equals(Action.SUPPRESSION.getAction())) {
            this.formulaireFrame.supprimerSociete();

        } else if (nomBp.equals(Action.AJOUT.getAction())) {
            this.formulaireFrame.ajouterSociete();
        }

    }
}
