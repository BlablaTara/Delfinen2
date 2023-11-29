import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Konkurrencesvømmer extends Medlem {
    private String svømmedisciplin;
    private double bedsteTid;
    private LocalDate dato;

    public Konkurrencesvømmer(String navn, int fødselsår, String aktivEllerPassiv, String motionistEllerKonkurrence
            , String svømmedisciplin, double bedsteTid, LocalDate dato) {
        super(navn, fødselsår, aktivEllerPassiv, motionistEllerKonkurrence);
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
/*
    public void erMedlemKonkurrenceSvømmer(Medlem medlem) {

        if (medlem.getMotionistEllerKonkurrence().equalsIgnoreCase("k")) {
            System.out.println("Hvilken svømmedisciplin skal medlemmet registreres i?: ");
            String svømmedisciplin = in.nextLine();
            medlem.setSvømmedisciplin(svømmedisciplin);
            System.out.println("Har medlemmet deltaget i et stævne: (ja/nej) ");
            String deltagetIStævne = in.nextLine();
            if (deltagetIStævne.equalsIgnoreCase("ja")) {
                System.out.println("Indtast et stævne som medlemmet har deltaget i: ");
                String stævne = in.nextLine();
                medlem.setStævne(stævne);
                System.out.println("Hvilken placering fik medlemmet til stævnet?: ");
                int placering = in.nextInt();
                in.nextLine();
                medlem.setPlacering(placering);
                System.out.println("Hvilken tid havde medlemmet?: ");
                double bedsteTid = in.nextDouble();
                in.nextLine();
                medlem.setBedsteTid(bedsteTid);
                System.out.println("Hvilken dato deltog medlemmet i stævnet?: ");
                String dato = in.nextLine();
                medlem.setDato(dato);
            }
            else {
                System.out.println("Har medlemmet en bedste tid");
                String harMedlemmetBedsteTid = in.nextLine();
                if (harMedlemmetBedsteTid.equalsIgnoreCase("ja")) {
                    System.out.println("Hvad er medlemmets bedste tid?: ");
                    double bedsteTid = in.nextDouble();
                    in.nextLine();
                    medlem.setBedsteTid(bedsteTid);
                    System.out.println("Hvilken dato havde medlemmet fået sin bedste tid?: ");
                    String dato = in.nextLine();
                    medlem.setDato(dato);
                }
            }
        }
    }

 */

    @Override
    public String toString() {
        return super.toString() + svømmedisciplin + " | " + dato + " | " + bedsteTid;

    }

}
