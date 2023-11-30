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
        if (medlem.getFødselsår() <= 1963) {
            price *= 0.75;
        }
        if (medlem.getAktivEllerPassiv().equalsIgnoreCase("p")) {
            price = 500;
        }
        return price;

    }
}

