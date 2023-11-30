import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Filer {
    Medlem medlem;
    Konkurrencesvømmer konkurrencesvømmer;

    public void læsFile() {  // Denne er ikke færdig - kan først se hvad der skal vises ud når vi er ved enden.
        File file = new File("motionister.txt");
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

    public void gemFileMotionist(Medlem medlem) {
        File file = new File("motionister.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            PrintStream ps = new PrintStream(fos);
            ps.println(medlem);
            ps.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void gemFileKonkurrenter(Medlem medlem) {
        File file = new File("konkurrenter.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            PrintStream ps = new PrintStream(fos);
            ps.println(medlem);
            ps.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

