import java.util.*;
import java.util.Iterator;


public abstract class Ensemble {
     protected String ensembleID;
     protected String eName;
     protected AbstractList musicians;

     public Ensemble(String eID ) {
            this.ensembleID = eID;
            musicians = new Vector<Musician>();
     }

        public String getEnsembleID() { return ensembleID; }
        public String getEName() { return eName; }
        public void setName(String name) { this.eName = name; }
        public void addMusician(Musician m) { musicians.add(m); }
        public void dropMusician(Musician m) { musicians.remove(m); }
        public Iterator<Musician> getMusicians() { return musicians.iterator(); }

        public abstract void updateMusicianRole();
        public abstract void showEnsemble();

}
