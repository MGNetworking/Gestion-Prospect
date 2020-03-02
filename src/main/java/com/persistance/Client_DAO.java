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
 * @author Maxime
 */
public class Client_DAO extends DAO<Client> {

    // cette requet recupére la liste des client
    private String LISTE_CLIENT = "select s.societe_id , raison_sociale , \"domain\" , telephone ,email , a.numero, " +
            "a.rue , a.cd_postale, a.ville, chiffre_affaire, employer_nb , s.commentaire\n" +
            "      from gestion.societe s " +
            "            inner join gestion.adresse a ON s.societe_id = a.societe_id" +
            "            inner join gestion.client  c on c.societe_id = s.societe_id;";

    // ces requete permet de l'insertion du client
    private String INSERT_SOCIETE = "insert into gestion.societe (raison_sociale,\"domain\",telephone,email,commentaire) " +
            "values(?,?,?,?,?);";
    private String INSERT_ADRESSE = "insert into gestion.adresse (societe_id ,cd_postale , numero , rue , ville )" +
            "values(?,?,?,?,?);";
    private String INSERT_CLIENT = "insert into gestion.client (societe_id ,chiffre_affaire ,employer_nb )" +
            "values(?,?,?);";

    /**
     * Class de mapping objet Client
     *
     * @param connection de type connection.
     */
    public Client_DAO(Connection connection) {
        super(connection);
    }


    /**
     * Permet la persistance des données en écriture.La transaction implementé dans cette methode
     * permet de garantire l'intégrité des données envoyées.
     * Si l'intégrité est compromie par une exception ou une erreurs d'excution la transaction
     * est stoppé est rien n'est envoyée.
     *
     * @param client de type Client
     * @return booelan l'etat d'excution de la transaction.
     * @throws SQLException en cas de problème de liè au données entre la base et le programme
     * @throws ExceptionDAO vérifie la clef génére par l'action du statement.Si la clef n'est pas généré les
     * données ne pour pas etre insert correcement.Donc, cette méthode permet de garentir l'intégrité des données
     * envoyées
     * @throws NumberFormatException en cas de format non respecté au niveau d'un wrapping
     */
    @Override
    public boolean create(Client client) throws SQLException, ExceptionDAO, NumberFormatException {

        // Début transaction
        this.connection.setAutoCommit(false);
        boolean insert = true;

        //le PreparedStatement est un objet qui représente une instruction SQL précompilée, pour la table societe
        PreparedStatement pstScoiete = this.connection.prepareStatement(INSERT_SOCIETE, Statement.RETURN_GENERATED_KEYS);
        pstScoiete.setObject(1, client.getRaisonSociale());
        pstScoiete.setObject(2, client.getDomainSociete().toString() , Types.OTHER );
        pstScoiete.setObject(3, client.getTelephone());
        pstScoiete.setObject(4, client.getEmail());
        pstScoiete.setObject(5, client.getCommentaire());

        int keyGenSociete = pstScoiete.executeUpdate();     // renvoi la clef générer de la table societe
        if (keyGenSociete == 0){
            throw new ExceptionDAO("ERREUR : probléme d'insertion dans le champs de la table Societe");
        }

        // Un objet qui représente une instruction SQL précompilée, pour la table adresse
        PreparedStatement pstAdresse = this.connection.prepareStatement(INSERT_ADRESSE, Statement.RETURN_GENERATED_KEYS);
        pstAdresse.setObject(1,keyGenSociete ); // ajoute l'id societe
        pstAdresse.setObject(2, Integer.parseInt(client.getAdresse().getCodePost()));
        pstAdresse.setObject(3, client.getAdresse().getNumeroDeRueSt());
        pstAdresse.setObject(4, client.getAdresse().getNomRue());
        pstAdresse.setObject(5, client.getAdresse().getVille());

        int keyGenAdresse = pstAdresse.executeUpdate();     // renvoi la clef générer de la table Adresse
        if (keyGenAdresse == 0){
            throw new ExceptionDAO("ERREUR : problème d'insertion dans les champs de la table Adresse");
        }

        // Un objet qui représente une instruction SQL précompilée, pour la table Client
        PreparedStatement pstClient = this.connection.prepareStatement(INSERT_CLIENT, Statement.RETURN_GENERATED_KEYS);
        pstClient.setObject(1, keyGenSociete); // ajoute l'id societe
        pstClient.setObject(2, client.getChiffreAffaire());
        pstClient.setObject(3, client.getNombreEmployer());

        int keyGenClient = pstClient.executeUpdate();       // renvoi la clef générer de la table Client
        if (keyGenClient == 0) { // si echec de la requet
            throw new ExceptionDAO("ERREUR : problème d'insertion dans les champs de la table client");
        }

        this.connection.commit();
        this.connection.setAutoCommit(true);
        // Fin transaction
        return true;
    }


    @Override
    public boolean delete(Client client) {
        return false;
    }

    @Override
    public boolean update(Client client) {
        return false;
    }

    @Override
    public Client find(Societe societe) {
        return new Client();
    }

    @Override
    public List<Societe> findAll() throws SQLException, ExceptionDAO,NumberFormatException {

        List<Societe> listClient = new ArrayList<>();

        PreparedStatement pst = null;
        ResultSet rst = null;
        try {

            // prepare une reqette de type select a la base de données
            pst = this.connection.prepareStatement(LISTE_CLIENT);
            rst = pst.executeQuery(); // récupére le resultat de la requette

            while (rst.next()) {// créer un client à chaque itération

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

            throw new SQLException();

        } catch (ExceptionDAO eDAO) {
            throw new ExceptionDAO("FindAll " + eDAO.getMessage());

        }finally {
            rst.close(); // fermeture du ResultSet

        }

        return listClient;
    }

}
