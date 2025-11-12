public class CreateEnsembleComand implements Command{

    private EnssembleFactroy factory;
    private EnsembleManager manager;
    private String type;
    private String eID;
    private String eName;
    private Ensemble createdEnsemble;


    public CreateEnsembleComand(EnssembleFactroy factory, EnsembleManager manager, String type, String eID, String eName) {
        this.factory = factory;
        this.manager = manager;
        this.type = type;
        this.eID = eID;
        this.eName = eName;
    }

    @Override
    public void execute() {
        createdEnsemble = factory.CreateEnsemble(type, eID, eName);
        manager.addEnsemble(createdEnsemble);
        if (createdEnsemble != null){
            if (createdEnsemble instanceof JazzBandEnsemble) {

                System.out.println("Jazz band ensemble is created.");
            } else if (createdEnsemble instanceof OrchestraEnsemble) {
                System.out.println("Orchestra ensemble is created.");
            }
        }else {
            System.out.println("Failed to create ensemble.");
        }
        // Here you would typically add the created ensemble to the manager
    }

    @Override
    public void undo() {

        // Logic to undo the creation of the ensemble
         manager.removeEnsemble(createdEnsemble.getEnsembleID());
    }

    @Override
    public void redo() {
        System.out.println("Nothing to Redo for CreateEnsembleCommand.");
        // Logic to redo the creation of the ensemble
        // manager.addEnsemble(createdEnsemble);
    }
}
