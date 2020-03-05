/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistance;

import com.exception.ExceptionPersonnaliser;
import com.metier.Client;
import com.metier.Societe;
import com.metier.Prospect;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.sql.Types.*;

import com.metier.Prospect.Interet;
import com.metier.Societe.DomainSociete;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * Cette classe permet de la gestion de la persistance des données concernant
 * un Prospect
 *
 * @author Maxime
 */
public class Prospect_DAO extends DAO<Prospect> {

    private String LISTE_PROSPECT = "select s.societe_id , raison_sociale , domainst , telephone ,email , a.numero, " +
            "a.rue , a.cd_postale, a.ville,  date_prospection , interet , s.commentaire" +
            "      from gestion.societe s \n" +
            "            inner join gestion.adresse a ON s.societe_id = a.societe_id " +
            "            inner join gestion.prospect  p on p.societe_id = s.societe_id;";

    // inseret Prospect
    private String INSERT_SOCIETE_PROSPECT = "insert into gestion.societe " +
            " (raison_sociale,domainst,telephone,email,commentaire) " +
            " values(?,?,?,?,?);";

    private String INSERT_ADRESSE_PROSPECT = "insert into gestion.adresse " +
            " (societe_id ,cd_postale , numero , rue , ville ) " +
            " values(?,?,?,?,?);";

    private String INSERT_PROSPECT = "insert into gestion.prospect " +
            " (societe_id ,date_prospection ,interet ) " +
            " values (?, to_date(?, 'DD/MM/YYYY') ,?);";

    // Delete Client
    private static final String DELETE_PROSPECT = "delete from gestion.societe where societe_id = ?;";

    // Update Cleint
    private static final String UPDATE_SOCIETE_PROSPECT = "update gestion.societe " +
            "set raison_sociale = ? ," +
            "domainst = ? ," +
            "telephone = ? ," +
            "email = ? ," +
            "commentaire = ? " +
            "where societe_id = ? ;";

    private static final String UPDATE_ADRESSE_PROSPECT = "update gestion.adresse " +
            "set numero = ? ," +
            "rue = ? ," +
            "cd_postale = ? , " +
            "ville = ? " +
            "where societe_id = ? ;";

    private static final String UPDATE_PROSPECT = "update gestion.prospect " +
            "set date_prospection = to_date(?, 'YYYY-MM-DD') ," +
            "interet = ?" +
            "where societe_id = ?;";

    /**
     * Ce constructeur doit prendre en paramètre en connection a la base de données.
     * La connection a la base de données est prévut de focntionné avec PostgreSQL.
     *
     * @param connec de type Connection
     */
    public Prospect_DAO(Connection connec) {
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

        //le PreparedStatement est un objet qui représente une instruction SQL précompilée, pour la table societe
        PreparedStatement pstSociete = this.connection.prepareStatement(INSERT_SOCIETE_PROSPECT, Statement.RETURN_GENERATED_KEYS);
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
        PreparedStatement pstAdresse = this.connection.prepareStatement(INSERT_ADRESSE_PROSPECT, Statement.RETURN_GENERATED_KEYS);
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
        PreparedStatement pstProspect = this.connection.prepareStatement(INSERT_PROSPECT, Statement.RETURN_GENERATED_KEYS);
        pstProspect.setObject(1, idKey); // ajoute l'id societe
        pstProspect.setString(2, prospect.getDatePropect());
        pstProspect.setObject(3, prospect.getInteresse(), Types.OTHER);

        if (pstProspect.executeUpdate() == 0) { // permet de vérifier l'envoi qui a était fait : 0 rien, 1 envoyé
            insert = false;

            // todo ecrire un logger
        } else {
            insert = true;
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

        PreparedStatement pst = connection.prepareStatement(DELETE_PROSPECT);
        pst.setInt(1, prospect.getIdentifiant());
        int resultat = pst.executeUpdate();

        if (resultat == 0) { // vérifie la transaction
            operation = true;
            // todo ecrire un logger
        } else {
            operation = false;
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
            PreparedStatement pstSTProspect = connection.prepareStatement(UPDATE_SOCIETE_PROSPECT);

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
            PreparedStatement pstAdProspect = connection.prepareStatement(UPDATE_ADRESSE_PROSPECT);

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
            PreparedStatement psPs = connection.prepareStatement(UPDATE_PROSPECT);

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


        pst = this.connection.prepareStatement(LISTE_PROSPECT); // preparation de la requete
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

        this.trieSociete(listProspect);     // trie de la liste
        return listProspect;
    }

}
