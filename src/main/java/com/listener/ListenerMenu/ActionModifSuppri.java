package com.listener.ListenerMenu;

import com.model.MenuFrame;

import javax.swing.JButton;

/**
 * Cette classe est un écouteur d'évenement sur la frame Menu.
 */
public class ActionModifSuppri implements java.awt.event.ActionListener {

    private MenuFrame menuFrame;

    /**
     * Ce Listener permet de gérer l'action utilisateur dans la frame du menu
     * dans le but de modifier la visibilité du choix de la comboBox Client ou prospect.
     * Si l'utilisateur choisi Modifier ou supprimer le panneau contenant la comboBox
     * apparaîtra.
     *
     * @param frame de type MenuFrame
     */
    public ActionModifSuppri(MenuFrame frame) {
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
