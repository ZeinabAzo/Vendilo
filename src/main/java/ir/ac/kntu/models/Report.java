package ir.ac.kntu.models;

import ir.ac.kntu.enums.ReportType;

public class Report {

    private String userID;
    private String context;
    private String response;
    private boolean responseStatus;
    private ReportType reportType;

    public Report(String context, String userID, ReportType reportType) {
        this.context = context;
        this.userID = userID;
        this.responseStatus=false;
        this.reportType = reportType;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
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

    @Override
    public String toString() {
        return "Complaint{" +
                "userID='" + userID + '\'' +
                ", context='" + context + '\'' +
                ", response='" + response + '\'' +
                ", responseStatus=" + responseStatus +
                '}';
    }
}
