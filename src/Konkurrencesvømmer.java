import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Konkurrencesvømmer extends Medlem {
    Scanner scanner = new Scanner(System.in);
    private String svømmedisciplin;
    private Træner træner;
    private double bedsteTid;
    private LocalDate dato;
    private String stævneNavn;
    private double stævneTid;
    private int stævnePlacering;

    public Konkurrencesvømmer(String navn, int fødselsår, String aktivEllerPassiv, String erKontingentBetalt, String motionistEllerKonkurrence
            , String svømmedisciplin, double bedsteTid, LocalDate dato, String stævneNavn, double stævneTid, int stævnePlacering) {
        super(navn, fødselsår, aktivEllerPassiv, erKontingentBetalt, motionistEllerKonkurrence);
        this.svømmedisciplin = svømmedisciplin;
        this.bedsteTid = bedsteTid;
        this.dato = dato;
        this.stævneNavn = stævneNavn;
        this.stævneTid = stævneTid;
        this.stævnePlacering = stævnePlacering;
    }

    public Konkurrencesvømmer() {
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

                bedsteTid = minutter + sekunder / 100.0;

                korrektFormat = true;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Ugyldigt tidsformat. Prøv igen (mm,ss):");
            }
        }
        return bedsteTid;
    }

    public String svømmeDisciplinSomOrd() {
        while (true) {
            System.out.println("Indtast hvilken svømmedisciplin medlemmet skal registreres i: ('c' for Crawl. " +
                    "'b' for Brystsvømning. 'bf' for Butterfly)");
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


    public String getSvømmedisciplin() {
        return svømmedisciplin;
    }

    public LocalDate getDato() {
        return dato;
    }

    public double getBedsteTid() {
        return bedsteTid;
    }

    public void setBedsteTid(double bedsteTid) {
        this.bedsteTid = bedsteTid;
    }

    public Træner getTræner() {
        return træner;
    }

    public void setTræner(Træner træner) {
        this.træner = træner;
    }

    public String getStævneNavn() {
        return stævneNavn;
    }

    public void setStævneNavn(String stævneNavn) {
        this.stævneNavn = stævneNavn;
    }

    public double getStævneTid() {
        return stævneTid;
    }

    public void setStævneTid(double stævneTid) {
        this.stævneTid = stævneTid;
    }

    public int getStævnePlacering() {
        return stævnePlacering;
    }

    public void setStævnePlacering(int stævnePlacering) {
        this.stævnePlacering = stævnePlacering;
    }

    @Override
    public String toString() {
        return super.toString() + "," + svømmedisciplin + "," + bedsteTid + "," + dato + "," + stævneNavn + "," + stævneTid + "," + stævnePlacering;
    }
}