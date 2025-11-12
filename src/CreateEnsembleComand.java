public class CreateEnsembleComand implements Command{


    private EnsembleManager manager;
    private String type;
    private String eID;
    private String eName;
    private Ensemble createdEnsemble;
    public CreateEnsembleComand( EnsembleManager manager, String type, String eID, String eName) {
        this.manager = manager;
        this.type = type;
        this.eID = eID;
        this.eName = eName;
    }
    @Override
    public void execute() {
        //Find the ensemble Type
        if (type == "jazz"){
            EnssembleFactroy jazzFactory = new JazzBandEnsembleFactory();
            createdEnsemble =jazzFactory.CreateEnsemble( eID , eName );
        } else if (type == "orchestra"){
            EnssembleFactroy orchestraFactory = new OrchestraEnsembleFactory();
            createdEnsemble = orchestraFactory.CreateEnsemble( eID , eName );
        } else {
            System.out.println("Invalid ensemble type: " + type);
        }
        // add the ensemble to the manager ensembles list
        manager.addEnsemble(createdEnsemble);
        // output the result
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
            try {
                manager.removeEnsemble(createdEnsemble.getEnsembleID());
            }catch (Exception e){
                System.out.println("Error during undo: " + e.getMessage());
            }
    }
    @Override
    public void redo() {
        System.out.println("Nothing to Redo for CreateEnsembleCommand.");
        // Logic to redo the creation of the ensemble
        // manager.addEnsemble(createdEnsemble);
    }
}
