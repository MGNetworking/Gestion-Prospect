package com.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionQuitter implements ActionListener {

    public ActionQuitter(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
