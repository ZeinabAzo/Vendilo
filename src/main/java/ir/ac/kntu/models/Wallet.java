package ir.ac.kntu.models;

public class Wallet {
    private double balance;
    private boolean canCharge;

    public Wallet(boolean canDeposit) {
        this.balance = 0.0;
        this.canCharge = canDeposit;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (!canCharge) {
            //"Deposit not allowed for this wallet."
            return false;
        }
        if (amount <= 0) {
            //"Amount must be positive."
            return false;
        }
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            //"Amount must be positive."
            return false;
        }
        if (amount > balance) {
            //"Error: Withdrawal amount exceeds wallet balance."
            return false;
        }
        balance -= amount;
        return true;
    }

    public void receivePaymentFromSale(double productPrice) {
        //for sellers: 90% of product price goes to wallet
        if (!canCharge) {
            balance += productPrice * 0.9;
        }
    }
}
