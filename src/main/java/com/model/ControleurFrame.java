package com.model;


import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import static com.DAO.ConnectionSingletonDAO.getConnection;

import com.exception.ExceptionPersonnaliser;
import com.metier.Societe.DomainSociete;
import com.metier.Societe.TypeSociete;
import com.metier.Societe;
import com.metier.Client;
import com.metier.Prospect;
import com.metier.Prospect.Interet;


/**
 * @author Maxime
 */
public class ControleurFrame {
    /**
     * Cette est l'interface entre les données est l'application
     */
    public ControleurFrame() {

    }
    // TODO debut

    /**
     * Permet d'obtenir la liste des client ou prospect.
     *
     * @param choixSociete
     * @return un objet de type List
     */
    public List<Societe> getListeChoixSociete(String choixSociete) throws RuntimeException {

        List list = null;

        // En fonction du choix renvoie la liste des Client ou prospect
        if (choixSociete.equals(TypeSociete.CLIENT.getTypeSociete())) {
            list = Client.getListeClient();

        } else if (choixSociete.equals(TypeSociete.PROSPECT.getTypeSociete())) {
            list = Prospect.getListePropect();
        } else {
            throw new RuntimeException("Erreur sur le renvoi de la liste");
        }

        return list;
    }

    public void addClientControle(Client client){
        Client.addLisClient(client); // Ajoute à la liste le nouveaux Client
    }

    public void addProspectControl(Prospect prospect){
        Prospect.addListeProspect(prospect); // Ajoute à la liste le nouveaux Client
    }

    public void deleteClientControle(Client client){
        Client.removeListeClient(client);
    }

    public void deleteProspectControle(Prospect prospect){
        Prospect.removeListeProspect(prospect);
    }


    // TODO fin

}
