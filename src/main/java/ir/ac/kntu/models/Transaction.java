package ir.ac.kntu.models;

import java.time.LocalDate;

public class Transaction {

    private LocalDate date;
    private double amount;
    private double trackingID;

    public Transaction(double trackingID, double amount, LocalDate date) {
        this.trackingID = trackingID;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(){
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTrackingID() {
        return trackingID;
    }

    public void setTrackingID(double trackingID) {
        this.trackingID = trackingID;
    }
}
