package com.metier;

import java.util.ArrayList;
import java.util.List;

import com.exception.ExceptionPersonnaliser;
import java.util.Collections;

/**
 * Cette classe permet la création d'un client en phase de prospection.
 */
public class Prospect extends Societe {

    public enum Interet {

        OUI,
        NON;

    }

    private String datePropect;
    private Interet interesse;

    /**
     * Constructeur par défaut sans paramétre.
     */
    public Prospect() {
        super();
    }

    /**
     * Constructeur permet de créé un Prospect en phase de prospecion.
     *
     * @param raisonSt      String raison sociale du prospecter.
     * @param domainSt      DomainSociete domain de la société du prospecter.
     * @param numeroDeRueSt int numéro de l'adresse du prospecter.
     * @param nomRueSt      String nom de la rue du prospecter.
     * @param codePostSt    String code postale du prospecter.
     * @param villeSt       String ville du prospecter.
     * @param telephoneSt   String numero de telephone du prospecter.
     * @param emailSt       String adresse email du prospecter.
     * @param datePropectSt la date du prospecter.
     * @param interesseSt   Interet intéret du prospecter.
     * @param commentaireSt String commentaire du prospect
     */
    public Prospect(String raisonSt, DomainSociete domainSt,
                    int numeroDeRueSt, String nomRueSt, String codePostSt,
                    String villeSt, String telephoneSt, String emailSt, String datePropectSt, Interet interesseSt, String commentaireSt) {

        // constructeur de la classe Societe.
        super(raisonSt, domainSt, numeroDeRueSt, nomRueSt, codePostSt, villeSt, telephoneSt, emailSt, commentaireSt);

        this.setDatePropect(datePropectSt);
        this.setInteresse(interesseSt);
    }

    /**
     * La date de prospection.
     *
     * @return String de la date de propection du client.
     */
    public String getDatePropect() {
        return datePropect;
    }

    /**
     * Ajout de la date de prospection.
     *
     * @param datePropectSt String de la date propection du client.
     * @throws ExceptionPersonnaliser si la date est vide ou si le format est
     *                                n'est pas respecté.
     */
    public void setDatePropect(String datePropectSt) throws ExceptionPersonnaliser, NullPointerException {

        // vérification null
        if (datePropectSt == null)
            throw new NullPointerException("variable date null");

        // vérification vide
        if (datePropectSt.isEmpty())
            throw new ExceptionPersonnaliser("Date de prospection : la date est vide ");

        // vérification format
        if (!(datePropectSt.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}")))
            throw new ExceptionPersonnaliser("Date de prospection : la date entré n'est pas comforme ");


        this.datePropect = datePropectSt;
    }

    /**
     * L'interet du prospect.
     *
     * @return String l'intérêt du client propect.
     */
    public Enum getInteresse() {
        return interesse;
    }

    /**
     * Modication ou ajout de l'intérêt du prospect.
     *
     * @param interesse String intérêt du prospect.
     * @throws ExceptionPersonnaliser si le champs ne correspond pas à OUI ou
     *                                NON.
     * @throws NullPointerException si la variable est null.
     */
    public void setInteresse(Interet interesse) throws ExceptionPersonnaliser {

        // variable null
        if (interesse == null)
            throw new NullPointerException("Interet prospect null");

        // patern regex
        if (!(interesse.equals(Interet.OUI) | interesse.equals(Interet.NON)))
            throw new ExceptionPersonnaliser("Interet : le champs intéret n'a pas été renseigné");


        this.interesse = interesse;

    }

    @Override
    public String toString() {
        return this.getRaisonSociale();
    }

}
