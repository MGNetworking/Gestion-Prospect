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

    private ClientDAO client_dao = new ClientDAO(ConnectionDAO.getConnectionPostgres());
    private ProspectDAO prospect_dao = new ProspectDAO(ConnectionDAO.getConnectionPostgres());

    /**
     * Cette est l'interface entre les données est l'application
     */
    public ControleurFrame() {
    }

    /**
     * Permet d'obtenir la liste des client ou prospect.
     *
     * @param choixSociete de type String
     * @return un objet de type List
     * @throws SQLException en cas d'erreur sur la transaction a la base de données
     */
    public List<Societe> getListeSocieteControleur(String choixSociete) throws SQLException {

        // En fonction du choix renvoyer par la slection de dans la liste des Clients ou prospects
        if (choixSociete.equals(TypeSociete.CLIENT.getTypeSociete())) {

            return this.client_dao.findAll();

        } else if (choixSociete.equals(TypeSociete.PROSPECT.getTypeSociete())) {

            return this.prospect_dao.findAll();

        } else {
            throw new SQLException("Erreur sur le renvoi de la liste");
        }

    }

    /**
     * Recupération du client ou prospect
     *
     * @param st de type Societe.
     * @return true si succés de la transaction.
     * @throws SQLException renvoie l'exception générer par la transaction.
     */
    public boolean addSocieteControleur(Societe st) throws SQLException {

        if (st instanceof Client) {
            return client_dao.create((Client) st);

        } else if (st instanceof Prospect) {
            return prospect_dao.create((Prospect) st);
        }
        return false;
    }

    /**
     * Permet de supprimer un client ou prospect
     *
     * @param societe de type Societe.
     * @return true si la requette était exécuter avec succé.
     * @throws SQLException renvoie l'exception générer par la transaction.
     */
    public boolean deleteSocieteControle(Societe societe) throws SQLException {

        if (societe instanceof Client) {
            return client_dao.delete((Client) societe);

        } else if (societe instanceof Prospect) {
            return prospect_dao.delete((Prospect) societe);
        }

        return false;
    }

    /**
     * Permet de mettre a jours un client / prospect
     *
     * @param st de type Societe
     * @return boolean true si successFully de la mise a jour
     * @throws SQLException si problème de type SQl générer pendant la mise à jours.
     */
    public boolean updateSocieteControleur(Societe st) throws SQLException {

        if (st instanceof Client) {
            return client_dao.update((Client) st);

        } else if (st instanceof Prospect) {
            return prospect_dao.update((Prospect) st);

        }
        return false;
    }

}
