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
     * Méthode qui permet le trie du liste contenant des objets de type Société.
     *
     * @param list de type list
     */
/*    public static void trieProspect(List<Prospect> list) {

        Collections.sort(list, Societe.comparatorRaisonSociale);
    }*/
    // TODO debut

    /**
     * Permet d'obtenir la liste des client ou prospect.
     *
     * @param choixSociete de type String
     * @return un objet de type List
     */
    public List<Societe> getListeSocieteControleur(String choixSociete) {

        List listSt = null;

        // En fonction du choix renvoie la liste des Client ou prospect
        if (choixSociete.equals(TypeSociete.CLIENT.getTypeSociete())) {

            try {
                listSt = this.client_dao.findAll();
                // todo cree un indic pour cleint et prospect qui permet de donnée un id a la création du prochain
            } catch (SQLException sql) {
                System.err.format("SQL Error [State: %s]\n Message : %s",
                        sql.getSQLState(), sql.getMessage());
            }

        } else if (choixSociete.equals(TypeSociete.PROSPECT.getTypeSociete())) {

            try {
                listSt = this.prospect_dao.findAll();
            } catch (SQLException sql) {
                System.err.format("SQL Error [State: %s]\n Message : %s",
                        sql.getSQLState(), sql.getMessage());
            } catch (Exception e) {
                System.out.println("Erreur exec prospect" + e.getMessage());
            }

        } else {
            throw new RuntimeException("Erreur sur le renvoi de la liste");
        }

        return listSt;
    }

    /**
     * recupération du client ou prospect
     *
     * @param st de type Societe.
     */
    public boolean addSocieteControleur(Societe st)throws SQLException, ExceptionDAO, NumberFormatException {
        // todo revoyer le boolean et créé une boite de dialogue pour la confirmation
        boolean controle = false;

        if (st instanceof Client) {
            controle =  client_dao.create((Client) st);

        } else if (st instanceof Prospect) {
            controle = prospect_dao.create((Prospect) st);
        }
        return controle;
    }

    /**
     * permet de supprimer un client ou prospect
     *
     * @param Societe
     */
    public void deleteSocieteControle(Societe st) {
        // todo revoyer le boolean et créé une boite de dialogue pour la confirmation
        if (st instanceof Client) {
            client_dao.delete((Client) st);

        } else if (st instanceof Prospect) {
            prospect_dao.create((Prospect) st);
        }
    }

    public void updateSocieteControleur(Societe st) {
        // todo revoyer le boolean et créé une boite de dialogue pour la confirmation

        if (st instanceof Client) {
            client_dao.update((Client) st);

        } else if (st instanceof Prospect) {
            prospect_dao.update((Prospect) st);

        }
    }

}
