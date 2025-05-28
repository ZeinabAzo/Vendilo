package ir.ac.kntu.models;

import java.time.LocalDate;

public class Complaint {

    private String userID;
    private String context;
    private String response;
    private boolean responseStatus;

    public Complaint(String context, String userID, boolean responseStatus) {
        this.context = context;
        this.userID = userID;
        this.responseStatus=false;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
        setResponseStatus(true);
    }

    public boolean isResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(boolean responseStatus) {
        this.responseStatus = responseStatus;
    }
}
