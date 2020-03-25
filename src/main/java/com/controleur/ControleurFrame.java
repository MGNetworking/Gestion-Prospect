package com.controleur;

import java.util.List;
import java.sql.SQLException;
import com.metier.Societe.TypeSociete;

import com.metier.Prospect;
import com.metier.Societe;
import com.metier.Client;

import com.persistance.ClientDAO;
import com.persistance.ConnectionDAO;
import com.persistance.ProspectDAO;


/**
 * @author Maxime
 */
public class ControleurFrame {

    private ClientDAO client_dao;
    private ProspectDAO prospect_dao;

    /**
     * Cette est l'interface entre les données est l'application
     */
    public ControleurFrame() {

        // Acces à la base de données
        this.client_dao = new ClientDAO(ConnectionDAO.getConnectionPostgres());
        this.prospect_dao = new ProspectDAO(ConnectionDAO.getConnectionPostgres());

    }

    /**
     * Permet d'obtenir la liste des client ou prospect.
     *
     * @param choixSociete de type String
     * @return un objet de type List
     */
    public List<Societe> getListeSocieteControleur(String choixSociete) throws SQLException{

        List listSt = null;

        // En fonction du choix renvoie la liste des Client ou prospect
        if (choixSociete.equals(TypeSociete.CLIENT.getTypeSociete())) {

            listSt = this.client_dao.findAll();

        } else if (choixSociete.equals(TypeSociete.PROSPECT.getTypeSociete())) {

            listSt = this.prospect_dao.findAll();

        } else {
            throw new SQLException("Erreur sur le renvoi de la liste");
        }

        return listSt;
    }

    /**
     * Recupération du client ou prospect
     *
     * @param st de type Societe.
     */
    public boolean addSocieteControleur(Societe st) throws SQLException {

        boolean operation = false;

        if (st instanceof Client) {
            operation = client_dao.create((Client) st);

        } else if (st instanceof Prospect) {
            operation = prospect_dao.create((Prospect) st);
        }
        return operation;
    }

    /**
     * Permet de supprimer un client ou prospect
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

    /**
     *
     * @param st
     * @return
     * @throws SQLException
     */
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
