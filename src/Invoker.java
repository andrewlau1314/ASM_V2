import java.util.*;

public class Invoker {
    private static class Record {
        Command cmd;
        String desc;
        public Record(Command cmd, String desc) { this.cmd = cmd; this.desc = desc; }
    }

    private final Deque<Record> undoStack = new ArrayDeque<>();
    private final Deque<Record> redoStack = new ArrayDeque<>();

    // execute and recordable flag to decide whether to put into undo list
    public void executeCommand(Command cmd, String desc, boolean recordable) {
        cmd.execute();
        if (recordable) {
            undoStack.push(new Record(cmd, desc));
            redoStack.clear();
        }
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        Record r = undoStack.pop();
        r.cmd.undo();
        redoStack.push(r);
        System.out.println("Command (" + r.desc + ") is undone.");
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo.");
            return;
        }
        Record r = redoStack.pop();
        r.cmd.redo();
        undoStack.push(r);
        System.out.println("Command (" + r.desc + ") is redone.");
    }

    public void showUndoRedoList() {
        System.out.println("Undo List");
        if (undoStack.isEmpty()) {
            System.out.println("-- End of undo list --");
        } else {
            // print from bottom to top for chronological order
            List<Record> list = new ArrayList<>(undoStack);
            Collections.reverse(list);
            for (Record r : list) System.out.println(r.desc);
            System.out.println("-- End of undo list --");
        }

        System.out.println("Redo List");
        if (redoStack.isEmpty()) {
            System.out.println("-- End of redo list --");
        } else {
            List<Record> list = new ArrayList<>(redoStack);
            Collections.reverse(list);
            for (Record r : list) System.out.println(r.desc);
            System.out.println("-- End of redo list --");
        }
    }
}

