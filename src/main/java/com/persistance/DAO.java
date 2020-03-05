package com.persistance;

import com.metier.Societe;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.metier.Societe;

/**
 * Cette classe permet la création de manière générique des méthodes d'accés
 * à la base de données.
 *
 * @param <type> objet générique
 */
public abstract class DAO<type> {


    protected Connection connection;    // instance de la connection SGBD

    DAO(Connection connec) {
        this.connection = connec;
    }

    /**
     * Methode pour la création
     *
     * @param objet
     * @return boolean
     */
    abstract boolean create(type objet) throws SQLException;

    /**
     * Méthode pour la suppréssion.
     *
     * @param objet
     * @return boolean
     */
    abstract boolean delete(type objet) throws SQLException;

    /**
     * Méthode pour la mise à jour
     *
     * @param objet
     * @return boolean
     */
    abstract boolean update(type objet) throws SQLException;

    /**
     * Méthode de recherche des informations.
     *
     * @return boolean
     */
    abstract type find(Societe societe);

    /**
     * Import tout les élements de la base de données.
     *
     * @return
     */
    abstract List<Societe> findAll() throws SQLException, Exception;


    /**
     * Méthode qui permet le trie d'une liste contenant des objets de type Société.
     *
     * @param list de type list
     */
    public static void trieSociete(List<Societe> listSociete) {

        // appelle static du comparator
        Collections.sort(listSociete, DAO.comparatorRaisonSociale);
    }

    /**
     * Objet qui permet la comparaison entre deux nom d'entreprise.
     */
    protected static Comparator<Societe> comparatorRaisonSociale = new Comparator<Societe>() {

        @Override
        public int compare(Societe societe1, Societe societe2) {
            return societe1.getRaisonSociale().compareTo(societe2.getRaisonSociale());
        }

    };

}
