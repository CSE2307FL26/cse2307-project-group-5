package test;

import main.BankAccount;

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


}
