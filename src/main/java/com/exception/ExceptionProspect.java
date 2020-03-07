package com.exception;

import com.metier.Prospect;
import com.exception.ExceptionPersonnaliser.ExceptionEnumAdresse;

public class ExceptionProspect extends ExceptionPersonnaliser {

    ExceptionEnumProspect indicationPs = ExceptionEnumProspect.IS_NULL_PROSPECT;

    /**
     * Ce constructeur permet de créer une exception de type RuntimeException
     * pour la classe prospect .Il permet l'ajouté de renseignement pour l'utilisateur.
     * Elle utilise une énumération de type ExceptionEnumProspect qui permet de
     * tracer la provenance de l'exception en rapport avec elle.
     *
     * @param message            de type String
     * @param indicationProspect de type ExceptionEnumProspect, l'ajout des indications
     */
    public ExceptionProspect(String message, ExceptionEnumProspect indicationProspect) {
        super(message);
        this.indicationPs = indicationProspect ;
    }

    /**
     * recupére une indication pour les exceptions Prospects
     *
     * @return de type enumération ExceptionEnumProspect
     */
    public ExceptionEnumProspect getIndicationProspect() {
        return this.indicationPs;
    }
}
