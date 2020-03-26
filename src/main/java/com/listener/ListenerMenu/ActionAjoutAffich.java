package com.listener.ListenerMenu;

import com.model.AffichageListeFrame;
import com.controleur.ControleurFrame;
import com.model.FormulaireFrame;
import com.model.MenuFrame;
import com.model.MenuFrame.Action;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

/**
 * Cette class est un ecouteur d'événement sur la frame Menu
 */
public class ActionAjoutAffich implements java.awt.event.ActionListener {

    private MenuFrame menuFrame;
    private ControleurFrame Controleur;

    /**
     * Action du bouton Ajout ou Affichage Liste dans le menu principale
     *
     * @param frame
     */
    public ActionAjoutAffich(MenuFrame frame, ControleurFrame controleur) {
        this.menuFrame = frame;
        this.Controleur = controleur;

    }

    /**
     * Méthode qui exécute une action venant des boutons Affichage et Ajout
     *
     * @param e de type ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton bp = (JButton) e.getSource();                   // caste de la source ajouter et affichage liste
        String nomButton = (String) bp.getText();               // recupération de la valeur du bp

        if (nomButton.equals(Action.AFFICHAGE.getAction())) {   // pour l'affichage de liste
            new AffichageListeFrame(this.menuFrame.getMemoireClientProspect(), this.Controleur);

        } else if (nomButton.equals(Action.AJOUT.getAction())) { // pour l'affichage de l'ajoute
            new FormulaireFrame(Action.AJOUT, this.Controleur, this.menuFrame.getMemoireClientProspect());
        }

        this.menuFrame.dispose();   //  libaire les resources de la Frame menu
    }
}
