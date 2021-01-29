/*
 * Cette classe appartient au package core qui constitue le coeur du projet
 * et fait l'usage de différentes options d'où les imports
 */
package Projet.core;

import Projet.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Hotel {
    //******************************************************************************************************************
    //Attributs
    //******************************************************************************************************************
    /**
     * @b numChambre le tableau d'entiers contenant tous les nombres de chambre de l'hôtel
     * @b client le tableau de client occupant une chambre dans l'hôtel
     * @b chambres le tableau de chambres de l'hôtel
     * @b nbSimple/Double/Triple/Deluxe entiers représentant les nombres de chambres simples,doubles,triples et deluxes
     * @b resto le restaurant de l'hôtel
     * @b disponibilité tableau de chaînes de caractères qui disent si les chambres sont libres ou occupées
     */
    private static int[] numChambre;
    private Client[] client;
    private Chambre[] chambres;
    private int nbSimple;
    private int nbDouble;
    private int nbTriple;
    private int nbDeluxe;
    private Restaurant resto;
    private String[] disponibilite;


    //******************************************************************************************************************
    //Constructeurs
    //******************************************************************************************************************

    /**
     * On crée l'hôtel
     *
     * @param nbSimple le nombre de chambres simples souhaité
     * @param nbDouble le nombre de chambres doubles souhaité
     * @param nbTriple le nombre de chambres triples souhaité
     * @param nbDeluxe le nombre de chambres deluxes souhaité
     */
    public Hotel(int nbSimple, int nbDouble, int nbTriple, int nbDeluxe) {
        this.nbSimple = nbSimple;
        this.nbDouble = nbDouble;
        this.nbTriple = nbTriple;
        this.nbDeluxe = nbDeluxe;
        chambres = new Chambre[nbSimple + nbDouble + nbTriple + nbDeluxe];
        disponibilite = new String[nbSimple + nbDouble + nbTriple + nbDeluxe];
        client = new Client[nbSimple + nbDouble + nbTriple + nbDeluxe];
        numChambre = new int[nbSimple + nbDouble + nbTriple + nbDeluxe];
        for (int i = 0; i < nbSimple; i++) {
            this.chambres[i] = new Chambre("simple");
        }
        for (int i = 0; i < nbDouble; i++) {
            this.chambres[nbSimple + i] = new Chambre("double");
        }
        for (int i = 0; i < nbTriple; i++) {
            this.chambres[nbSimple + nbDouble + i] = new Chambre("triple");
        }
        for (int i = 0; i < nbDeluxe; i++) {
            this.chambres[nbSimple + nbDouble + nbTriple + i] = new Chambre("deluxe");
        }

        for (int i = 0; i < nbSimple + nbDouble + nbTriple + nbDeluxe; i++) {
            disponibilite[i] = "libre";
            numChambre[i] = i + 1;
            client[i] = new Client();

        }
        this.resto = new Restaurant();
    }
    //******************************************************************************************************************
    //Méthodes
    //******************************************************************************************************************
    //Getters

    /**
     * Getter du nombre de chambres doubles
     *
     * @return le nombre de chambres doubles de l'hôtel
     */
    public int getNbDouble() {
        return nbDouble;
    }

    /**
     * Getter du nombre de chambres triples
     *
     * @return le nombre de chambres triples de l'hôtel
     */
    public int getNbTriple() {
        return nbTriple;
    }

    /**
     * Getter du nombre de chambres deluxes
     *
     * @return le nombre de chambres deluxes de l'hôtel
     */
    public int getNbDeluxe() {
        return nbDeluxe;
    }

    /**
     * Getter du nombre de chambres simples
     *
     * @return le nombre de chambres simples de l'hôtel
     */
    public int getNbSimple() {
        return nbSimple;
    }

    /**
     * Getter du restaurant de l'hôtel
     *
     * @return le restaurant de l'hôtel
     */
    public Restaurant getResto() {
        return resto;
    }

    /**
     * Getter du tableau de clients
     *
     * @return le tableau de clients de l'hôtel
     */
    public Client[] getClient() {
        return client;
    }

    /**
     * Mise à jour du tableau de clients
     *
     * @param client le tableau de client après mise à jour
     */
    public void setClient(Client[] client) {
        this.client = client;
    }
    //Setters

    /**
     * Getter du tableau de chambres
     *
     * @return le tableau de chambres de l'hôtel
     */
    public Chambre[] getChambres() {
        return chambres;
    }

    /**
     * Méthode vidant une chambre lorsque le client la quitte définitivement
     *
     * @param num le numéro de la chambre à vider
     */
    public void videchambre(int num) {
        this.disponibilite[num] = "libre";
        this.client[num] = new Client();
    }


    /**
     * Détermine le maximum de personnes pouvant occuper une chambre
     *
     * @param type le type de la chambre
     * @return un couple d'entier dont la première composante est le maximum de personnes pouvant occuper la chambre
     * et la seconde indiquant si il y a eu une erreur ou non
     */
    public int[] max(String type) {
        switch (type) {
            case "simple":
                return new int[]{1, 1};
            case "double":
            case "deluxe":
                return new int[]{2, 1};
            case "triple":
                return new int[]{3, 1};
            default:
                System.out.println("Mauvais type de chambre renseigné");
                return new int[]{0, 0};
        }
    }

    /**
     * Méthode affichant l'état courant de l'hôtel sous la forme:
     * numérochambre           typechambre             disponibilité                client éventuel
     */
    public void affichage() {
        for (int i = 0; i < 40; i++) {
            String c;
            if (client[i] == null) {
                c = "aucun";
            } else {
                c = client[i].getNom();
            }
            System.out.println(numChambre[i] + "               " + chambres[i].getType() + "               " + disponibilite[i] + "               " + c);
        }
    }

    /**
     * Méthode réalisant la réservation d'une chambre et qui y affecte un client
     *
     * @param max  le maximum de personnes pouvant occuper la chambre
     * @param c    le client voulant réserver une chambre
     * @param n    le nombre de personnes que le client veut faire occuper la chambre
     * @param type le type de la chambre
     * @return 0 si la réservation échoue et 2 si elle fonctionne
     */
    public int reservation(int max, Client c, int n, String type) {
        if (n > max) {
            System.out.println("Vous ne pouvez pas être autant que ça dans cette chambre, veuillez réaliser une nouvelle réservation.");
            return 0;
        } else if (this.disponibilite(type) == -1) {
            System.out.println("Le type de chambre que vous souhaitez n'est pas disponible");
            return 0;
        } else {
            int numChambre = this.disponibilite(type);
            disponibilite[numChambre - 1] = "occupée";
            client[numChambre - 1] = c;
            return 2;
        }
    }

    /**
     * Méthode vérifiant si il reste des chambres disponible d'un certain type
     *
     * @param type le type de chambre
     * @return -1 si il ne reste plus de chambre de ce type et le plus petit numéro de chambre du type demandé étant
     * libre sinon
     */
    public int disponibilite(String type) {
        int b = 0;
        switch (type) {
            case "simple":
                for (int i = 0; i < nbSimple; i++) {
                    b = i;
                    if (disponibilite[i].equals("libre")) {
                        break;
                    }
                    if (disponibilite[nbSimple - 1].equals("occupée")) {
                        return -1;
                    }
                }
                break;
            case "double":
                for (int i = nbSimple; i < nbSimple + nbDouble; i++) {
                    b = i;
                    if (disponibilite[i].equals("libre")) {
                        break;
                    }
                    if (disponibilite[nbSimple + nbDouble - 1].equals("occupée")) {
                        return -1;
                    }

                }
                break;
            case "triple":
                for (int i = nbSimple + nbDouble; i < nbSimple + nbDouble + nbTriple; i++) {
                    b = i;
                    if (disponibilite[i].equals("libre")) {
                        break;
                    }
                    if (disponibilite[nbSimple + nbDouble + nbTriple - 1].equals("occupée")) {
                        return -1;
                    }
                }
                break;
            case "deluxe":
                for (int i = nbSimple + nbDouble + nbTriple; i < 40; i++) {
                    b = i;
                    if (disponibilite[i].equals("libre")) {
                        break;
                    }
                    if (disponibilite[nbSimple + nbDouble + nbTriple + nbDeluxe].equals("occupée")) {
                        return -1;
                    }

                }
                break;
            default:
                b = -1;
                break;
        }
        return b + 1;
    }

    /**
     * Méthode imprimant le ticket certifiant de la réservation d'une chambre au sein de l'hôtel
     *
     * @param c le client qui a réalisé une réservation
     */
    public void ticket(Client c) {
        Date date = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("d MMMM yyyy, H:m:s");
        System.out.println("**********HOTEL BLUE BAY CARACAO**********\n\nDate de réservation : " + formater.format(date) + "\n");
        c.affichage();
        int numChambre;
        int i = 0;
        while (client[i] != c && i <= 38) {
            i += 1;
        }
        numChambre = i + 1;
        String type = chambres[i].getType();
        System.out.println("Chambre n°: " + numChambre + "       Type de chambre : " + type);
    }

    /**
     * Methode simulant le choix du type de chambre à réserver par le client
     *
     * @return un tableau de chaînes de caractères dont la première composante est le maximum de personnes pouvant
     * occuper la chambre et la deuxième est le type de la chambre
     */
    public String[] choixType() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel type de chambre voulez-vous réserver ? (simple/double/triple/deluxe)");
        String type = sc.nextLine();
        int[] tab = this.max(type);

        while (tab[1] == 0) {
            System.out.println("Quel type de chambre voulez-vous réserver ? (simple/double/triple/deluxe)");
            type = sc.nextLine();
            tab = this.max(type);
        }
        String max = tab[0] + "";
        return new String[]{max, type};
    }

    /**
     * Méthode simulant la réservation d'une chambre par un client avec interaction
     *
     * @param c le client réservant la chambre
     */
    public void Reservation(Client c) {
        Scanner sc = new Scanner(System.in);
        String[] tab = this.choixType();
        int max = Integer.parseInt(tab[0]);
        String type = tab[1];
        System.out.println("Les chambres simples acceuillent au maximum 1 pers,les chambres doubles et deluxes 2 "
                + "et les triples 3");
        System.out.println("Combien de personnes occuperont la chambre?");
        int n = sc.nextInt();
        while (this.reservation(max, c, n, type) == 0) {
            tab = this.choixType();
            max = Integer.parseInt(tab[0]);
            type = tab[1];
            System.out.println("Combien de personnes occuperont la chambre?");
            n = sc.nextInt();
        }
        System.out.println("Votre réservation est faite.");
        this.ticket(c);
    }

    /**
     * Méthode simulant la réception des nouveaux clients de l'hôtel au début de la journée
     */
    public void Reception() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Y-a-t-il un nouveau client ? (o/n)");
        String rep = sc.nextLine();
        while (rep.equals("o")) {
            Client c = Test.Enregistrement();
            while (rep.equals("o")) {
                this.Reservation(c);
                System.out.println("Voulez-vous réserver une ou des autres chambres?(o/n)");
                rep = sc.nextLine();
            }
            System.out.println("Y-a-t-il un nouveau client ? (o/n)");
            rep = sc.nextLine();
        }
    }

    /**
     * Méthode simulant la participation ou non des différents clients de l'hôtel aux activités du Casino
     */
    public void Casino() {
        Client[] c = this.getClient();
        for (Client i : c) {
            if (!i.getNom().equals("non renseigné") && i.getPresence()) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Bienvenue devant le  casino " + i.getNom());
                System.out.println("Voulez-vous entrer dans le casino ? (o/n)");
                String rep = sc.nextLine();
                if (rep.equals("o")) {
                    i.partie();
                }

            }
        }
    }

    /**
     * Méthode simulant la participation ou non des différents clients de l'hôtel aux activités de plongée
     */
    public void Plongee() {
        Client[] c = this.getClient();
        for (Client i : c) {
            if (!i.getNom().equals("non renseigné") && i.getPresence()) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Bienvenue au site de plongée " + i.getNom());
                System.out.println("Voulez-vous faire un baptême de plongée ou une croisière ? (o/n)");
                String rep = sc.nextLine();
                if (rep.equals("o")) {
                    i.plong();
                }

            }
        }
    }

    /**
     * Méthode simulant le payment du wifi pour tous les clients présents dans l'hôtel
     */
    public void wifi() {
        Client[] c = this.getClient();
        for (Client i : c) {
            if (!i.getNom().equals("non renseigné") && i.getPresence()) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Bienvenue au site de règlement de l'utilisation d'Internet " + i.getNom());
                System.out.println("Avez-vous utilisé Internet aujourd'hui ? (o/n)");
                String rep = sc.nextLine();
                if (rep.equals("o")) {
                    i.CoInt();
                }

            }
        }
    }

    /**
     * Méthode simulant un repas de midi au restaurant
     */
    public void Restau() {
        Client[] c = this.getClient();
        Restaurant r = this.getResto();
        System.out.println("Le restaurant ouvre ses portes, voici sa carte : \n\n");
        r.afficheCarte();
        for (Client i : c) {
            if (!i.getNom().equals("non renseigné") && i.getPresence()) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Bienvenue au restaurant " + i.getNom());
                System.out.println("Voulez-vous commander quelque chose ? (o/n)");
                String rep = sc.nextLine();
                while (rep.equals("o")) {
                    i.commandeRestau();
                    System.out.println("Voulez-vous commander autre chose ? (o/n)");
                    rep = sc.nextLine();
                }

            }
        }
    }

    /**
     * Méthode simulant la participation ou non des différents clients de l'hôtel aux activités du spa
     */
    public void Spa() {
        Client[] c = this.getClient();
        for (Client i : c) {
            if (!i.getNom().equals("non renseigné") && i.getPresence()) {
                double tot = 0;
                Scanner sc = new Scanner(System.in);
                System.out.println("Bienvenue au Spa " + i.getNom());
                System.out.println("Voulez-vous un massage ? (o/n)");
                String rep = sc.nextLine();
                while (rep.equals("o")) {
                    tot += i.Massage();
                    System.out.println("Voulez-vous autre massage ? (o/n)");
                    rep = sc.nextLine();
                }
                System.out.println("Coût total : " + tot + " euros");
            }
        }
    }

    /**
     * Méthode simulant la mise à jours des notes de tous les clients de l'hôtel en y ajoutant le prix journalier
     * de leurs chambres respectives
     */
    public void payrooms() {
        Chambre[] rooms = this.getChambres();
        Client[] c = this.getClient();
        for (int i = 0; i < 40; i++) {
            if (!c[i].getNom().equals("non renseigné")) {
                c[i].setNote(c[i].getNote() + rooms[i].Prix());
                System.out.println("La note de M(me) " + c[i].getNom() + " a été incrémentée de "
                        + rooms[i].Prix() + " pour la location de sa chambre");
            }
        }

    }

    /**
     * Méthode simulant le déroulement d'une journée à l'hôtel
     */
    public void day() {
        this.Reception();
        System.out.println("\n");
        this.payrooms();
        System.out.println("\n");
        this.Spa();
        System.out.println("\n");
        this.Restau();
        System.out.println("\n");
        this.Plongee();
        System.out.println("\n");
        this.Casino();
        System.out.println("\n");
        this.wifi();
        System.out.println("\n");
        Client[] c = this.getClient();
        for (Client i : c) {
            if (!i.getPresence()) {
                i.setAbroad(i.getAbroad() + 1);
            }
        }
    }
}
