import java.time.LocalDateTime;
import java.util.Scanner;

public class Medlem {
    private String navn;
    private int fødselsår;
    private String aktivEllerPassiv;
    private String motionistEllerKonkurrence;

    public Medlem(String navn, int fødselsår, String aktivEllerPassiv, String motionistEllerKonkurrence) {
        this.navn = navn;
        this.fødselsår = fødselsår;
        this.aktivEllerPassiv = aktivEllerPassiv;
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


    public void setAktivEllerPassiv(String aktivEllerPassiv){
        this.aktivEllerPassiv = aktivEllerPassiv;
    }

    public String getAktivEllerPassiv() {
        return aktivEllerPassiv;
    }


    public int udregnMedlemsAlder() {
        Scanner scanner = new Scanner(System.in);
        fødselsår = scanner.nextInt();
        scanner.nextLine();
        int alder = LocalDateTime.now().getYear() - fødselsår;
        return alder;
    }


    public String erMedlemmetJuniorEllerSenior() {
        int alder = LocalDateTime.now().getYear() - fødselsår;
        if (alder < 18) {
            return "Junior";
        } else {
            return "Senior";
        }
    }

    @Override
    public String toString() {
        return  "\n" + aktivEllerPassiv + ": \n " +
                motionistEllerKonkurrence + ": " + navn + " | " + udregnMedlemsAlder() + " | " + erMedlemmetJuniorEllerSenior();
    }
}







