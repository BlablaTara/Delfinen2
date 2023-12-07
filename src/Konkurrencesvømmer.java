import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Konkurrencesvømmer extends Medlem {
    private ArrayList<Stævne> stævner; //TARA NY LINJE
    private String svømmedisciplin;
    private Træner træner; // NY LINJE
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
        //  this.stævner = new ArrayList<>(); //TARA NY LINJE
        // tilføjStævne(stævneNavn, stævneTid, stævnePlacering);
        this.stævneNavn = stævneNavn;
        this.stævneTid = stævneTid;
        this.stævnePlacering = stævnePlacering;


    }


    public void tilføjStævne(String stævneNavn, double tid, int placering) {
        Stævne stævne = new Stævne(stævneNavn, tid, stævnePlacering);
        stævner.add(stævne);
    }


    public String getSvømmedisciplin() {
        return svømmedisciplin;
    }




    public double getBedsteTid() {
        return bedsteTid;
    }

    public void setBedsteTid(double bedsteTid) {
        this.bedsteTid = bedsteTid;
    }

    public void setTræner(Træner træner) { // NY METODE
        this.træner = træner;
    }

    public Træner getTræner() {
        return træner;
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

    public void setStævneNavn(String stævneNavn) {
        this.stævneNavn = stævneNavn;
    }

    public String getStævneNavn() {
        return stævneNavn;
    }

    public void setStævneTid(double stævneTid) {
        this.stævneTid = stævneTid;
    }

    public double getStævneTid() {
        return stævneTid;
    }

    public int getStævnePlacering() {
        return stævnePlacering;
    }

    public void setStævnePlacering(int stævnePlacering) {
        this.stævnePlacering = stævnePlacering;
    }

    public String getFormattedDato() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dato.format(formatter);
    }


    @Override
    public String toString() {
        // Tilføj bedste tid og dato, undlad træner information eller andre komplekse data
        return super.toString() + "," + svømmedisciplin + "," + bedsteTid + "," + dato + "," + stævneNavn + "," + stævneTid + "," + stævnePlacering;
    }
}