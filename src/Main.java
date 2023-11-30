import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Medlem> medlemmer = new ArrayList<>();
    private ArrayList<Medlem> medlemmerSomHarBetalt = new ArrayList<>();
    private ArrayList<Medlem> medlemmerSomErIRestance = new ArrayList<>();
    private ArrayList<Konkurrencesvømmer> konkurrenter = new ArrayList<>();
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
                        int brugervalg1 = menu.brugerensValg();
                        switch (brugervalg1) {
                            case 1:
                                opretMedlem();
                                break;
                            case 2:
                                tjekListe();

                            case 3:
                                kørProgram1 = false;
                        }
                    }
                    break;
                case 2:
                    boolean kørProgram2 = true;
                    Menu trænerMenu = new Menu("** TRÆNER **", "Vælg en mulighed: ", new String[]{
                            "1. cjeicnoew",
                            "2. hallooo",
                            "3. Gå tilbage til hoved-menuen"
                    });

                    while (kørProgram2) {
                        trænerMenu.printMenu();
                        int brugervalg2 = menu.brugerensValg();
                        switch (brugervalg2) {
                            case 1:
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
                        int brugervalg3 = menu.brugerensValg();
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
        System.out.println("Du er nu i gang med at oprette en konkurrence svømmer!");
        String svømmeDisciplinSomOrd = svømmeDisciplinSomOrd();
        System.out.println("Har medlemmet en bedste tid? (ja/nej)");
        String jaEllerNej = scanner.nextLine();
        if (jaEllerNej.equalsIgnoreCase("ja")) {
            System.out.println("Hvad er medlemmets bedste tid?");
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
            // medlemmer.add(konkurrencesvømmer);
            System.out.println("Medlem oprettet: " + konkurrencesvømmer);
            filer.gemFileKonkurrenter(konkurrencesvømmer);
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
                System.out.println("Indtast datoen for kontingentbetalingen: (dd-mm-yyyy)");
                String dato = scanner.nextLine();
                return "Kontingent betalt " + dato;
            }
            else if (nEllerS.equalsIgnoreCase("s")) {
                return "";
            }
            else {
                System.out.println("Ugyldigt bogstav. Prøv igen.");

            }
        }
    }

    public String kontigentRestance(Medlem medlem) {
        Betaling betaling = new Betaling();
        double restance = betaling.udregnPris(medlem);

        String restanceSomString = String.valueOf(restance);
        return "Kontingent er ikke betalt. Restance på: " + restanceSomString;

    }
/*
    public void hvilkenKontingentListe(Medlem nytMedlem) {
        if (nytMedlem.getErKontingentBetalt().isEmpty()) {
            nytMedlem.setErKontingentBetalt(kontigentRestance(nytMedlem));
            medlemmerSomErIRestance.add(nytMedlem);
        }
        else {
            medlemmerSomHarBetalt.add(nytMedlem);
        }
    }

 */




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
        for (int i = 0; i < medlemmerSomHarBetalt.size(); i++) {
            System.out.println(medlemmerSomHarBetalt.get(i));
        }
    }

    public void printListeOverMedlemmerSomErIRestance() {
        for (int i = 0; i < medlemmerSomErIRestance.size(); i++) {
            System.out.println(medlemmerSomErIRestance.get(i));
        }
    }
}