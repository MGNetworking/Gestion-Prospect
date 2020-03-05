package com.listener;

import com.model.AffichageListeFrame;
import com.controleur.ControleurFrame;
import com.model.FormulaireFrame;
import com.model.MenuFrame;
import com.model.MenuFrame.Action;

import javax.swing.JButton;

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

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {

        JButton bp = (JButton) e.getSource();                   // caste de la source ajouter et affichage liste
        String nomButton = (String) bp.getText();              // recupération de la valeur du bp

        // pour l'affichage de liste
        if (nomButton.equals(Action.AFFICHAGE_LISTE.getAction())) {

            new AffichageListeFrame(this.menuFrame.getMemoireClientProspect(), this.Controleur);

            // pour l'affichage de l'ajoute
        } else if (nomButton.equals(Action.AJOUT.getAction())) {

            new FormulaireFrame(Action.AJOUT, this.Controleur ,this.menuFrame.getMemoireClientProspect());
            this.menuFrame.dispose();     //  libaire les resources de la Frame menu
        }

        this.menuFrame.dispose();   //  libaire les resources de la Frame menu
    }
}
