package ir.ac.kntu.models;

public abstract class User {

    private String fName;
    private String lName;
    protected String password;
    private Wallet wallet;

    public User(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    public String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
