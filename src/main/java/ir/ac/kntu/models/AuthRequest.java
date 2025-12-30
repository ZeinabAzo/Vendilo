package ir.ac.kntu.models;

import ir.ac.kntu.enums.ReportType;

public class AuthRequest extends Report {

    private boolean isAccepted;

    public AuthRequest(String context, String userID, ReportType reportType) {
        super(context, userID, reportType);
    }


    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    @Override
    public String toString() {
        return "AuthRequest{" +
                "isAccepted=" + isAccepted +
                "response=" + getResponse() +
                '}';
    }
}
