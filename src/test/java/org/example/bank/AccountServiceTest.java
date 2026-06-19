package org.example.bank;

import org.example.bank.exception.AccountNotFoundException;
import org.example.bank.exception.InsufficientFundsException;
import org.example.bank.exception.InvalidAmountException;
import org.example.bank.model.Bank;
import org.example.bank.model.CreditAccount;
import org.example.bank.model.DebitAccount;
import org.example.bank.service.AccountService;
import org.example.bank.strategy.NoFeeStrategy;
import org.example.bank.strategy.PercentFeeStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountServiceTest {
    @Test
    void depositShouldIncreaseBalance() {
        Bank bank = new Bank("Test Bank");
        AccountService accountService = new AccountService(bank, new NoFeeStrategy());

        accountService.addAccount(new DebitAccount("UA001", "Test User", 1000));
        accountService.deposit("UA001", 500);

        double balance = accountService.findAccountByNumber("UA001").getBalance();
        assertEquals(1500, balance, 0.0001);
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        Bank bank = new Bank("Test Bank");
        AccountService accountService = new AccountService(bank, new NoFeeStrategy());

        accountService.addAccount(new DebitAccount("UA001", "Test User", 1000));
        accountService.withdraw("UA001", 300);

        double balance = accountService.findAccountByNumber("UA001").getBalance();
        assertEquals(700, balance, 0.0001);
    }

    @Test
    void withdrawShouldThrowExceptionWhenNotEnoughMoney() {
        Bank bank = new Bank("Test Bank");
        AccountService accountService = new AccountService(bank, new NoFeeStrategy());

        accountService.addAccount(new DebitAccount("UA001", "Test User", 1000));

        assertThrows(InsufficientFundsException.class,
                () -> accountService.withdraw("UA001", 1500));
    }

    @Test
    void transferShouldApplyFee() {
        Bank bank = new Bank("Test Bank");
        AccountService accountService = new AccountService(bank, new PercentFeeStrategy(0.01));

        accountService.addAccount(new DebitAccount("UA001", "First User", 1000));
        accountService.addAccount(new DebitAccount("UA002", "Second User", 0));

        accountService.transfer("UA001", "UA002", 100);

        double firstBalance = accountService.findAccountByNumber("UA001").getBalance();
        double secondBalance = accountService.findAccountByNumber("UA002").getBalance();

        assertEquals(899, firstBalance, 0.0001);
        assertEquals(100, secondBalance, 0.0001);
    }

    @Test
    void depositShouldThrowExceptionWhenAmountIsInvalid() {
        Bank bank = new Bank("Test Bank");
        AccountService accountService = new AccountService(bank, new NoFeeStrategy());

        accountService.addAccount(new DebitAccount("UA001", "Test User", 1000));

        assertThrows(InvalidAmountException.class,
                () -> accountService.deposit("UA001", -100));
    }

    @Test
    void operationShouldThrowExceptionWhenAccountNotFound() {
        Bank bank = new Bank("Test Bank");
        AccountService accountService = new AccountService(bank, new NoFeeStrategy());

        assertThrows(AccountNotFoundException.class,
                () -> accountService.deposit("UNKNOWN", 100));
    }

    @Test
    void creditAccountShouldAllowBalanceWithinCreditLimit() {
        Bank bank = new Bank("Test Bank");
        AccountService accountService = new AccountService(bank, new NoFeeStrategy());

        accountService.addAccount(new CreditAccount("UA001", "Credit User", 500, 1000));
        accountService.withdraw("UA001", 1200);

        double balance = accountService.findAccountByNumber("UA001").getBalance();
        assertEquals(-700, balance, 0.0001);
    }
}