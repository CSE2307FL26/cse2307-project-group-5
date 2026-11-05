package test;

import main.BankAccount;
import main.MainMenu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;
import java.util.List;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidDeposit() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.deposit(-50);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test
    public void testWithdraw(){
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.withdraw(20);
        assertEquals(80, testAccount.getBalance(), 0.01);
    }

    @Test 
    public void testCreateAdditionalAccount() {
        MainMenu testMenu = new MainMenu();
        testMenu.createAccount();
        testMenu.createAccount();
        assertEquals(3, testMenu.getNumOfAccounts());
    }

    @Test
    public void testEmptyTransactionHistory() {
        BankAccount testAccount = new BankAccount();

        assertEquals(0, testAccount.getTransactionHistory().size());
    }

    @Test
    public void testDepositHistory() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);

        List<String> history = testAccount.getTransactionHistory();

        assertEquals(1, history.size());
        assertEquals("Deposit: $50.0", history.get(0)); 
    }

    @Test 
    public void testWithdrawalHistory() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.withdraw(20);

        List<String> history = testAccount.getTransactionHistory();

        assertEquals(2, history.size());
        assertEquals("Withdrawal: $20.0", history.get(1)); 
    }

    @Test 
    public void testMultipleTransactions() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.withdraw(20);
        testAccount.deposit(50);

        List<String> history = testAccount.getTransactionHistory();

        assertEquals(3, history.size());
        assertEquals("Deposit: $100.0", history.get(0)); 
        assertEquals("Withdrawal: $20.0", history.get(1)); 
        assertEquals("Deposit: $50.0", history.get(2)); 
    }

    

    
}
