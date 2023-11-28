import java.util.Scanner;

public class Menu {
    private String overskrift;
    private String menuTekst;
    private String[] menuIndeks;

    public Menu(String overskrift, String menuTekst, String[] menuIndeks) {
        this.overskrift = overskrift;
        this.menuTekst = menuTekst;
        this.menuIndeks = menuIndeks;
    }

    public void printMenu() {
        String printString = overskrift + "\n";

        for (int i = 0; i < menuIndeks.length; i++)
            printString += menuIndeks[i] + "\n";
        System.out.println("\n" + printString);
    }

    public int brugerensValg() {
        Scanner scanner = new Scanner(System.in);
        boolean valideretValg = false;
        int valg = -1;

        while (! valideretValg) {
            System.out.println(menuTekst);
            if (scanner.hasNextInt()) {
                valg = scanner.nextInt();
                valideretValg = true;
            } else {
                scanner.nextLine(); // Scanner bug
            }
        }
        return valg;
    }
}
