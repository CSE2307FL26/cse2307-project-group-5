package main;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private int accountID;
    private ArrayList<String> transactionHistory = new ArrayList<>();
    private boolean isOpen;
    private double loanBalance;

    public BankAccount() {
        this.balance = 0;
        this.isOpen = true;
        this.accountID = 0;
        this.loanBalance = 0;
    }

    public void deposit(double amount) {
        if (this.isOpen) {
            if (amount > 0) {
                this.balance += amount;
                transactionHistory.add("Deposit: $" + amount);
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalStateException();
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public void withdraw(double amount) {
        if (this.isOpen) {
            if (amount > 0 && this.balance >= amount) {
                this.balance -= amount;
                transactionHistory.add("Withdrawal: $" + amount);
            }
        } else {
            throw new IllegalStateException();
        }
    }

    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    public void closeAccount() {
        if (!this.isOpen) {
            throw new IllegalStateException();
        }

        if (this.loanBalance > 0) {
            if (this.balance >= this.loanBalance) {
                this.payBackLoan(this.loanBalance);
            } else {
                throw new IllegalStateException("Cannot close account with unpaid loan");
            }
        }
        this.withdraw(this.balance);
        this.isOpen = false;
    }

    public boolean isAccountOpen() {
        return this.isOpen;
    }

    public void transfer(BankAccount otherAccount, double amount) {
        if (this == otherAccount) {
            throw new IllegalArgumentException();
        }
        if (!this.isOpen || !otherAccount.isOpen) {
            throw new IllegalStateException();
        }
        if (amount <= 0 || amount > this.balance) {
            throw new IllegalArgumentException();
        }

        this.balance -= amount;
        otherAccount.balance += amount;
    }

    public int getAccountID() {
        return this.accountID;
    }

    public void loan(double amount) {
        if (!this.isOpen) {
            throw new IllegalStateException();
        }
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        this.balance += amount;
        this.loanBalance += amount;
    }

    public void payBackLoan(double amount) {
        if (!this.isOpen) {
            throw new IllegalStateException("Account is closed");
        }
        if (!(amount > 0)) {
            throw new IllegalArgumentException("Payment must be positive");
        }
        if (amount > this.loanBalance) {
            throw new IllegalArgumentException("Payment exceeds loan balance");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance -= amount;
        this.loanBalance -= amount;
    }

    public double getLoanBalance() {
        return this.loanBalance;
    }
}
