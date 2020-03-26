package com.listener.ListenerCommun;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe est un écouteur d'événement. Elle permet de quitter l'application
 */
public class ActionQuitter implements ActionListener {
    /**
     * Si l'événement est solicité, fermeture de l'application
     *
     * @param e de type actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
