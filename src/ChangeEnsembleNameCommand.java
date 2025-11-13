public class ChangeEnsembleNameCommand implements Command {
    private EnsembleManager manager;
    private String newName;
    private EnsembleMemento before;
    private EnsembleMemento after;

    public ChangeEnsembleNameCommand(EnsembleManager manager, String newName) {
        this.manager = manager;
        this.newName = newName;
    }

    @Override
    public void execute() {
        Ensemble current = manager.getCurrentEnsemble();
        if (current == null) {
            System.out.println("No current ensemble selected.");
            return;
        }
        before = new EnsembleMemento(current);
        manager.changeCurrentEnsembleName(newName);
        after = new EnsembleMemento(current);
        System.out.println("Ensemble's name is updated.");
    }

    @Override
    public void undo() {
        Ensemble current = manager.getCurrentEnsemble();
        if (current == null || before == null) {
            System.out.println("Nothing to undo for ChangeEnsembleNameCommand");
            return;
        }
        before.restore(current);
        System.out.println("Undo: ensemble name restored.");
    }

    @Override
    public void redo() {
        Ensemble current = manager.getCurrentEnsemble();
        if (current == null || after == null) {
            System.out.println("Nothing to redo for ChangeEnsembleNameCommand");
            return;
        }
        after.restore(current);
        System.out.println("Redo: ensemble name updated.");
    }
}
