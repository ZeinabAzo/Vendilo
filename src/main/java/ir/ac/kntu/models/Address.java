package ir.ac.kntu.models;

public class Address {
    private String title;
    private String state;
    private String city;

    public Address(String title, String state, String city) {
        this.title = title;
        this.state = state;
        this.city = city;
    }

    public Address(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "title='" + title + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

}
