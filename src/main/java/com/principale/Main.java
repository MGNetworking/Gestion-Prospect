package com.principale;

import com.metier.Client;
import com.metier.Prospect;
import com.metier.Societe;
import com.controleur.ControleurFrame;
import com.model.MenuFrame;
import java.awt.*;

public class Main {

    /**
     * Point d'entré du programme.
     *
     * @param agrs String
     */
    public static void main(String[] agrs) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ControleurFrame controleurFrame = new ControleurFrame();
                MenuFrame menuF = new MenuFrame(controleurFrame);

            }
        });
    }
}
