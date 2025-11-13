import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EnsembleMemento {
    private final String ensembleID;
    private final String ensembleName;
    private final Map<String, Integer> musicianRoles; // musicianID -> role

    public EnsembleMemento(Ensemble e) {
        this.ensembleID = e.getEnsembleID();
        this.ensembleName = e.getEName();
        this.musicianRoles = new HashMap<>();
        Iterator<Musician> it = e.getMusicians();
        while (it.hasNext()) {
            Musician m = it.next();
            musicianRoles.put(m.getMusicianID(), m.getRole());
        }
    }

    public String getEnsembleID() { return ensembleID; }
    public String getEnsembleName() { return ensembleName; }

    // Restore the saved state into the given Ensemble
    public void restore(Ensemble e) {
        // restore name
        if (ensembleName != null) e.setName(ensembleName);
        // restore musician roles
        Iterator<Musician> it = e.getMusicians();
        while (it.hasNext()) {
            Musician m = it.next();
            Integer r = musicianRoles.get(m.getMusicianID());
            if (r != null) m.setRole(r);
        }
    }
}
