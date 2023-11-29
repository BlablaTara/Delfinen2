import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Medlem> medlemmer = new ArrayList<>();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        boolean kørProgram = true;
        Menu menu = new Menu("**** Menu ****", "Vælg en mulighed:", new String[]{
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
                    Menu formandMenu = new Menu("**** FORMAND ****", "Vælg en mulighed: ", new String[]{
                            "1. Opret medlem",
                            "2. Se medlemmer",
                            "3. bla bla"
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
                        }
                    }
                    break;
                case 2:
                    boolean kørProgram2 = true;
                    Menu trænerMenu = new Menu("**** TRÆNER ****", "Vælg en mulighed: ", new String[]{
                            "1. cjeicnoew",
                            "2. hallooo",
                            "3. tihi"
                    });

                    while (kørProgram2) {
                        trænerMenu.printMenu();
                        int brugervalg2 = menu.brugerensValg();
                        switch (brugervalg2) {
                            case 1:
                            case 2:
                            case 3:
                        }
                    }
                    break;
                case 3:
                    boolean kørProgram3 = true;
                    Menu kasserMenu = new Menu("**** KASSER ****", "Vælg en mulighed: ", new String[]{
                            "1. gutchi gutchi",
                            "2. tara er en mørksej",
                            "3. something"
                    });

                    while (kørProgram3) {
                        kasserMenu.printMenu();
                        int brugervalg3 = menu.brugerensValg();
                        switch (brugervalg3) {
                            case 1:
                            case 2:
                            case 3:
                        }
                    }
                    break;
                default:
                    System.out.println("Dette er ikke en mulighed. Prøv igen :)");

            }
        }
    }

    private void tjekListe() {
        for (int i = 0; i < medlemmer.size(); i++) {
            System.out.println(medlemmer.get(i));
        }

    }

    public void opretMedlem() {
        System.out.println("** OPRET NYT MEDLEM **");
        System.out.println("Indtast fulde navn:");
        String navn = scanner.nextLine();

        System.out.println("Indtast fødselsår: (YYYY)");
        int fødselsår = scanner.nextInt();
        scanner.nextLine(); // Ryd buffer efter at have læst et tal

        String aktivEllerPassivSomOrd = aktivEllerPassivSomOrd(); // Spørg kun én gang
        String motionistEllerKonkurrenceSomOrd = motionistEllerKonkurrenceSomOrd(); // Spørg kun én gang

        Medlem nytMedlem = new Medlem(navn, fødselsår, aktivEllerPassivSomOrd, motionistEllerKonkurrenceSomOrd);
        if (nytMedlem.getMotionistEllerKonkurrence().equalsIgnoreCase("konkurrence")) {
            opretKonkurrenceSvømmer(nytMedlem);
        } else {
            medlemmer.add(nytMedlem);
            System.out.println("Medlem oprettet: " + nytMedlem);
        }
    }

    public void opretKonkurrenceSvømmer(Medlem nytMedlem) {

        System.out.println("Du er nu i gang med at oprette en konkurrence svømmer!");
        System.out.println("Indtast hvilken svømmedisciplin medlemmet skal registreres i: ('c' for Crawl. 'b' for Brystsvømning. 'bf' for Butterfly)");
        String svømmeDisciplin = scanner.nextLine();
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

            Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer(nytMedlem.getNavn(), nytMedlem.getFødselsår(), nytMedlem.getAktivEllerPassiv(), nytMedlem.getMotionistEllerKonkurrence(),
                    svømmeDisciplinSomOrd, bedsteTid, dato);
            medlemmer.add(konkurrencesvømmer);
            System.out.println("Medlem oprettet: " + konkurrencesvømmer);
        }
        else {
            double bedsteTid = 0;
            LocalDate dato = null;
            Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer(nytMedlem.getNavn(), nytMedlem.getFødselsår(), nytMedlem.getAktivEllerPassiv(), nytMedlem.getMotionistEllerKonkurrence(),
                    svømmeDisciplinSomOrd, bedsteTid, dato);
            medlemmer.add(konkurrencesvømmer);
            System.out.println("Medlem oprettet: " + konkurrencesvømmer);
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
}

