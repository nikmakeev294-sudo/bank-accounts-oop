package org.example.bank.factory;

import org.example.bank.model.Account;
import org.example.bank.model.CreditAccount;
import org.example.bank.model.DebitAccount;
import org.example.bank.model.SavingsAccount;

public class AccountFactory {
    public Account createAccount(AccountData data) {
        switch (data.getType()) {
            case DEBIT:
                return new DebitAccount(
                        data.getAccountNumber(),
                        data.getOwnerName(),
                        data.getInitialBalance()
                );
            case CREDIT:
                return new CreditAccount(
                        data.getAccountNumber(),
                        data.getOwnerName(),
                        data.getInitialBalance(),
                        data.getCreditLimit()
                );
            case SAVINGS:
                return new SavingsAccount(
                        data.getAccountNumber(),
                        data.getOwnerName(),
                        data.getInitialBalance(),
                        data.getInterestRate()
                );
            default:
                throw new IllegalArgumentException("Unknown account type: " + data.getType());
        }
    }
}