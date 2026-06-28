package org.example.bank.observer;

import org.example.bank.model.Transaction;
import org.example.bank.visitor.TransactionMessageVisitor;

public class ConsoleTransactionObserver implements TransactionObserver {
    @Override
    public void update(Transaction transaction) {
        TransactionMessageVisitor visitor = new TransactionMessageVisitor();
        transaction.accept(visitor);

        System.out.println("Notification: " + visitor.getMessage());
    }
}
