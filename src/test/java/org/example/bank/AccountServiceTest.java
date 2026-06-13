package org.example.bank;

import org.example.bank.exception.InsufficientFundsException;
import org.example.bank.model.Bank;
import org.example.bank.model.DebitAccount;
import org.example.bank.service.AccountService;
import org.example.bank.strategy.NoFeeStrategy;
import org.example.bank.strategy.PercentFeeStrategy;

public class AccountServiceTest {
    public static void main(String[] args) {
        testDeposit();
        testWithdraw();
        testWithdrawWithNotEnoughMoney();
        testTransferWithFee();

        System.out.println("All tests passed.");
    }

    private static void testDeposit() {
        Bank bank = new Bank("Test Bank");
        AccountService accountService = new AccountService(bank, new NoFeeStrategy());

        accountService.addAccount(new DebitAccount("UA001", "Test User", 1000));
        accountService.deposit("UA001", 500);

        double balance = accountService.findAccountByNumber("UA001").getBalance();
        assertEquals(1500, balance, "Deposit should increase balance");
    }

    private static void testWithdraw() {
        Bank bank = new Bank("Test Bank");
        AccountService accountService = new AccountService(bank, new NoFeeStrategy());

        accountService.addAccount(new DebitAccount("UA001", "Test User", 1000));
        accountService.withdraw("UA001", 300);

        double balance = accountService.findAccountByNumber("UA001").getBalance();
        assertEquals(700, balance, "Withdraw should decrease balance");
    }

    private static void testWithdrawWithNotEnoughMoney() {
        Bank bank = new Bank("Test Bank");
        AccountService accountService = new AccountService(bank, new NoFeeStrategy());

        accountService.addAccount(new DebitAccount("UA001", "Test User", 1000));

        try {
            accountService.withdraw("UA001", 1500);
            throw new AssertionError("Expected InsufficientFundsException");
        } catch (InsufficientFundsException e) {
            // expected result
        }
    }

    private static void testTransferWithFee() {
        Bank bank = new Bank("Test Bank");
        AccountService accountService = new AccountService(bank, new PercentFeeStrategy(0.01));

        accountService.addAccount(new DebitAccount("UA001", "First User", 1000));
        accountService.addAccount(new DebitAccount("UA002", "Second User", 0));

        accountService.transfer("UA001", "UA002", 100);

        double firstBalance = accountService.findAccountByNumber("UA001").getBalance();
        double secondBalance = accountService.findAccountByNumber("UA002").getBalance();

        assertEquals(899, firstBalance, "Transfer should withdraw amount with fee");
        assertEquals(100, secondBalance, "Transfer should deposit only transfer amount");
    }

    private static void assertEquals(double expected, double actual, String message) {
        if (Math.abs(expected - actual) > 0.0001) {
            throw new AssertionError(message + ". Expected: " + expected + ", actual: " + actual);
        }
    }
}