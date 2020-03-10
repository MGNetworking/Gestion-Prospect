/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistance;

import com.metier.Societe;
import com.metier.Prospect;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.metier.Prospect.Interet;
import com.metier.Societe.DomainSociete;

/**
 * Cette classe permet de la gestion de la persistance des données concernant
 * un Prospect
 *
 * @author Maxime
 */
public class ProspectDAO extends DAO<Prospect> {

    private static Logger LOGGER_PS_DAO = Logger.getLogger(ProspectDAO.class.getName());

    /**
     * Ce constructeur doit prendre en paramètre en connection a la base de données.
     * La connection a la base de données est prévut de focntionné avec PostgreSQL.
     *
     * @param connec de type Connection
     */
    public ProspectDAO(Connection connec) {
        super(connec);
    }

    /**
     * Permet l'ajout à la base de données d'un prospect.
     *
     * @param prospect de type prospect
     * @return boolean de l'opération réalisé. true pour succés et false pour échec
     * @throws SQLException en cas d'erreur sur la transaction a la base de données
     */
    @Override
    public boolean create(Prospect prospect) throws SQLException {

        // Début transaction
        this.connection.setAutoCommit(false);

        boolean insert = false;
        try {


            //le PreparedStatement est un objet qui représente une instruction SQL précompilée, pour la table societe
            PreparedStatement pstSociete = this.connection.prepareStatement(this.getQuery("insert_societe"),
                    Statement.RETURN_GENERATED_KEYS);
            pstSociete.setObject(1, prospect.getRaisonSociale());
            pstSociete.setObject(2, prospect.getDomainSociete().toString(), Types.OTHER);
            pstSociete.setObject(3, prospect.getTelephone());
            pstSociete.setObject(4, prospect.getEmail());
            pstSociete.setObject(5, prospect.getCommentaire());

            if (pstSociete.executeUpdate() == 0) { // permet de vérifier l'envoi qui a était fait : 0 rien, 1 envoyé
                insert = false;
                System.out.println("etape 1: ");
                // todo ecrire un logger
            } else {
                insert = true;
            }

            // récupération de la clef généré pour les insertions des table en références
            ResultSet resultKeys = pstSociete.getGeneratedKeys();
            resultKeys.next();                              // passe l'index au suivant
            int idKey = resultKeys.getInt(1);   //  envoi de la clef
            System.out.println("Clef : " + idKey);

            // Un objet qui représente une instruction SQL précompilée, pour la table adresse
            PreparedStatement pstAdresse = this.connection.prepareStatement(this.getQuery("insert_adresse")
                    , Statement.RETURN_GENERATED_KEYS);

            pstAdresse.setObject(1, idKey); // ajoute l'id societe
            pstAdresse.setObject(2, Integer.parseInt(prospect.getAdresse().getCodePost()));
            pstAdresse.setObject(3, prospect.getAdresse().getNumeroDeRueSt());
            pstAdresse.setObject(4, prospect.getAdresse().getNomRue());
            pstAdresse.setObject(5, prospect.getAdresse().getVille());

            if (pstAdresse.executeUpdate() == 0) { // permet de vérifier l'envoi qui a était fait : 0 rien, 1 envoyé
                insert = false;

                // todo ecrire un logger
            } else {
                insert = true;
            }

            // Un objet qui représente une instruction SQL précompilée, pour la table Client
            PreparedStatement pstProspect = this.connection.prepareStatement(this.getQuery("insert_prospect"),
                    Statement.RETURN_GENERATED_KEYS);

            pstProspect.setObject(1, idKey); // ajoute l'id societe
            pstProspect.setString(2, prospect.getDatePropect());
            pstProspect.setObject(3, prospect.getInteresse(), Types.OTHER);

            if (pstProspect.executeUpdate() == 0) { // permet de vérifier l'envoi qui a était fait : 0 rien, 1 envoyé
                insert = false;

                // todo ecrire un logger
            } else {
                insert = true;
            }
        } catch (IOException ioe) {
            LOGGER_PS_DAO.severe("requete create prospect: " + ioe.getMessage() +
                    "\n" + ioe.getStackTrace());
        }
        this.connection.commit();            // validation de la transaction et fin transaction
        this.connection.setAutoCommit(true);

        return insert;
    }

    /**
     * Permet la suppression a la base de données d'un prospect.
     *
     * @param prospect de type prospect
     * @return boolean de l'opération réalisé. true pour succés et false pour échec
     * @throws SQLException en cas d'erreur sur la transaction a la base de données
     */
    @Override
    public boolean delete(Prospect prospect) throws SQLException {

        boolean operation = false;
        try {

            PreparedStatement pst = connection.prepareStatement(this.getQuery("delete_societe"));
            pst.setInt(1, prospect.getIdentifiant());
            int resultat = pst.executeUpdate();

            if (resultat == 0) { // vérifie la transaction
                operation = true;
                // todo ecrire un logger
            } else {
                operation = false;
            }


        } catch (IOException ioe) {
            LOGGER_PS_DAO.severe("requete delete prospect : " + ioe.getMessage() +
                    "\n" + ioe.getStackTrace());
        }
        return operation;
    }

    /**
     * Permet la mise a jour d'un prospect.Elle envoi à la base de données un Prospect
     * avec la mise a jour de ses shamps mofications.
     *
     * @param prospect de type Prospect
     * @return boolean true si l'opération est réaliser avec succés. Est false si c'est un échec
     * @throws SQLException en cas d'erreur sur la transaction a la base de données
     */
    @Override
    public boolean update(Prospect prospect) throws SQLException {

        boolean operation = false;
        this.connection.setAutoCommit(false);

        try {
            // update sur les champs du Client
            PreparedStatement pstSTProspect = connection.prepareStatement(this.getQuery("update_societe"));

            pstSTProspect.setString(1, prospect.getRaisonSociale());
            pstSTProspect.setString(2, prospect.getDomainSociete().getDomainst());
            pstSTProspect.setString(3, prospect.getTelephone());
            pstSTProspect.setString(4, prospect.getEmail());
            pstSTProspect.setString(5, prospect.getCommentaire());
            pstSTProspect.setInt(6, prospect.getIdentifiant());

            int resultatST = pstSTProspect.executeUpdate();

            if (resultatST == 0) {
                operation = false;
            } else {
                operation = true;
            }

            // update sur les champs de l'adresse
            PreparedStatement pstAdProspect = connection.prepareStatement(this.getQuery("update_adresse"));

            pstAdProspect.setInt(1, prospect.getAdresse().getNumeroDeRueSt());
            pstAdProspect.setString(2, prospect.getAdresse().getNomRue());
            pstAdProspect.setString(3, String.valueOf(prospect.getAdresse().getCodePost()));
            pstAdProspect.setString(4, prospect.getAdresse().getVille());
            pstAdProspect.setInt(5, prospect.getIdentifiant());

            int resultatAd = pstAdProspect.executeUpdate();

            if (resultatAd == 0) {
                operation = false;
            } else {
                operation = true;
            }

            // update sur les champs du prospect
            PreparedStatement psPs = connection.prepareStatement(this.getQuery("update_prospect"));

            psPs.setString(1, prospect.getDatePropect());
            psPs.setString(2, prospect.getInteresse().toString());
            psPs.setInt(3, prospect.getIdentifiant());

            int resultatPs = psPs.executeUpdate();

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

            LOGGER_PS_DAO.severe("requete update prospect : " + ioe.getMessage() +
                    "\n" + ioe.getStackTrace());
        } finally {

            this.connection.commit();
            this.connection.setAutoCommit(true);
        }


        return operation;
    }

    @Override
    public Prospect find(Societe societe) {
        return new Prospect();
    }

    /**
     * Permet la persistance des données en écriture.La transaction implementé dans cette methode
     * permet de garantire l'intégrité des données envoyées.
     * Si l'intégrité est compromie par une exception de type SQLException une erreurs d'excution de la transaction
     * est déclarée stoppé est rien n'est envoyée.
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Societe> findAll() throws SQLException {

        List<Societe> listProspect = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rst = null;

        try {


            pst = this.connection.prepareStatement(this.getQuery("select_prospect")); // preparation de la requete
            rst = pst.executeQuery();       // excécute est renvoi le resultat

            int i = 0;
            while (rst.next()) {            // créer un client à chaque itération

                listProspect.add(new Prospect(
                        Integer.parseInt(rst.getString("societe_id")),
                        rst.getString("raison_sociale"),
                        DomainSociete.valueOf(rst.getString("domainst")),
                        Integer.parseInt(rst.getString("numero")),
                        rst.getString("rue"),
                        rst.getString("cd_postale"),
                        rst.getString("ville"),
                        rst.getString("telephone"),
                        rst.getString("email"),
                        rst.getString("date_prospection"),
                        Interet.valueOf(rst.getString("interet")),
                        rst.getString("commentaire")

                ));
            }

            if (rst != null) {                  // fermeture du ResultSet
                rst.close();
            }

        } catch (IOException ioe) {

            LOGGER_PS_DAO.severe("requete select  prospect : " + ioe.getMessage() +
                    "\n" + ioe.getStackTrace());
        }
        this.trieSociete(listProspect);     // trie de la liste
        return listProspect;
    }

}
