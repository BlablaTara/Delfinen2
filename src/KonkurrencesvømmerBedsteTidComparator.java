import java.util.Comparator;

class KonkurrencesvømmerBedsteTidComparator implements Comparator<Konkurrencesvømmer> {
    @Override
    public int compare(Konkurrencesvømmer svømmer1, Konkurrencesvømmer svømmer2) {

        int disciplinComparison = svømmer1.getSvømmedisciplin().compareTo(svømmer2.getSvømmedisciplin());

        if (disciplinComparison == 0) {

            return Double.compare(svømmer1.getBedsteTid(), svømmer2.getBedsteTid());
        }

        return disciplinComparison;
    }
}