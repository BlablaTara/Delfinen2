import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Filer {

    public void læsFile() {  // Denne er ikke færdig - kan først se hvad der skal vises ud når vi er ved enden.
        File file = new File("medlemmer.txt");
        try (Scanner inFile = new Scanner(file)) {
            while (inFile.hasNextLine()) {
                String s = inFile.nextLine();
                if (!s.isEmpty()) {
                    String[] parts = s.split(",");
                    String motionistEllerKonkurrence = parts[0];
                    String navn = parts[1];
                    String alder = parts[2];
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void gemFile(ArrayList<Medlem> medlemmer) {
        File file = new File("medlemmer.txt");
        try {
            PrintStream ps = new PrintStream(file);
            for (Medlem medlem : medlemmer) {
                ps.println(medlem.getMotionistEllerKonkurrence() + "," + medlem.getNavn() + "," + medlem.getFødselsår() //skal regnes om til alder.
                        + "," + medlem.getSvømmedisciplin() + "," + medlem.getJuniorEllerSenior() + "," + medlem.getStævne() + ","
                        + medlem.getDato() + "," + medlem.getBedsteTid() + "," + medlem.getPlacering());
            }
            ps.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
