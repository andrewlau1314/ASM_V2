public class JazzBandEnsembleFactory  implements EnssembleFactroy{

    public Ensemble CreateEnsemble(String eID , String eName){

            JazzBandEnsemble jazzBand = new JazzBandEnsemble(eID );
            System.out.println("Jazz Band Ensemble Created with ID: " + eID);
            return jazzBand;
    }

}
