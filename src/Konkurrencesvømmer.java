import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Konkurrencesvømmer extends Medlem {
    private ArrayList<Stævne> stævner; //TARA NY LINJE
    private String svømmedisciplin;
    private Træner træner; // NY LINJE
    private double bedsteTid;
    private LocalDate dato;


    public Konkurrencesvømmer(String navn, int fødselsår, String aktivEllerPassiv, String erKontingentBetalt, String motionistEllerKonkurrence
            , String svømmedisciplin, double bedsteTid, LocalDate dato) {
        super(navn, fødselsår, aktivEllerPassiv, erKontingentBetalt, motionistEllerKonkurrence);
        this.svømmedisciplin = svømmedisciplin;
        this.bedsteTid = bedsteTid;
        this.dato = dato;
        this.stævner = new ArrayList<>(); //TARA NY LINJE
    }

    public String getSvømmedisciplin() {
        return svømmedisciplin;
    }

    public double getBedsteTid() {
        return bedsteTid;
    }
    public void setTræner(Træner træner) { // NY METODE
        this.træner = træner;
    }
    public ArrayList<Stævne> getStævner() { //TARA NY LINJE
        return stævner;
    }
    public void tilføjStævne(Stævne stævne){ //TARA NY LINJE
        stævner.add(stævne);
    }

    public LocalDate getDato() {
        return dato;
    }

    public String getFormattedDato() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dato.format(formatter);
    }


    @Override
    public String toString() {
        return super.toString() + svømmedisciplin + " | Træner: " + træner + " | Svømmers bedste tid: " + bedsteTid
                + " d. " + dato;
    }
}