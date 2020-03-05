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
import java.util.List;
import java.sql.Types.*;
import com.metier.Prospect.Interet;
import com.metier.Societe.DomainSociete;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 *
 * @author Maxime
 */
public class Prospect_DAO extends DAO<Prospect> {

    private String LISTE_PROSPECT = "select s.societe_id , raison_sociale , \"domain\" , telephone ,email , a.numero, " +
            "a.rue , a.cd_postale, a.ville,  date_prospection , interet , s.commentaire" +
            "      from gestion.societe s \n" +
            "            inner join gestion.adresse a ON s.societe_id = a.societe_id " +
            "            inner join gestion.prospect  p on p.societe_id = s.societe_id;";

    private String LISTE_SOCIETE ="insert into gestion.societe (raison_sociale,\"domain\",telephone,email,commentaire) " +
            "values(?,?,?,?,?);";

    private String INSERT_ADRESSE = "insert into gestion.adresse (societe_id ,cd_postale , numero , rue , ville )" +
            "values(?,?,?,?,?);";

    private String INSERT_CLIENT = "insert into prospect (societe_id ,date_prospection ,interet ) " +
            "values (?,?,?);";

    public Prospect_DAO(Connection connec) {
        super(connec);
    }

    @Override
    public boolean create(Prospect objet) {return false;}

    @Override
    public boolean delete(Prospect objet)throws SQLException {
        return false;
    }

    @Override
    public boolean update(Prospect objet) {
        return false;
    }

    @Override
    public Prospect find(Societe societe) {
        return new Prospect();
    }

    @Override
    public List<Societe> findAll()throws SQLException, ExceptionPersonnaliser {


        List<Societe> listProspect = new ArrayList<>();
        try {

            // prepare une reqette de type select a la base de données
            PreparedStatement pst = this.connection.prepareStatement(LISTE_PROSPECT);
            ResultSet rst = pst.executeQuery(); // récupére le resultat de la requette

    int i = 0;
            while (rst.next()) {

                // créer un client à chaque itération
                listProspect.add(new Prospect(
                        Integer.parseInt(rst.getString("societe_id")),
                        rst.getString("raison_sociale"),
                        DomainSociete.valueOf(rst.getString("domain")),
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

        } catch (SQLException sql) {

            System.err.format("SQL Error [State: %s]\n Message : %s",
                    sql.getSQLState(), sql.getMessage());
            throw new SQLException("FindAll " + sql.getMessage());

        }catch (Exception e) {
            throw new SQLException("FindAll " + e.getMessage());

        }
        return listProspect;
    }

}
