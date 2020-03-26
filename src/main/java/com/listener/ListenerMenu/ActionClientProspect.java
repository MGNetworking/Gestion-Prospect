package com.listener.ListenerMenu;

import com.exception.ExceptionAdresse;
import com.exception.ExceptionPersonnaliser;
import com.exception.ExceptionProspect;
import com.exception.ExceptionSociete;
import com.metier.Societe;

import javax.swing.*;

import com.metier.Societe.TypeSociete;
import com.model.MenuFrame;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cette est une ecouteur d'évenement sur la frame Menu.
 */
public class ActionClientProspect implements java.awt.event.ActionListener {

    private static final Logger LOGGER_ACTION_CLIENT_PROSPECT = Logger.getLogger(ActionClientProspect.class.getName());

    private MenuFrame menuFrame;

    /**
     * Ce Listener permet de gérer l'action utilisateur dans la frame du menu
     * dans le but de sélectionné la parti client ou prospect.
     *
     * @param frame       de type MenuFrame
     * @param controFrame de type controFrame, le controleur des Fenètres
     */
    public ActionClientProspect(MenuFrame frame) {
        this.menuFrame = frame;

    }

    /**
     * Évenement sur les boutons Client et prospect.
     *
     * @param e de type ActionEvent
     * @throws NullPointerException si le choix ne trouve pas de correspondance
     *                              entre le bouton CLient et Prospect.
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {

        JButton bp = (JButton) e.getSource();  // récupération de l'évenement.
        String nombp = (String) bp.getText();  // récupération du nom du bouton
        String choix = null;                   // choix du client ou du prospect

        // contexte de choix de l'application
        this.menuFrame.getPanActionUser().setVisible(true);
        this.menuFrame.getPanClientProspect().setVisible(false);

        // aiguillage par le nom du bouton client ou propect
        if (nombp.equals(TypeSociete.CLIENT.getTypeSociete())) {
            choix = Societe.TypeSociete.CLIENT.getTypeSociete();


        } else if (nombp.equals(TypeSociete.PROSPECT.getTypeSociete())) {
            choix = TypeSociete.PROSPECT.getTypeSociete();

        }

        if (choix != null) { // Initialisation des composants
            try {

                this.menuFrame.getLabelTitreMenuDeSelection().setText("CHOIX : " + choix);
                // garde en memoire le choix client prospect
                this.menuFrame.setMemoireMenuClientProspect(choix);

                this.menuFrame.updateComboBoxList(choix);


                // Interception des erreurs générer venant de la base de données.
            } catch (ExceptionSociete exp) {

                LOGGER_ACTION_CLIENT_PROSPECT.log(Level.SEVERE, exp.getCause() + "\n" +
                    exp.getMessage() + "\n" +
                    exp.getStackTrace());

            } catch (ExceptionProspect exPr) {

                LOGGER_ACTION_CLIENT_PROSPECT.log(Level.SEVERE, exPr.getCause() + "\n" +
                    exPr.getMessage() + "\n" +
                    exPr.getStackTrace());

            } catch (ExceptionAdresse exAdr) {

                LOGGER_ACTION_CLIENT_PROSPECT.log(Level.SEVERE, exAdr.getCause() + "\n" +
                    exAdr.getMessage() + "\n" +
                    exAdr.getStackTrace());

            } catch (ExceptionPersonnaliser exp) {

                LOGGER_ACTION_CLIENT_PROSPECT.log(Level.SEVERE, exp.getCause() + "\n" +
                    exp.getMessage() + "\n" +
                    exp.getStackTrace());

            } catch (SQLException sql) {

                LOGGER_ACTION_CLIENT_PROSPECT.log(Level.SEVERE, sql.getSQLState() + "\n" + sql.getMessage());

            } catch (Exception exc) {

                LOGGER_ACTION_CLIENT_PROSPECT.log(Level.SEVERE, exc.getMessage() + "\n" +
                    exc.getCause() + "\n" +
                    exc.fillInStackTrace());

            }
        }

    }
}
