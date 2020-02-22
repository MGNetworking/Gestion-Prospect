package com.metier;

import java.util.ArrayList;
import java.util.List;

import com.exception.ExceptionPersonnaliser;
import java.util.Collections;

/**
 * Cette classe permet la création d'un client d'entreprise.
 */
public class Client extends Societe {

    private int chiffreAffaire;
    private int nombreEmployer;

    // Liste des Clients
    private static List<Client> listeClient = new ArrayList<>(50);

    /**
     * Constructeur implicite.
     */
    public Client() {
        super();
        // ajoute un user est donne un ID
        setIdentifiant(Societe.addUser());
    }

    /**
     * Constructeur principale, permet de créé un client d'entreprise.
     *
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
    public Client(String raisonSt, DomainSociete domainSt, int numeroDeRueSt,
                  String nomRueSt, String codePostSt, String villeSt,
                  String telephoneSt, String emailSt, String commentaireSt,
                  int chiffreAffaireSt, int nombreEmployerST) throws ExceptionPersonnaliser {

        // constructeur de la classe Societe.
        super(raisonSt, domainSt, numeroDeRueSt, nomRueSt, codePostSt, villeSt, telephoneSt, emailSt, commentaireSt);

        this.calculRatioClientEmployer(chiffreAffaireSt, nombreEmployerST);

        // ajoute un user est donne un ID
        setIdentifiant(Societe.addUser());

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
     * Permet d'ajoute un client à la liste
     *
     * @param client de type Client.
     */
    public static void addLisClient(Client client) {
        listeClient.add(client);
    }

    /**
     * la liste de la société.
     *
     * @return List qui contient la liste des clients.
     */
    public static List<Client> getListeClient() {
        return listeClient;
    }

    /**
     * Supprime un client de la liste.
     *
     * @param client de type Client.
     */
    public static void removeListeClient(Client client) {

        listeClient.remove(client);
    }

    /**
     * @return le taille de la liste Client.
     */
    public static int getSizeListeClient() {
        return listeClient.size();
    }

    /**
     * Permet de calculée le ratio la moyen : Chiffre d'affaire / client.
     *
     * @param chiffreAffaire int le chiffre d'affaire de l'entreprise.
     * @param nEmloyer       int le nombre d'employé
     * @throws ExceptionPersonnaliser si le ratio est inférieur a 10.
     * @throws ArithmeticException    dans le cas d'une dividion par zero.
     */
    public void calculRatioClientEmployer(int chiffreAffaire, int nEmloyer) throws ExceptionPersonnaliser,
        ArithmeticException {

        // vérifaction du nombre d'emplyer
        if (nEmloyer == 0) {
            throw new ArithmeticException("division par 0");
        }

        // calcul du ratio
        int totale = chiffreAffaire / nEmloyer;

        // si ratio est inférieur a 10
        if (totale < 10) {
            throw new ExceptionPersonnaliser("Ratio : le calcul de la moyen chiffre d'affaire / employer, est inférieur à 10");
        }

        // initailisation des variables d'instance.
        this.setChiffreAffaire(chiffreAffaire);
        this.setNombreEmployer(nEmloyer);

    }

    /**
     * renvoie la raison Sociale.
     * @return de tyep string
     */
    @Override
    public String toString() {
        return this.getRaisonSociale();
    }

    /**
     * Trie la liste avec avec object comparateur.
     *
     * @param list Objet de type list.
     */
    public static void trieClient(List<Client> list) {
        Collections.sort(list, Societe.comparatorRaisonSociale);

    }

}
