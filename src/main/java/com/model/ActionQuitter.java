package com.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionQuitter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
