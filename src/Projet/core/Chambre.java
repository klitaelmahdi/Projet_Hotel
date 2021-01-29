/*
 * Cette classe appartient au package core qui constitue le coeur du projet
 * et fait l'usage de différentes options d'où les imports
 */
package Projet.core;

/**
 * Cette classe sert à créer les différents types de chambres
 */
public class Chambre {
    //******************************************************************************************************************
    //Attributs
    //******************************************************************************************************************
    /**
     * Son unique attribut est son type(simple/double/triple/deluxe)
     */
    private String type;

    //******************************************************************************************************************
    //Constructeurs
    //******************************************************************************************************************

    /**
     * On ne fait pas de test sur la validité du type car on dirige la création dans le test
     *
     * @param type le @b type de chambre que l'on veut créer
     */
    public Chambre(String type) {
        this.type = type;
    }
    //******************************************************************************************************************
    //Méthodes
    //******************************************************************************************************************

    /**
     * Getter du type de la chambre
     *
     * @return le type de la chambre
     */
    public String getType() {
        return this.type;
    }

    /**
     * Méthode donnant le prix d'une chambre pour une journée selon son type
     *
     * @return le prix journalier de la chambre
     */
    public double Prix() {
        switch (this.type) {
            case "simple":
                return 124.70 + (14.0 / 100.0) * 124.70;
            case "double":
                return 137.60 + (14.0 / 100.0) * 137.60;
            case "triple":
                return 163.40 + (14.0 / 100.0) * 163.40;
            case "deluxe":
                return 189.20 + (14.0 / 100.0) * 189.20;
            default:
                return 0;
        }
    }

}
