/*Ceci est la classe Test,l'éxécution de celle-ci lance la simulation de gestion d'hôtel
 */
package Projet;

import Projet.core.Client;
import Projet.core.Hotel;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Test {

    public static void main(String[] args) {
        Sejour();


    }

    /**
     * Méthode simulant la création d'un hôtel de 40 chambres dont la répartition par types est choisie par le gérant
     */
    private static Hotel crHotel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nous allons créer votre hôtel");
        System.out.println("Quelle est la répartition des types de chambres (simples/doubles/triples/deluxes)?Vous en avez 40 au total.");
        System.out.println("Combien simples?");
        int simple = sc.nextInt();
        System.out.println("Combien doubles?");
        int doubles = sc.nextInt();
        System.out.println("Combien triples?");
        int triple = sc.nextInt();
        System.out.println("Combien deluxes?");
        int deluxe = sc.nextInt();
        Hotel h = new Hotel(simple, doubles, triple, deluxe);
        while (h.getNbDeluxe() + h.getNbDouble() + h.getNbSimple() + h.getNbTriple() != 40) {
            System.out.println("Nombre de chambres total incorrect");
            h = crHotel();
        }
        if (h.getNbDeluxe() + h.getNbDouble() + h.getNbSimple() + h.getNbTriple() == 40) {
            System.out.println("Parfait! votre hôtel de 40 chambres est prêt .");
        }
        return h;
    }
    /**
     * Méthode simulant l'enregistrement d'un client
     *
     * @return le client créé
     */
    public static Client Enregistrement() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Quel est votre nom ? ");
        String nom = sc.nextLine();
        System.out.println("Quel est votre prénom ? ");
        String prenom = sc.nextLine();
        System.out.println("Quel est votre date de naissance ? (jj/mm/aaaa)");
        String birtday = sc.nextLine();
        System.out.println("Quel est votre adresse email ? ");
        String mail = sc.nextLine();
        System.out.println("Quel est votre numéro de téléphone ? ");
        String num = sc.nextLine();
        System.out.println("Quel est votre indice téléphonique ?");
        String indice = sc.nextLine();
        System.out.println("Quelle est la durée de votre séjour ?(nombre de jours en entier) ");
        int duree = sc.nextInt();
        Client c = new Client(nom, prenom, birtday, mail, num, indice, duree);
        while (c.getEmail() == null || c.getNaissance() == null || c.getTel() == null) {
            System.out.println("Echec de la réception. Veuillez recommencez. ");
            c = Enregistrement();
        }
        return c;
    }

    /**
     * Méthode simulant une saison de l'hôtel (entre l'arrivée du premier client et le départ du dernier
     */
    private static void Sejour() {
        Hotel h = crHotel();
        int max = 1;
        int day = 1;
        double recette = 0.0;
        while (max > 0) {
            System.out.println("--------------------------DAY " + day + "-----------------------");
            h.day();
            Client[] c = h.getClient();
            int[] durees = new int[c.length];
            for (int i = 0; i < 40; i++) {
                c[i].setDuree(c[i].getDuree() - 1);
                durees[i] = c[i].getDuree();
                if (c[i].getDuree() == 0 && !c[i].getNom().equals("non renseigné")) {
                    System.out.println("Merci de nous avoir fait confiance, votre séjour touche à sa fin M(me)" + c[i].getNom()
                            + "nous espérons vous revoir bientôt\n");
                    c[i].setNote(c[i].getNote() - c[i].getGains());
                    System.out.println("Votre note totale s'éleve à : " + c[i].getNote() + " euros.");
                    recette += c[i].getNote();
                    h.videchambre(i);
                }
            }
            h.setClient(c);
            max = IntStream.of(durees).max().getAsInt();
            System.out.println("Voici l'état de disponibilité des chambres à la fin de cette journée:\n ");
            h.affichage();
            System.out.println("\n\n");
            day += 1;
        }
        System.out.println("Le dernier client quitte l'hôtel,celui-ci ferme donc ses portes." +
                "La recette totale de l'hôtel pour cette saison est de : " + recette);

    }
}