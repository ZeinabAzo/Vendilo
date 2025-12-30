package ir.ac.kntu.data;

import ir.ac.kntu.models.Manager;

import java.util.ArrayList;
import java.util.List;

public class ManagerDB {

    private List<Manager> managers;

    public ManagerDB() {
        this.managers = new ArrayList<>();
    }

    public ManagerDB(List<Manager> managers) {
        this.managers =new ArrayList<>(managers);
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void addManager(Manager manager){
        managers.add(manager);
    }

    public Manager getManager(Manager manager){
        return managers.stream().filter(manager1 -> manager1.equals(manager)).findFirst().orElse(null);
    }

    //todo: create manager method and other necessary methods

    public int size() {
        return managers.size();
    }

    public boolean exists(String fName, String lName) {
        return managers.stream()
                .anyMatch(m -> fName.equals(m.getfName()) && lName.equals(m.getlName()));
    }

    public Manager findByUsername(String username){
        return managers.stream().filter(manager -> manager.getlName().equals(username))
                .findFirst().orElse(null);
    }
}
