package main;

import java.util.Scanner;
import java.util.ArrayList; 
import java.util.List;

public class MainMenu {

    private static final int EXIT_SELECTION = 4;
	private static final int MAX_SELECTION = 4;

    private BankAccount userAccount;
	private ArrayList<BankAccount> accounts;
    private Scanner keyboardInput;

    public MainMenu() {
        this.userAccount = new BankAccount();
        this.keyboardInput = new Scanner(System.in);
        this.accounts = new ArrayList<>();
        this.accounts.add(userAccount);
    }

    public void displayOptions() {
        System.out.println("Welcome to the 237 Bank App!");
        
        System.out.println("1. Make a deposit");
        System.out.println("2. Make a withdrawl");
        System.out.println("3. View Transaction History");
        System.out.println("4. Exit the app");

    }

    public int getUserSelection(int max) {
        int selection = -1;
        while(selection < 1 || selection > max) {
            System.out.print("Please make a selection: ");
            selection = keyboardInput.nextInt();
        }
        return selection;
    }

    public void processInput(int selection) {
        switch (selection) {
            case 1:
                performDeposit();
                break;
            case 2:
                performWithdrawl();
                break;
            case 3:
                viewTransactionHistory();
                break;
            case 4:
                break;
        }
    }

    public void performDeposit() {
        double depositAmount = -1;
        while(depositAmount < 0) {
            System.out.print("How much would you like to deposit: ");
            depositAmount = keyboardInput.nextInt();
        }
        userAccount.deposit(depositAmount);
    }

    public void performWithdrawl() {
        double withdrawlAmount = -1;
        while(withdrawlAmount < 0) {
            System.out.print("How much would you like to withdraw: ");
            withdrawlAmount = keyboardInput.nextInt();
        }
        userAccount.withdraw(withdrawlAmount);
    }

    public void run() {
        int selection = -1;
        while(selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            processInput(selection);
        }
    }

    public void createAccount() {
        accounts.add(new BankAccount());
    }

    public int getNumOfAccounts() {
        return accounts.size();
    }
 
    public void viewTransactionHistory() {
        List<String> history = userAccount.getTransactionHistory();

        if(history.isEmpty()) {
            System.out.println("No transactions have occurred.");
        } else {
            System.out.println("Transaction History: ");
            
            for (int i = 0; i < history.size(); i++){
                System.out.println(history.get(i));
            }
        }
    }
    



    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
    }

}
