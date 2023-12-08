public class Træner {
    private String name;
    Træner crawlTræner;
    Træner brystsvømningTræner;
    Træner butterflyTræner;

    public Træner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void opretTrænere() {
        crawlTræner = new Træner("Jamie");
        brystsvømningTræner = new Træner("The Rock");
        butterflyTræner = new Træner("David Hasselhoff");
    }

    public Træner hvilkenTrænerSkalMedlemmetHave(Konkurrencesvømmer medlem) {
        if (medlem.getSvømmedisciplin().equalsIgnoreCase("crawl")) {
            medlem.setTræner(crawlTræner);
            return crawlTræner;
        }
        if (medlem.getSvømmedisciplin().equalsIgnoreCase("brystsvømning")) {
            medlem.setTræner(brystsvømningTræner);
            return brystsvømningTræner;
        }
        if (medlem.getSvømmedisciplin().equalsIgnoreCase("butterfly")) {
            medlem.setTræner(butterflyTræner);
            return butterflyTræner;
        }
        return null;
    }

    @Override // NY toString()
    public String toString() {
        return name;
    }
}