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

/**
 * @author Maxime
 */
public class Client_DAO extends DAO<Client> {

    private String LISTE_CLIENT = "select s.id_societe , raisonsociale , \"domain\" , telephone ,email , a.numero" +
            ", a.rue , a.codepostale, a.ville, chiffreaffaire, nombreemployer " +
            "from gestion.societe s " +
            "inner join gestion.adresse a ON s.id_adresse = a.id_adresse " +
            "inner join gestion.client  c on c.id_societe = s.id_societe;";


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


        // prepare la connection avec des parametres SQL
        PreparedStatement pst = this.connection.prepareStatement(LISTE_CLIENT);

        ResultSet rst = pst.executeQuery(); // recupération de la requette

    int i = 0;
        try {
            while (rst.next()) {
                listClient.add(new Client(
                        Integer.parseInt(   rst.getString("id_societe")),
                                            rst.getString("raisonsociale"),
                        Integer.parseInt(   rst.getString("numero"),
                                            rst.getString("rue"),
                                            rst.getString("codepostale"),
                                            rst.getString("ville"),
                                            rst.getString("telephone"),
                                            rst.getString("email"),
                        Societe.DomainSociete.valueOf(rst.getString("domain")),
                        );

                cl.setTelephone();
                cl.setEmail();
                cl.setAdresseSt(),
                        ,
                        ,
                        );

                cl.calculRatioClientEmployer(Integer.parseInt(rst.getString("chiffreaffaire")),
                        Integer.parseInt(rst.getString("nombreemployer")));
            }



        } catch (SQLException sql) {
            throw  new SQLException("FindAll " + sql.getMessage());

        }

        return listClient;
    }

}
