import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Filer {

    public void gemMotionisterTilFil(ArrayList<Medlem> motionistMedlemmer, String filnavn) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filnavn))) {
            for (Medlem medlem : motionistMedlemmer) {
                writer.write(medlem.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gemKonkurrenceTilFil(ArrayList<Medlem> konkurrenceMedlemmer, String filnavn) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filnavn))) {
            for (Medlem medlem : konkurrenceMedlemmer) {
                writer.write(medlem.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Medlem> indlæsMotionisterFraFil(String filnavn) {
        ArrayList<Medlem> motionister = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filnavn))) {
            String linje;
            while ((linje = reader.readLine()) != null) {
                Medlem medlem = opretMedlemFraString(linje);
                motionister.add(medlem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return motionister;
    }

    public ArrayList<Medlem> indlæsKonkurrenceMedlemmerFraFil(String filnavn) {
        ArrayList<Medlem> konkurrenceMedlemmer = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filnavn))) {
            String linje;
            while ((linje = reader.readLine()) != null) {
                Konkurrencesvømmer medlem = opretKonkurrenceMedlemFraString(linje);
                konkurrenceMedlemmer.add(medlem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return konkurrenceMedlemmer;
    }

    public Medlem opretMedlemFraString(String linje) {
        String[] data = linje.split(",");
        String aktivEllerPassiv = data[0];
        String navn = data[1];
        int fødselsår = Integer.parseInt(data[2]);
        String erKontingentBetalt = data[3];
        String motionistEllerKonkurrence = data[4];

        return new Medlem(navn, fødselsår, aktivEllerPassiv, erKontingentBetalt, motionistEllerKonkurrence);
    }

    public Konkurrencesvømmer opretKonkurrenceMedlemFraString(String linje) {
        String[] data = linje.split(",");
        String aktivEllerPassiv = data[0];
        String navn = data[1];
        int fødselsår = Integer.parseInt(data[2]);
        String erKontingentBetalt = data[3];
        String motionistEllerKonkurrence = data[4];
        String svømmedisciplin = data[5];
        double bedsteTid = Double.parseDouble(data[6]);
        LocalDate dato = LocalDate.parse(data[7]);
        String stævneNavn = data[8];
        double stævneTid = Double.parseDouble(data[9]);
        int stævnePlacering = Integer.parseInt(data[10]);

        return new Konkurrencesvømmer(navn, fødselsår, aktivEllerPassiv, erKontingentBetalt, motionistEllerKonkurrence,
                svømmedisciplin, bedsteTid, dato, stævneNavn, stævneTid, stævnePlacering);
    }
}