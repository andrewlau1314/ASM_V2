public class ShowCurrentEnsembleCommand implements Command {
    private EnsembleManager manager;
    public ShowCurrentEnsembleCommand(EnsembleManager ensembleManager) {
        this.manager = ensembleManager;
    }

    @Override
    public void execute() {
        System.out.println(manager.getCurrentEnsemble());
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
