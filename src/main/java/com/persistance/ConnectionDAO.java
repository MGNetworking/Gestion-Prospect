package com.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {

    private static final String SGBD_NAME = "postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
    private static Connection connection = null;

/*
    // exemple
    static {
        connection = connectionSimple.createConnectionSimple();
        System.out.println("Connection established");
    }*/

    /**
     * Constructeur implicite pour design pattern Singleton.
     */
    private ConnectionDAO() {

    }


    /**
     * Délégue la création de la connection la SGBD.
     *
     * @return de type Connection
     */
    private static Connection createConnectionSimple() {
        Connection connection = null;
        try {

            // creation d'une connection unique SGBD
            connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/" + SGBD_NAME, USER, PASSWORD);
            System.out.println("Connection Reussi");

        } catch (SQLException sqle) {
            System.err.format("SQL Error [State: %s]\n Message : %s",
                sqle.getSQLState(), sqle.getMessage());
        }

        return connection;
    }


    /**
     * Permet d'appeller la connection.
     *
     * @return de type connection.
     */
    public static Connection getConnectionPostgres() {

        if (connection == null) {
            connection = ConnectionDAO.createConnectionSimple();

        }

        return connection;
    }

}
