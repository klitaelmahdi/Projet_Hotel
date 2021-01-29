/*
 * Cette classe appartient au package core qui constitue le coeur du projet
 * et fait l'usage de différentes options d'où les imports
 */
package Projet.core;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Cette classe a pour but de représenter l'activité Casion proposée par l'hôtel et plus précisémment la Roulette
 *
 * @author MalteseDenis
 */

public class Casino {
    //******************************************************************************************************************
    //FIELDS
    //******************************************************************************************************************
    /**
     * Roulette la roulette avec ses cases numérotées et de couleur représentée par un Hasmap afin de pouvoir
     * associer un nombe avec sa parité et ça couleur
     * gainpl le gain du joueur pendant cette instance de roulette
     * nbj le nombre jetons en possession du joueur
     */
    private static HashMap<Integer, String[]> Roulette = new HashMap<>(37);
    private int gainpl = 0;
    private int nbj;
    //******************************************************************************************************************
    //CONSTRUCTORS
    //******************************************************************************************************************

    /**
     * constructeur vide car la roulette est la même tout le temps
     * De plus, tout joueur arrive avec un gain nul et un nombre de jetons qui lui est propre
     */
    public Casino() {
    }
    //******************************************************************************************************************
    //Methods
    //******************************************************************************************************************
    /*Setters
     */

    /**
     * Getter du nombre de jetons du joueur
     *
     * @return le nombre de @b jetons en possession du joueur sachant qu'il ne peut pas avoir un nombre négatif de jetons
     */
    public int getNbj() {
        return Math.max(nbj, 0);
    }

    /**
     * Mise à jour du nombre de jetons du joueurs
     *
     * @param nbj le nombre de jetons en possession du joueur
     */
    public void setNbj(int nbj) {
        this.nbj = nbj;
    }


    /*Getters
     */

    /**
     * @return @b gainpl le gain du joueur
     */
    public int getGainpl() {
        return gainpl;
    }

    /**
     * Mise à jour du gain du joueur
     *
     * @param gainpl legain du joueur après la mise à jour
     */
    public void setGainpl(int gainpl) {
        this.gainpl = gainpl;
    }
    /* Other Methods

     */

    /**
     * Méthode construisant la roulette en associant à chaque case sa parité et sa couleur
     */
    public void create() {
        Roulette.put(0, new String[]{"zéro", "vert"});
        for (int i = 1; i <= 36; i++) {
            if (i <= 10) {
                if (i % 2 == 0) {
                    Roulette.put(i, new String[]{"pair", "noir"});
                } else {
                    Roulette.put(i, new String[]{"impair", "rouge"});
                }
            } else if (i <= 18) {
                if (i % 2 == 0) {
                    Roulette.put(i, new String[]{"pair", "rouge"});
                } else {
                    Roulette.put(i, new String[]{"impair", "noir"});
                }
            } else if (i <= 28) {
                if (i % 2 == 0) {
                    Roulette.put(i, new String[]{"pair", "noir"});
                } else {
                    Roulette.put(i, new String[]{"impair", "rouge"});
                }
            } else {
                if (i % 2 == 0) {
                    Roulette.put(i, new String[]{"pair", "rouge"});
                } else Roulette.put(i, new String[]{"impair", "noir"});
            }
        }
    }
    /**
     * Méthode renvoyant un entier aléatoire entre 0 et 36 pour simuler l'aléa dé l'arrivée de la bille
     *
     * @return le nombre sur lequel s'arrête la bille
     */
    public int roule() {
        Random r = new Random();
        int n = r.nextInt(38) - 1;
        return Math.max(n, 0);
    }

    /**
     * Méthode qui détermine si le joueur à miser juste en foncyion du nombre sur lequel s'arrête la bille
     *
     * @param n    le nombre sur lequelle la bille s'arrête dans la roulette
     * @param mise le type de mise du joueur(rouge/noir/manque/passe/pair/impair)
     * @return vrai si le joueur à miser juste faux sinon
     */
    public boolean gagne(int n, String mise) {
        System.out.println("Rien ne va plus!");
        switch (mise) {
            case "pair":
                return Roulette.get(n)[0].equals("pair");
            case "impair":
                return Roulette.get(n)[0].equals("impair");
            case "noir":
                return Roulette.get(n)[1].equals("noir");
            case "rouge":
                return Roulette.get(n)[1].equals("rouge");
            case "manque":
                return n>=1 && n <= 18;
            case "passe":
                return n >= 19;
            case "zéro":
                return n == 0;
            default:
                return false;
        }
    }


    /**
     * Méthode qui traite la mise du joueur et détermine si celui-ci à gagné ou non puis met à jour son gain
     *
     * @param mise   le type de mise effectuée par le joueur(rouge/noir/manque/passe/pair/impair)
     * @param valeur la valeur entière en euros misée par le joueur
     */

    public void miser(String mise, int valeur) {
        int n = roule();
        System.out.println(n + "--->" + Roulette.get(n)[1]);
        switch (mise) {
            case "pair":
                if (gagne(n, "pair")) {
                    System.out.println("Vous avez gagné et avez doublé votre mise!");
                    gainpl += valeur;
                    if (nbj >= 0) {
                        nbj += valeur;
                    }
                } else {
                    System.out.println("Aïe!C'est loupé dommage...Vous aurez plus de chance la prochaine fois!");
                    gainpl -= valeur;
                    nbj -= valeur;
                }
                break;
            case "impair":
                if (gagne(n, "impair")) {
                    System.out.println("Vous avez gagné et avez doublé votre mise!");
                    gainpl += valeur;
                    if (nbj >= 0) {
                        nbj += valeur;
                    }
                } else {
                    System.out.println("Aïe!C'est loupé dommage...Vous aurez plus de chance la prochaine fois!");
                    gainpl -= valeur;
                    nbj -= valeur;
                }
                break;
            case "noir":
                if (gagne(n, "noir")) {
                    System.out.println("Vous avez gagné et avez doublé votre mise!");
                    gainpl += valeur;
                    if (nbj >= 0) {
                        nbj += valeur;
                    }
                } else {
                    System.out.println("Aïe!C'est loupé dommage...Vous aurez plus de chance la prochaine fois!");
                    gainpl -= valeur;
                    nbj -= valeur;
                }
                break;
            case "rouge":
                if (gagne(n, "rouge")) {
                    System.out.println("Vous avez gagné et avez doublé votre mise!!");
                    gainpl += valeur;
                    if (nbj >= 0) {
                        nbj += valeur;
                    }
                } else {
                    System.out.println("Aïe!C'est loupé dommage...Vous aurez plus de chance la prochaine fois!");
                    gainpl -= valeur;
                    nbj -= valeur;
                }
                break;
            case "manque":
                if (gagne(n, "manque")) {
                    System.out.println("Vous avez gagné et avez doublé votre mise!");
                    gainpl += valeur;
                    if (nbj >= 0) {
                        nbj += valeur;
                    }
                } else {
                    System.out.println("Aïe!C'est loupé dommage...Vous aurez plus de chance la prochaine fois!");
                    gainpl -= valeur;
                    nbj -= valeur;
                }
                break;
            case "passe":
                if (gagne(n, "passe")) {
                    System.out.println("Vous avez gagné et avez doublé votre mise!");
                    gainpl += valeur;
                    if (nbj >= 0) {
                        nbj += valeur;
                    }
                } else {
                    System.out.println("Aïe!C'est loupé dommage...Vous aurez plus de chance la prochaine fois!");
                    gainpl -= valeur;
                    nbj -= valeur;
                }
                break;
            case "zéro":
                if (gagne(n, "zéro")) {
                    System.out.println("Vous avez gagné et avez multiplié votre mise par 35 !");
                    gainpl += 34 * valeur;
                    if (nbj >= 0) {
                        nbj += valeur;
                    }
                } else {
                    System.out.println("Aïe!C'est loupé dommage...Vous aurez plus de chance la prochaine fois!");
                    gainpl -= valeur;
                    nbj -= valeur;
                }
                break;


        }
    }




    /**
     * Méthode faisant appel à l'interaction du joueur et simulant une mise et un résultat pour un unique tour de roulette
     */
    public void tour() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Que voulez vous miser?(rouge/noir/pair/impair/manque/passe/zéro)");
        System.out.println("Les mises simples doublent votre mise et miser sur zéro et gagner multiplie par 35 votre mise!");
        String mise = sc.nextLine();
        if (!mise.equals("rouge") && !mise.equals("noir") && !mise.equals("pair") && !mise.equals("impair") && !mise.equals("manque") && !mise.equals("passe") && !mise.equals("zéro")) {
            System.out.println("Type de mise renseigné non reconnu veuillez recommencer svp");
            tour();
            return;
        }
        System.out.println("Combien voulez vous miser?Mises entières uniquement");
        int valeur = sc.nextInt();
        this.miser(mise, valeur);
        System.out.println("Résultats de ce tour:");
        System.out.println("Gain total:" + this.getGainpl());
        System.out.println("Jetons restants:" + this.getNbj());
    }



}