package test;

import main.BankAccount;
import main.MainMenu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

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
    public void testCreateAdditionalAccount() {
        MainMenu testMenu = new MainMenu();
        testMenu.createAccount();
        testMenu.createAccount();
        assertEquals(3, testMenu.getNumOfAccounts());
    }

    @Test
    public void testNewAccountIsOpen() {
        BankAccount testAccount = new BankAccount();
        assertTrue(testAccount.isAccountOpen());
    }

    @Test
    public void testCloseEmptyAccount() {
        BankAccount emptyAccount = new BankAccount();

        emptyAccount.closeAccount();
        assertFalse(emptyAccount.isAccountOpen());
    }

    @Test
    public void testCloseNonEmptyAccount() {
        BankAccount testAccount = new BankAccount();

        testAccount.deposit(50);
        testAccount.closeAccount();
        assertFalse(testAccount.isAccountOpen());
        assertEquals(0, testAccount.getBalance(), .01);
    }

    @Test
    public void testCloseOneOfMultipleAccounts() {
        BankAccount firstAccount = new BankAccount();
        BankAccount secondAccount = new BankAccount();
        ArrayList<BankAccount> accounts = new ArrayList<>();

        accounts.add(firstAccount);
        accounts.add(secondAccount);

        firstAccount.closeAccount();
        assertFalse(firstAccount.isAccountOpen());
        assertTrue(secondAccount.isAccountOpen());
    }

    @Test
    public void testDepositIntoClosedAccount() {
        BankAccount testAccount = new BankAccount();

        testAccount.closeAccount();

        assertThrows(IllegalStateException.class, () -> testAccount.deposit(50));
        assertEquals(0, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testWithdrawFromClosedAccount() {
        BankAccount testAccount = new BankAccount();

        testAccount.deposit(50);
        testAccount.closeAccount();

        assertThrows(IllegalStateException.class, () -> testAccount.withdraw(50));
    }

    @Test
    public void testValidTransfer() {
        BankAccount startingAccount = new BankAccount();
        BankAccount targetAccount = new BankAccount();

        startingAccount.deposit(60);
        startingAccount.transfer(targetAccount, 50);

        assertEquals(10, startingAccount.getBalance(), 0.01);
        assertEquals(50, targetAccount.getBalance(), 0.01);
    }

    @Test
    public void testTransferFromOpenAccountToClosedAccount() {
        BankAccount startingAccount = new BankAccount();
        BankAccount targetAccount = new BankAccount();

        startingAccount.deposit(60);
        targetAccount.closeAccount();

        assertThrows(IllegalStateException.class, () -> startingAccount.transfer(targetAccount, 50));

        assertEquals(60, startingAccount.getBalance(), 0.01);
        assertEquals(0, targetAccount.getBalance(), 0.01);

    }

    @Test
    public void testTransferFromClosedAccountToOpenAccount() {
        BankAccount startingAccount = new BankAccount();
        BankAccount targetAccount = new BankAccount();

        startingAccount.deposit(60);
        startingAccount.closeAccount();

        assertThrows(IllegalStateException.class, () -> startingAccount.transfer(targetAccount, 50));
        assertEquals(0, startingAccount.getBalance(), 0.01);
        assertEquals(0, targetAccount.getBalance(), 0.01);
    }

    @Test
    public void testTransferFromClosedAccountToClosedAccount() {
        BankAccount startingAccount = new BankAccount();
        BankAccount targetAccount = new BankAccount();

        startingAccount.deposit(60);
        startingAccount.closeAccount();
        targetAccount.closeAccount();

        assertThrows(IllegalStateException.class, () -> startingAccount.transfer(targetAccount, 50));

        assertEquals(0, startingAccount.getBalance(), 0.01);
        assertEquals(0, targetAccount.getBalance(), 0.01);
    }

    @Test
    public void testTransferFromEmptyAccount() {
        BankAccount startingAccount = new BankAccount();
        BankAccount targetAccount = new BankAccount();

        assertThrows(IllegalArgumentException.class,
                () -> startingAccount.transfer(targetAccount, 50));

        assertEquals(0, startingAccount.getBalance(), 0.01);
        assertEquals(0, targetAccount.getBalance(), 0.01);
    }

    @Test
    public void testTransferFromAccountToItself() {
        BankAccount startingAccount = new BankAccount();

        startingAccount.deposit(60);

        try {
            startingAccount.transfer(startingAccount, 50);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }

        assertEquals(60, startingAccount.getBalance(), 0.01);
    }

    @Test
    public void testTransferZero() {
        BankAccount startingAccount = new BankAccount();
        BankAccount targetAccount = new BankAccount();

        startingAccount.deposit(60);

        assertThrows(IllegalArgumentException.class,
                () -> startingAccount.transfer(targetAccount, 0));

        assertEquals(60, startingAccount.getBalance(), 0.01);
        assertEquals(0, targetAccount.getBalance(), 0.01);
    }

    @Test
    public void testTransferNegativeAmount() {
        BankAccount startingAccount = new BankAccount();
        BankAccount targetAccount = new BankAccount();

        startingAccount.deposit(60);

        assertThrows(IllegalArgumentException.class,
                () -> startingAccount.transfer(targetAccount, -10));

        assertEquals(60, startingAccount.getBalance(), 0.01);
        assertEquals(0, targetAccount.getBalance(), 0.01);
    }

    @Test
    public void checkBalanceForAnyAccount() {
        BankAccount checkingAccount = new BankAccount();
        BankAccount savingsAccount = new BankAccount();
        checkingAccount.deposit(100);
        savingsAccount.deposit(200);
        assertEquals(100, checkingAccount.getBalance(), 0.01);
        assertEquals(200, savingsAccount.getBalance(), 0.01);
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

    // Loan Testing
    @Test
    public void testLoanToEmptyAccount() {
        BankAccount testAccount = new BankAccount();

        testAccount.loan(50);

        assertEquals(50, testAccount.getBalance(), 0.01);
        assertEquals(50, testAccount.getLoanBalance(), 0.01);

    }

    @Test
    public void testLoanAlreadyMoneyInAccount() {
        BankAccount testAccount = new BankAccount();

        testAccount.deposit(50);
        testAccount.loan(50);

        assertEquals(100, testAccount.getBalance(), 0.01);
        assertEquals(50, testAccount.getLoanBalance(), 0.01);
    }

    @Test
    public void testLoanThenWithdraw() {
        BankAccount testAccount = new BankAccount();

        testAccount.loan(50);
        testAccount.withdraw(50);

        assertEquals(0, testAccount.getBalance(), 0.01);
        assertEquals(50, testAccount.getLoanBalance(), 0.01);
    }

    @Test
    public void testLoanToClosedAccount() {
        BankAccount testAccount = new BankAccount();

        testAccount.closeAccount();

        assertThrows(IllegalStateException.class, () -> testAccount.loan(50));

        assertEquals(0, testAccount.getBalance(), 0.01);
        assertEquals(0, testAccount.getLoanBalance(), 0.01);
    }

    @Test
    public void testLoanNegativeAmount() {
        BankAccount testAccount = new BankAccount();

        assertThrows(IllegalArgumentException.class, () -> testAccount.loan(-10));

        assertEquals(0, testAccount.getBalance(), 0.01);
        assertEquals(0, testAccount.getLoanBalance(), 0.01);
    }

    @Test
    public void testLoanZero() {
        BankAccount testAccount = new BankAccount();

        assertThrows(IllegalArgumentException.class, () -> testAccount.loan(0));

        assertEquals(0, testAccount.getBalance(), 0.01);
        assertEquals(0, testAccount.getLoanBalance(), 0.01);
    }

    @Test
    public void testPayBackLoan() {
        BankAccount testAccount = new BankAccount();
        testAccount.loan(50);
        // testAccount.deposit(100);
        testAccount.payBackLoan(50);

        assertEquals(0, testAccount.getBalance(), 0.01);
        assertEquals(0, testAccount.getLoanBalance(), 0.01);
    }

    @Test
    public void testPayBackLoanInsufficientFunds() {
        BankAccount testAccount = new BankAccount();

        testAccount.loan(50);
        testAccount.withdraw(25);

        assertThrows(IllegalArgumentException.class, () -> testAccount.payBackLoan(50));

        assertEquals(25, testAccount.getBalance(), 0.01);
        assertEquals(50, testAccount.getLoanBalance(), 0.01);
    }

    @Test
    public void testPayBackLoanPartialAmount() {
        BankAccount testAccount = new BankAccount();

        testAccount.loan(50);
        // testAccount.deposit(100);
        testAccount.payBackLoan(25);

        assertEquals(25, testAccount.getBalance(), 0.01);
        assertEquals(25, testAccount.getLoanBalance(), 0.01);
    }

    @Test
    public void testCloseFullAccountWithLoan() {
        BankAccount testAccount = new BankAccount();

        testAccount.loan(50);
        testAccount.deposit(50);

        testAccount.closeAccount();

        assertFalse(testAccount.isAccountOpen());
        assertEquals(0, testAccount.getBalance(), 0.01);
        assertEquals(0, testAccount.getLoanBalance(), 0.01);
    }

    @Test
    public void testCloseEmptyAccountWithLoan() {
        BankAccount testAccount = new BankAccount();

        testAccount.loan(50);
        testAccount.withdraw(50);

        assertThrows(IllegalStateException.class, () -> testAccount.closeAccount());

        assertTrue(testAccount.isAccountOpen());
        assertEquals(0, testAccount.getBalance(), 0.01);
        assertEquals(50, testAccount.getLoanBalance(), 0.01);
    }

    @Test
    public void testCloseAccountWithInsufficientFundsForLoan() {
        BankAccount testAccount = new BankAccount();

        testAccount.loan(50);
        testAccount.withdraw(25);

        assertThrows(IllegalStateException.class, () -> testAccount.closeAccount());

        assertTrue(testAccount.isAccountOpen());
        assertEquals(25, testAccount.getBalance(), 0.01);
        assertEquals(50, testAccount.getLoanBalance(), 0.01);
    }

    @Test
    public void testPayBackLoanWithNoLoan() {
        BankAccount testAccount = new BankAccount();

        testAccount.deposit(50);
        assertThrows(IllegalArgumentException.class, () -> testAccount.payBackLoan(10));

        assertEquals(50, testAccount.getBalance(), 0.1);
    }

    @Test
    public void testPayBackZeroOrNegative() {

        BankAccount testAccount = new BankAccount();
        testAccount.loan(50);

        assertThrows(IllegalArgumentException.class, () -> testAccount.payBackLoan(0));
        assertThrows(IllegalArgumentException.class, () -> testAccount.payBackLoan(-5));

        assertEquals(50, testAccount.getLoanBalance(), 0.1);

    }

    @Test
    public void testMultipleLoansAccumulate() {

        BankAccount testAccount = new BankAccount();
        testAccount.loan(50);
        testAccount.loan(25);

        assertEquals(75, testAccount.getBalance(), 0.1);
        assertEquals(75, testAccount.getLoanBalance(), 0.1);
    }

    @Test
    public void testPayBackMoreThanLoan() {
        BankAccount testAccount = new BankAccount();
        testAccount.loan(50);
        testAccount.deposit(100);

        assertThrows(IllegalArgumentException.class, () -> testAccount.payBackLoan(100));

        assertEquals(150, testAccount.getBalance(), 0.1);
        assertEquals(50, testAccount.getLoanBalance(), 0.1);
    }

    @Test
    public void testPayBackLoanClosedAccount() {
        BankAccount testAccount = new BankAccount();
        testAccount.closeAccount();

        assertThrows(IllegalStateException.class, () -> testAccount.payBackLoan(10));
    }
}
