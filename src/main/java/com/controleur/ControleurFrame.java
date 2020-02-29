package com.controleur;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.metier.Prospect;
import com.model.MenuFrame;
import com.persistance.Client_DAO;
import com.exception.ExceptionPersonnaliser;
import com.metier.Client;
import com.metier.Societe.TypeSociete;
import com.metier.Societe;
import com.persistance.ConnectionDAO;
import com.persistance.Prospect_DAO;

import javax.swing.*;


/**
 * @author Maxime
 */
public class ControleurFrame {

    private Client_DAO client_dao;
    private Prospect_DAO Prospect_dao;

    /**
     * Cette est l'interface entre les données est l'application
     */
    public ControleurFrame() {

        // Acces au base de donnéées
        this.client_dao = new Client_DAO(ConnectionDAO.getConnectionPostgres());        // instance avec connection SGBD
        this.Prospect_dao = new Prospect_DAO(ConnectionDAO.getConnectionPostgres());

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
    public List<Societe> getListeSocieteControleur(String choixSociete)  {

        List list = null;

        // En fonction du choix renvoie la liste des Client ou prospect
        if (choixSociete.equals(TypeSociete.CLIENT.getTypeSociete())) {


            try{
                list = this.client_dao.findAll();

            }catch (SQLException sql){
                System.out.println("Erreur dans getListeSocieteControleur() " + sql.getMessage());
            }



        } else if (choixSociete.equals(TypeSociete.PROSPECT.getTypeSociete())) {
            // TODO a implementer list = this.Prospect_dao.findAll();
        } else {
            throw new RuntimeException("Erreur sur le renvoi de la liste");
        }

        return list;
    }

    /**
     * recupération du client ou prospect
     *
     * @param st de type Societe.
     */
    public void addSocieteControleur(Societe st) {

        if (st instanceof Client) {

        } else if (st instanceof Prospect) {

        }

    }

    /**
     * permet de supprimer un client ou prospect
     *
     * @param Societe
     */
    public void deleteSocieteControle(Societe st) {
        if (st instanceof Client) {

        } else if (st instanceof Prospect) {

        }
    }


    public void getCLientSGBD() {
        String LISTE_CLIENT = "select s.id_societe , raisonsociale , \"domain\" , telephone ,email , a.numero" +
                ", a.rue , a.codepostale, a.ville, chiffreaffaire, nombreemployer " +
                "from gestion.societe s " +
                "inner join gestion.adresse a ON s.id_adresse = a.id_adresse " +
                "inner join gestion.client  c on c.id_societe = s.id_societe;";

        String req = "select * from gestion.societe;";

        try {
            PreparedStatement prepStat = ConnectionDAO.getConnectionPostgres().prepareStatement(req);

            ResultSet resultSet = prepStat.executeQuery();

            while (resultSet.next()) {// passe le 1er curseur avec le next


                System.out.println("Values : " + resultSet.getString("id_societe"));
                System.out.println("Values : " + resultSet.getString("id_adresse"));
                System.out.println("Values : " + resultSet.getString("raisonsociale"));
                System.out.println("Values : " + resultSet.getString("domain"));
                System.out.println("Values : " + resultSet.getString("telephone"));
                System.out.println("Values : " + resultSet.getString("email"));

            }

            //Client.addLisClient();


        } catch (SQLException sqle) {
            System.err.format("SQL Error [State: %s]\n Message : %s",
                    sqle.getSQLState(), sqle.getMessage());
        }
    }
}
