package com.exception;

import com.exception.ExceptionPersonnaliser.ExceptionEnumAdresse;

public class ExceptionAdresse extends ExceptionPersonnaliser {

    ExceptionEnumAdresse indicationAdresse = ExceptionEnumAdresse.IS_NULL_ADRESSE;

    /**
     * Ce constructeur permet de créer une exception de type ExceptionPersonnaliser
     * et d'ajouté des renseignements pour l'utilisateur.
     * Elle utilise une enumeration de type ExceptionEnumAdresse qui permet de
     * retracer l'exception en rapport avec elle.
     *
     * @param message      de type String
     * @param indicationTp de type ExceptionEnumAdresse, l'ajout des indications
     */
    public ExceptionAdresse(String message, ExceptionEnumAdresse indicationAdresse) {
        super(message);
        this.indicationAdresse = indicationAdresse;

    }

    /**
     * Récupère une indication pour l'exceptions Adresse
     *
     * @return de type enumération ExceptionEnumAdresse
     */
    public ExceptionEnumAdresse getIndicationAdresse() {
        return this.indicationAdresse;
    }
}
