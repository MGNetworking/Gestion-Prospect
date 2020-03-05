package com.controleur;

import javax.swing.*;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.exception.ExceptionPersonnaliser;
import com.exception.ExceptionDAO;
import com.metier.Societe.TypeSociete;

import com.metier.Prospect;
import com.metier.Societe;
import com.metier.Client;

import com.persistance.Client_DAO;
import com.persistance.ConnectionDAO;
import com.persistance.Prospect_DAO;

import com.model.MenuFrame;


/**
 * @author Maxime
 */
public class ControleurFrame {

    private Client_DAO client_dao;
    private Prospect_DAO prospect_dao;

    /**
     * Cette est l'interface entre les données est l'application
     */
    public ControleurFrame() {

        // Acces au base de données
        this.client_dao = new Client_DAO(ConnectionDAO.getConnectionPostgres());        // instance avec connection SGBD
        this.prospect_dao = new Prospect_DAO(ConnectionDAO.getConnectionPostgres());

    }

    /**
     * Permet d'obtenir la liste des client ou prospect.
     *
     * @param choixSociete de type String
     * @return un objet de type List
     */
    public List<Societe> getListeSocieteControleur(String choixSociete) throws SQLException, ExceptionPersonnaliser {

        List listSt = null;

        // En fonction du choix renvoie la liste des Client ou prospect
        if (choixSociete.equals(TypeSociete.CLIENT.getTypeSociete())) {

            listSt = this.client_dao.findAll();

        } else if (choixSociete.equals(TypeSociete.PROSPECT.getTypeSociete())) {

            listSt = this.prospect_dao.findAll();

        } else {
            throw new ExceptionPersonnaliser("Erreur sur le renvoi de la liste");
        }

        return listSt;
    }

    /**
     * recupération du client ou prospect
     *
     * @param st de type Societe.
     */
    public boolean addSocieteControleur(Societe st) throws SQLException, ExceptionPersonnaliser, NumberFormatException {

        boolean operation = false;

        if (st instanceof Client) {
            operation = client_dao.create((Client) st);

        } else if (st instanceof Prospect) {
            operation = prospect_dao.create((Prospect) st);
        }
        return operation;
    }

    /**
     * permet de supprimer un client ou prospect
     *
     * @param Societe
     */
    public boolean deleteSocieteControle(Societe st) throws SQLException {

        boolean operation = false;

        if (st instanceof Client) {
            operation = client_dao.delete((Client) st);

        } else if (st instanceof Prospect) {
            operation = prospect_dao.delete((Prospect) st);
        }

        return operation;
    }

    public boolean updateSocieteControleur(Societe st) throws SQLException {

        boolean operation = false;

        if (st instanceof Client) {
            operation = client_dao.update((Client) st);

        } else if (st instanceof Prospect) {
            operation = prospect_dao.update((Prospect) st);

        }

        return operation;
    }

}
