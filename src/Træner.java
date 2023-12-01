public class Træner {
    private String name;

    public Træner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override // NY toString()
    public String toString() {
        return name;
    }
}
