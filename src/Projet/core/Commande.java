package Projet.core;

/**
 * Classe représantant les commandes possibles dans le restaurant
 *
 * @author MalteseDenis
 */

public class Commande {

    boolean[] commande = new boolean[5];
    private double prix;

    //**************************************************************************************************************
    //CONSTRUCTORS
    //**************************************************************************************************************

    /**
     * Constructeur par défault
     * Le prix est initialisé à 0
     */

    public Commande() {
        this.prix = 0.0;
    }

    //*****************************************************************************************************************
    //PUBLIC METHODS
    //*****************************************************************************************************************

    /**
     * Méthode réservée au client choisissant une formule
     *
     * @param rep formule choisie
     * @return prix de la formule
     */
    public double commandeFormule(String rep) {
        if (rep.equals("mojito")) {
            prix = 6.0;
            commande[1] = true;
            commande[4] = true;
        } else if (rep.equals("daiquiri")) {
            prix = 8.0;
            commande[1] = true;
            commande[2] = true;
            commande[4] = true;
        }
        return prix;
    }

    /**
     * Méthode réservée au client choisissant une commande à la carte
     *
     * @param entree         true si le client veut une entrée, faux sinon
     * @param plat           true si le client veut un plat, faux sinon
     * @param accompagnement true si le client veut un accompagnement, faux sinon
     * @param dessert        true si le client veut un dessert, faux sinon
     * @param boisson        true si le client veut une boisson, faux sinon
     * @return prix de la commande
     */

    public double commandeCarte(boolean entree, boolean plat, boolean accompagnement, boolean dessert, boolean boisson) {
        if (entree) {
            commande[0] = true;
            prix += 3.0;
        }
        if (plat) {
            commande[1] = true;
            prix += 5.0;
        }
        if (accompagnement) {
            commande[2] = true;
            prix += 2.0;
        }
        if (dessert) {
            commande[3] = true;
            prix += 3.5;
        }
        if (boisson) {
            commande[4] = true;
            prix += 2.0;
        }
        return prix;
    }

    /**
     * Méthode réservée au client choisissant une formule mojito. Permet d'ajouter un supplément
     *
     * @param entree         true si le client veut une entrée, faux sinon
     * @param accompagnement true si le client veut un accompagnement, faux sinon
     * @param dessert        true si le client veut un dessert, faux sinon
     * @return prix de la commande
     */
    public double addMojito(boolean entree, boolean accompagnement, boolean dessert) {
        if (entree) {
            commande[0] = true;
            prix += 3.0;
        }
        if (accompagnement) {
            commande[2] = true;
            prix += 2.0;
        }
        if (dessert) {
            commande[3] = true;
            prix += 3.5;
        }
        return prix;
    }

    /**
     * @param entree  true si le client veut une entrée, faux sinon
     * @param dessert true si le client veut un dessert, faux sinon
     * @return prix de la commande
     * @fn Méthode réservée au client choisissant une formule daiquiri. Permet d'ajouter un supplément
     */
    public double addDaiquiri(boolean entree, boolean dessert) {
        if (entree) {
            commande[0] = true;
            prix += 3.0;
        }
        if (dessert) {
            commande[3] = true;
            prix += 3.5;
        }
        return prix;
    }

    /**
     * Déduit un ticket de 5 euros au prix de la commande
     */
    public void useTicket() {
        if (prix > 5) {
            prix = prix - 5;
        } else {
            prix = 0;
        }
    }

    /**
     * Affiche le prix total de la commande
     */
    public void afficheCommande() {
        System.out.println("Prix total de la commande : " + this.prix + "€");
    }


}
