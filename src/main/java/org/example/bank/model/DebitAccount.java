package org.example.bank.model;

import org.example.bank.exception.InsufficientFundsException;

public class DebitAccount extends Account {
    public DebitAccount(String accountNumber, String ownerName, double initialBalance) {
        super(accountNumber, ownerName, initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        validatePositiveAmount(amount);

        if (amount > getBalance()) {
            throw new InsufficientFundsException(getBalance(), amount);
        }

        changeBalance(-amount);
    }
}