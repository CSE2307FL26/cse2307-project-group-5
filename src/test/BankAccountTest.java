package test;

import main.BankAccount;
import main.MainMenu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

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

    
}
