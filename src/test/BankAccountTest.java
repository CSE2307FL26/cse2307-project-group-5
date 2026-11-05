package test;

import main.BankAccount;
import main.MainMenu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

<<<<<<< HEAD
    public void testCloseEmptyAccount() {
        BankAccount emptyAccount = new BankAccount();
        emptyAccount.closeAccount();
        assertFalse(isAccountOpen(emptyAccount));
    }

    public void testCloseNonEmptyAccount() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        testAccount.closeAccount();
        assertFalse(isAccountOpen(testAccount));
    }


=======
    @Test
    public void testWithdraw(){
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.withdraw(20);
        assertEquals(80, testAccount.getBalance(), 0.01);
    }

>>>>>>> ddc3e5f1f93e193ca487d409864ac085d885bb2f
    @Test 
    public void testCreateAdditionalAccount() {
        MainMenu testMenu = new MainMenu();
        testMenu.createAccount();
        testMenu.createAccount();
        assertEquals(3, testMenu.getNumOfAccounts());
    }

    
}
