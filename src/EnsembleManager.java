import java.util.ArrayList;
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
//    public String displayAllEnsembles() {
//        if (ensembles.isEmpty()) return "No ensembles.";
//        return ensembles.stream()
//                .map(en -> en.getId() + " : " + en.getName())
//                .collect(Collectors.joining("\n"));
//    }
}
