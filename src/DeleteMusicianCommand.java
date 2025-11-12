public class DeleteMusicianCommand  implements Command {

    private  Musician readyToDeleteMusician;
    private  EnsembleManager manager;
    private  String musicianID;

    public DeleteMusicianCommand(EnsembleManager manager, String mID, String mName ){
        this.manager = manager;
        this.musicianID = mID;
    }

    @Override
    public void execute() {
        //get the current ensemble and remove the musician from it
        Ensemble currentEnsemble = manager.getCurrentEnsemble();
        if (currentEnsemble != null) {
            manager.removeMusicianFromCurrent(musicianID);
        }
    }
    @Override
    public void undo() {
        // Re-add the musician that was removed during execute
        if (readyToDeleteMusician != null && manager.getCurrentEnsemble() != null) {
            manager.addMusicianToCurrent(readyToDeleteMusician);
        }

    }
    @Override
    public void redo() {
        // Remove the musician again
        execute();
    }

}














