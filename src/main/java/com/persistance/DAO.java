package com.persistance;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

import com.metier.Societe;
import com.metier.Societe;

/**
 * Cette classe permet la création de manière générique des méthodes d'accés
 * à la base de données.
 *
 * @param <type> objet générique
 */
public abstract class DAO<type> {

    private static Logger LOGGER_DAO = Logger.getLogger(DAO.class.getName());


    protected Connection connection;                            // Instance de la connection SGBD
    protected Map<String, String> queryMap = new HashMap<>();   // liste des requetes

    /**
     * Ce constructeur prende en paramétre la connection a la base de données.
     *
     * @param connec objet de type connection.
     */
    DAO(Connection connec) {
        this.connection = connec;
    }

    /**
     * Méthode pour la création d'un objet en base de données.
     *
     * @param objet l'objet attendu en paramétre.
     * @return boolean le resultat de l'opération.
     * @throws SQLException en cas d'erreur sur la transaction a la base de données
     */
    abstract boolean create(type objet) throws SQLException;

    /**
     * Méthode pour la suppréssion d'un objet en base de données.
     *
     * @param objet l'objet attendu en paramétre.
     * @return boolean le resultat de l'opération.
     * @throws SQLException en cas d'erreur sur la transaction a la base de données
     */
    abstract boolean delete(type objet) throws SQLException;

    /**
     * Méthode pour la mise à jour d'un objet en base de données.
     *
     * @param objet l'objet attendu en paramétre.
     * @return boolean le resultat de l'opération.
     * @throws SQLException en cas d'erreur sur la transaction a la base de données
     */
    abstract boolean update(type objet) throws SQLException;


    /**
     * Méthode de recherche des informations.
     *
     * @param societe l'objet attendu en paramétre.
     * @return un objet de type générique le resultat de l'opération.
     */
    abstract type find(Societe societe);

    /**
     * Importe tout les élements de la base de données.
     *
     * @return un objet de type List
     * @throws SQLException en cas d'erreur sur la transaction a la base de données
     */
    abstract List<Societe> findAll() throws SQLException;


    /**
     * Méthode qui permet le trie d'une liste contenant des objets de type Société.
     *
     * @param listSociete e type list
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

    /**
     * Renvoie la requete correspondante au noms passé en paramétre.
     *
     * @param nameQuery de type String.
     * @return la requette rechercher grace au nom.
     * @throws IOException si le nom de la requette n'existe pas
     *                     ou si un probléme d'ouverture de flux est lever détecter.
     */
    protected String getQuery(String nameQuery) throws IOException {

        if (!queryMap.keySet().contains(nameQuery)) {

            StringBuilder builder = new StringBuilder();
            String path = "postgresql/" + nameQuery + ".sql";

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            this.getClass()
                                    .getClassLoader()
                                    .getResourceAsStream(path)));) {

                String line = null;
                while ((line = reader.readLine()) != null) {

                    builder.append(line);
                }
                queryMap.put(nameQuery, builder.toString());
                LOGGER_DAO.info("construction de la requette fini ");
            }
        }
        return queryMap.get(nameQuery);

    }

}
