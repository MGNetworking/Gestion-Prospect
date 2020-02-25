package com.model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionRetourMenu implements ActionListener {

    private JFrame jframe;

    /**
     * Cette permet le retour au menu principale
     *
     * @param frame
     */
    ActionRetourMenu(JFrame frame) {
        this.jframe = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        new MenuFrame(new ControleurFrame());
        this.jframe.dispose();
    }
}
