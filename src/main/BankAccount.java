package main;

public class BankAccount {

    private double balance;
    private boolean isOpen;

    public BankAccount() {
        this.balance = 0;
        this.isOpen = true;
    }

    public void deposit(double amount) {
        if(this.isOpen){
            if(amount > 0) {
                this.balance += amount;
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
            }
        }
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
}
