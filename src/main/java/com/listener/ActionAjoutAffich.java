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
     * Action du bouton Ajout ou Affichage Liste
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

        if (Action.AJOUT.getAction().equals(nomButton)) {
            // TODO a faire implementation de la frame et faire un New MenuFrame pour le retour
            new FormulaireFrame(this.menuFrame.getMemoireClientProspect(),
                                Action.AJOUT, this.Controleur);

        } else if (Action.AFFICHAGE_LISTE.getAction().equals(nomButton)) {
            // TODO a faire implementation de la frame et faire un New MenuFrame pour le retour
            new AffichageListeFrame(this.menuFrame.getMemoireClientProspect(), this.Controleur);
        }

        this.menuFrame.dispose();   //  libaire les resources de la Frame menu
    }
}
