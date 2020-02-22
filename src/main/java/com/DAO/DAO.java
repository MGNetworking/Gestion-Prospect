package com.DAO;

import com.metier.Societe;
import java.sql.Connection;

/**
 * Cette classe permet la création de manière générique les méthodes d'accés
 * à la base de données.
 *
 * @param <type> objet générique
 */
public abstract class DAO<type> {

    // instance de la connection SGBD
    protected Connection connection;

    /**
     * Methode pour la création
     *
     * @param objet
     * @return boolean
     */
    abstract boolean create(type objet);

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
}
