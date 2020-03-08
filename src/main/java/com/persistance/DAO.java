package com.persistance;

import com.metier.Societe;

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

/**
 * Cette classe permet la création de manière générique des méthodes d'accés
 * à la base de données.
 *
 * @param <type> objet générique
 */
public abstract class DAO<type> {

    private static Logger LOGGER_DAO = Logger.getLogger((DAO.class.getName()));


    protected Connection connection;                        // Instance de la connection SGBD
    protected Map<String, String> queryMap = new HashMap<>();  // liste des requetes

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

    /**
     * Renvoie la requete correspondante au noms passé en paramétre.
     *
     * @param nameQuery
     * @return
     * @throws IOException
     */
    protected String getQuery(String nameQuery) throws IOException {

        LOGGER_DAO.info("Message debut : " + nameQuery + " fichier : ");
        if (!queryMap.keySet().contains(nameQuery)) {
            StringBuilder builder = new StringBuilder();

            String path = "postgresql/" + nameQuery + ".sql";
            LOGGER_DAO.info("Path : " + path);

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            this.getClass()
                                    .getClassLoader()
                                    .getResourceAsStream(path)));) {

                String line = null;
                while ((line = reader.readLine()) != null) {
                    LOGGER_DAO.info("Requete : " + line);
                    builder.append(line);
                }


                LOGGER_DAO.info("Name " + queryMap.get(nameQuery) + " query : " + builder.toString());
                queryMap.put(nameQuery, builder.toString());

            } catch (NullPointerException nulP) {
                LOGGER_DAO.severe("Erreur null pointeur  : " + nulP);
            } catch (IOException ioe) {
                LOGGER_DAO.severe("Erreur  ioe: " + ioe);
                throw new IOException();
            }
        }

        return queryMap.get(nameQuery);

    }

}
