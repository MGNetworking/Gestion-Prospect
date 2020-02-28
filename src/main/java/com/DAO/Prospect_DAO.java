/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.metier.Societe;
import com.metier.Prospect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Maxime
 */
public class Prospect_DAO extends DAO<Prospect> {

    Prospect_DAO(Connection connec) {
        super(connec);
    }

    @Override
    public boolean create(Prospect objet) {return false;}

    @Override
    public boolean delete(Prospect objet) {
        return false;
    }

    @Override
    public boolean update(Prospect objet) {
        return false;
    }

    @Override
    public Prospect find(Societe societe) {
        return new Prospect();
    }

    @Override
    public List<String> findAll()throws SQLException {
        return null;
    }

}
