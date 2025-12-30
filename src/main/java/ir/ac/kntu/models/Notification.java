package ir.ac.kntu.models;

public class Notification {

    private String massage;
    private String senderUsername;

    public Notification(String massage,String senderUsername) {
        this.massage = massage;
        this.senderUsername = senderUsername;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    @Override
    public String toString() {
        return  "¦  ⁂Notification⁂  ¦" +
                "¦  massage='" + massage + '\'' +
                "¦  senderUsername='" + senderUsername + '\'';
    }
}
