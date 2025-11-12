import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class EnsembleManager {
    private final List<Ensemble> ensembles = new ArrayList<>();
    private Ensemble current;

    public void addEnsemble(Ensemble e) {
        ensembles.add(e);
    }

    public void removeEnsemble(String id) {
        ensembles.removeIf(en -> en.getEnsembleID().equals(id));
        if (current != null && current.getEnsembleID().equals(id)) current = null;
    }

    public Ensemble getEnsembleById(String id) {
        return ensembles.stream().filter(en -> en.getEnsembleID().equals(id)).findFirst().orElse(null);
    }

    public void setCurrentEnsembleById(String id) {
        current = getEnsembleById(id);
    }

    public Ensemble getCurrentEnsemble() {
        return current;
    }

    public void addMusicianToCurrent(Musician m) {
        if (current != null) current.addMusician(m);
    }

    public void removeMusicianFromCurrent(String musicianId) {
        if (current == null) return;
        Musician m = null;
        Iterator<Musician> itMList = current.getMusicians();

        while (itMList.hasNext()) {
            Musician tempM = itMList.next();
            if (tempM.getMusicianID().equals(musicianId)) {
                m = tempM;
                break;
            }
        }
        if (m != null) {
            current.dropMusician(m);
        }else  {
            System.out.println("Musician with ID " + musicianId + " not found in current ensemble.");
        }
    }

//    public void modifyMusicianInstrument(String musicianId, String instrument) {
//        if (current == null) return;
//        Musician m;
//
//        Iterator<Musician> itMList = current.getMusicians();
//
//        while (itMList.hasNext()) {
//            Musician tempM = itMList.next();
//            if (tempM.getMusicianID().equals(musicianId)) {
//                m = tempM;
//
//            }
//
//
//        }
//        if (m != null) m.setInstrument(instrument);
//    }
    public void changeCurrentEnsembleName(String name) {
        if (current != null) current.setName(name);
    }

//    public String showCurrentEnsemble() {
//        if (current == null) return "No current ensemble set.";
//        StringBuilder sb = new StringBuilder();
//        sb.append("Ensemble ").append(current.getId()).append(": ").append(current.getName()).append("\n");
//        for (Musician m : current.getMusicians()) {
//            sb.append("- ").append(m.getId()).append(" ").append(m.getName()).append(" : ").append(m.getInstrument()).append("\n");
//        }
//        return sb.toString();
//    }
//
    public String displayAllEnsembles() {
        if (ensembles.isEmpty()) return "";
        return ensembles.stream()
                .map(en -> {
                    String typeName = "Ensemble";
                    if (en instanceof OrchestraEnsemble) {
                        typeName = "Orchestra Ensemble";
                    } else if (en instanceof JazzBandEnsemble) {
                        typeName = "Jazz Band Ensemble";
                    }
                    return typeName + " " + en.getEName() + " (" + en.getEnsembleID() + ")";
                })
                .collect(Collectors.joining("\n"));
    }
}
