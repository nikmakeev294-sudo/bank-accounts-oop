package org.example.bank.model;

import org.example.bank.exception.InsufficientFundsException;
import org.example.bank.exception.InvalidAmountException;

public class CreditAccount extends Account {
    private final double creditLimit;

    public CreditAccount(String accountNumber, String ownerName, double initialBalance, double creditLimit) {
        super(accountNumber, ownerName, initialBalance);

        if (creditLimit < 0) {
            throw new InvalidAmountException(creditLimit);
        }

        this.creditLimit = creditLimit;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    @Override
    public void withdraw(double amount) {
        validatePositiveAmount(amount);

        double availableAmount = getBalance() + creditLimit;

        if (amount > availableAmount) {
            throw new InsufficientFundsException(availableAmount, amount);
        }

        changeBalance(-amount);
    }
}