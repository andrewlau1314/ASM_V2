public class AddMusicianCommand implements Command{
    private  Musician CreateMusician;
    private  EnsembleManager manager;
    public AddMusicianCommand(EnsembleManager manager, String mID, String mName ){
        this.manager = manager;
        this.CreateMusician = new Musician(mID);
        this.CreateMusician.setName(mName);
    }

    @Override
    public void execute() {
        //get the current ensemble and add the musician to it
        Ensemble currentEnsemble = manager.getCurrentEnsemble();
        if (currentEnsemble != null) {
            manager.addMusicianToCurrent(CreateMusician);
            System.out.println("Musician " + CreateMusician.getMusicianID() + " added to Ensemble " + currentEnsemble.getEnsembleID());
        } else {
            System.out.println("No current ensemble selected. Cannot add musician.");

        }
    }

    @Override
    public void undo() {
        if (manager.getCurrentEnsemble() != null) {
            manager.removeMusicianFromCurrent(CreateMusician.getMusicianID());
            System.out.println("Musician " + CreateMusician.getMusicianID() + " removed from Ensemble " + manager.getCurrentEnsemble().getEnsembleID());
        } else {
            System.out.println("No current ensemble selected. Cannot remove musician.");
        }
    }

    @Override
    public void redo() {
        execute();
    }
}
