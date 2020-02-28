package com.controleur;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DAO.Client_DAO;
import com.metier.Societe.TypeSociete;
import com.metier.Societe;
import com.DAO.ConnectionDAO;


/**
 * @author Maxime
 */
public class ControleurFrame {

    // liste des client et prospect.
    private List<Societe> ListeSociete;
    private Client_DAO client_dao;

    /**
     * Cette est l'interface entre les données est l'application
     */
    public ControleurFrame() {

        ListeSociete = new ArrayList<>();
        client_dao = new Client_DAO(ConnectionDAO.getConnectionPostgres()); // instance avec connection SGBD

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
     * @param choixSociete
     * @return un objet de type List
     */
    public List<Societe> getListeChoixSociete(String choixSociete) throws RuntimeException, SQLException {

        List list = null;

        // En fonction du choix renvoie la liste des Client ou prospect
        if (choixSociete.equals(TypeSociete.CLIENT.getTypeSociete())) {
           list = this.client_dao.findAll();


        } else if (choixSociete.equals(TypeSociete.PROSPECT.getTypeSociete())) {
            // todo a modifer list = Prospect.getListePropect();
        } else {
            throw new RuntimeException("Erreur sur le renvoi de la liste");
        }

        return list;
    }



    public void getCLientSGBD() {

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
