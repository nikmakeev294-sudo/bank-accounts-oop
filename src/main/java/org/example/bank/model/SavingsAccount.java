package org.example.bank.model;

import org.example.bank.exception.InsufficientFundsException;
import org.example.bank.exception.InvalidAmountException;

public class SavingsAccount extends Account {
    private final double interestRate;

    public SavingsAccount(String accountNumber, String ownerName, double initialBalance, double interestRate) {
        super(accountNumber, ownerName, initialBalance);

        if (interestRate < 0) {
            throw new InvalidAmountException(interestRate);
        }

        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void addInterest() {
        double interest = getBalance() * interestRate;
        changeBalance(interest);
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