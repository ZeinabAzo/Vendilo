package ir.ac.kntu.models;

import ir.ac.kntu.enums.ReportType;

import java.util.List;

public class Admin extends User {

    private List<ReportType> accesses;

    public Admin(String fName, String userName, String password, List<ReportType> accesses) {
        super(fName, userName, password);
        this.setActive(true);
        this.accesses = accesses;
    }

    public List<ReportType> getAccesses() {
        return accesses;
    }

    public void setAccesses(List<ReportType> accesses) {
        this.accesses = accesses;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "firstName='" + getfName() + '\'' +
                ", userName='" + getlName() + '\'' +
                ", active=" + getActive() +
                ", accesses=" + accesses +
                '}';
    }
}
