import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private ArrayList<Medlem> medlemmer = new ArrayList<>();
    private ArrayList<Medlem> crawlJunior = new ArrayList<>();
    private ArrayList<Medlem> crawlSenior = new ArrayList<>();
    private ArrayList<Medlem> brystsvømningJunior = new ArrayList<>();
    private ArrayList<Medlem> brystsvømningSenior = new ArrayList<>();
    private ArrayList<Medlem> butterflyJunior = new ArrayList<>();
    private ArrayList<Medlem> butterflySenior = new ArrayList<>();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        boolean kørProgram = true;
        Menu menu = new Menu("**** Menu ****", "Vælg en mulighed:\n", new String[]{
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
                    Menu formandMenu = new Menu("**** FORMAND ****", "Vælg en mulighed", new String[]{
                            "1. Opret medlem",
                            "2. bla bla bla",
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
                            case 3:
                        }
                    }
                    break;

                case 2:
                    boolean kørProgram2 = true;
                    Menu trænerMenu = new Menu("**** TRÆNER ****", "Vælg en mulighed:", new String[]{
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
                    Menu kasserMenu = new Menu("**** KASSER ****", "Vælg en mulighed:", new String[]{
                            "1. gutchi gutchi",
                            "2. tara er en sej",
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

    public void opretMedlem() {
        Filer filer = new Filer(); // Skal vi have et filNavn?

        Scanner scanner = new Scanner(System.in);
        System.out.println("** OPRET NYT MEDLEM **");
        System.out.println("Indtast fulde navn:");
        String navn = scanner.nextLine();

        System.out.println("Indtast fødselsår: (YYYY)");
        int fødselsår = scanner.nextInt();
        scanner.nextLine();
        // Medlem medlem = new Medlem();
        // medlem.udregnMedlemsAlder(alder);
        int alder = 2023 - fødselsår;

        System.out.println("Indtast aktivitetsform: (Hvis aktiv tast 'A' / Hvis passiv tast 'P')");
        String aktivEllerPassiv = scanner.nextLine();
        Medlem medlem = new Medlem();
        medlem.erMedlemmetAktivEllerPassiv();

        System.out.println("Indtast aktivitetstype: (Hvis motionist tast 'M' / Hvis konkurrencesvømmer tast 'K')");
        String motionistEllerKonkurrence = scanner.nextLine();

        Medlem nytMedlem = new Medlem(navn, fødselsår, aktivEllerPassiv, motionistEllerKonkurrence);
        Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer();
        if (nytMedlem.getMotionistEllerKonkurrence().equalsIgnoreCase("k")) {
            konkurrencesvømmer.erMedlemKonkurrenceSvømmer(nytMedlem);
            if (nytMedlem.getFødselsår() >= 2005) {
                if (nytMedlem.getSvømmedisciplin().equalsIgnoreCase("crawl")) {
                    crawlJunior.add(nytMedlem);
                }
                if (nytMedlem.getSvømmedisciplin().equalsIgnoreCase("brystsvømning")) {
                    brystsvømningJunior.add(nytMedlem);
                }
                else {
                    butterflyJunior.add(nytMedlem);
                }
            }

            if (nytMedlem.getFødselsår() < 2005) {
                if (nytMedlem.getSvømmedisciplin().equalsIgnoreCase("crawl")) {
                    crawlSenior.add(nytMedlem);
                }
                if (nytMedlem.getSvømmedisciplin().equalsIgnoreCase("brystsvømning")) {
                    brystsvømningSenior.add(nytMedlem);
                }
                else {
                    butterflySenior.add(nytMedlem);
                }
            }
            filer.gemFile(medlemmer);

            System.out.println("Medlem oprettet.");
            System.out.println("Medlem: " + nytMedlem.getAktivEllerPassiv() + " | " + nytMedlem.getNavn() + " | " + alder  + " \n "
                    + nytMedlem.getMotionistEllerKonkurrence() + nytMedlem.getSvømmedisciplin() + nytMedlem.getJuniorEllerSenior() + "\n"
                    + nytMedlem.getStævne() + nytMedlem.getDato() + nytMedlem.getBedsteTid() + nytMedlem.getPlacering());
        }
    }
}

