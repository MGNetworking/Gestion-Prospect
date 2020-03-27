/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistance;

import com.metier.Client;
import com.metier.Societe;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.metier.Societe.DomainSociete;

import javax.swing.*;


/**
 * Cette classe permet de la gestion de la persistance des données concernant
 * un client
 *
 * @author Maxime
 */
public class ClientDAO extends DAO<Client> {

    private static Logger LOGGER_CL_DAO = Logger.getLogger(DAO.class.getName());

    /**
     * Ce controleur doit fonctionné avec une connection de Base de données.
     *
     * @param connection de type connection.
     */
    public ClientDAO(Connection connection) {
        super(connection);
    }


    /**
     * Permet la persistance des données en écriture.La transaction implementé dans cette methode
     * permet de garantire l'intégrité des données envoyées.
     * Si l'intégrité est compromie par une exception de type SQLException une erreurs d'excution de la transaction
     * est déclarée stoppé est rien n'est envoyée.
     *
     * @param client de type Client
     * @return booelan l'etat d'excution de la transaction.
     * @throws SQLException en cas d'erreur sur la transaction a la base de données
     */
    @Override
    public boolean create(Client client) throws SQLException {

        // Début transaction
        this.connection.setAutoCommit(false);

        try {

            boolean insert = false;

            //le PreparedStatement est un objet qui représente une instruction SQL précompilée, pour la table societe
            PreparedStatement pstSociete = this.connection.prepareStatement(this.getQuery("insert_societe"), Statement.RETURN_GENERATED_KEYS);
            pstSociete.setObject(1, client.getRaisonSociale());
            pstSociete.setObject(2, client.getDomainSociete().toString(), Types.OTHER);
            pstSociete.setObject(3, client.getTelephone());
            pstSociete.setObject(4, client.getEmail());
            pstSociete.setObject(5, client.getCommentaire());

            if (pstSociete.executeUpdate() == 0) { // permet de vérifier l'envoi qui a était fait : 0 rien, 1 envoyé
                insert = false;
                LOGGER_CL_DAO.info("Échec d'exécution de la 1er requette précompilé ");
            } else {
                insert = true;
                LOGGER_CL_DAO.info("exécution de la 1er requette précompilé ");
            }

            // récupération de la clef généré pour les insertions des table en références
            ResultSet resultKeys = pstSociete.getGeneratedKeys();
            resultKeys.next();                              // passe l'index au suivant
            int idKey = resultKeys.getInt(1);   //  envoi de la clef

            // Un objet qui représente une instruction SQL précompilée, pour la table adresse
            PreparedStatement pstAdresse = this.connection.prepareStatement(this.getQuery("insert_adresse"),
                    Statement.RETURN_GENERATED_KEYS);
            pstAdresse.setObject(1, idKey); // ajoute l'id societe
            pstAdresse.setObject(2, Integer.parseInt(client.getAdresse().getCodePost()));
            pstAdresse.setObject(3, client.getAdresse().getNumeroDeRueSt());
            pstAdresse.setObject(4, client.getAdresse().getNomRue());
            pstAdresse.setObject(5, client.getAdresse().getVille());

            if (pstAdresse.executeUpdate() == 0) { // permet de vérifier l'envoi qui a était fait : 0 rien, 1 envoyé
                insert = false;

            } else {
                insert = true;
            }

            // Un objet qui représente une instruction SQL précompilée, pour la table Client
            PreparedStatement pstClient = this.connection.prepareStatement(this.getQuery("insert_client"),
                    Statement.RETURN_GENERATED_KEYS);

            pstClient.setObject(1, idKey); // ajoute l'id societe
            pstClient.setObject(2, client.getChiffreAffaire());
            pstClient.setObject(3, client.getNombreEmployer());

            if (pstClient.executeUpdate() == 0) { // permet de vérifier l'envoi qui a était fait : 0 rien, 1 envoyé
                insert = false;
                LOGGER_CL_DAO.info("Échec d'exécution de la 2eme requette précompilé ");
            } else {
                insert = true;
                LOGGER_CL_DAO.info("exécution de la 2eme  requette précompilé ");
            }
        } catch (IOException ioe) {

            LOGGER_CL_DAO.log(Level.SEVERE, ioe.getMessage() + "\n" + ioe.getStackTrace(), ioe.fillInStackTrace());
        }

        this.connection.commit();            // validation de la transaction et fin transaction
        this.connection.setAutoCommit(true);

        return true;
    }


    /**
     * Permet la suppression d'un client
     *
     * @param client de type Client
     * @return boolean true si l'opération.
     * @throws SQLException en cas d'erreur sur la transaction a la base de données
     */
    @Override
    public boolean delete(Client client) throws SQLException {

        boolean operation = false;
        try {

            PreparedStatement pst = connection.prepareStatement(this.getQuery("delete_societe"));

            pst.setInt(1, client.getIdentifiant());
            int resultat = pst.executeUpdate();

            if (resultat == 0) { // vérifie la transaction
                operation = true;

            } else {
                operation = false;
            }
        } catch (IOException ioe) {

            LOGGER_CL_DAO.log(Level.SEVERE, ioe.getMessage() + "\n" + ioe.getStackTrace(), ioe.getCause());
        }
        return operation;
    }

    /**
     * Mes a jour l'objet client passer en paramétre.
     *
     * @param client de type client
     * @return true si l'operation est un succès
     * @throws SQLException en cas d'erreur sur la transaction a la base de données
     */
    @Override
    public boolean update(Client client) throws SQLException {

        boolean operation = false;
        this.connection.setAutoCommit(false);   // transaction SQL

        try {

            PreparedStatement pstStClient = connection.prepareStatement(this.getQuery("update_societe"));

            pstStClient.setString(1, client.getRaisonSociale());
            pstStClient.setString(2, client.getDomainSociete().getDomainst());
            pstStClient.setString(3, client.getTelephone());
            pstStClient.setString(4, client.getEmail());
            pstStClient.setString(5, client.getCommentaire());
            pstStClient.setInt(6, client.getIdentifiant());

            int resultatST = pstStClient.executeUpdate();

            if (resultatST == 0) {
                operation = false;
            } else {
                operation = true;
            }

            PreparedStatement pstAdClient = connection.prepareStatement(this.getQuery("update_adresse"));
            pstAdClient.setInt(1, client.getAdresse().getNumeroDeRueSt());
            pstAdClient.setString(2, client.getAdresse().getNomRue());
            pstAdClient.setString(3, String.valueOf(client.getAdresse().getCodePost()));
            pstAdClient.setString(4, client.getAdresse().getVille());
            pstAdClient.setInt(5, client.getIdentifiant());

            int resultatAd = pstAdClient.executeUpdate();

            if (resultatAd == 0) {
                operation = false;
            } else {
                operation = true;
            }

            PreparedStatement psPsClient = connection.prepareStatement(this.getQuery("update_client"));
            psPsClient.setObject(1, client.getChiffreAffaire());
            psPsClient.setObject(2, client.getNombreEmployer());
            psPsClient.setInt(3, client.getIdentifiant());

            int resultatPs = psPsClient.executeUpdate();

            if (resultatPs == 0) {
                operation = false;
            } else {
                operation = true;
            }


        } catch (SQLException sql) {

            throw new SQLException("UPDATE SQL :" + sql.getSQLState() + "\n" +
                    "Message : " + sql.getMessage() + "\n" +
                    "Code ERREUR : " + sql.getErrorCode());

        } catch (IOException ioe) {
            LOGGER_CL_DAO.log(Level.SEVERE, ioe.getMessage() + "\n" + ioe.getStackTrace(), ioe.fillInStackTrace());
        } finally {

            this.connection.commit();
            this.connection.setAutoCommit(true);
        }

        return operation;
    }

    @Override
    public Client find(Societe societe) {
        return new Client();
    }

    /**
     * Permet la persistance des données en écriture.La transaction implementé dans cette methode
     * permet de garantire l'intégrité des données envoyées.
     * Si l'intégrité est compromie par une exception de type SQLException une erreurs d'excution de la transaction
     * est déclarée stoppé est rien n'est envoyée.
     *
     * @return un objet de type List
     * @throws SQLException en cas d'erreur sur la transaction a la base de données
     */
    @Override
    public List<Societe> findAll() throws SQLException {

        boolean operation = false;
        List<Societe> listClient = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rst = null;


        try {

            // preparation de la requete
            pst = this.connection.prepareStatement(this.getQuery("select_client"));

            // excécute est renvoi le resultat
            rst = pst.executeQuery();

            operation = true;
        } catch (IOException ioe) {

            LOGGER_CL_DAO.log(Level.SEVERE, ioe.getMessage() + "\n" + ioe.getStackTrace(), ioe.getCause());
            operation = false;
        }

        if (operation != false) {
            while (rst.next()) {            // créer un client à chaque itération

                listClient.add(new Client(
                        Integer.parseInt(rst.getString("societe_id")),
                        rst.getString("raison_sociale"),
                        DomainSociete.valueOf(rst.getString("domainst")),
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

            if (rst != null) {                   // fermeture du ResultSet
                rst.close();
            }
        }

        this.trieSociete(listClient);       // trie de la liste
        return listClient;
    }

}
