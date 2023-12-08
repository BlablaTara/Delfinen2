import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Medlem {
    Scanner scanner = new Scanner(System.in);
    private String navn;
    private int fødselsår;
    private String aktivEllerPassiv;
    private String erKontingentBetalt;
    private String motionistEllerKonkurrence;
    Betaling betaling = new Betaling();

    public Medlem(String navn, int fødselsår, String aktivEllerPassiv, String erKontingentBetalt, String motionistEllerKonkurrence) {
        this.navn = navn;
        this.fødselsår = fødselsår;
        this.aktivEllerPassiv = aktivEllerPassiv;
        this.erKontingentBetalt = erKontingentBetalt;
        this.motionistEllerKonkurrence = motionistEllerKonkurrence;
    }

    public Medlem() {
    }

    public String getNavn() {
        return navn;
    }

    public int getFødselsår() {
        return fødselsår;
    }

    public String getMotionistEllerKonkurrence() {
        return motionistEllerKonkurrence;
    }

    public String getErKontingentBetalt() {
        return erKontingentBetalt;
    }

    public void setErKontingentBetalt(String erKontingentBetalt) {
        this.erKontingentBetalt = erKontingentBetalt;
    }

    public String getAktivEllerPassiv() {
        return aktivEllerPassiv;
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
                return "Kontingent betalt ";
            } else if (nEllerS.equalsIgnoreCase("s")) {
                return "RESTANCE";
            } else {
                System.out.println("Ugyldigt bogstav. Prøv igen.");
            }
        }
    }

    public String erKontingentBetaltSomOrd(Medlem medlem) {
        if (medlem.getErKontingentBetalt().equalsIgnoreCase("Kontingent betalt ")) {
            LocalDate dato = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDato = dato.format(formatter);
            Betaling betaling = new Betaling();
            double medlemsPris = betaling.udregnPris(medlem);
            String medlemsPrisSomString = String.valueOf(medlemsPris);
            return "Medlemskab på: " + medlemsPrisSomString + "kr. er betalt d. " + formattedDato;
        }
        String returnText = betaling.kontigentRestance(medlem);
        return returnText;
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

    public void seMedlemmer(ArrayList<Medlem> motionistMedlemmer, ArrayList<Medlem> konkurrenceMedlemmer) {
        String linjer = "-------------------------------------------------------------------";
        System.out.println(linjer + "Motionist medlemmer " + linjer + "\n");
        for (int i = 0; i < motionistMedlemmer.size(); i++) {
            System.out.print("Navn: " + motionistMedlemmer.get(i).getNavn());
            int alder = LocalDate.now().getYear() - motionistMedlemmer.get(i).getFødselsår();
            System.out.print(" - Alder: " + alder + " - Medlems status: " + motionistMedlemmer.get(i).getAktivEllerPassiv());
            System.out.print(" - Kontingentbetaling: " + motionistMedlemmer.get(i).getErKontingentBetalt());
            System.out.println(" - Motionist/Konkurrence: " + motionistMedlemmer.get(i).getMotionistEllerKonkurrence());

        }
        System.out.println("\n" + linjer + "Konkurrence medlemmer" + linjer + " \n ");
        for (int i = 0; i < konkurrenceMedlemmer.size(); i++) {
            System.out.print("Navn: " + konkurrenceMedlemmer.get(i).getNavn());
            int alder = LocalDate.now().getYear() - konkurrenceMedlemmer.get(i).getFødselsår();
            System.out.print(" - Alder: " + alder + " - Medlems status: " + konkurrenceMedlemmer.get(i).getAktivEllerPassiv());
            System.out.print(" - Kontingentbetaling: " + konkurrenceMedlemmer.get(i).getErKontingentBetalt());
            System.out.println(" - Motionist/Konkurrence: " + konkurrenceMedlemmer.get(i).getMotionistEllerKonkurrence());
            Medlem medlem = konkurrenceMedlemmer.get(i);
            if (medlem instanceof Konkurrencesvømmer) {
                Konkurrencesvømmer konkurrencesvømmer = (Konkurrencesvømmer) medlem;
                Main main = new Main();
                main.opretTrænere();
                konkurrencesvømmer.setTræner(main.hvilkenTrænerSkalMedlemmetHave(konkurrencesvømmer));
                System.out.print("Svømmedisciplin: " + konkurrencesvømmer.getSvømmedisciplin());
                System.out.print(" - Træner: " + konkurrencesvømmer.getTræner());
                System.out.print(" - Bedste tid: " + konkurrencesvømmer.getBedsteTid());
                System.out.print(" - Dato: " + konkurrencesvømmer.getDato());
                System.out.print(" - Stævne: " + konkurrencesvømmer.getStævneNavn());
                System.out.print(" - Stævne tid: " + konkurrencesvømmer.getStævneTid());
                System.out.println(" - Stævne placering: " + konkurrencesvømmer.getStævnePlacering() + "\n");
            }
        }
    }

    public boolean erJunior() {
        int alder = LocalDateTime.now().getYear() - fødselsår;
        return alder <18;
    }

    @Override
    public String toString() {
        return aktivEllerPassiv + "," + navn + "," + fødselsår + "," + erKontingentBetalt + "," + motionistEllerKonkurrence;
    }
}