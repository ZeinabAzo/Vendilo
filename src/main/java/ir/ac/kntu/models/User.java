package ir.ac.kntu.models;

public class User {

    private String fName;
    private String lName;
    private String password;
    private Wallet wallet;
    private Boolean isActive;

    public User(String fName, String lName, String password) {
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        isActive = true;
    }

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void banAccount(){
        isActive = false;
    }

    public void setPassword(String password) {
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

}
