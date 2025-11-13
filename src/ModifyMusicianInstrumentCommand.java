import java.util.Iterator;

public class ModifyMusicianInstrumentCommand  implements Command {
    private String musicianID;
    private int newinstrumentID;
    private EnsembleManager Manager;
    private EnsembleMemento before;
    private EnsembleMemento after;

    public  ModifyMusicianInstrumentCommand(EnsembleManager manager, String musicianID, int newinstrumentID) {
        this.Manager = manager;
        this.musicianID = musicianID;
        this.newinstrumentID = newinstrumentID;
    }

    @Override
    public void execute() {
        Ensemble current = Manager.getCurrentEnsemble();
        if (current == null) {
            System.out.println("No current ensemble selected.");
            return;
        }
        // capture before state
        before = new EnsembleMemento(current);

        // perform change
        Iterator<Musician> it = current.getMusicians();
        boolean found = false;
        while (it.hasNext()) {
            Musician m = it.next();
            if (m.getMusicianID().equals(musicianID)) {
                m.setRole(newinstrumentID);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Musician with ID " + musicianID + " not found in current ensemble.");
            before = null; // nothing changed
            return;
        }
        // capture after state
        after = new EnsembleMemento(current);
        System.out.println("Instrument is updated.");
    }

    @Override
    public void undo() {
        Ensemble current = Manager.getCurrentEnsemble();
        if (current == null || before == null) {
            System.out.println("Nothing to undo for ModifyMusicianInstrumentCommand");
            return;
        }
        before.restore(current);
        System.out.println("Undo: instrument restored for " + musicianID);
    }

    @Override
    public void redo() {
        Ensemble current = Manager.getCurrentEnsemble();
        if (current == null || after == null) {
            System.out.println("Nothing to redo for ModifyMusicianInstrumentCommand");
            return;
        }
        after.restore(current);
        System.out.println("Redo: instrument updated for " + musicianID);
    }
}
