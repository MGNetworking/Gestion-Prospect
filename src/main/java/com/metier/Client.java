package com.metier;

import com.exception.ExceptionClient;
import com.exception.ExceptionPersonnaliser;

import static com.exception.ExceptionPersonnaliser.ExceptionEnumClient.*;


/**
 * Cette classe permet la création d'un client d'entreprise.
 */
public class Client extends Societe {

    private int chiffreAffaire;
    private int nombreEmployer;

    /**
     * Constructeur implicite.
     * Ce constructeur posséde une méthode calcul ratio qui prend en charge
     * le chiffre d'affaire et le nombre d'employés.
     * L'utilisateur de ce constructeur doit utilise la méthode :
     * calculRatioClientEmployer(int chiffreAffaire, int nEmloyer)
     * pour ajouter les valeurs liées chiffres d'affaires et le nombre d'employés.
     */
    public Client() {
        super();
    }

    /**
     * Constructeur principale, permet de créé un client d'entreprise.
     *
     * @param identifiant      int identifiant de la societe
     * @param raisonSt         String raison sociale du client
     * @param domainSt         DomainSociete domain de la société du client
     * @param numeroDeRueSt    int numéro de l'adresse du client
     * @param nomRueSt         String nom de la rue du client
     * @param codePostSt       String code postale du client
     * @param villeSt          String ville du client
     * @param telephoneSt      String numero de telephone du client
     * @param emailSt          String adresse email du client
     * @param commentaireSt    String commentaire du client
     * @param chiffreAffaireSt int chiffre d'affaire du client
     * @param nombreEmployerST int le nombre d'employés.
     * @throws ExceptionPersonnaliser si les champs ne sont pas respectées
     */
    public Client(int identifiant, String raisonSt, DomainSociete domainSt, int numeroDeRueSt,
                  String nomRueSt, String codePostSt, String villeSt,
                  String telephoneSt, String emailSt, String commentaireSt,
                  int chiffreAffaireSt, int nombreEmployerST) throws ExceptionPersonnaliser {

        // constructeur de la classe Societe.
        super(identifiant, raisonSt, domainSt, numeroDeRueSt, nomRueSt, codePostSt,
                villeSt, telephoneSt, emailSt, commentaireSt);

        this.calculRatioClientEmployer(chiffreAffaireSt, nombreEmployerST);


    }

    /**
     * Le chiffre d'affaire.
     *
     * @return int le chiffre d'affaire.
     */
    public int getChiffreAffaire() {
        return chiffreAffaire;
    }

    /**
     * Modification ou ajoute du chiffre d'affaire.
     *
     * @param chiffreAffaireSt int le chiffre d'affaire.
     * @throws ExceptionPersonnaliser si le chiffre d'affaire est inférieur à 0.
     */
    private void setChiffreAffaire(int chiffreAffaireSt) throws ExceptionPersonnaliser {

        this.chiffreAffaire = chiffreAffaireSt;
    }

    /**
     * le nombre d'employé.
     *
     * @return int le nombre d'employé.
     */
    public int getNombreEmployer() {
        return this.nombreEmployer;
    }

    /**
     * Modification du nombre d'employé.
     *
     * @param nombreEmployerSt int le nombre d'employé.
     * @throws ExceptionPersonnaliser si le nombre d'employés est supérieur a 0.
     */
    private void setNombreEmployer(int nombreEmployerSt) throws ExceptionPersonnaliser {

        this.nombreEmployer = nombreEmployerSt;
    }

    /**
     * Permet de calculée le ratio la moyen : Chiffre d'affaire / client.
     *
     * @param chiffreAffaire int le chiffre d'affaire de l'entreprise.
     * @param nEmloyer       int le nombre d'employé
     * @throws ExceptionPersonnaliser si le ratio est inférieur a 10,
     *                                chiffre d'affaire et le nombre d'employer est égale 0.
     */
    public void calculRatioClientEmployer(int chiffreAffaire, int nEmloyer) throws ExceptionPersonnaliser {

        if (nEmloyer == 0) {        // vérifaction du nombre d'emplyer != 0
            throw new ExceptionClient("division par 0", CALCUL_RATIO_DIVISON_EMPLOYER);
        }

        if (chiffreAffaire == 0) {  // vérifaction du chiffre d'affaire != 0
            throw new ExceptionClient("division par 0", CALCUL_RATIO_DIVISON_CHIFFRE);
        }

        int totale = chiffreAffaire / nEmloyer;  // calcul du ratio

        if (totale < 10) {          // si ratio est inférieur a 10
            throw new ExceptionClient("Ratio :  chiffre d'affaire / employer inférieur à 10",
                    CALCUL_RATIO);
        }

        // initailisation des variables d'instance.
        this.setChiffreAffaire(chiffreAffaire);
        this.setNombreEmployer(nEmloyer);

    }

    /**
     * renvoie la raison Sociale.
     *
     * @return de type String
     */
    @Override
    public String toString() {
        return this.getRaisonSociale();
    }


}
