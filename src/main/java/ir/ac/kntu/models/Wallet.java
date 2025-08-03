package ir.ac.kntu.models;


import java.util.ArrayList;
import java.util.List;

import static ir.ac.kntu.util.PrintHelper.printError;

public class Wallet {
    private double balance;
    private final boolean canCharge;
    private List<Transaction> transactions;

    public Wallet(boolean canDeposit) {
        this.balance = 0.0;
        this.canCharge = canDeposit;
        transactions= new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "balance=" + balance +
                ", canCharge=" + canCharge +
                ", transactions=" + transactions +
                '}';
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (!canCharge) {
            printError("Deposit not allowed for this wallet.");
            return false;
        }
        if (amount <= 0) {
            printError("Amount must be positive.");
            return false;
        }
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            printError("Amount must be positive.");
            return false;
        }
        if (amount > balance) {
            printError("Error: Withdrawal amount exceeds wallet balance.");
            return false;
        }
        balance -= amount;
        return true;
    }

    public double receivePaymentFromSale(double productPrice) {
        //for sellers: 90% of product price goes to wallet
        balance += productPrice * 0.9;
        return productPrice;
    }

    public void receiveBonus(Double amount) {
        if(!canCharge){
            balance += amount;
        }
    }
}
