package com.principale;

import com.controleur.ControleurFrame;
import com.model.MenuFrame;

import java.awt.*;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER_MAIN = Logger.getLogger(Main.class.getName());

    /**
     * Point d'entré du programme.
     *
     * @param agrs String
     */
    public static void main(String[] agrs) {

        try {

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            LOGGER_MAIN.severe(ex.getMessage());
        } catch (InstantiationException ex) {
            LOGGER_MAIN.severe(ex.getMessage());
        } catch (IllegalAccessException ex) {
            LOGGER_MAIN.severe(ex.getMessage());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            LOGGER_MAIN.severe(ex.getMessage());
        }

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ControleurFrame controleurFrame = new ControleurFrame();
                MenuFrame menuF = new MenuFrame(controleurFrame);


            }
        });
    }
}
