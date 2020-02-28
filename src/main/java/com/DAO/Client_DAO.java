/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.metier.Client;
import com.metier.Societe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DAO.ConnectionDAO;

/**
 * @author Maxime
 */
public class Client_DAO extends DAO<Client> {

    private String LISTE_CLIENT = "select s.id_societe , raisonsociale , \"domain\" , telephone ,email , a.numero" +
        ", a.rue , a.codepostale, a.ville, chiffreaffaire, nombreemployer\n" +
        "from societe s \n" +
        "inner join adresse a ON s.id_adresse = a.id_adresse \n" +
        "inner join client  c on c.id_societe = s.id_societe; \n";


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
    public List<String> findAll() throws SQLException {

        // prepare la requette avec la connection
        PreparedStatement pst = this.connection.prepareStatement(LISTE_CLIENT);

        // recupération de la requette
        ResultSet rst = pst.executeQuery();

        List<String> listClient = new ArrayList<>();
        Client cl = new Client();
        while (rst.next()) {

            rst.getString("id_societe");
            rst.getString("raisonsociale");
            rst.getString("domain");
            rst.getString("telephone");
            rst.getString("email");
            rst.getString("numero");
            rst.getString("rue");
            rst.getString("codepostale");
            rst.getString("ville");
            rst.getString("chiffreaffaire");
            rst.getString("nombreemployer");



        }

        return listClient;
    }

}
