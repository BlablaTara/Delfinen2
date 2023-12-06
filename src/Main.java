import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class Main {
    Konkurrencesvømmer konkurrencesvømmer;
    Medlem nytMedlem;
    Træner crawlTræner; // NY LINJE
    Træner brystsvømningTræner; // NY LINJE
    Træner butterflyTræner; // NY LINJE
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Medlem> medlemmer = new ArrayList<>();
    private ArrayList<Medlem> motionistMedlemmer = new ArrayList<>();
    private ArrayList<Medlem> konkurrenceMedlemmer = new ArrayList<>();
    private ArrayList<Medlem> medlemmerSomHarBetalt = new ArrayList<>();
    private ArrayList<Medlem> medlemmerSomErIRestance = new ArrayList<>();
    private ArrayList<Konkurrencesvømmer> konkurrenter = new ArrayList<>();
    private ArrayList<Stævne> stævner = new ArrayList<>();
    Filer filer = new Filer();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        motionistMedlemmer = filer.indlæsMotionisterFraFil("MotionistSvømmere.txt");
        konkurrenceMedlemmer = filer.indlæsKonkurrenceMedlemmerFraFil("KonkurrenceSvømmere.txt");
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
                                seMedlemmer();
                                break;
                            case 3:
                                kørProgram1 = false;
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean kørProgram2 = true;
                    Menu trænerMenu = new Menu("* TRÆNER *", "Vælg en mulighed: ", new String[]{
                            "1. Opret en konkurrencesvømmers stævne",
                            "2. Se fem bedste svømmere",
                            "3. Opdater konkurrencesvømmers bedste tid",
                            "4. Gå tilbage til hoved-menuen"
                    });

                    while (kørProgram2) {
                        trænerMenu.printMenu();
                        int brugervalg2 = trænerMenu.brugerensValg();
                        switch (brugervalg2) {
                            case 1:
                                opretStævneTilEnKonkurrencesvømmer();
                                break;
                            case 2:
                                seOgSorterKonkurrencesvømmere();
                                break;

                            case 3:
                                System.out.println("Indtast svømmerens fulde navn: "); // ny mulighed
                                String fullName = scanner.nextLine();
                                opdaterSvømmersBedsteTid(fullName);
                                break;

                            case 4:
                                kørProgram2 = false;
                                break;
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
                                // printListeOverMedlemmerSomHarBetaltKontingent();
                                break;
                            case 2:
                                seMedlemmerRestance();
                                break;
                            case 3:
                                kørProgram3 = false;
                                break;
                        }
                    }
                    break;
                default:
                    System.out.println("Dette er ikke en mulighed. Prøv igen 🙂");

            }
        }
    }


    private void seOgSorterKonkurrencesvømmere() {
        List<Konkurrencesvømmer> crawlJuniorSvømmere = new ArrayList<>();
        List<Konkurrencesvømmer> crawlSeniorSvømmere = new ArrayList<>();
        List<Konkurrencesvømmer> brystJuniorSvømmere = new ArrayList<>();
        List<Konkurrencesvømmer> brystSeniorSvømmere = new ArrayList<>();
        List<Konkurrencesvømmer> butterFlyJuniorSvømmere = new ArrayList<>();
        List<Konkurrencesvømmer> butterFlySeniorSvømmere = new ArrayList<>();

        for (Medlem medlem : konkurrenceMedlemmer) {
            if (medlem instanceof Konkurrencesvømmer) {
                Konkurrencesvømmer svømmer = ((Konkurrencesvømmer) medlem);
                if ("Crawl".equalsIgnoreCase(svømmer.getSvømmedisciplin())) {
                    if (svømmer.erJunior()) {
                        crawlJuniorSvømmere.add(svømmer);
                    } else {
                        crawlSeniorSvømmere.add(svømmer);
                    }
                } else if ("BrystSvømning".equalsIgnoreCase(svømmer.getSvømmedisciplin())) {
                    if (svømmer.erJunior()) {
                        brystJuniorSvømmere.add(svømmer);
                    } else {
                        brystSeniorSvømmere.add(svømmer);
                    }
                } else if ("Butterfly".equalsIgnoreCase(svømmer.getSvømmedisciplin())) {
                    if (svømmer.erJunior()) {
                        butterFlyJuniorSvømmere.add(svømmer);
                    } else {
                        butterFlySeniorSvømmere.add(svømmer);

                    }
                }
            }
        }
        Collections.sort(crawlJuniorSvømmere, new KonkurrencesvømmerBedsteTidComparator());
        Collections.sort(crawlSeniorSvømmere, new KonkurrencesvømmerBedsteTidComparator());
        Collections.sort(brystJuniorSvømmere, new KonkurrencesvømmerBedsteTidComparator());
        Collections.sort(brystSeniorSvømmere, new KonkurrencesvømmerBedsteTidComparator());
        Collections.sort(butterFlyJuniorSvømmere, new KonkurrencesvømmerBedsteTidComparator());
        Collections.sort(butterFlySeniorSvømmere, new KonkurrencesvømmerBedsteTidComparator());

        System.out.println("\nTop 5 i Crawl(Junior):");
        visTop5(crawlJuniorSvømmere);
        System.out.println("\nTop 5 i Crawl (Senior)");
        visTop5(crawlSeniorSvømmere);
        System.out.println("\nTop 5 i Brystsvømning(Junior");
        visTop5(brystJuniorSvømmere);
        System.out.println("\nTop 5 i Brystsvømning(Senior)");
        visTop5(brystSeniorSvømmere);
        System.out.println("\nTop 5 i Butterfly(Junior)");
        visTop5(butterFlyJuniorSvømmere);
        System.out.println("\nTop 5 i Butterfly(Senior");
        visTop5(butterFlySeniorSvømmere);
    }

    private void visTop5(List<Konkurrencesvømmer> svømmere) {
        int topN = Math.min(5, svømmere.size());
        List<Konkurrencesvømmer> top5Svømmere = svømmere.subList(0, topN);

        for (Konkurrencesvømmer svømmer : top5Svømmere) {
            System.out.println(svømmer.getNavn() + "-" + svømmer.getSvømmedisciplin() + "-Tid:" + svømmer.getBedsteTid()
                    + "-Status:" + svømmer.erJunior());
        }
    }


    public void hvilkenTrænerSkalMedlemmetHave(Konkurrencesvømmer medlem) {
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

    public void opretTrænere() {
        crawlTræner = new Træner("Jamie");
        brystsvømningTræner = new Træner("The Rock");
        butterflyTræner = new Træner("David Hasselhoff");
    }

    public void opretStævneTilEnKonkurrencesvømmer() { // NYE LINJER HER
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

        double tid = 0;
        boolean validtInput = false;

        do {
            try {
                System.out.println("Indtast tid: (mm,ss)");
                tid = indtastGyldigTid();
                validtInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Ugyldigt svar. Prøv igen.");
            }
        } while (!validtInput);

        int placering = 0;

        do {
            validtInput = false;
            try {
                System.out.println("Indtast din placering: ");
                placering = scanner.nextInt();
                scanner.nextLine();
                validtInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Ugyldigt svar. Prøv igen.");
                scanner.nextLine();
            }
        } while (!validtInput);

        Stævne nytStævne = new Stævne(stævneNavn, tid, placering);
        konkurrencesvømmer.tilføjStævne(nytStævne);

        System.out.println("Stævne tilføjet til medlemmet: " + fuldeNavn + "\n" + nytStævne);
        // filer.gemFileKonkurrenterMedStævne(konkurrencesvømmer);
        // filer.fjernKonkurrentFraFil(konkurrencesvømmer);
    }

    private Konkurrencesvømmer findMedlemUdFraFuldtNavn(String fuldeNavn) {
        for (Medlem medlem : medlemmer) {
            if (medlem instanceof Konkurrencesvømmer && medlem.getNavn().equalsIgnoreCase(fuldeNavn)) {
                System.out.println("Medlem fundet!");
                return (Konkurrencesvømmer) medlem;
            }
        }
        System.out.println("Medlem ikke fundet.");
        return null;
    }

    public void opdaterSvømmersBedsteTid(String fuldeNavn) { // LIGE SAT IND
        boolean blevMedlemFundet = false;
        for (int i = 0; i < konkurrenceMedlemmer.size(); i++) {
            if (konkurrenceMedlemmer.get(i) instanceof Konkurrencesvømmer && konkurrenceMedlemmer.get(i).getNavn().equalsIgnoreCase(fuldeNavn)) {
                System.out.println("Medlem fundet!");
                System.out.println("Indtast " + fuldeNavn + "'s nye tid: ");
                double nyTid = scanner.nextDouble();
                scanner.nextLine();
                if (nyTid < ((Konkurrencesvømmer) konkurrenceMedlemmer.get(i)).getBedsteTid() || ((Konkurrencesvømmer) konkurrenceMedlemmer.get(i)).getBedsteTid() == 0) {
                    ((Konkurrencesvømmer) konkurrenceMedlemmer.get(i)).setBedsteTid(nyTid);
                    filer.gemKonkurrenceTilFil(konkurrenceMedlemmer, "KonkurrenceSvømmere.txt");
                } else {
                    System.out.println(fuldeNavn + "'s gamle tid var hurtigere end " + nyTid);
                }
                System.out.println(konkurrenceMedlemmer.get(i));
                blevMedlemFundet = true;
            }
        }

        if (!blevMedlemFundet) {
            System.out.println(fuldeNavn + " blev ikke fundet");
        }

    }


    /*
    public Konkurrencesvømmer læsKonkurrencesvømmersFuldeNavnFraFil(String fuldeNavn) {
        File file = new File("konkurrenter.txt");
        try {
            Scanner inFile = new Scanner(file);

            while (inFile.hasNextLine()){
                String s = inFile.nextLine();
                if (s.contains(fuldeNavn)) {
                    System.out.println("Medlem fundet*: \n" + s);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Medlem ikke fundet*");
        return null;
    }

     */

    public void opretMedlem() { // NYE LINJER HER
        System.out.println("* OPRET NYT MEDLEM *");
        System.out.println("Indtast fulde navn:");
        String navn = scanner.nextLine();

        int fødselsår = 0;
        boolean gyldigtFødselsår = false;

        while (!gyldigtFødselsår) { // NY while-løkke, for at finde ud af om fødselsåret er validt!
            try {
                System.out.println("Indtast fødselsår: (YYYY)");
                fødselsår = scanner.nextInt();
                scanner.nextLine();

                if (erFødselsåretValidt(fødselsår)) {
                    gyldigtFødselsår = true;
                } else {
                    System.out.println("Ugyldigt fødselsår. Indtast et gyldigt år, eller kontakt personalet for spørgsmål."); // hehe er jeg ikke sjov
                }
            } catch (InputMismatchException e) {
                System.out.println("Indtast et årstal. Prøv igen.");
                scanner.nextLine();
                fødselsår = 0;
            }
        }

        String aktivEllerPassivSomOrd = aktivEllerPassivSomOrd();
        String erKontingentBetaltSomOrd = erKontingentBetaltSomOrd();
        String motionistEllerKonkurrenceSomOrd = motionistEllerKonkurrenceSomOrd();

        Medlem nytMedlem = new Medlem(navn, fødselsår, aktivEllerPassivSomOrd, erKontingentBetaltSomOrd, motionistEllerKonkurrenceSomOrd);

        nytMedlem.setErKontingentBetalt(erKontingentBetaltSomOrd(nytMedlem)); // NY LINJE


        if (nytMedlem.getMotionistEllerKonkurrence().equalsIgnoreCase("konkurrence")) {
            opretKonkurrenceSvømmer(nytMedlem);
        } else {
            motionistMedlemmer.add(nytMedlem);
            System.out.println("Medlem oprettet: " + nytMedlem);
            filer.gemMotionisterTilFil(motionistMedlemmer, "motionistsvømmere.txt");

        }
    }

    public boolean erFødselsåretValidt(int år) { // NY METODE
        int aktueltÅr = Year.now().getValue();
        return år >= aktueltÅr - 100 && år <= aktueltÅr - 1;
    }

    /* public void opretKonkurrenceSvømmer(Medlem nytMedlem) {
        opretTrænere();
        System.out.println("Du er nu i gang med at oprette en konkurrence svømmer!");
        String svømmeDisciplinSomOrd = svømmeDisciplinSomOrd();

        String jaEllerNej;
        do {
            System.out.println("Har medlemmet en bedste tid? (ja/nej)");
            jaEllerNej = scanner.nextLine();

            if (!jaEllerNej.equalsIgnoreCase("ja") && !jaEllerNej.equalsIgnoreCase("nej")) {
                System.out.println("Ugyldigt svar. Prøv igen.");
            }
        } while (!jaEllerNej.equalsIgnoreCase("ja") && !jaEllerNej.equalsIgnoreCase("nej"));

        if (jaEllerNej.equalsIgnoreCase("ja")) {
            double bedsteTid;
            try {
                System.out.println("Hvad er medlemmets bedste tid? (mm,ss)");
                bedsteTid = indtastGyldigTid();
            } catch (InputMismatchException e) {
                System.out.println("Ugyldigt svar. Prøv igen.");
                opretKonkurrenceSvømmer(nytMedlem);
                return;
            }

            System.out.println("Hvilken dato havde medlemmet sin bedste tid? (format: dd-MM-yyyy)");
            String datoStr = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dato = null;

            try {
                dato = LocalDate.parse(datoStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldigt datoformat. Prøv igen.");
                opretKonkurrenceSvømmer(nytMedlem);
                return;
            }

            String stævneJaEllerNej;
            do {
                System.out.println("Har medlemmet været til et stævne? (ja/nej)");
                stævneJaEllerNej = scanner.nextLine();

                if (!stævneJaEllerNej.equalsIgnoreCase("ja") && !stævneJaEllerNej.equalsIgnoreCase("nej")) {
                    System.out.println("Ugyldigt svar. Prøv igen.");
                }
            } while (!stævneJaEllerNej.equalsIgnoreCase("ja") && !stævneJaEllerNej.equalsIgnoreCase("nej"));


            String stævneNavn = null;
            if (stævneJaEllerNej.equalsIgnoreCase("ja")) {
                System.out.println("Indtast stævne-navn: ");
                stævneNavn = scanner.nextLine();

                double stævneTid = 0;
                boolean validtInput = false;

                do {
                    try {
                        System.out.println("Indtast tid: (mm,ss)");
                        stævneTid = indtastGyldigTid();
                        validtInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Ugyldigt svar. Prøv igen.");
                    }
                } while (!validtInput);

                int placering = 0;

                do {
                    validtInput = false;
                    try {
                        System.out.println("Indtast din placering: ");
                        placering = scanner.nextInt();
                        scanner.nextLine();
                        validtInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Ugyldigt svar. Prøv igen.");
                        scanner.nextLine();
                    }
                } while (!validtInput);

                Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer(nytMedlem.getNavn(), nytMedlem.getFødselsår(),
                        nytMedlem.getAktivEllerPassiv(), nytMedlem.getErKontingentBetalt(),
                        nytMedlem.getMotionistEllerKonkurrence(), svømmeDisciplinSomOrd, bedsteTid, dato, stævneNavn, stævneTid, placering);
                hvilkenTrænerSkalMedlemmetHave(konkurrencesvømmer); // NY LINJE
                medlemmer.add(konkurrencesvømmer);
                // String formattedDato = dato.format(formatter); // NY LINJE
                System.out.println("Medlem oprettet: " + konkurrencesvømmer);
                filer.gemFileKonkurrenter(konkurrencesvømmer);
            } else {
                // double bedsteTid = 0;
                // LocalDate dato = null;
                Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer(nytMedlem.getNavn(), nytMedlem.getFødselsår(), nytMedlem.getAktivEllerPassiv(), nytMedlem.getErKontingentBetalt(),
                        nytMedlem.getMotionistEllerKonkurrence(),
                        svømmeDisciplinSomOrd, 0, null, null, 0, 0);

                hvilkenTrænerSkalMedlemmetHave(konkurrencesvømmer); // NY LINJE
                // medlemmer.add(konkurrencesvømmer);
                System.out.println("Medlem oprettet: " + konkurrencesvømmer);
                filer.gemFileKonkurrenter(konkurrencesvømmer);
                medlemmer.add(konkurrencesvømmer); // DET HAR EMILIA SAT IND - SKAL LIGE SE OM DET VIRKER, VI KAN SNAKKE OM OM DET SKAL BLIVE ELLER EJ
            }
        }
    }

     */

    public void opretKonkurrenceSvømmer(Medlem nytMedlem) {
        opretTrænere();
        System.out.println("Du er nu i gang med at oprette en konkurrence svømmer!");
        String svømmeDisciplinSomOrd = svømmeDisciplinSomOrd();

        double bedsteTid = 0;
        LocalDate dato = LocalDate.now();

        String jaEllerNej;
        do {
            System.out.println("Har medlemmet en bedste tid? (ja/nej)");
            jaEllerNej = scanner.nextLine();

            if (!jaEllerNej.equalsIgnoreCase("ja") && !jaEllerNej.equalsIgnoreCase("nej")) {
                System.out.println("Ugyldigt svar. Skriv enten 'ja' eller 'nej'. Prøv igen.");
            }
        } while (!jaEllerNej.equalsIgnoreCase("ja") && !jaEllerNej.equalsIgnoreCase("nej"));

        if (jaEllerNej.equalsIgnoreCase("ja")) {
            try {
                System.out.println("Hvad er medlemmets bedste tid? (mm,ss)");
                bedsteTid = indtastGyldigTid();

                System.out.println("Hvilken dato havde medlemmet sin bedste tid? (format: dd-MM-yyyy)");
                String datoStr = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                dato = LocalDate.parse(datoStr, formatter);
            } catch (InputMismatchException | DateTimeParseException e) {
                System.out.println("Ugyldig tid eller dato. Prøv igen.");
                opretKonkurrenceSvømmer(nytMedlem);
                return;
            }
        }

        System.out.println("Har medlemmet været til et stævne? (ja/nej)");
        String stævneJaEllerNej = scanner.nextLine();

        String stævneNavn = null;
        double stævneTid = 0;
        int placering = 0;

        if (stævneJaEllerNej.equalsIgnoreCase("ja")) {
            System.out.println("Indtast stævne-navn: ");
            stævneNavn = scanner.nextLine();

            boolean validtInput = false;

            do {
                try {
                    System.out.println("Indtast tid: (mm,ss)");
                    stævneTid = indtastGyldigTid();
                    validtInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Ugyldigt svar. Prøv igen.");
                }
            } while (!validtInput);

            do {
                validtInput = false;
                try {
                    System.out.println("Indtast din placering: ");
                    placering = scanner.nextInt();
                    scanner.nextLine();
                    validtInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Ugyldigt svar. Prøv igen.");
                    scanner.nextLine();
                }
            } while (!validtInput);
        }

        Konkurrencesvømmer konkurrencesvømmer = new Konkurrencesvømmer(nytMedlem.getNavn(), nytMedlem.getFødselsår(),
                nytMedlem.getAktivEllerPassiv(), nytMedlem.getErKontingentBetalt(),
                nytMedlem.getMotionistEllerKonkurrence(), svømmeDisciplinSomOrd, bedsteTid, dato);
        //  nytMedlem.getMotionistEllerKonkurrence(), svømmeDisciplinSomOrd, bedsteTid, dato, stævneNavn, stævneTid, placering); - emilias
        hvilkenTrænerSkalMedlemmetHave(konkurrencesvømmer);
        konkurrenceMedlemmer.add(konkurrencesvømmer);
        filer.gemKonkurrenceTilFil(konkurrenceMedlemmer, "konkurrencesvømmere.txt");
        System.out.println("Medlem oprettet: " + konkurrencesvømmer);
        //filer.gemFileKonkurrenter(konkurrencesvømmer);
    }

    public double indtastGyldigTid() throws InputMismatchException {
        double bedsteTid = 0;
        boolean korrektFormat = false;

        while (!korrektFormat) {
            try {
                String tidInput = scanner.nextLine();
                String[] tidArray = tidInput.split(",");
                int minutter = Integer.parseInt(tidArray[0].trim());
                int sekunder = Integer.parseInt(tidArray[1].trim());

                bedsteTid = minutter + sekunder / 100.0; // Gem som decimaltal

                korrektFormat = true;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Ugyldigt tidsformat. Prøv igen (mm,ss):");
            }
        }
        return bedsteTid;
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
                LocalDate dato = LocalDate.now(); // kan slettes tror jeg
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // kan slettes tror jeg
                String formattedDato = dato.format(formatter); // kan slettes tror jeg
                return "Kontingent betalt ";// + formattedDato; // DET VAR ET MELLEMRUM DER GJORDE FEJLEN
            } else if (nEllerS.equalsIgnoreCase("s")) {
                return "RESTANCE";
            } else {
                System.out.println("Ugyldigt bogstav. Prøv igen.");
            }
        }
    }

    public String erKontingentBetaltSomOrd(Medlem medlem) {
        if (medlem.getErKontingentBetalt().equalsIgnoreCase("Kontingent betalt ")) { // LAVET ET MELLEMRUM
            LocalDate dato = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDato = dato.format(formatter);
            Betaling betaling = new Betaling();
            double medlemsPris = betaling.udregnPris(medlem);
            String medlemsPrisSomString = String.valueOf(medlemsPris);
            return "Medlemskab på: " + medlemsPrisSomString + "kr. er betalt d. " + formattedDato;
        }
        String returnText = kontigentRestance(medlem); // DETTE ER NYT
        return returnText; // DETTE ER NYT
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

    public void seMedlemmer() {
        System.out.println("Motionist medlemmer: ");
        for (int i = 0; i < motionistMedlemmer.size(); i++) {
            System.out.println(motionistMedlemmer.get(i));
        }
        System.out.println("Konkurrence medlemmer: ");
        for (int i = 0; i < konkurrenceMedlemmer.size(); i++) {
            System.out.println(konkurrenceMedlemmer.get(i));
        }
    }

    public void seMedlemmerRestance() {
        System.out.println("Medlemmer der er i restance: ");
        for (int i = 0; i < motionistMedlemmer.size(); i++) {
            if (motionistMedlemmer.get(i).getErKontingentBetalt().contains("Restance")) {
                System.out.println(motionistMedlemmer.get(i));
            }


        }
        for (int i = 0; i < konkurrenceMedlemmer.size(); i++) {
            if (konkurrenceMedlemmer.get(i).getErKontingentBetalt().contains("Restance")) {
                System.out.println(konkurrenceMedlemmer.get(i));
            }

        }


    }

}