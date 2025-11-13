import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShowCurrentEnsembleCommand implements Command {
    private EnsembleManager manager;
    public ShowCurrentEnsembleCommand(EnsembleManager ensembleManager) {
        this.manager = ensembleManager;
    }

    @Override
    public void execute() {
        Ensemble e = manager.getCurrentEnsemble();
        if (e == null) {
            System.out.println("No current ensemble set.");
            return;
        }
        String typeName = "Ensemble";
        if (e instanceof OrchestraEnsemble) typeName = "Orchestra Ensemble";
        else if (e instanceof JazzBandEnsemble) typeName = "Jazz Band Ensemble";

        System.out.println(typeName + " " + e.getEName() + " (" + e.getEnsembleID() + ")");

        // prepare role buckets depending on ensemble type
        if (e instanceof OrchestraEnsemble) {
            List<String> violinists = new ArrayList<>();
            List<String> cellists = new ArrayList<>();
            Iterator<Musician> it = e.getMusicians();
            while (it.hasNext()) {
                Musician m = it.next();
                if (m.getRole() == 1) violinists.add(m.getMusicianID() + ", " + m.getMName());
                else if (m.getRole() == 2) cellists.add(m.getMusicianID() + ", " + m.getMName());
            }
            System.out.println("Violinist: " + (violinists.isEmpty() ? "NIL" : String.join("\n", violinists)));
            if (!cellists.isEmpty()) System.out.println("Cellist: " + String.join("\n", cellists));
        } else if (e instanceof JazzBandEnsemble) {
            List<String> pianists = new ArrayList<>();
            List<String> saxes = new ArrayList<>();
            List<String> drummers = new ArrayList<>();
            Iterator<Musician> it = e.getMusicians();
            while (it.hasNext()) {
                Musician m = it.next();
                if (m.getRole() == 1) pianists.add(m.getMusicianID() + ", " + m.getMName());
                else if (m.getRole() == 2) saxes.add(m.getMusicianID() + ", " + m.getMName());
                else if (m.getRole() == 3) drummers.add(m.getMusicianID() + ", " + m.getMName());
            }
            System.out.println("Pianist: " + (pianists.isEmpty() ? "NIL" : String.join("\n", pianists)));
            System.out.println("Saxophonist: " + (saxes.isEmpty() ? "NIL" : String.join("\n", saxes)));
            System.out.println("Drummer: " + (drummers.isEmpty() ? "NIL" : String.join("\n", drummers)));
        } else {
            // generic listing
            Iterator<Musician> it = e.getMusicians();
            while (it.hasNext()) {
                Musician m = it.next();
                System.out.println(m.getMusicianID() + ", " + m.getMName());
            }
        }
    }

    @Override
    public void undo() {
        System.out.println("Undo not applicable for ShowCurrentEnsembleCommand");
    }

    @Override
    public void redo() {
        execute();
    }
}
