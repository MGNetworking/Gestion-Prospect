/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.metier.Societe;
import com.metier.Prospect;

/**
 *
 * @author Maxime
 */
public class DAO_Prospect extends DAO<Prospect> {

    @Override
    boolean create(Prospect objet) {

        return false;
    }

    @Override
    boolean delete(Prospect objet) {
        return false;
    }

    @Override
    boolean update(Prospect objet) {
        return false;
    }

    @Override
    Prospect find(Societe societe) {
        return new Prospect();
    }

}
