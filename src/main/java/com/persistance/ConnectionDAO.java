package com.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Classe permet la connection à la base de données.
 * Elle utilise de le design patern Singleton pour garantire
 * L'unicité de la connection.
 */
public class ConnectionDAO {

    // connection SGBD
    private static Connection connection = null;
    // Logger utilise pour sont implémentation un desing Patner Singleton
    private static final Logger LOGGER_DAO = Logger.getLogger(ConnectionDAO.class.getName());


    // Pour la fermeture de la connection
    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                if (null != connection) {
                    try {
                        connection.close();
                        LOGGER_DAO.info("Fermeture de la onnection");
                    } catch (SQLException sql) {
                        LOGGER_DAO.severe(sql.getMessage());

                    }
                }
            }
        });

    }

    /**
     * Délégue la création de la connection à la base de données.
     *
     * @return de type Connection
     */
    public static Connection getConnectionPostgres() {
        Properties proprieteConnection = new Properties();

        // si connection null
        if (connection == null) {

            // ouverture d'un flux en lecture vers le fichier de propriété propriétés
            try (InputStream inputStream = ConnectionDAO
                    .class
                    .getClassLoader()
                    .getResourceAsStream("connection.properties")) {

                // lecture
                proprieteConnection.load(inputStream);

                // creation d'une connection unique SGBD vers postgre
                // https://jdbc.postgresql.org/documentation/head/connect.html
                connection = DriverManager.getConnection(
                        (String) proprieteConnection.get("url"), proprieteConnection);

                System.out.println("Connection Reussi");

            } catch (IOException ioe) {
                LOGGER_DAO.severe("le fichier de configuration n'a put étre chargée : " + ioe.getMessage());
            } catch (SQLException sql) {
                LOGGER_DAO.severe("La connection a la base de données a échoué" + sql.getMessage() + sql.getSQLState());
            } catch (Exception ex) {
                LOGGER_DAO.severe("Erreur de type : " + ex.getMessage() + ex.getStackTrace());
            }
        }


        return connection;
    }


}
