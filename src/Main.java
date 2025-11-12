import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){

        }
    }


    private static void printMenu(Ensemble current) {
        System.out.println("\nMusic Ensembles Management System (MEMS)");
        System.out.println("c = create ensemble, s = set current ensemble, a = add musician, m = modify musician’s instrument,");
        System.out.println("d = delete musician, se = show ensemble, sa = display all ensembles, cn = change ensemble’s name,");
        System.out.println("u = undo, r = redo, l = list undo/redo, x = exit system");
        if (current != null) {
            System.out.println("The current ensemble is " + current.getEnsembleID() + " " + current.getEName() + ".");
        }
        System.out.print("Please enter command [ c | s | a | m | d | se | sa | cn | u | r | l | x ] :- ");
    }



}
