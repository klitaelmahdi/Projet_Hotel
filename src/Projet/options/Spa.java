package Projet.options;

/**
 * Classe représentant l'option Spa de l'hôtel
 *
 * @author MalteseDenis
 */

public class Spa {

    public double prix;

    /**
     * Constructeur par défaut
     * Le prix est initialisé à 0
     */
    public Spa() {
        this.prix = 0.0;
    }

    /**
     * affichage des tarifs du spa
     */
    public void afficheTarifs() {
        System.out.println("     Tarifs du Spa     \nMassage relaxant : 50€\nMassage aux pierres chaudes : 70€\nMassage à l'elixir de bougie : 80€");
    }

    /**
     * @param rep massage choisi par le client
     * @return prix du massage
     */
    public double Prix(String rep) {
        switch (rep) {
            case "relaxant":
                return prix = 50;
            case "pierres":
                return prix = 70;
            case "elixir":
                return prix = 80;
            default:
                System.out.println("Mauvais type de massage renseigné.");
                return prix = 0;
        }
    }

    /**
     * affichage du prix du massage
     */
    public void affichePrix() {
        System.out.println("Coût du massage : " + prix + "€");
    }
}
