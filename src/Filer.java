import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Filer {
    Medlem medlem;
    Konkurrencesvømmer konkurrencesvømmer;

    public void læsFileMotionisterBetalt() {
        File file = new File("motionister.txt");
        try (Scanner inFile = new Scanner(file)) {
            while (inFile.hasNextLine()) {
                String s = inFile.nextLine();
                if (s.trim().contains(",- Betalt d. ")) {
                    //if (!s.contains("RESTANCE")) {
                    System.out.println(s);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void læsFileKonkurrenterBetalt() {
        File file = new File("konkurrenter.txt");
        try (Scanner inFile = new Scanner(file)) {
            while (inFile.hasNextLine()) {
                String s = inFile.nextLine();
                if (s.trim().contains(",- Betalt d. ")) {
                    //if (!s.contains("RESTANCE")) {
                    System.out.println(s);

                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void læsFileMotionisterIRestance() {
        File file = new File("motionister.txt");
        try (Scanner inFile = new Scanner(file)) {
            while (inFile.hasNextLine()) {
                String s = inFile.nextLine();
                if (s.contains("Restance")) {
                    System.out.println(s);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void læsFileKonkurrenterIRestance() {
        File file = new File("konkurrenter.txt");
        try (Scanner inFile = new Scanner(file)) {
            while (inFile.hasNextLine()) {
                String s = inFile.nextLine();
                if (s.contains("RESTANCE")) {
                    System.out.println(s);

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
            ps.print(medlem);
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
            ps.print(medlem);
            ps.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

