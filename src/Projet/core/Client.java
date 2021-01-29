/*
 * Cette classe appartient au package core qui constitue le coeur du projet
 * et fait l'usage de différentes options d'où les imports
 */

package Projet.core;

import Projet.options.ConnexionInternet;
import Projet.options.Plongee;
import Projet.options.Spa;

import java.util.Scanner;

/**
 * Classe servant à représenter l'existence  d'un client en lui associant tous les paramètres nécéssaires
 * à une gestion optimale de son passage à l'hôtel
 *
 * @author MalteseDenis
 */
public class Client {
    //*************************************************************************************************************
    //FIELDS
    //*************************************************************************************************************
    /**
     * nom     le nom de famille du client
     * prenom le prénom du client
     * dateNaissance     la date de naissance du client
     * email      l'adresse e-mail du client
     * telephone     le numéro de téléphone du client
     * duree      la durée du séjour du client
     * note      la note de frais du client
     * tickets   le nombre de tickets à disposition du client
     * gains     les gains obtenus par le client au casino
     * jetons    le nombre de jetons à disposition du client
     * presence  la présence(ou non) du client dans l'hôtel, initialisée à true ca l'on crée le client à son arrivée
     * à l'hôtel
     * abroad    le nombre de jours consécutifs que le client a passé hors de l'hôtel, utile pour la gestion de la croisière
     */
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String email;
    private String telephone;
    private int duree;
    private double note;
    private int tickets;
    private int gains;
    private int jetons;
    private boolean presence = true;
    private int abroad = 0;
    //**************************************************************************************************************
    //CONSTRUCTORS
    //**************************************************************************************************************

    /**
     * @param name      le nom de famille du client
     * @param firstName le prénom du client
     * @param birth     la date de naissance du client
     * @param mail      l'adresse e-mail du client
     * @param phone     le numéro de téléphone du client
     * @param ind       l'indice téléphonique du client
     * @param time      la durée du séjour du client
     *                  On effectue des tests de validité sur différentes informations afin que le client ne renseigne pas n'importe
     *                  quoi ex:être né en l'100
     */
    //test validite naissance, email, tel
    public Client(String name, String firstName, String birth, String mail, String phone, String ind, int time) {
        nom = name;
        prenom = firstName;
        note = 0.0;
        gains = 0;
        jetons = 20;

        if (time >= 0) {
            duree = time;
        } else {
            System.out.println("Durée renseignée incorrecte");
            duree = 0;
        }
        if (birth.matches("[0-3]+[0-9]+/[0 1]+[0-9]+/[1 2]+[0 9]+[0-9]+[0-9]+")) {
            dateNaissance = birth;
        } else {
            System.out.println("Date de naissance invalide M(Mme): " + this.nom);
        }

        if ((mail.indexOf('.', mail.indexOf("@")) != -1) && (mail.contains("@"))) {
            email = mail;
        } else {
            System.out.println("Adresse e-mail invalide M(Mme) : " + this.nom);
        }

        if ((phone.length() == 10) && (phone.matches("[0-9]*"))) {
            if (ind.matches("\\+[0-9]+[0-9]+")) {
                telephone = ind + phone.substring(1);
            } else {
                telephone = phone;
            }
        } else {
            System.out.println("Numéro de téléphone invalide M(Mme) : " + this.nom);
        }
        tickets = duree;
    }

    /**
     * Constructeur par défaut utilisé lors de la création de l'hôtel vide ou lorsque l'on vide une chambre
     * On met des valeurs sans significations car one ne les utilisera pas
     */

    public Client() {
        this("non renseigné", "renseigné", "01/01/1000", "non@f.df", "0000000000", "", 0);
    }
    //*****************************************************************************************************************
    //PUBLIC METHODS
    //*****************************************************************************************************************
    //GETTERS

    /**
     * Getter d'abroad
     *
     * @return le nombre de jour @b abroad que le client a passé succesivement hors de l'hôtel au moment de l'appel de la
     * fonction
     */
    public int getAbroad() {
        return abroad;
    }

    /**
     * Cet attribut est intimement lié à la croisière donc la durée passée hors de l'hôtel ne peu pas excéder 10
     * jours et lorsqu'elle atteint cette marque le client est de retour donc est de nouveau présent
     * Met à jour la durée passée hors de l'hôtel
     *
     * @param abroad la nouvelle @b durée
     */
    public void setAbroad(int abroad) {
        if (abroad > 9) {
            this.abroad = 0;
            this.setPresence(true);
        } else {
            this.abroad = abroad;
        }
    }

    /**
     * Getter de la présence
     *
     * @return un booléen disant si le client est présent ou non à l'hôtel
     */
    public boolean getPresence() {
        return presence;
    }

    /**
     * Met à jour la présence du client
     *
     * @param presence la @b présence ou non du client après le moment du changement
     */
    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    /**
     * Récupère les gains totaux du client au casino
     *
     * @return les @b gains du client à la roulette
     */
    public int getGains() {
        return gains;
    }

    /**
     * Met à jour les gains totaux du client
     *
     * @param gains les @b gains du client au moment de l'appel
     */
    public void setGains(int gains) {
        this.gains = gains;
    }

    /**
     * Récupère le nombre de jetons en possession du client
     *
     * @return le nombre de @b jetons du client
     */
    public int getJetons() {
        return jetons;
    }

    /**
     * Met à jour le nombre de jetons du client
     *
     * @param jetons le nouveau nombre de @b jetons du client
     */
    public void setJetons(int jetons) {
        this.jetons = jetons;
    }

    /**
     * Récupère le temps de séjour restant du client
     *
     * @return la @b durée restante sur la réservation à l'hôtel
     */
    public int getDuree() {
        return duree;
    }

    /**
     * Met à jour la durée de séjour restante
     *
     * @param duree la @b durée restante au moment de l'appel
     */
    public void setDuree(int duree) {
        this.duree = Math.max(duree, 0);
    }

    /**
     * Récupère le nom
     *
     * @return le @b nom du client
     */
    public String getNom() {
        return this.nom;
    }
    //SETTERS

    /**
     * Récupère la date de naissance du client
     *
     * @return la @b date de naissance
     */
    public String getNaissance() {
        return this.dateNaissance;
    }

    /**
     * Récupère l'email
     *
     * @return l'adresse @b email du client
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Récupère le numéro de téléphone du client
     *
     * @return le numéro de @b téléphone du client
     */
    public String getTel() {
        return this.telephone;
    }

    /**
     * Récupère le nombre de tickets du client
     *
     * @return les @b tickets en possesion du client
     */
    public int getTickets() {
        return tickets;
    }

    /**
     * Récupère la note du client
     *
     * @return la @b note du client
     */
    public double getNote() {
        return note;
    }

    /**
     * Met à jour la note de frais du client
     *
     * @param note la nouvelle @b note du client après augmentation/réduction
     */
    public void setNote(double note) {
        this.note = note;
    }

    /**
     * On utilise les tickets uniquement un à la fois
     */
    public void setTickets() {
        this.tickets = tickets - 1;
    }
    //OTHERS

    /**
     * Affiche les informations du client
     */
    public void affichage() {
        System.out.println("Nom et prénom du client : " + this.nom + " " + this.prenom + "\n e-mail : " + this.email + "\nTélephone : " + this.telephone + "" +
                "Durée du séjour : " + this.duree);
    }

    /**
     * Simule une partie de roulette avec interaction du client qui prend le rôle du joueur
     */
    public void partie() {
        Casino cas = new Casino();
        cas.create();
        cas.setGainpl(this.getGains());
        cas.setNbj(this.getJetons());
        System.out.println("Nous vous proposons de jouer à la roulette cela vous intéresse t-il?(o/n)");
        Scanner sc = new Scanner(System.in);
        String rep = sc.nextLine();
        while (rep.equals("o")) {
            cas.tour();
            System.out.println("Voulez  vous rejouer?(o/n)");
            rep = sc.nextLine();
        }
        System.out.println("Gains totaux" + cas.getGainpl());
        System.out.println("Jetons" + cas.getNbj());
        this.setGains(cas.getGainpl());
        this.setJetons(cas.getNbj());
    }

    /**
     * SImulation de la commande de baptêmes de plongée et de croisières
     */
    public void plong() {
        Scanner sc = new Scanner(System.in);
        int l;
        Plongee def = new Plongee();
        def.affichageTarifs();
        System.out.println("Combien de baptêmes souhaitez vous?");
        int n = sc.nextInt();
        if (this.getDuree() < 10) {
            System.out.println("Votre temps de séjour n'est pas assez long pour participer à la croisière");
            l = 0;
        } else {
            if (this.getDuree() == 10) {
                System.out.println("Attention!Il ne vous reste plus que 10 jours de séjour, c'est votre dernière chance de " +
                        "profiter de la croisière.");
            }
            System.out.println("Combien de croisières voulez vous effectuer? Si vous en choisissez vous partirez sur le champ");
            l = sc.nextInt();
            if (l > 0) {
                this.setPresence(false);
            }
        }
        Plongee p = new Plongee(n, l);
        //Rajouter tableau de présence à l'hôtel
        this.setNote(this.getNote() + p.total());

        System.out.println("Votre note de frais a bien été mise à jour");
    }

    /**
     * SImulation de la mise à jour de la note de frais du client pour l'utilisation journalière de la connexion internet
     */
    public void CoInt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Combien d'heures avez vous passé sur internet aujourd'hui?(nombre d'heures en entier)");
        int t = sc.nextInt();
        ConnexionInternet co = new ConnexionInternet(t);
        System.out.println("La conexion internet est facturée 10euros de l'heure.Votre note de frais a été mise à jour.");
        double p = co.getPrix();
        this.setNote(this.getNote() + p);
    }

    /**
     * Simulation d'une commande au restaurant
     */
    public void commandeRestau() {
        boolean entree;
        boolean plat;
        boolean accompagnement;
        boolean dessert;
        boolean boisson;
        double prix;
        double note = this.getNote();
        Commande com = new Commande();
        Scanner sc = new Scanner(System.in);
        System.out.println("Voulez-vous une formule ou une commande à la carte ? (formule/carte) ");
        String rep = sc.nextLine();
        if (rep.equals("formule")) {
            System.out.println("Quelle formule avez-vous choisi ? (mojito/daiquiri)");
            String formule = sc.nextLine();
            if (formule.equals("mojito")) {
                prix = com.commandeFormule(formule);
                System.out.println("Voulez-vous autre chose ? (o/n) ");
                String rep2 = sc.nextLine();
                if (rep2.equals("o")) {
                    System.out.println("Voulez-vous ajouter une entrée ? (o/n)");
                    String rep21 = sc.nextLine();
                    System.out.println("Voulez-vous ajouter un accompagnement ? (o/n)");
                    String rep22 = sc.nextLine();
                    System.out.println("Voulez-vous ajouter un desert ? (o/n)");
                    String rep23 = sc.nextLine();
                    entree = rep21.equals("o");
                    accompagnement = rep22.equals("o");
                    dessert = rep23.equals("o");
                    prix += com.addMojito(entree, accompagnement, dessert);
                    this.setNote(note + prix);
                    com.afficheCommande();
                } else {
                    com.afficheCommande();
                    this.setNote(note + prix);
                }
            } else {
                prix = com.commandeFormule(formule);
                System.out.println("Voulez-vous autre chose ? (o/n) ");
                String rep3 = sc.nextLine();
                if (rep3.equals("o")) {
                    System.out.println("Voulez-vous ajouter une entrée ? (o/n)");
                    String rep31 = sc.nextLine();
                    System.out.println("Voulez-vous ajouter un dessert ? (o/n)");
                    String rep32 = sc.nextLine();
                    entree = rep31.equals("o");
                    dessert = rep32.equals("o");
                    prix += com.addDaiquiri(entree, dessert);
                    this.setNote(note + prix);
                    com.afficheCommande();
                } else {
                    com.afficheCommande();
                    this.setNote(note + prix);
                }
            }
        } else if (rep.equals("carte")) {
            System.out.println("Voulez-vous une entrée ? (o/n)");
            String rep41 = sc.nextLine();
            System.out.println("Voulez-vous un plat ? (o/n)");
            String rep42 = sc.nextLine();
            System.out.println("Voulez-vous un accompagnement ? (o/n)");
            String rep43 = sc.nextLine();
            System.out.println("Voulez-vous un dessert ? (o/n)");
            String rep44 = sc.nextLine();
            System.out.println("Voulez-vous une boisson ? (o/n)");
            String rep45 = sc.nextLine();
            entree = rep41.equals("o");
            plat = rep42.equals("o");
            accompagnement = rep43.equals("o");
            dessert = rep44.equals("o");
            boisson = rep45.equals("o");
            prix = com.commandeCarte(entree, plat, accompagnement, dessert, boisson);
        } else {
            System.out.println("Nous n'avons pas compris votre commande. Veuillez recommencer s'il vous plait.");
            return;
        }

        System.out.println("Voulez-vous utiliser des tickets restaurants ? (o/n)");
        String re = sc.nextLine();
        if (re.equals("o") && this.getTickets() > 0) {
            com.useTicket();
            this.setNote(note + prix);
            this.setTickets();
            System.out.println("Votre note a été mise à jour. Votre nombre de tickets aussi. Il vous reste " + this.getTickets() + " tickets.");
        } else {
            this.setNote(note + prix);
        }
        com.afficheCommande();

    }

    /**
     * Simulation de la commande de différents types de massage
     */
    public double Massage() {
        Spa spa = new Spa();
        Scanner sc = new Scanner(System.in);
        spa.afficheTarifs();
        System.out.println("Quel type de massage voulez-vous ? (relaxant/pierres/elixir) ");
        String type = sc.nextLine();
        double prix = spa.Prix(type);
        spa.affichePrix();
        this.setNote(this.getNote() + prix);
        return prix;
    }
}
