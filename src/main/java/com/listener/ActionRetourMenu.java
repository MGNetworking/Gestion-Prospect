package com.listener;

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
     */
    public ActionRetourMenu(JFrame frame, ControleurFrame controleur) {
        this.jframe = frame;
        this.controleur = controleur;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // ici si je fait this.controleur a la place de new ControleurFrame()
        // quand je revien vers le me
        // nu apres avoir fait une selection j'ai un nullPointeur
        new MenuFrame(this.controleur);
        this.jframe.dispose();
    }
}
