public class OrchestraEnsembleFactory implements EnssembleFactroy {
    public Ensemble CreateEnsemble(String eID , String eName){

        OrchestraEnsemble Orchestra = new OrchestraEnsemble(String eID ,String eName);
        System.out.println("Orchestra Ensemble Created with ID: " + eID);
        return Orchestra;
    }

}
