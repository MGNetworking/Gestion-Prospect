/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exception;

import com.metier.Adresse;
import com.metier.Adresse.ExceptionAdresse;
import com.metier.Societe.ExceptionSociete;
import com.metier.Prospect.ExceptionProspect;
import com.metier.Client.ExceptionClient;

/**
 * @author Maxime
 */
public class ExceptionPersonnaliser extends RuntimeException {

    ExceptionAdresse indicationAdresse = ExceptionAdresse.IS_NULL_ADRESSE;
    ExceptionSociete indicationSt= ExceptionSociete.IS_NULL_SOCIETE;
    ExceptionProspect indicationPs = ExceptionProspect.IS_NULL_PROSPECT;
    ExceptionClient indicationCl = ExceptionClient.IS_NULL_CLIENT;

    /**
     * Ce constructeur permet de créer une exception de type RuntimeException
     *
     * @param message de type String
     */
    public ExceptionPersonnaliser(String message) {
        super(message);
    }

    /**
     * Ce constructeur permet de crées une exception de type RuntimeException
     * et d'ajouté des renseignements pour l'utilisateur.
     * Elle utilise une enumeration de type ExceptionAdresse qui permet de
     * retracer l'exception en rapport avec elle.
     *
     * @param message      de type String
     * @param indicationTp de type ExceptionAdresse, l'ajout des indications
     */
    public ExceptionPersonnaliser(String message, ExceptionAdresse indicationAdresse) {
        super(message);
        this.indicationAdresse = indicationAdresse;

    }

    /**
     * Ce constructeur permet de crées une exception de type RuntimeException
     * et d'ajouté des renseignements pour l'utilisateur.
     * Elle utilise une enumeration de type ExceptionSociete qui permet de
     * retracer l'exception en rapport avec elle.
     *
     * @param message      de type String
     * @param indicationSt de type ExceptionSociete, l'ajout des indications
     */
    public ExceptionPersonnaliser(String message, ExceptionSociete indicationSt) {
        super(message);
        this.indicationSt = indicationSt;
    }

    /**
     * Ce constructeur permet de crées une exception de type RuntimeException
     * et d'ajouté des renseignements pour l'utilisateur.
     * Elle utilise une enumeration de type ExceptionProspect qui permet de
     * retracer l'exception en rapport avec elle.
     *
     * @param message            de type String
     * @param indicationProspect de type ExceptionProspect, l'ajout des indications
     */
    public ExceptionPersonnaliser(String message, ExceptionProspect indicationProspect) {
        super(message);
        this.indicationPs = indicationProspect;

    }

    /**
     * Ce constructeur permet de crées une exception de type RuntimeException
     * et d'ajouté des renseignements pour l'utilisateur.
     * Elle utilise une enumeration de type ExceptionClient qui permet de
     * retracer l'exception en rapport avec elle.
     *
     * @param message          de type String
     * @param indicationClient de type ExceptionClient, l'ajout des indications
     */
    public ExceptionPersonnaliser(String message, ExceptionClient indicationClient) {
        super(message);
        this.indicationCl = indicationClient;
    }

    /**
     * recupére une indication pour les exceptions Adresse
     *
     * @return de type enumération ExceptionAdresse
     */
    public ExceptionAdresse getIndicationAdresse() {
        return this.indicationAdresse;
    }

    /**
     * recupére une indication pour les exceptions sociétés
     *
     * @return de type enumération ExceptionSociete
     */
    public ExceptionSociete getIndicationSt() {
        return this.indicationSt;
    }

    /**
     * recupére une indication pour les exceptions Prospects
     *
     * @return de type enumération ExceptionProspect
     */
    public ExceptionProspect getIndicationPs() {
        return this.indicationPs;
    }

    /**
     * recupére une indication pour les exceptions clients
     *
     * @return de type enumération ExceptionClient
     */
    public ExceptionClient getIndicationCl() {
        return this.indicationCl;
    }
}
