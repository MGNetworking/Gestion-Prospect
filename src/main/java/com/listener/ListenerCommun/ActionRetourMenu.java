package com.listener.ListenerCommun;

import com.controleur.ControleurFrame;
import com.model.MenuFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe est un ecouteur d'événement
 * Elle permet le retour vers la Frame Menu.
 */
public class ActionRetourMenu implements ActionListener {

    private JFrame jframe;
    private ControleurFrame controleur;

    /**
     * Cette permet le retour au menu principale
     *
     * @param frame de type JFrame
     * @param controleur de type ControleurFrame.
     */
    public ActionRetourMenu(JFrame frame, ControleurFrame controleur) {
        this.jframe = frame;
        this.controleur = controleur;
    }

    /**
     * Si l'événement est solicité, retour au menu princiaple.
     *
     * @param e de type actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new MenuFrame(new ControleurFrame());
        this.jframe.dispose();
    }
}
