package org.example.bank.decorator;

import org.example.bank.exception.InvalidAmountException;
import org.example.bank.model.Account;
import org.example.bank.model.SavingsAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TermDepositAccountDecoratorTest {
    @Test
    void calculateInterestForSixMonthDeposit() {
        Account account = new SavingsAccount("UA100", "Test Owner", 1000, 0.06);
        TermDepositAccountDecorator depositAccount = new TermDepositAccountDecorator(account, 6, 0.06);

        double interest = depositAccount.calculateInterestForTerm();

        assertEquals(30.0, interest, 0.001);
    }

    @Test
    void calculateInterestForOneYearDeposit() {
        Account account = new SavingsAccount("UA101", "Test Owner", 1000, 0.12);
        TermDepositAccountDecorator depositAccount = new TermDepositAccountDecorator(account, 12, 0.12);

        double interest = depositAccount.calculateInterestForTerm();

        assertEquals(120.0, interest, 0.001);
    }

    @Test
    void addInterestForTermShouldIncreaseBalance() {
        Account account = new SavingsAccount("UA102", "Test Owner", 1000, 0.06);
        TermDepositAccountDecorator depositAccount = new TermDepositAccountDecorator(account, 6, 0.06);

        depositAccount.addInterestForTerm();

        assertEquals(1030.0, depositAccount.getBalance(), 0.001);
    }

    @Test
    void decoratedAccountShouldKeepOriginalAccountData() {
        Account account = new SavingsAccount("UA103", "Test Owner", 1000, 0.06);
        TermDepositAccountDecorator depositAccount = new TermDepositAccountDecorator(account, 6, 0.06);

        assertEquals("UA103", depositAccount.getAccountNumber());
        assertEquals("Test Owner", depositAccount.getOwnerName());
        assertEquals(1000.0, depositAccount.getBalance(), 0.001);
    }

    @Test
    void constructorShouldThrowExceptionForInvalidTerm() {
        Account account = new SavingsAccount("UA104", "Test Owner", 1000, 0.06);

        assertThrows(
                InvalidAmountException.class,
                () -> new TermDepositAccountDecorator(account, 0, 0.06)
        );
    }
}
