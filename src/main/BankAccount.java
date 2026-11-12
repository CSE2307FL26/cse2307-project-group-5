package main;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private int accountID;
    private ArrayList<String> transactionHistory = new ArrayList<>();
    private boolean isOpen;

    public BankAccount() {
        this.balance = 0;
        this.isOpen = true;
        this.accountID = 0;
    }

    public void deposit(double amount) {
        if(this.isOpen){
            if(amount > 0) {
                this.balance += amount;
                transactionHistory.add("Deposit: $" + amount);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public void withdraw(double amount){
        if (this.isOpen){
            if(amount>0 && this.balance >= amount){
                this.balance -= amount;
                transactionHistory.add("Withdrawal: $" + amount);
            }
        }
    }
  
    public List<String> getTransactionHistory() {
      return new ArrayList<>(transactionHistory);
    }

    public void closeAccount(){
        if (!this.isOpen){
            return;
        }

        this.withdraw(this.balance);
        this.isOpen = false;
    }

    public boolean isAccountOpen(){
        return this.isOpen;
    }

     public int getAccountID(){
        return this.accountID;
    }
}
