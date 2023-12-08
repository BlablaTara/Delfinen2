import java.time.LocalDateTime;
import java.util.ArrayList;

public class Betaling {

    public Betaling() {
    }

    public void seMedlemmerBetalt(ArrayList<Medlem> motionister, ArrayList<Medlem> konkurrence) {
        System.out.println("Medlemmer der har betalt: ");
        for (int i = 0; i < motionister.size(); i++) {
            if (motionister.get(i).getErKontingentBetalt().contains("betalt")) {
                System.out.println(motionister.get(i).getNavn() + " - " + motionister.get(i).getErKontingentBetalt());
            }
        }
        for (int i = 0; i < konkurrence.size(); i++) {
            if (konkurrence.get(i).getErKontingentBetalt().contains("betalt")) {
                System.out.println(konkurrence.get(i).getNavn() + " - " + konkurrence.get(i).getErKontingentBetalt());
            }
        }
    }

    public void seMedlemmerRestance(ArrayList<Medlem> motionister, ArrayList<Medlem> konkurrence) {
        System.out.println("Medlemmer der er i restance:");
        for (int i = 0; i < motionister.size(); i++) {
            if (motionister.get(i).getErKontingentBetalt().contains("Restance")) {
                System.out.println(motionister.get(i).getNavn() + " - " + motionister.get(i).getErKontingentBetalt() + "kr");
            }
        }

        for (int i = 0; i < konkurrence.size(); i++) {
            if (konkurrence.get(i).getErKontingentBetalt().contains("Restance")) {
                System.out.println(konkurrence.get(i).getNavn() + " - " + konkurrence.get(i).getErKontingentBetalt() + "kr");
            }
        }
    }

    public String kontigentRestance(Medlem medlem) {
        Betaling betaling = new Betaling();
        double restance = betaling.udregnPris(medlem);

        String restanceSomString = String.valueOf(restance);
        return "Restance på: " + restanceSomString;
    }

    public double udregnPris(Medlem medlem){

        double price = 1600;
        int alder = LocalDateTime.now().getYear() - medlem.getFødselsår();
        if (alder < 18) {
            price = 1000;
        }
        if (alder > 60) {
            price = price * 0.75;
        }
        if (medlem.getAktivEllerPassiv().equalsIgnoreCase("passiv")) {
            price = 500;
        }
        return price;
    }
}

