package com.principale;

import com.metier.Client;
import com.metier.Prospect;
import com.metier.Societe;
import com.model.ControleurFrame;
import com.model.MenuFrame;

import java.awt.*;

import static com.DAO.ConnectionSingletonDAO.getConnection;

public class Main {

    /**
     * Point d'entré du programme.
     *
     * @param agrs String
     */
    public static void main(String[] agrs) {
        // variable de teste
        Client client1 = new Client("carefour", Societe.DomainSociete.PRIVE, 10,
            "rue d en haut", "01800", "molliens-Dreuil",
            "06 98 44 20 69", "soldat.inconnue@gmail.com",
            "ils étaient quatre avant, maintenant ils sont trois et ont les connais", 100000, 10);

        Client client2 = new Client("darty", Societe.DomainSociete.PUBLIC, 50,
            "rue des boulangers", "68130", "Altkirch",
            "06.98.44.20.69", "Fabien.Zindy@gmail.com",
            "Je suis un geek, est alors ;) ", 500000, 100);

        Prospect prospect1 = new Prospect("afpa", Societe.DomainSociete.PRIVE, 20,
            "Rue de chez toi", "80000", "Amiens", "06.07.08.09.10",
            "Ghalem.maxime@gmail.com", "01/09/1982", Prospect.Interet.NON, "Un commentaire de teste");

        Prospect prospect2 = new Prospect("clemessy", Societe.DomainSociete.PUBLIC, 100,
            "Rue d en haut", "68000", "colmar", "05.17.88.59.11",
            "Sylvie.Touchot@gmail.com", "10/06/1985", Prospect.Interet.OUI, "Tu fait des fautes d'orthographe méme quand tu parle, alors fait gaffe ;) ");

        Client.addLisClient(client1);
        Client.addLisClient(client2);

        Prospect.addListeProspect(prospect1);
        Prospect.addListeProspect(prospect2);

        getConnection(); // connection SGBD

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ControleurFrame controleurFrame = new ControleurFrame();
                MenuFrame menuF = new MenuFrame(controleurFrame);

            }
        });
    }
}
