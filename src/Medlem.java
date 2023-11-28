import java.util.Scanner;

public class Medlem {
    private String navn;
    private int fødselsår;
    private String aktivEllerPassiv;
    private String motionistEllerKonkurrence;
    private String svømmedisciplin;
    private String stævne;
    private double bedsteTid;
    private int placering;
    private String dato;
    private boolean juniorEllerSenior;


    public Medlem(String navn, int fødselsår, String aktivEllerPassiv, String motionistEllerKonkurrence) {
        this.navn = navn;
        this.fødselsår = fødselsår;
        this.aktivEllerPassiv = aktivEllerPassiv;
        this.motionistEllerKonkurrence = motionistEllerKonkurrence;
        // junior- senior opdeling sker ikke her!

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

    public String getStævne() {
        return stævne;
    }

    public void setStævne(String stævne) {
        this.stævne = stævne;
    }

    public double getBedsteTid() {
        return bedsteTid;
    }

    public void setBedsteTid(double bedsteTid) {
        this.bedsteTid = bedsteTid;
    }

    public int getPlacering() {
        return placering;
    }

    public void setPlacering(int placering) {
        this.placering = placering;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getSvømmedisciplin() {
        return svømmedisciplin;
    }

    public void setSvømmedisciplin(String svømmedisciplin) {
        this.svømmedisciplin = svømmedisciplin;
    }

    public boolean getJuniorEllerSenior() {
        return juniorEllerSenior;
    }

    public void setJuniorEllerSenior(boolean juniorEllerSenior) {
        this.juniorEllerSenior = juniorEllerSenior;
    }

    public void setAktivEllerPassiv(String aktivEllerPassiv) {
        this.aktivEllerPassiv = aktivEllerPassiv;
    }

    public String getAktivEllerPassiv() {
        return aktivEllerPassiv;
    }


    public void udregnMedlemsAlder(int fødselsår) {
        Scanner scanner = new Scanner(System.in);
        fødselsår = scanner.nextInt();
        scanner.nextLine();
        int alder = 2023 - fødselsår;
    }
    public String bestemSeniorEllerJunior() {
        int nuværendeÅr = java.time.Year.now().getValue();
        int alder = nuværendeÅr - fødselsår;

        if (alder < 18) {
            juniorEllerSenior = true; //junior
            return "Junior";
        } else {
            juniorEllerSenior = false; //senior
            return "Senior";
        }
    }

        //ordner det om lidt
        @Override
        public String toString() {
            return "MEDLEM: " + motionistEllerKonkurrence + "| Navn: " + navn + "| Alder: " + fødselsår //skal regnes om til alder.
                    + " | Svømmedisciplin: " + svømmedisciplin + " | Hold: " + juniorEllerSenior + "| Stævne: " + stævne + "| Dato: "
                    + dato + "| Bedste tid: " + bedsteTid + " | Placering: " + placering;
        }

        public void erMedlemmetAktivEllerPassiv(){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("A")) {
                setAktivEllerPassiv("Aktiv");
            } else if (input.equalsIgnoreCase("P")) {
                setAktivEllerPassiv("Passiv");
            } else {
                System.out.println("Dette er ikke en mulighed, Prøv igen. \n Vælg [A eller P]");
                erMedlemmetAktivEllerPassiv();
            }
        }

    }





