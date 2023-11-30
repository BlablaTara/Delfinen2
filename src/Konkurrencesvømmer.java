import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Konkurrencesvømmer extends Medlem {
    private String svømmedisciplin;
    private double bedsteTid;
    private LocalDate dato;

    public Konkurrencesvømmer(String navn, int fødselsår, String aktivEllerPassiv, String erKontingentBetalt, String motionistEllerKonkurrence
            , String svømmedisciplin, double bedsteTid, LocalDate dato) {
        super(navn, fødselsår, aktivEllerPassiv, erKontingentBetalt, motionistEllerKonkurrence);
        this.svømmedisciplin = svømmedisciplin;
        this.bedsteTid = bedsteTid;
        this.dato = dato;
    }

    public String getSvømmedisciplin() {
        return svømmedisciplin;
    }

    public double getBedsteTid() {
        return bedsteTid;
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
        return super.toString() + svømmedisciplin + " | " + dato + " | " + bedsteTid;

    }

}