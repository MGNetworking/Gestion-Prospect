/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.metier.Client;
import com.metier.Societe;

/**
 *
 * @author Maxime
 */
public class DAO_Client extends DAO<Client> {

    @Override
    boolean create(Client objet) {
       return false;
    }

    @Override
    boolean delete(Client objet) {
         return false;
    }

    @Override
    boolean update(Client objet) {
       return false;
    }

    @Override
    Client find(Societe societe) {
       return new Client();
    }

}
