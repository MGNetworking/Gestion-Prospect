package com.listener.ListenerFormulaire;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.controleur.ControleurFrame;
import com.model.FormulaireFrame;
import com.model.MenuFrame.Action;

import javax.swing.*;

public class ActionValideFormulaire implements ActionListener {

    private FormulaireFrame formulaireFrame;
    private ControleurFrame controleur;

    public ActionValideFormulaire(FormulaireFrame frame, ControleurFrame controleur) {
        this.formulaireFrame = frame;
        this.controleur = controleur;
    }

    /**
     * Cette Evenement gére l'action sur le bouton valide de la frame Formulaire
     *
     * @param e de type ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String nomBp = (String) ((JButton) e.getSource()).getText();

        if (nomBp.equals(Action.MODIFICATION.getAction())) {
            this.formulaireFrame.ajouterModifierSociete(false);

        } else if (nomBp.equals(Action.SUPPRESSION.getAction())) {
            this.formulaireFrame.supprimerSociete();

        } else if (nomBp.equals(Action.AJOUT.getAction())) {

            this.formulaireFrame.ajouterModifierSociete(true);
        }

    }
}
