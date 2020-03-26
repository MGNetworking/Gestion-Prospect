package com.exception;


import com.exception.ExceptionPersonnaliser.ExceptionEnumSociete;

public class ExceptionSociete extends ExceptionPersonnaliser {

    ExceptionEnumSociete indicationSt = ExceptionEnumSociete.IS_NULL_SOCIETE;

    /**
     * Ce constructeur permet de créer une exception de type ExceptionPersonnaliser
     * et d'ajouté des renseignements pour l'utilisateur.
     * Elle utilise une enumeration de type ExceptionEnumSociete qui permet de
     * retracer l'exception en rapport avec elle.
     *
     * @param message      de type String le message
     * @param indicationSt de type ExceptionEnumSociete, l'ajout des indications
     */
    public ExceptionSociete(String message, ExceptionEnumSociete indicationSt) {
        super(message);
        this.indicationSt = indicationSt;
    }

    /**
     * Récupère une indication pour l'exceptions sociétés
     *
     * @return de type enumération ExceptionEnumSociete
     */
    public ExceptionEnumSociete getIndicationSociete() {
        return this.indicationSt;
    }
}
