package ir.ac.kntu.models;

public class Manager extends User {

    private int level;

    public Manager(String fName, String lName, String password, int creatorLevel) {
        super(fName, lName, password);
        level = creatorLevel + 1;
    }

    public int getLevel() {
        return level;
    }


}