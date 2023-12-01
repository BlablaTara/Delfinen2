public class Stævne {
    private String stævneNavn;
    private double tid;
    private int placering;

    public Stævne(String stævneNavn, double tid, int placering) {
        this.stævneNavn = stævneNavn;
        this.tid = tid;
        this.placering = placering;
    }

    @Override
    public String toString() {
        return "Stævne: " + stævneNavn + " | Tid: " + tid + " | Placering: " + placering + ". plads";
    }
}
