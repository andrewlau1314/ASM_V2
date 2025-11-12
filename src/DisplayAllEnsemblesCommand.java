public class DisplayAllEnsemblesCommand implements  Command {
    private  EnsembleManager manager;
    public DisplayAllEnsemblesCommand(EnsembleManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        System.out.println(manager.displayAllEnsembles());
    }

    @Override
    public void undo() {
        System.out.println("Undo not applicable for DisplayAllEnsemblesCommand");
    }

    @Override
    public void redo() {
        execute();
    }
}
