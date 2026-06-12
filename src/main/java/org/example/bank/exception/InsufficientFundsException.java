package org.example.bank.exception;

public class InsufficientFundsException extends BankException {
    public InsufficientFundsException(double availableAmount, double requestedAmount) {
        super("Not enough money. Available: " + availableAmount + ", requested amount: " + requestedAmount);
    }
}