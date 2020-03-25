package com.metier;

import com.exception.ExceptionPersonnaliser.ExceptionEnumSociete;
import com.exception.ExceptionPersonnaliser;
import com.exception.ExceptionSociete;

import static com.exception.ExceptionPersonnaliser.ExceptionEnumSociete.*;

import java.util.Comparator;


/**
 * @author Maxime
 */
public abstract class Societe {


    // variable contenant les informations sur les entreprises.
    private int identifiant;
    private String raisonSociale;
    private DomainSociete domainSociete;
    private Adresse listAdresse;
    private String telephone;
    private String email;
    private String commentaire;

    /**
     * Constructeur implicite.
     */
    public Societe() {
    }

    /**
     * Constructeur princiaple ,participe à la création : client / prospect.
     *
     * @param identifiant   int identifiant de la societe
     * @param raisonSt      String les raisons sociale.
     * @param domainSt      DomainSociete DomainSociete.
     * @param numeroAd      int le numero d'adresse de la société.
     * @param nomRueSt      String le nom de la rue de la société.
     * @param codePostSt    String du code postale de la société.
     * @param villeSt       String le nom de la ville de la société.
     * @param telephoneSt   String le téléphone de la société.
     * @param emailSt       String l'adresse Email de la société.
     * @param commantaireSt String commentaire de la société.
     * @throws ExceptionPersonnaliser gére les exception de la Classe Société.
     */
    public Societe(int identifiant, String raisonSt, DomainSociete domainSt, int numeroAd, String nomRueSt,
                   String codePostSt, String villeSt, String telephoneSt, String emailSt,
                   String commantaireSt) throws ExceptionPersonnaliser {

        this.setIdentifiant(identifiant);
        this.setRaisonSociale(raisonSt);
        this.setDomainSociete(domainSt);

        // champs Adresse
        this.setAdresseSt(numeroAd, nomRueSt, codePostSt, villeSt);

        this.setTelephone(telephoneSt);
        this.setEmail(emailSt);
        this.setCommentaire(commantaireSt);

    }

    /**
     * renvoi l'identifiant
     *
     * @return de type int
     */
    public int getIdentifiant() {
        return this.identifiant;
    }

    /**
     * modifi l'indentifiant
     *
     * @param id de type int
     */
    public void setIdentifiant(int id) {
        this.identifiant = id;
    }

    /**
     * Renvoie la raison sociale de l'entreprise.
     *
     * @return String du nom de l'entreprise.
     */
    public String getRaisonSociale() {

        return this.raisonSociale;
    }

    /**
     * Modifie la raison sociale de l'entreprise.
     *
     * @param raisonsocialeST String la raison sociale de l'entreprise.
     * @throws ExceptionPersonnaliser si le champ est vide.
     * @throws NullPointerException   si la variable est null.
     */
    public void setRaisonSociale(String raisonsocialeST) throws ExceptionSociete, NullPointerException {

        // variable null
        if (raisonsocialeST == null)
            throw new NullPointerException("Raison Sociale : variable raison sociale vide ");


        // si le champs est vide, lève un exception
        if (raisonsocialeST.isEmpty()) {
            throw new ExceptionSociete("Raison Sociale : Le champs vide", EMPTY_RAISONSOCIALE);
        }

        // si champs moin de 4 lettre uniquement
        if (!(raisonsocialeST.matches("^[a-z A-Z]{4,}$"))) {
            throw new ExceptionSociete("raison sociale : non match 3 caractére", MATCH__RAISONSOCIALE);
        }

        // Passe en grand caractére la première lettre de la raison sociale
        if (raisonsocialeST.matches("[a-z]{1,}")) {
            raisonsocialeST = raisonsocialeST.replaceFirst((raisonsocialeST.charAt(0) + ""), (raisonsocialeST.charAt(0) + "").toUpperCase());
        }

        this.raisonSociale = raisonsocialeST;
    }

    /**
     * Le domain de la société.
     *
     * @return String du domain de la société.
     */
    public DomainSociete getDomainSociete() {
        return this.domainSociete;
    }

    /**
     * Renseigne le domaine d'activité de l'entreprise.
     *
     * @param domainSt String PRIVE ou PUBLIC
     * @throws ExceptionPersonnaliser si non renseigné par l'utilisateur.
     * @throws NullPointerException   si la variable est null.
     */
    public void setDomainSociete(DomainSociete domainSt) throws ExceptionPersonnaliser, NullPointerException {

        if (domainSt == null) {
            throw new NullPointerException("Domain societe : Variable domain Societe Null");
        }

        this.domainSociete = domainSt;

    }

    /**
     * La liste des champs contenant les renseignements de la société.
     *
     * @return un Objet de type Adresse.
     */
    public Adresse getAdresse() {
        return listAdresse;
    }

    /**
     * Modification des champs concernants l'adresse de la société.
     *
     * @param numeroAd int le numero de la société.
     * @param nomRue   String le nom de la rue de la société.
     * @param codePost String le code postale de la société.
     * @param ville    String le nom de la ville de la société.
     * @throws ExceptionPersonnaliser si l'un des champs n'a pas été renseigner.
     */
    public void setAdresseSt(int numeroAd, String nomRue,
                             String codePost, String ville) throws ExceptionPersonnaliser {
        this.listAdresse = new Adresse(numeroAd, nomRue, codePost, ville);
    }

    /**
     * Le numéro de télephone de l'entreprise.
     *
     * @return String le numero de téléphone.
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Modification du numéro de téléphon de l'entreprise.
     *
     * @param telephone String le numero de téléphone de l'entreprise.
     * @throws ExceptionPersonnaliser si le champ est vide.
     */
    public void setTelephone(String telephone) throws ExceptionPersonnaliser {

        // si c'est vide
        if (telephone.isEmpty()) {
            throw new ExceptionSociete("Téléphone : vous n'avez pas renseigner le numéro de l'entreprise ",
                EMPTY_TELEPHONE);
        }

        // format du numéro de téléphone
        if (!(telephone.matches("^0\\d(\\s|-)?(\\d{2}(\\s|-)?){4}$"))) {
            throw new ExceptionSociete("Téléphone : le format du numéro n'est pas comforme",
                MATCH_TELEPHONE);
        }


        this.telephone = telephone;
    }

    /**
     * L'adresse Email de contact de l'entreprise.
     *
     * @return String de l'adresse email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modification de l'adresse Email de l'entreprise.
     *
     * @param adresseEmailSt String de l'adresse Email de la societe.
     * @throws ExceptionPersonnaliser si le champ est vide ou si le format n'a
     *                                pas été pas respecter.
     */
    public void setEmail(String adresseEmailSt) throws ExceptionPersonnaliser {

        if (adresseEmailSt.isEmpty()) {
            throw new ExceptionSociete("Adresse Email : l'adresse Email doit étre renseigné ",
                ExceptionEnumSociete.EMPTY_EMAIL);
        }
        System.out.println("Email : " + adresseEmailSt + " " + adresseEmailSt.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"));
        if (!adresseEmailSt.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new ExceptionSociete("Adresse Email : entré une adresse eamil valide",
                ExceptionEnumSociete.MATCH_EMAIL);
        }

        this.email = adresseEmailSt;

    }

    /**
     * @return String le commentaire.
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Modification du commentaire.
     *
     * @param commentaireSt String le commentaire.
     */
    public void setCommentaire(String commentaireSt) {
        this.commentaire = commentaireSt;
    }

    /**
     * Type de clientèle
     */
    public enum TypeSociete {
        CLIENT("CLIENT"),
        PROSPECT("PROSPECT");

        private String type;

        TypeSociete(String typeSociete) {
            this.type = typeSociete;
        }

        public String getTypeSociete() {
            return type;
        }

    }

    /**
     * Secteur d'activité des entreprise
     */
    public enum DomainSociete {

        PUBLIC("PUBLIC"),
        PRIVE("PRIVE");

        private String domain;

        DomainSociete(String domain) {
            this.domain = domain;
        }

        public String getDomainst() {
            return this.domain;
        }
    }


}
