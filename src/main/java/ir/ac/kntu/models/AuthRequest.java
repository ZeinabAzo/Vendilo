package ir.ac.kntu.models;

public class AuthRequest {

    private Seller seller;
    private boolean isAccepted;
    private String response;


    public AuthRequest(Seller seller, String response) {
        this.seller = seller;
        this.isAccepted = false;
        this.response = response;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public String getResponse() {
        return response;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "AuthRequest{" +
                "seller=" + seller +
                ", isAccepted=" + isAccepted +
                ", response='" + response + '\'' +
                '}';
    }
}
