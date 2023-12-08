public class Stævne {
    private String stævneNavn;
    private double tid;
    private int placering;

    public Stævne(String stævneNavn, double tid, int placering) {
        this.stævneNavn = stævneNavn;
        this.tid = tid;
        this.placering = placering;
    }

    public String getStævneNavn() {
        return stævneNavn;
    }

    public double getTid() {
        return tid;
    }

    public int getPlacering() {
        return placering;
    }

    @Override
    public String toString() {
        return "Stævne: " + stævneNavn + " | Tid: " + tid + " | Placering: " + placering + ". plads";
    }
}