import java.time.LocalDateTime;
import java.util.ArrayList;

public class Betaling {
    private ArrayList<Medlem> kontigentBetal;
    private ArrayList<Medlem> kontigentRestance;

    public Betaling() {
        kontigentBetal = new ArrayList<>();
        kontigentRestance = new ArrayList<>();
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


