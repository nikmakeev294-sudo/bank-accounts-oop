package org.example.bank.observer;

import org.example.bank.model.Transaction;

public class ConsoleTransactionObserver implements TransactionObserver {
    @Override
    public void update(Transaction transaction) {
        System.out.println("Notification: transaction completed - " +
                transaction.getType() + ", amount: " +
                transaction.getAmount());
    }
}