import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EnsembleManager manager = new EnsembleManager();
        Invoker invoker = new Invoker();

        // small seed data (optional) - none added here

        while (true) {
            printMenu(manager.getCurrentEnsemble());
            String cmd = sc.nextLine().trim().toLowerCase();
            if (cmd.equals("x")) {
                break;
            }

            switch (cmd) {
                case "c" -> handleCreate(manager, invoker, sc);
                case "s" -> handleSetCurrent(manager, invoker, sc);
                case "a" -> handleAddMusician(manager, invoker, sc);
                case "m" -> handleModifyInstrument(manager, invoker, sc);
                case "d" -> handleDelete(manager, invoker, sc);
                case "cn" -> handleChangeName(manager, invoker, sc);
                case "se" -> invoker.executeCommand(new ShowCurrentEnsembleCommand(manager), "Show current ensemble", false);
                case "sa" -> invoker.executeCommand(new DisplayAllEnsemblesCommand(manager), "Display all ensembles", false);
                case "u" -> invoker.undo();
                case "r" -> invoker.redo();
                case "l" -> invoker.showUndoRedoList();
                default -> System.out.println("Invalid command.");
            }
        }

        sc.close();
        System.out.println("Exiting MEMS. Bye.");
    }


    private static void handleCreate(EnsembleManager manager, Invoker invoker, Scanner sc) {
        System.out.print("Please input ensemble type (o = orchestra/ j = jazz):- ");
        String type = sc.nextLine().trim().toLowerCase();
        System.out.print("Please input ensemble ID:- ");
        String id = sc.nextLine().trim();
        System.out.print("Please input ensemble name:- ");
        String name = sc.nextLine();
        CreateEnsembleComand cmd = new CreateEnsembleComand(manager, type, id, name);
        String desc = "Create " + type + " ensemble, " + id + ", " + name;
        invoker.executeCommand(cmd, desc, true);
    }

    private static void handleSetCurrent(EnsembleManager manager, Invoker invoker, Scanner sc) {
        System.out.print("Please input ensemble ID:- ");
        String id = sc.nextLine().trim();
        Ensemble e = manager.getEnsembleById(id);
        if (e == null) {
            System.out.println("Ensemble " + id + " is not found!!");
            return;
        }
        manager.setCurrentEnsembleById(id);
        System.out.println("The current ensemble is changed to " + e.getEnsembleID() + " " + e.getEName() + ".");
        // Setting current ensemble is not recorded for undo/redo in this simplified invoker
    }

    private static void handleAddMusician(EnsembleManager manager, Invoker invoker, Scanner sc) {
        System.out.print("Please input musician information (id, name):- ");
        String line = sc.nextLine().trim();
        String[] parts = line.split(",");
        if (parts.length < 2) {
            System.out.println("Invalid input. Use: id, name");
            return;
        }
        String mID = parts[0].trim();
        String mName = parts[1].trim();
        int role = 0;
        Ensemble current = manager.getCurrentEnsemble();
        String roleName = "";
        if (current instanceof OrchestraEnsemble) {
            System.out.print("Instrument (1 = violinist | 2 = cellist ):- ");
            try { role = Integer.parseInt(sc.nextLine().trim()); } catch (Exception ex) { role = 0; }
            if (role == 1) roleName = "violinist";
            else if (role == 2) roleName = "cellist";
        } else if (current instanceof JazzBandEnsemble) {
            System.out.print("Instrument (1 = pianist | 2 = saxophonist | 3 = drummer ):- ");
            try { role = Integer.parseInt(sc.nextLine().trim()); } catch (Exception ex) { role = 0; }
            if (role == 1) roleName = "pianist";
            else if (role == 2) roleName = "saxophonist";
            else if (role == 3) roleName = "drummer";
        }
        AddMusicianCommand cmd = new AddMusicianCommand(manager, mID, mName, role);
        String desc = "Add musician, " + mID + ", " + mName + (roleName.isEmpty() ? "" : ", " + roleName);
        invoker.executeCommand(cmd, desc, true);
    }

    private static void handleModifyInstrument(EnsembleManager manager, Invoker invoker, Scanner sc) {
        System.out.print("Please input musician ID:- ");
        String mID = sc.nextLine().trim();
        System.out.print("Instrument (enter numeric role):- ");
        int role = 0;
        try { role = Integer.parseInt(sc.nextLine().trim()); } catch (Exception ex) { role = 0; }
        String roleName = Integer.toString(role);
        Ensemble current = manager.getCurrentEnsemble();
        if (current instanceof OrchestraEnsemble) {
            if (role == 1) roleName = "violinist"; else if (role == 2) roleName = "cellist";
        } else if (current instanceof JazzBandEnsemble) {
            if (role == 1) roleName = "pianist"; else if (role == 2) roleName = "saxophonist"; else if (role == 3) roleName = "drummer";
        }
        ModifyMusicianInstrumentCommand cmd = new ModifyMusicianInstrumentCommand(manager, mID, role);
        String desc = "Modify musician's instrument, " + mID + ", " + roleName;
        invoker.executeCommand(cmd, desc, true);
    }

    private static void handleDelete(EnsembleManager manager, Invoker invoker, Scanner sc) {
        System.out.print("Please input musician ID:- ");
        String mID = sc.nextLine().trim();
        DeleteMusicianCommand cmd = new DeleteMusicianCommand(manager, mID, "");
        String desc = "Delete musician, " + mID;
        invoker.executeCommand(cmd, desc, true);
    }

    private static void handleChangeName(EnsembleManager manager, Invoker invoker, Scanner sc) {
        System.out.print("Please input new name of the current ensemble:- ");
        String newName = sc.nextLine().trim();
        ChangeEnsembleNameCommand cmd = new ChangeEnsembleNameCommand(manager, newName);
        String currentId = manager.getCurrentEnsemble()!=null?manager.getCurrentEnsemble().getEnsembleID():"";
        String desc = "Change ensemble's name, " + currentId + ", " + newName;
        invoker.executeCommand(cmd, desc, true);
    }


    private static void printMenu(Ensemble current) {
        System.out.println("\nMusic Ensembles Management System (MEMS)");
        System.out.println("c = create ensemble, s = set current ensemble, a = add musician, m = modify musician's instrument,");
        System.out.println("d = delete musician, se = show ensemble, sa = display all ensembles, cn = change ensemble's name,");
        System.out.println("u = undo, r = redo, l = list undo/redo, x = exit system");
        if (current != null) {
            System.out.println("The current ensemble is " + current.getEnsembleID() + " " + current.getEName() + ".");
        }
        System.out.print("Please enter command [ c | s | a | m | d | se | sa | cn | u | r | l | x ] :- ");
    }
}
