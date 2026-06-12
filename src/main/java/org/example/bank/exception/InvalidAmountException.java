package org.example.bank.exception;

public class InvalidAmountException extends BankException {
    public InvalidAmountException(double amount) {
        super("Amount must be positive. Current amount: " + amount);
    }
}