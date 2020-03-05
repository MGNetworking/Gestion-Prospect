/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistance;

import com.exception.ExceptionDAO;
import com.metier.Client;
import com.metier.Societe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.metier.Societe.DomainSociete;

/**
 * Cette classe permet de la gestion de la persistance des données concernant
 * un client
 *
 * @author Maxime
 */
public class Client_DAO extends DAO<Client> {

    // recupére la liste des client
    private static final String LISTE_CLIENT = "select s.societe_id , raison_sociale , domainst , telephone ,email , a.numero, " +
            "a.rue , a.cd_postale, a.ville, chiffre_affaire, employer_nb , s.commentaire\n" +
            "      from gestion.societe s " +
            "            inner join gestion.adresse a ON s.societe_id = a.societe_id" +
            "            inner join gestion.client  c on c.societe_id = s.societe_id;";

    // insert Client
    private static final String INSERT_SOCIETE = "insert into gestion.societe " +
            "(raison_sociale,domainst,telephone,email,commentaire) " +
            "values(?,?,?,?,?);";

    private static final String INSERT_ADRESSE_CLIENT = "insert into gestion.adresse " +
            "(societe_id ,cd_postale , numero , rue , ville )" +
            "values(?,?,?,?,?);";

    private static final String INSERT_CLIENT = "insert into gestion.client " +
            "(societe_id ,chiffre_affaire ,employer_nb )" +
            "values(?,?,?);";

    // Delete Client
    private static final String DELETE_CLIENT = "delete from gestion.societe where societe_id = ?;";

    // Update Cleint
    private static final String UPDATE_SOCIETE_CLIENT = "update gestion.societe " +
            "set raison_sociale = ? ," +
            "domainst = ? ," +
            "telephone = ? ," +
            "email = ? ," +
            "commentaire = ? " +
            "where societe_id = ? ;";

    private static final String UPDATE_ADRESSE_CLIENT = "update gestion.adresse " +
            "set numero = ? ," +
            "rue = ? ," +
            "cd_postale = ? , " +
            "ville = ? " +
            "where societe_id = ? ;";

    private static final String UPDATE_CLIENT = "update gestion.client " +
            "set chiffre_affaire = ?," +
            "employer_nb = ?" +
            "where societe_id = ?;";


    /**
     * Ce controleur doit fonctionné avec une connection de Base de données.
     *
     * @param connection de type connection.
     */
    public Client_DAO(Connection connection) {
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
     * @throws SQLException en cas de problème de liè au données entre la base et le programme
     * @throws ExceptionDAO vérifie la clef génére par l'action du statement.Si la clef n'est pas généré les
     *                      données ne pour pas etre insert correcement.Donc, cette méthode permet de garentir l'intégrité des données
     *                      envoyées
     */
    @Override
    public boolean create(Client client) throws SQLException {

        // Début transaction
        this.connection.setAutoCommit(false);

        boolean insert = false;

        //le PreparedStatement est un objet qui représente une instruction SQL précompilée, pour la table societe
        PreparedStatement pstSociete = this.connection.prepareStatement(INSERT_SOCIETE, Statement.RETURN_GENERATED_KEYS);
        pstSociete.setObject(1, client.getRaisonSociale());
        pstSociete.setObject(2, client.getDomainSociete().toString(), Types.OTHER);
        pstSociete.setObject(3, client.getTelephone());
        pstSociete.setObject(4, client.getEmail());
        pstSociete.setObject(5, client.getCommentaire());

        if (pstSociete.executeUpdate() == 0) { // permet de vérifier l'envoi qui a était fait : 0 rien, 1 envoyé
            insert = false;
            // todo ecrire un logger
        } else {
            insert = true;
        }

        // récupération de la clef généré pour les insertions des table en références
        ResultSet resultKeys = pstSociete.getGeneratedKeys();
        resultKeys.next();                              // passe l'index au suivant
        int idKey = resultKeys.getInt(1);   //  envoi de la clef

        // Un objet qui représente une instruction SQL précompilée, pour la table adresse
        PreparedStatement pstAdresse = this.connection.prepareStatement(INSERT_ADRESSE_CLIENT, Statement.RETURN_GENERATED_KEYS);
        pstAdresse.setObject(1, idKey); // ajoute l'id societe
        pstAdresse.setObject(2, Integer.parseInt(client.getAdresse().getCodePost()));
        pstAdresse.setObject(3, client.getAdresse().getNumeroDeRueSt());
        pstAdresse.setObject(4, client.getAdresse().getNomRue());
        pstAdresse.setObject(5, client.getAdresse().getVille());

        if (pstAdresse.executeUpdate() == 0) { // permet de vérifier l'envoi qui a était fait : 0 rien, 1 envoyé
            insert = false;
            // todo ecrire un logger
        } else {
            insert = true;
        }

        // Un objet qui représente une instruction SQL précompilée, pour la table Client
        PreparedStatement pstClient = this.connection.prepareStatement(INSERT_CLIENT, Statement.RETURN_GENERATED_KEYS);
        pstClient.setObject(1, idKey); // ajoute l'id societe
        pstClient.setObject(2, client.getChiffreAffaire());
        pstClient.setObject(3, client.getNombreEmployer());

        if (pstClient.executeUpdate() == 0) { // permet de vérifier l'envoi qui a était fait : 0 rien, 1 envoyé
            insert = false;
            // todo ecrire un logger
        } else {
            insert = true;
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
     * @throws SQLException
     * @throws ExceptionDAO
     */
    @Override
    public boolean delete(Client client) throws SQLException {

        boolean operation = false;

        PreparedStatement pst = connection.prepareStatement(DELETE_CLIENT);
        pst.setInt(1, client.getIdentifiant());
        int resultat = pst.executeUpdate();

        if (resultat == 0) { // vérifie la transaction
            operation = true;
            // todo ecrire un logger
        } else {
            operation = false;
        }

        return operation;
    }

    @Override
    public boolean update(Client client) throws SQLException {

        boolean operation = false;
        this.connection.setAutoCommit(false);

        try {

            System.out.println(UPDATE_SOCIETE_CLIENT);
            PreparedStatement pstStClient = connection.prepareStatement(UPDATE_SOCIETE_CLIENT);

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

            System.out.println(UPDATE_ADRESSE_CLIENT);
            PreparedStatement pstAdClient = connection.prepareStatement(UPDATE_ADRESSE_CLIENT);
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

            PreparedStatement psPsClient = connection.prepareStatement(UPDATE_CLIENT);
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
     * @return
     * @throws SQLException
     */
    @Override
    public List<Societe> findAll() throws SQLException {

        List<Societe> listClient = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rst = null;

        pst = this.connection.prepareStatement(LISTE_CLIENT); // preparation de la requete
        rst = pst.executeQuery();       // excécute est renvoi le resultat

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

        this.trieSociete(listClient);       // trie de la liste
        return listClient;
    }

}
