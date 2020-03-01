/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistance;

import com.metier.Client;
import com.metier.Societe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.metier.Societe.DomainSociete;

/**
 * @author Maxime
 */
public class Client_DAO extends DAO<Client> {

    private String LISTE_CLIENT = "select s.societe_id , raison_sociale , \"domain\" , telephone ,email , a.numero, a.rue , a.cd_postale, a.ville, chiffre_affaire, employer_nb , s.commentaire\n" +
            "      from gestion.societe s " +
            "            inner join gestion.adresse a ON s.societe_id = a.societe_id" +
            "            inner join gestion.client  c on c.societe_id = s.societe_id;";


    /**
     * Class de mapping objet Client
     *
     * @param connection de type connection.
     */
    public Client_DAO(Connection connection) {
        super(connection);
    }


    @Override
    public boolean create(Client objet) {
        return false;
    }

    @Override
    public boolean delete(Client objet) {
        return false;
    }

    @Override
    public boolean update(Client objet) {
        return false;
    }

    @Override
    public Client find(Societe societe) {
        return new Client();
    }

    @Override
    public List<Societe> findAll() throws SQLException, NumberFormatException {

        List<Societe> listClient = new ArrayList<>();
        try {

        // prepare une reqette de type select a la base de données
        PreparedStatement pst = this.connection.prepareStatement(LISTE_CLIENT);
        ResultSet rst = pst.executeQuery(); // récupére le resultat de la requette

            while (rst.next()) {

                // créer un client à chaque itération
                listClient.add(new Client(
                        Integer.parseInt(rst.getString("societe_id")),
                        rst.getString("raison_sociale"),
                        DomainSociete.valueOf(rst.getString("domain")),
                        Integer.parseInt(rst.getString("numero")),
                        rst.getString("rue"),
                        rst.getString("cd_postale"),
                        rst.getString("ville"),
                        rst.getString("telephone"),
                        rst.getString("email"),
                        rst.getString("commentaire"),
                        Integer.parseInt(rst.getString("chiffre_affaire")),
                        Integer.parseInt(rst.getString("employer_nb"))
                ));
            }

        } catch (SQLException sql) {

            System.err.format("SQL Error [State: %s]\n Message : %s",
                    sql.getSQLState(), sql.getMessage());
            throw new SQLException("FindAll " + sql.getMessage());

        }catch (Exception e) {
            throw new SQLException("FindAll " + e.getMessage());

        }

        return listClient;
    }

}
