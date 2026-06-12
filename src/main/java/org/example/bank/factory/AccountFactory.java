package org.example.bank.factory;

import org.example.bank.model.Account;
import org.example.bank.model.CreditAccount;
import org.example.bank.model.DebitAccount;
import org.example.bank.model.SavingsAccount;

public class AccountFactory {
    private static final double DEFAULT_CREDIT_LIMIT = 1000;
    private static final double DEFAULT_INTEREST_RATE = 0.05;

    public Account createAccount(AccountType type, String accountNumber, String ownerName, double initialBalance) {
        switch (type) {
            case DEBIT:
                return new DebitAccount(accountNumber, ownerName, initialBalance);
            case CREDIT:
                return new CreditAccount(accountNumber, ownerName, initialBalance, DEFAULT_CREDIT_LIMIT);
            case SAVINGS:
                return new SavingsAccount(accountNumber, ownerName, initialBalance, DEFAULT_INTEREST_RATE);
            default:
                throw new IllegalArgumentException("Unknown account type: " + type);
        }
    }
}