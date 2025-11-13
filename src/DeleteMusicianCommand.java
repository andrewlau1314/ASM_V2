import java.util.Iterator;

public class DeleteMusicianCommand  implements Command {

    private  Musician readyToDeleteMusician;
    private  EnsembleManager manager;
    private  String musicianID;
    private  String originalEnsembleId;

    public DeleteMusicianCommand(EnsembleManager manager, String mID, String mName ){
        this.manager = manager;
        this.musicianID = mID;
    }

    @Override
    public void execute() {
        // determine ensemble to delete from (current)
        Ensemble currentEnsemble = manager.getCurrentEnsemble();
        if (currentEnsemble == null) {
            System.out.println("No current ensemble selected. Cannot delete musician.");
            return;
        }
        originalEnsembleId = currentEnsemble.getEnsembleID();

        // find and store the musician object before removal
        Iterator<Musician> it = currentEnsemble.getMusicians();
        while (it.hasNext()) {
            Musician m = it.next();
            if (m.getMusicianID().equals(this.musicianID)) {
                readyToDeleteMusician = m;
                break;
            }
        }

        boolean removed = manager.removeMusicianFromEnsemble(originalEnsembleId, musicianID);
        if (removed) System.out.println("Musician is deleted.");
        else System.out.println("Musician with ID " + musicianID + " not found in ensemble " + originalEnsembleId + ".");
    }
    @Override
    public void undo() {
        // Re-add the musician that was removed during execute to the original ensemble
        if (readyToDeleteMusician != null && originalEnsembleId != null) {
            manager.addMusicianToEnsemble(originalEnsembleId, readyToDeleteMusician);
            System.out.println("Musician " + readyToDeleteMusician.getMusicianID() + " restored to Ensemble " + originalEnsembleId);
        } else {
            System.out.println("Nothing to undo for DeleteMusicianCommand");
        }

    }
    @Override
    public void redo() {
        // Remove the musician again from the original ensemble
        if (originalEnsembleId != null) {
            boolean removed = manager.removeMusicianFromEnsemble(originalEnsembleId, musicianID);
            if (removed) System.out.println("Musician is deleted.");
            else System.out.println("Musician with ID " + musicianID + " not found in ensemble " + originalEnsembleId + ".");
        } else {
            System.out.println("Nothing to redo for DeleteMusicianCommand");
        }
    }
}
