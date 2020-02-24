package com.model;

import javax.swing.*;

import com.model.MenuFrame.action;

public class ActionButtonChoix implements java.awt.event.ActionListener {

    private MenuFrame menuFrame;
    private ControleurFrame controleurFrame;

    public ActionButtonChoix(MenuFrame frame, ControleurFrame controlFrame) {
        this.menuFrame = frame;
        this.controleurFrame = controlFrame;
    }


    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        
        JButton bp = (JButton) e.getSource();                   // caste de la source
        String nomButton = (String) bp.toString();              // recupération de la valeur du bp
        this.menuFrame.getPanValidationUser().setVisible(true); // visibilité la combobox et du pb valider
        this.menuFrame.setActionUser(nomButton);                // garde en memoire l'action utilisateur


    }
}
