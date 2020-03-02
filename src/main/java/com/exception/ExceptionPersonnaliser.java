/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exception;

import com.metier.Adresse.ExceptionType;
import com.metier.Societe.ExceptionSociete;

/**
 * @author Maxime
 */
public class ExceptionPersonnaliser extends RuntimeException {

    ExceptionType indicationTp;
    ExceptionSociete indicationSt;

    /**
     * Ce constructeur permet de crées une exception de type RuntimeException
     *
     * @param message
     */
    public ExceptionPersonnaliser(String message) {
        super(message);
    }

    /**
     * Ce constructeur permet de crées une exception de type RuntimeException
     * et d'ajouté des renseignements pour l'utilisateur.
     * Elle utilise une enumeration de type ExceptionType qui permet de
     * retracer l'exception en rapport avec elle.
     *
     * @param message      de type String
     * @param indicationTp de type ExceptionType, l'ajout des indications
     */
    public ExceptionPersonnaliser(String message, ExceptionType indicationTp) {
        super(message);
        this.indicationTp = indicationTp;

    }

    /**
     * Ce constructeur permet de crées une exception de type RuntimeException
     * et d'ajouté des renseignements pour l'utilisateur.
     * Elle utilise une enumeration de type ExceptionSociete qui permet de
     * retracer l'exception en rapport avec elle.
     *
     * @param message
     * @param indicationSt
     */
    public ExceptionPersonnaliser(String message, ExceptionSociete indicationSt) {
        this.indicationSt = indicationSt;
    }


    /**
     *
     * @return
     */
    public ExceptionType getIndicationTp() {
        return this.indicationTp;
    }

    public ExceptionSociete getIndicationSt(){
        return this.indicationSt;
    }

}
