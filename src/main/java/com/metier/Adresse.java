package com.metier;

import com.exception.ExceptionAdresse;
import com.exception.ExceptionPersonnaliser;
import com.exception.ExceptionPersonnaliser.ExceptionEnumAdresse;

import static com.exception.ExceptionPersonnaliser.ExceptionEnumAdresse.*;

/**
 * Cette permet la gestion de l'adresse du client ou prospect.
 *
 * @author Maxime
 */
public class Adresse {

    private int numeroDeRueSt;
    private String nomRue;
    private String codePost;
    private String ville;

    /**
     * Constructeur implicite.
     */
    public Adresse() {

    }

    /**
     * Constructeur princiaple, permet d'ajouter une adresse a une société.
     *
     * @param numeroRueSt int le numéro de rue.
     * @param nomRue      String le nom de la rue.
     * @param codePost    String le code postale.
     * @param ville       String le nom de la ville.
     * @throws ExceptionPersonnaliser si les champs sont vide.
     */
    public Adresse(int numeroRueSt, String nomRue, String codePost, String ville) throws ExceptionPersonnaliser {

        this.setNumeroDeRueSt(numeroRueSt);
        this.setNomRue(nomRue);
        this.setCodePost(codePost);
        this.setVille(ville);
    }

    /**
     * @return int le numéro.
     */
    public int getNumeroDeRueSt() {
        return numeroDeRueSt;
    }

    /**
     * Ajoute un numéro de rue de l'entreprise.
     *
     * @param numeroDeRueSt int le numéro.
     * @throws ExceptionPersonnaliser si vide ou égale à 0.
     */
    public void setNumeroDeRueSt(int numeroDeRueSt) throws ExceptionPersonnaliser {

        if (numeroDeRueSt <= 0)
            throw new ExceptionAdresse("Numéro rue <= 0",
                    NUMERO_RUE_INF_0);

        if (numeroDeRueSt > 200){
            throw new ExceptionAdresse("Numéro rue <= 0",
                NUMERO_RUE_INF_BIG200);
        }

        this.numeroDeRueSt = numeroDeRueSt;
    }

    /**
     * il renvoie le nom de la rue
     *
     * @return de type String.
     */
    public String getNomRue() {
        return nomRue;
    }

    /**
     * Ajoute le nom de la rue.
     *
     * @param nomRue String le nom de la rue
     * @throws ExceptionPersonnaliser si le nom de la rue est vide.
     * @throws NullPointerException   si l'objet de type String est Null.
     */
    public void setNomRue(String nomRue) throws ExceptionPersonnaliser, NullPointerException {

        if (nomRue == null) {
            throw new NullPointerException("nom de la rue = null ");
        }


        if (nomRue.isEmpty()) {
            throw new ExceptionAdresse("Non de la rue vide",
                    ExceptionEnumAdresse.EMPTY_NOM_RUE);
        }


        // Verifi le format du nom de la rue de 4 a 30 caractére
        if (!(nomRue.matches("^\\s*[a-z A-ZéèàêÉîï]{4,30}"))) {
            throw new ExceptionAdresse("Non de la rue : contient des chiffres",
                    ExceptionEnumAdresse.MATCH_NOM_RUE);
        }

        this.nomRue = nomRue;
    }

    /**
     * Renvoie le code postale
     *
     * @return de type String.
     */
    public String getCodePost() {
        return codePost;
    }

    /**
     * Ajoute un code postale.
     *
     * @param codePost de type String.
     * @throws ExceptionPersonnaliser si le code postale es vide.
     * @throws NullPointerException   si l'objet de type String est Null.
     */
    public void setCodePost(String codePost) throws ExceptionPersonnaliser, NullPointerException {

        if (codePost == null) {
            throw new NullPointerException("code postale Null");
        }

        if (codePost.isEmpty()) {
            throw new ExceptionAdresse("Code Postale : vide ",
                    ExceptionEnumAdresse.EMPTY_CD_POSTALE);
        }

        // teste le code postale francais
        if (!(codePost.matches("\\b[0-9]{5}\\b"))) {
            throw new ExceptionAdresse("Code Postale : Le format non respecté ",
                    ExceptionEnumAdresse.MACTH_CD_POSTALE);
        }

        this.codePost = codePost;
    }

    /**
     * renvoie le nom de la ville.
     *
     * @return String la ville.
     */
    public String getVille() {
        return ville;
    }

    /**
     * Ajoute le nom de la ville.
     *
     * @param ville String le nom de la ville.
     * @throws ExceptionPersonnaliser si le nom de la ville es vide.
     * @throws NullPointerException   si l'objet de type String est Null.
     */
    public void setVille(String ville) throws ExceptionPersonnaliser, NullPointerException {

        if (ville == null) {
            throw new NullPointerException("Erreur Ville : null");
        }

        if (ville.isEmpty()) {
            throw new ExceptionAdresse("Erreur Ville : vide ",
                    ExceptionEnumAdresse.EMPTY_NOM_VILLE);
        }

        // teste 30 caractére avec espace
        if (!(ville.matches("\\b[a-zA-ZéèàêÉîï\\s]{4,30}\\b"))) {
            throw new ExceptionAdresse("Ville : format ville 4 caractére non respecter ",
                    ExceptionEnumAdresse.MATCH_NOM_VILLE);
        }


        this.ville = ville;
    }


}
