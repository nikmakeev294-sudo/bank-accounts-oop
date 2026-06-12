package org.example.bank.exception;

public class InsufficientFundsException extends BankException {
    public InsufficientFundsException(double balance, double amount) {
        super("Not enough money. Balance: " + balance + ", requested amount: " + amount);
    }
}