package Projet.options;

/**
 * Classe représentant l'option wifi de l'hôtel
 *
 * @author MalteseDenis
 */


public class ConnexionInternet {

    private int prix;

    /**
     * Constructeur
     * construit le prix en fonction du nombre d'heures
     *
     * @param h nombre d'heures de wifi utilisé
     */
    public ConnexionInternet(int h) {
        prix = 10 * h;
    }

    /**
     * @return coût de l'utilisation du wifi
     */
    public int getPrix() {
        return prix;
    }

}
