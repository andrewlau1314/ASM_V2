public class MusicianFactory {
    public Musician createMusician(String mID, String mName){
        Musician musician = new Musician(mID);
        musician.setName(mName);
        return musician;
    };
}
