import java.time.LocalDateTime;
import java.util.Scanner;

public class Konkurrencesvømmer {
    Scanner in = new Scanner(System.in);

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
}

