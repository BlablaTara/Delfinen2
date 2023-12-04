import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Filer {
    Medlem medlem;
    Konkurrencesvømmer konkurrencesvømmer;

    public void UdskrivKonkurrencesvømmer() {//TTARA NY METODE
        File file = new File("konkurrenter.txt");
        try (Scanner inFile = new Scanner(file)) {
            while (inFile.hasNextLine()) {
                String s = inFile.nextLine();
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void UdskrivMotionister() { //TTARA NY METODE
        File file = new File("motionister.txt");

        try (Scanner inFile = new Scanner(file)) {
            if (inFile.hasNextLine()) {
                while (inFile.hasNextLine()) {
                    String s = inFile.nextLine();
                    System.out.println(s);
                }
            } else {
                System.out.println("Der er ingen motionister i filen.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


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

    /* public void gemFileKonkurrenter(Medlem medlem) {
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

     */
    public void gemFileKonkurrenter(Medlem medlem) { //TTARA NY METODE
        File file = new File("konkurrenter.txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            // Gem standardoplysninger om medlemmet
            writer.println(medlem);

            // Hvis medlemmet er en konkurrencesvømmer, gem stævneinformation
            if (medlem instanceof Konkurrencesvømmer) {
                Konkurrencesvømmer konkurrencesvømmer = (Konkurrencesvømmer) medlem;
                for (Stævne stævne : konkurrencesvømmer.getStævner()) {
                    writer.println("Stævne: " + stævne.getStævneNavn());
                    writer.println("Tid: " + stævne.getTid());
                    writer.println("Placering: " + stævne.getPlacering());

                }
            }

            writer.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void gemFileKonkurrenterMedStævne(Konkurrencesvømmer konkurrencesvømmer) { //TARA NY METODE
        File file = new File("konkurrenterMedStævne.txt"); // Ændret filnavnet
        ArrayList<Konkurrencesvømmer> eksisterendeKonkurrenter = læsKonkurrenterFraFil();

        // Fjern den gamle version af konkurrencesvømmer fra listen
        eksisterendeKonkurrenter.removeIf(existing -> existing.getNavn().equalsIgnoreCase(konkurrencesvømmer.getNavn()));

        // Tilføj den nye version af konkurrencesvømmer til listen
        eksisterendeKonkurrenter.add(konkurrencesvømmer);

        // Gem den opdaterede liste til den nye fil
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(eksisterendeKonkurrenter);
        } catch (IOException e) {
            throw new RuntimeException("Fejl ved gemning af opdateret konkurrentliste", e);
        }
    }
    private ArrayList<Konkurrencesvømmer> læsKonkurrenterFraFil() { //TARA NU METODE
        File file = new File("konkurrenter.txt");
        ArrayList<Konkurrencesvømmer> konkurrenter = new ArrayList<>();

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                konkurrenter = (ArrayList<Konkurrencesvømmer>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return konkurrenter;
    }
    public void fjernKonkurrentFraFil(Konkurrencesvømmer konkurrencesvømmer) { // TARA NY METODE
        File file = new File("konkurrenter.txt");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ArrayList<Konkurrencesvømmer> konkurrenter = (ArrayList<Konkurrencesvømmer>) ois.readObject();

            // Fjern den specifikke konkurrencesvømmer
            konkurrenter.removeIf(k -> k.getNavn().equalsIgnoreCase(konkurrencesvømmer.getNavn()));

            // Gem den opdaterede liste tilbage i filen
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(konkurrenter);
            } catch (IOException e) {
                throw new RuntimeException("Fejl ved gemning af opdateret konkurrentliste", e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Filen med konkurrenter blev ikke fundet", e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Fejl ved læsning af konkurrentliste", e);
        }
    }



}
