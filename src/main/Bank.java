package main;

import java.util.ArrayList;

public class Bank {
    private int accountID;
    private ArrayList<String> customerAccounts;



    public Bank(){
    this.accountID = 0;
    this.customerAccounts = new ArrayList<>();
    }

    public int makeAccountID(){
        int idNumber = this.accountID;
        this.accountID++;
        return idNumber;
    }
}
