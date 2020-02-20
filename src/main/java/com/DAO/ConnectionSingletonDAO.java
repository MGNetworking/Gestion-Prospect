/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Cette permet la connexion à la base de données Mysql.
 *
 */
public class ConnectionSingletonDAO {

    private static Connection connection = null;

    // TODO l'url est à compléter
    private String url = "jdbc:mysql://localhost:3306/reverso?useUnicode=true&serverTimezone=UTC";

    private String user = "maxReverso";

    private String passWord = "ultra";

    /**
     * Constructeur implicite et unique.
     */
    private ConnectionSingletonDAO() {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                        this.url,
                        this.user,
                        this.passWord);

            System.out.println("Connection effectuées");

        } catch (ClassNotFoundException ex) {
            
            System.out.println("la classe n'a pas était trouvé");
            
        } catch (SQLException sqlEx) {
            System.out.println("échec de la connection" + "\n"
                        + sqlEx.getLocalizedMessage());

        }

    }

    /**
     * Cette méthode permet de créé l'instance de connection à la base de
     * données. Si une instance existe déjà, aucune autre ne sera créé.
     *
     * @return objet de type Connection.
     */
    public static Connection getConnection() {

        if (connection == null) {
            new ConnectionSingletonDAO();
        }

        return connection;
    }
}
