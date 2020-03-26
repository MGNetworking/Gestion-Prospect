package com.exception;

import com.exception.ExceptionPersonnaliser.ExceptionEnumClient;

public class ExceptionClient extends ExceptionPersonnaliser {

    ExceptionEnumClient indicationCl = ExceptionEnumClient.IS_NULL_CLIENT;

    /**
     * Ce constructeur permet de créer une exception de type ExceptionPersonnaliser
     * et d'ajouté des renseignements pour l'utilisateur.
     * Elle utilise une enumeration de type ExceptionEnumClient qui permet de
     * retracer l'exception en rapport avec elle.
     *
     * @param message          de type String
     * @param indicationClient de type ExceptionEnumClient, l'ajout des indications
     */
    public ExceptionClient(String message, ExceptionEnumClient indicationClient) {
        super(message);
        this.indicationCl = indicationClient;
    }

    /**
     * Récupère une indication pour l'exceptions clients
     *
     * @return de type enumération ExceptionEnumClient
     */
    public ExceptionEnumClient getIndicationClient() {
        return this.indicationCl;
    }
}
