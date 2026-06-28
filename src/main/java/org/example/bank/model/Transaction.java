package org.example.bank.model;

import org.example.bank.visitor.TransactionVisitor;

import java.time.LocalDateTime;

public class Transaction {
    private final TransactionType type;
    private final String fromAccountNumber;
    private final String toAccountNumber;
    private final double amount;
    private final LocalDateTime dateTime;

    public Transaction(TransactionType type, String fromAccountNumber, String toAccountNumber, double amount) {
        this.type = type;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    public TransactionType getType() {
        return type;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void accept(TransactionVisitor visitor) {
        switch (type) {
            case DEPOSIT -> visitor.visitDeposit(this);
            case WITHDRAW -> visitor.visitWithdraw(this);
            case TRANSFER -> visitor.visitTransfer(this);
        }
    }
}
