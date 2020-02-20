package com.bean.metier;

import com.bean.exception.ExceptionPersonnaliser;
import java.util.Comparator;

/**
 *
 * @author Maxime
 */
public abstract class Societe {

    // constante d'incrémantation
    private static final int INDIC = 1;

    // compteur du nombre de personne
    private static int compteur = 0;

    // variable contenant les informations sur les entreprises.
    private int identifiant = 0;
    private String raisonSociale;
    private String domainSociete;
    private Adresse listAdresse = null;
    private String telephone;
    private String adresseEmail;
    private String commentaire;

    /**
     * Constructeur implicite.
     */
    public Societe() {
    }

    /**
     * Constructeur princiaple ,participe à la création : client / prospect.
     *
     * @param raisonSt String les raisons sociale.
     * @param domainSt String DomainSociete.
     * @param numeroAd int le numero d'adresse de la société.
     * @param nomRueSt String le nom de la rue de la société.
     * @param codePostSt String du code postale de la société.
     * @param villeSt String le nom de la ville de la société.
     * @param telephoneSt String le téléphone de la société.
     * @param emailSt String l'adresse Email de la société.
     * @param commantaireSt String commentaire de la société.
     * @throws ExceptionPersonnaliser gére les exception de la Classe Société.
     */
    public Societe(String raisonSt, String domainSt, int numeroAd, String nomRueSt,
            String codePostSt, String villeSt, String telephoneSt, String emailSt,
            String commantaireSt) throws ExceptionPersonnaliser {

        this.setRaisonSociale(raisonSt);
        this.setDomainSociete(domainSt);

        // champs Adresse
        this.setListAdresse(numeroAd, nomRueSt, codePostSt, villeSt);

        this.setTelephone(telephoneSt);
        this.setAdresseEmail(emailSt);
        this.setCommentaire(commantaireSt);

    }

    /**
     * Identifiant de la société.
     *
     * @return int de l'identifiant de la société.
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * Ajoute un ID a un utilisateur.
     *
     * @param valeur int valeur ID.
     */
    protected void setIdentifiant(int valeur) {
        this.identifiant = valeur;
    }

    /**
     * Compteur du nombre de personne créée.
     *
     * @return le nombre de personnes.
     */
    public static int getCompteur() {
        return compteur;
    }

    /**
     * Modificateur du nombre de personnes créées.
     *
     * @param compteur
     */
    private static void setCompteur(int compteurSt) {
        Societe.compteur = compteurSt;
    }

    /**
     * Ajoute l'identifiant à un utilisateur et d'incrémenter le compteur du
     * nombre de personne.
     *
     * @return int de la valeur du nouvelle indic.
     */
    protected static int addUser() {

        int valueConteur = Societe.getCompteur() + INDIC;
        Societe.setCompteur(valueConteur);
        return valueConteur;
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
     */
    public final void setRaisonSociale(String raisonsocialeST) throws ExceptionPersonnaliser {

        // si le champs est vide, lève un exception
        if (raisonsocialeST.isEmpty()) {
            throw new ExceptionPersonnaliser("Raison Sociale : Le champs doit être saisi");
        }

        // Passe en grand caractére la primere lettre de la raison sociale 
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
    public String getDomainSociete() {
        return this.domainSociete;
    }

    /**
     * Renseigne le domaine d'activité de l'entreprise.
     *
     * @param domainSt String PRIVE ou PUBLIC
     * @throws ExceptionPersonnaliser si non renseigné par l'utilisateur.
     */
    public final void setDomainSociete(String domainSt) throws ExceptionPersonnaliser {

        if (!(domainSt.matches("PUBLIC") | domainSt.matches("PRIVE"))) {
            throw new ExceptionPersonnaliser("Domain Societe : le domain de société n'a pas été renseigné.");
        }
        this.domainSociete = domainSt;

    }

    /**
     * La liste des champs contenant les renseignements de la société.
     *
     * @return un Objet de type Adresse.
     */
    public Adresse getListAdresse() {
        return listAdresse;
    }

    /**
     * Modification ou ajout des champs concernants la société.
     *
     * @param numeroAd int le numero de la société.
     * @param nomRue String le nom de la rue de la société.
     * @param codePost String le code postale de la société.
     * @param ville String le nom de la ville de la société.
     * @throws ExceptionPersonnaliser si l'un des champs n'a pas été renseigner.
     */
    public void setListAdresse(int numeroAd, String nomRue,
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
     * Modification ou ajout du numéro de téléphon de l'entreprise.
     *
     * @param telephone String le numero de téléphone de l'entreprise.
     * @throws ExceptionPersonnaliser si le champ est vide.
     */
    public void setTelephone(String telephone) throws ExceptionPersonnaliser {

        // si c'est vide
        if (telephone.isEmpty()) {
            throw new ExceptionPersonnaliser("Téléphone : vous n'avez pas renseigner le numéro de l'entreprise ");
        }

        // format du numéro de téléphone
        if (!(telephone.matches("[0-9-.]{14}|[0-9- ]{14}"))) {
            throw new ExceptionPersonnaliser("Téléphone : le format du numéro ne pas comforme");
        }

        this.telephone = telephone;
    }

    /**
     * L'adresse Email de contact de l'entreprise.
     *
     * @return String de l'adresse email.
     */
    public String getAdresseEmail() {
        return adresseEmail;
    }

    /**
     * Modification de l'adresse Email de l'entreprise.
     *
     * @param adresseEmailSt String de l'adresse Email de la societe.
     * @throws ExceptionPersonnaliser si le champ est vide ou si le format n'a
     * pas été pas respecter.
     */
    public void setAdresseEmail(String adresseEmailSt) throws ExceptionPersonnaliser {

        if (adresseEmailSt.isEmpty()) {
            throw new ExceptionPersonnaliser("Adresse Email : l'adresse Email doit étre renseigné ");
        }

        // matche adresse Email.
        if (adresseEmailSt.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")) {
            throw new ExceptionPersonnaliser("Adresse Email : entré une adresse eamil valide");
        }

        this.adresseEmail = adresseEmailSt;

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
     * Objet qui permet la comparaison entre deux nom d'entreprise.
     */
    protected static Comparator<Societe> comparatorRaisonSociale = new Comparator<Societe>() {

        @Override
        public int compare(Societe societe1, Societe societe2) {
            return societe1.getRaisonSociale().compareTo(societe2.getRaisonSociale());
        }

    };

}
