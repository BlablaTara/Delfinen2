import java.time.LocalDateTime;
import java.util.Scanner;

public class Medlem {
    private String navn;
    private int fødselsår;
    private int alder;
    private String aktivEllerPassiv;
    private String erKontingentBetalt;
    private String motionistEllerKonkurrence;
    private String juniorEllerSenior;


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

    public int setAlder(int fødselsår) {
        this.alder = LocalDateTime.now().getYear() - fødselsår;
        return alder;
    }

    public String getMotionistEllerKonkurrence() {
        return motionistEllerKonkurrence;
    }


    public void setAktivEllerPassiv(String aktivEllerPassiv){
        this.aktivEllerPassiv = aktivEllerPassiv;
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

    public String setJuniorEllerSenior(int fødselsår) {
        int alder = LocalDateTime.now().getYear() - fødselsår;
        if (alder < 18) {
            return "Juniorhold";
        } else {
            return "Seniorhold";
        }
    }

    @Override
    public String toString() {
        return  "\n" + aktivEllerPassiv + ": " +
                motionistEllerKonkurrence + " | " + navn + " | " + fødselsår+ " | " ;
    }
}

