package com.persistance;

import com.metier.Societe;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Cette classe permet la création de manière générique les méthodes d'accés
 * à la base de données.
 *
 * @param <type> objet générique
 */
public abstract class DAO<type> {


    protected Connection connection;    // instance de la connection SGBD

    DAO(Connection connec){
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
    abstract boolean delete(type objet);

    /**
     * Méthode pour la mise à jour
     *
     * @param objet
     * @return boolean
     */
    abstract boolean update(type objet);

    /**
     * Méthode de recherche des informations.
     *
     * @return boolean
     */
    abstract type find(Societe societe);

    /**
     * Import tout les élements de la base de données.
     * @return
     */
    abstract List<Societe> findAll() throws SQLException;

}
