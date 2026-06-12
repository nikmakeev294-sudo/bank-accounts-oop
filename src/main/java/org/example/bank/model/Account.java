package org.example.bank.model;

import org.example.bank.exception.InsufficientFundsException;
import org.example.bank.exception.InvalidAmountException;

public class Account {
    private final String accountNumber;
    private final String ownerName;
    private double balance;

    public Account(String accountNumber, String ownerName, double initialBalance) {
        if (initialBalance < 0) {
            throw new InvalidAmountException(initialBalance);
        }

        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        validatePositiveAmount(amount);
        balance += amount;
    }

    public void withdraw(double amount) {
        validatePositiveAmount(amount);

        if (amount > balance) {
            throw new InsufficientFundsException(balance, amount);
        }

        balance -= amount;
    }

    private void validatePositiveAmount(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }
    }
}