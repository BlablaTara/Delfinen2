import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Medlem {
    private String navn;
    private int fødselsår;
    private int alder;
    private String aktivEllerPassiv;
    private String erKontingentBetalt;
    private String motionistEllerKonkurrence;
    private String juniorEllerSenior;
    ArrayList<Stævne> stævner = new ArrayList<>();


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

    public void tilføjStævne(Stævne stævne) {
        stævner.add(stævne);
    }


    @Override
    public String toString() {
        // Simpel og klar CSV format
        return aktivEllerPassiv + "," + navn + "," + fødselsår + "," + erKontingentBetalt + "," + motionistEllerKonkurrence;
    }
    public boolean erJunior(){
        int alder = LocalDateTime.now().getYear() - fødselsår;
        return alder <18;
    }
}