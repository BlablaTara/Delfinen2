import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    Træner crawlTræner; // NY LINJE
    Træner brystsvømningTræner; // NY LINJE
    Træner butterflyTræner; // NY LINJE
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Medlem> medlemmer = new ArrayList<>();
    private ArrayList<Medlem> medlemmerSomHarBetalt = new ArrayList<>();
    private ArrayList<Medlem> medlemmerSomErIRestance = new ArrayList<>();
    private ArrayList<Konkurrencesvømmer> konkurrenter = new ArrayList<>();
    private ArrayList<Stævne> stævner = new ArrayList<>();
    Filer filer = new Filer();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        boolean kørProgram = true;
        Menu menu = new Menu("** Menu **", "Vælg en mulighed:", new String[]{
                "1. Formand",
                "2. Træner",
                "3. Kassér"
        });

        while (kørProgram) {
            menu.printMenu();
            int brugervalg = menu.brugerensValg();

            switch (brugervalg) {
                case 1:
                    boolean kørProgram1 = true;
                    Menu formandMenu = new Menu("** FORMAND **", "Vælg en mulighed: ", new String[]{
                            "1. Opret medlem",
                            "2. Se medlemmer",
                            "3. Gå tilbage til hoved-menuen"
                    });

                    while (kørProgram1) {
                        formandMenu.printMenu();
                        int brugervalg1 = formandMenu.brugerensValg();
                        switch (brugervalg1) {
                            case 1:
                                opretMedlem();
                                break;
                            case 2:
                                tjekListe();
                                break;
                            case 3:
                                kørProgram1 = false;
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean kørProgram2 = true;
                    Menu trænerMenu = new Menu("** TRÆNER **", "Vælg en mulighed: ", new String[]{
                            "1. Opret en konkurrencesvømmers stævne",
                            "2. hallooo",
                            "3. Gå tilbage til hoved-menuen"
                    });

                    while (kørProgram2) {
                        trænerMenu.printMenu();
                        int brugervalg2 = trænerMenu.brugerensValg();
                        switch (brugervalg2) {
                            case 1:
                                opretStævneTilEnKonkurrencesvømmer();
                                break;
                            case 2:
                            case 3:
                                kørProgram2 = false;
                        }
                    }
                    break;
                case 3:
                    boolean kørProgram3 = true;
                    Menu kasserMenu = new Menu("** KASSER **", "Vælg en mulighed: ", new String[]{
                            "1. Oversigt over medlemmer som har betalt kontingent",
                            "2. Oversigt over medlemmer som er i restance.",
                            "3. Gå tilbage til hoved-menuen"
                    });

                    while (kørProgram3) {
                        kasserMenu.printMenu();
                        int brugervalg3 = kasserMenu.brugerensValg();
                        switch (brugervalg3) {
                            case 1:
                                printListeOverMedlemmerSomHarBetaltKontingent();
                            case 2:
                                printListeOverMedlemmerSomErIRestance();
                            case 3:
                                kørProgram3 = false;
                        }
                    }
                    break;
                default:
                    System.out.println("Dette er ikke en mulighed. Prøv igen 🙂");

            }
        }
    }

    private void tjekListe() {
        for (int i = 0; i < medlemmer.size(); i++) {
            System.out.println(medlemmer.get(i));
        }
    }

    public void hvilkenTrænerSkalMedlemmetHave(Konkurrencesvømmer medlem) { // NY METODE
        if (medlem.getSvømmedisciplin().equalsIgnoreCase("crawl")) {
            medlem.setTræner(crawlTræner);
        }
        if (medlem.getSvømmedisciplin().equalsIgnoreCase("brystsvømning")) {
            medlem.setTræner(brystsvømningTræner);
        }
        if (medlem.getSvømmedisciplin().equalsIgnoreCase("butterfly")) {
            medlem.setTræner(butterflyTræner);
        }
    }

    public void opretTrænere() { // NY MEOTDE
        crawlTræner = new Træner("Jamie");
        brystsvømningTræner = new Træner("The Rock");
        butterflyTræner = new Træner("David Hasselhoff");
    }

    public void opretStævneTilEnKonkurrencesvømmer() {
        System.out.println("* OPRET STÆVNE *");
        System.out.println("Indtast det medlem der har været til stævne's fulde navn: ");
        String fuldeNavn = scanner.nextLine();

        Konkurrencesvømmer konkurrencesvømmer = findMedlemUdFraFuldtNavn(fuldeNavn);

        if (konkurrencesvømmer == null) {
            System.out.println("Konkurrenten med navnet " + fuldeNavn + " kan ikke findes blandt medlemmer.");
            return;
        }

        System.out.println("Indtast stævne-navn: ");
        String stævneNavn = scanner.nextLine();

        System.out.println("Indtast tid: (mm,ss)");
        double tid = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Indtast din placering: ");
        int placering = scanner.nextInt();
        scanner.nextLine();

        Stævne nytStævne = new Stævne(stævneNavn, tid, placering);
        konkurrencesvømmer.tilføjStævne(nytStævne);

        System.out.println("Stævne tilføjet til medlemmet: " + fuldeNavn + "\n" + nytStævne);
        // TODO lav fil som gemmer stævne
    }

    private Konkurrencesvømmer findMedlemUdFraFuldtNavn(String fuldeNavn) {
        for (Medlem medlem : medlemmer) {
            System.out.println("Sammenligner:" + medlem.getNavn() + " med " + fuldeNavn);
            if (medlem instanceof Konkurrencesvømmer && medlem.getNavn().equalsIgnoreCase(fuldeNavn)) {
                System.out.println("Medlem fundet!");
                return (Konkurrencesvømmer) medlem;
            }
        }
        System.out.println("Medlem ikke fundet.");
        return null;
    }

    public void opretMedlem() {
        System.out.println("* OPRET NYT MEDLEM *");
        System.out.println("Indtast fulde navn:");
        String navn = scanner.nextLine();

        System.out.println("Indtast fødselsår: (YYYY)");
        int fødselsår = scanner.nextInt();
        scanner.nextLine(); // Ryd buffer efter at have læst et tal

        String aktivEllerPassivSomOrd = aktivEllerPassivSomOrd(); // Spørg kun én gang
        String erKontingentBetaltSomOrd = erKontingentBetaltSomOrd();
        String motionistEllerKonkurrenceSomOrd = motionistEllerKonkurrenceSomOrd(); // Spørg kun én gang

        Medlem nytMedlem = new Medlem(navn, fødselsår, aktivEllerPassivSomOrd, erKontingentBetaltSomOrd, motionistEllerKonkurrenceSomOrd);
        // nytMedlem.setErKontingentBetalt(erKontingentBetaltSomOrd(nytMedlem)); // NY LINJE
        // hvilkenKontingentListe(nytMedlem);

        if (nytMedlem.getMotionistEllerKonkurrence().equalsIgnoreCase("konkurrence")) {
            opretKonkurrenceSvømmer(nytMedlem);
        } else {
            medlemmer.add(nytMedlem);
            System.out.println("Medlem oprettet: " + nytMedlem);
            filer.gemFileMotionist(nytMedlem);
        }
    }

    public void opretKonkurrenceSvømmer(Medlem nytMedlem) {
        opretTrænere();
        System.out.println("Du er nu i gang med at oprette en konkurrence svømmer!");
        String svømmeDisciplinSomOrd = svømmeDisciplinSomOrd();
        System.out.println("Har medlemmet en bedste tid? (ja/nej)");
        String jaEllerNej = scanner.nextLine();
        if (jaEllerNej.equalsIgnoreCase("ja")) {
            System.out.println("Hvad er medlemmets bedste tid? (mm,ss)");
            double bedsteTid = scanner.nextDouble();
            scanner.nextLine(); // Ryd scannerens buffer
            System.out.println("Hvilken dato havde medlemmet sin bedste tid? (format: dd-MM-yyyy)");
            String datoStr = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dato = null;
            try {
                dato = LocalDate.parse(datoStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldigt datoformat. Prøv igen.");
                opretKonkurrenceSvømmer(nytMedlem);
            }

            Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer(nytMedlem.getNavn(), nytMedlem.getFødselsår(), nytMedlem.getAktivEllerPassiv(),
                    nytMedlem.getErKontingentBetalt(), nytMedlem.getMotionistEllerKonkurrence(),
                    svømmeDisciplinSomOrd, bedsteTid, dato);
            hvilkenTrænerSkalMedlemmetHave(konkurrencesvømmer); // NY LINJE
            medlemmer.add(konkurrencesvømmer);
            System.out.println("Medlem oprettet: " + konkurrencesvømmer);
            filer.gemFileKonkurrenter(konkurrencesvømmer);
        }
        else {
            double bedsteTid = 0;
            LocalDate dato = null;
            Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer(nytMedlem.getNavn(), nytMedlem.getFødselsår(), nytMedlem.getAktivEllerPassiv(), nytMedlem.getErKontingentBetalt(),
                    nytMedlem.getMotionistEllerKonkurrence(),
                    svømmeDisciplinSomOrd, bedsteTid, dato);
            hvilkenTrænerSkalMedlemmetHave(konkurrencesvømmer); // NY LINJE
            // medlemmer.add(konkurrencesvømmer);
            System.out.println("Medlem oprettet: " + konkurrencesvømmer);
            filer.gemFileKonkurrenter(konkurrencesvømmer);
            medlemmer.add(konkurrencesvømmer); // DET HAR EMILIA SAT IND - SKAL LIGE SE OM DET VIRKER, VI KAN SNAKKE OM OM DET SKAL BLIVE ELLER EJ
        }


    }
    public String aktivEllerPassivSomOrd() {
        while (true) {
            System.out.println("Indtast aktivitetsform: (Hvis aktiv tast 'A' / Hvis passiv tast 'P')");
            String aEllerP = scanner.nextLine();

            if (aEllerP.equalsIgnoreCase("a")) {
                return "Aktiv";
            } else if (aEllerP.equalsIgnoreCase("p")) {
                return "Passiv";
            } else {
                System.out.println("Ugyldigt bogstav. Prøv igen.");
            }
        }
    }

    public String erKontingentBetaltSomOrd() {
        while (true) {
            System.out.println("Vil medlemmet betale nu eller senere? Tast 'n' for nu. Tast 's' for senere.");
            String nEllerS = scanner.nextLine();
            if (nEllerS.equalsIgnoreCase("n")) {
                LocalDate dato = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String formattedDato = dato.format(formatter);
                return "Kontingent betalt " + formattedDato; //TODO Beløb på så vi kan se pris
            }
            else if (nEllerS.equalsIgnoreCase("s")) {
                return "RESTANCE";
            }
            else {
                System.out.println("Ugyldigt bogstav. Prøv igen.");
            }
        }
    }

    public String erKontingentBetaltSomOrd(Medlem medlem) {
        if (medlem.getErKontingentBetalt().equalsIgnoreCase("Kontingent betalt")) {
            LocalDate dato = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDato = dato.format(formatter);
            Betaling betaling = new Betaling();
            double medlemsPris = betaling.udregnPris(medlem);
            String medlemsPrisSomString = String.valueOf(medlemsPris);
            return "Medlemskab på: " +medlemsPrisSomString +"kr. er betalt" + formattedDato;
        }
        return "";
    }

    public String kontigentRestance(Medlem medlem) {
        Betaling betaling = new Betaling();
        double restance = betaling.udregnPris(medlem);

        String restanceSomString = String.valueOf(restance);
        return "Restance på: " + restanceSomString; //TODO Beløb på så vi kan se pris
    }


    public String motionistEllerKonkurrenceSomOrd() {
        while (true) {
            System.out.println("Indtast aktivitetstype: (Hvis motionist tast 'M' / Hvis konkurrencesvømmer tast 'K')");
            String mEllerK = scanner.nextLine();

            if (mEllerK.equalsIgnoreCase("m")) {
                return "Motionist";
            } else if (mEllerK.equalsIgnoreCase("k")) {
                return "Konkurrence";
            } else {
                System.out.println("Ugyldigt bogstav. Prøv igen.");
            }
        }
    }


    public String svømmeDisciplinSomOrd() {
        while (true) {
            System.out.println("Indtast hvilken svømmedisciplin medlemmet skal registreres i: ('c' for Crawl. 'b' for Brystsvømning. 'bf' for Butterfly)");
            String bogstav = scanner.nextLine();

            if (bogstav.equalsIgnoreCase("c")) {
                return "Crawl";
            } else if (bogstav.equalsIgnoreCase("b")) {
                return "Brystsvømning";
            } else if (bogstav.equalsIgnoreCase("bf")) {
                return "Butterfly";
            } else {
                System.out.println("Ugyldigt bogstav. Prøv igen.");
            }
        }
    }

    public void printListeOverMedlemmerSomHarBetaltKontingent() {
        filer.læsFileMotionisterBetalt();
        filer.læsFileKonkurrenterBetalt();
    }

    public void printListeOverMedlemmerSomErIRestance() {
        filer.læsFileMotionisterIRestance();
        filer.læsFileKonkurrenterIRestance();
    }
}