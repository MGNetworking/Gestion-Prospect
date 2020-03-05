package com.principale;

import com.exception.ExceptionPersonnaliser;
import com.metier.Client;
import com.metier.Prospect;
import com.metier.Societe;
import com.controleur.ControleurFrame;
import com.model.MenuFrame;
import com.persistance.Client_DAO;
import com.persistance.ConnectionDAO;

import java.awt.*;
import java.util.List;

public class Main {

    /**
     * Point d'entré du programme.
     *
     * @param agrs String
     */
    public static void main(String[] agrs) {


        Client_DAO clientDAO = new Client_DAO(ConnectionDAO.getConnectionPostgres());
/*

        try {
            List<Societe> listClient = clientDAO.findAll();

            listClient.forEach(x -> System.out.println(x));

            listClient.get(0).setCommentaire("un ancien commentaire 3");
            listClient.get(0).setRaisonSociale("truc 3");
            listClient.get(0).setEmail("email.foo@gmail.com");
            listClient.get(0).setDomainSociete(Societe.DomainSociete.PUBLIC);
            listClient.get(0).setTelephone("06 99 08 09 99");


            listClient.get(0).getAdresse().setCodePost("99000");
            listClient.get(0).getAdresse().setNomRue("Rue machin");
            listClient.get(0).getAdresse().setNumeroDeRueSt(1982);
            listClient.get(0).getAdresse().setVille("Super truc");



            clientDAO.update((Client) listClient.get(0));

        } catch (ExceptionPersonnaliser ex) {
            System.out.println("Erreur : " + ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

*/


       EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ControleurFrame controleurFrame = new ControleurFrame();
                MenuFrame menuF = new MenuFrame(controleurFrame);

            }
        });
    }
}
