package main;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private ArrayList<String> transactionHistory = new ArrayList<>();

    public BankAccount() {
        this.balance = 0;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
            transactionHistory.add("Deposit: $" + amount);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public void withdraw(double amount){
        if(amount > 0 && this.balance >= amount){
            this.balance -= amount;
            transactionHistory.add("Withdrawal: $" + amount);
        }
    }

    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
}
