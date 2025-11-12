public class Musician {
    protected String musicianID;
    protected String mName;
    protected int role;

    public Musician(String mID) {
        this.musicianID = mID;
    }

    public String getMusicianID() { return musicianID; }
    public String getMName() { return mName; }
    public int getRole() { return role; }
    public void setRole(int role) { this.role = role; }
    public void setName(String name) { this.mName = name; }
}
