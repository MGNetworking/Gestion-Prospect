package com.listener;

import com.metier.Societe;

import javax.sound.midi.SysexMessage;
import javax.swing.*;

import com.metier.Societe.TypeSociete;
import com.model.MenuFrame;

import java.sql.SQLException;

public class ActionClientProspect implements java.awt.event.ActionListener {

    private MenuFrame menuFrame;

    /**
     * Ce Listener permet de gérer l"action utilisateur sur le menu,
     * dans le but de sélectionné la parti client ou prospect.
     *
     * @param frame
     * @param controFrame
     */
    public ActionClientProspect(MenuFrame frame) {
        this.menuFrame = frame;

    }

    /**
     * Evenement sur les bouton Client et prospect.
     *
     * @param e de type ActionEvent
     * @throws NullPointerException si le choix ne trouve pas de correspondance
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) throws NullPointerException {

        JButton bp = (JButton) e.getSource();   // récupération de l'évenement.
        String nombp = (String) bp.getText();  // récupération du nom du bouton
        String choix = null;                    // choix du client ou du prospect

        // contexte de choix Client
        this.menuFrame.getPanActionUser().setVisible(true);
        this.menuFrame.getPanClientProspect().setVisible(false);

        // aiguillage par le nom du bouton client ou propect
        if (nombp.equals(TypeSociete.CLIENT.getTypeSociete())) {
            choix = Societe.TypeSociete.CLIENT.getTypeSociete();


        } else if (nombp.equals(TypeSociete.PROSPECT.getTypeSociete())) {
            choix = TypeSociete.PROSPECT.getTypeSociete();

        }

        // Initialisation des composants
        if (choix != null) {

            this.menuFrame.getLabelTitreMenuDeSelection().setText(choix);

            // garde en memoire le choix client prospect
            this.menuFrame.setMemoireMenuClientProspect(choix);

            try{
                // initialisation du model
                DefaultComboBoxModel model = new DefaultComboBoxModel(
                        this.menuFrame.getControleur().getListeSocieteControleur(choix).toArray());
                System.out.println("prospect apres getList ");
                this.menuFrame.getJComboBoxListeSociete().setModel(model);

            }catch (Exception exc){
                // TODO voir les logg
                System.out.println("Erreur : " + exc.getMessage());
            }


        }

    }
}
