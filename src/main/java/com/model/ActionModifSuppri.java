package com.model;

import javax.swing.JButton;

public class ActionModifSuppri implements java.awt.event.ActionListener {

    private MenuFrame menuFrame;

    ActionModifSuppri(MenuFrame frame) {
        this.menuFrame = frame;
    }

    /**
     * Action du bouton Modifier et supprimer.
     *
     * @param e de type ActionEvent
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {

        JButton bp = (JButton) e.getSource();                   // caste de la source Modifier ou supprimer
        String nomButton = (String) bp.getText();               // recupération de la valeur du bp
        this.menuFrame.getPanValidationUser().setVisible(true); // visibilité la combobox et du pb valider
        this.menuFrame.setMemoireModifiSup(nomButton);          // garde en memoire l'action utilisateur


    }
}
