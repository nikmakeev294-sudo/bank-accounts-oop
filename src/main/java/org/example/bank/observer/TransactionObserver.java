package org.example.bank.observer;

import org.example.bank.model.Transaction;

public interface TransactionObserver {
    void update(Transaction transaction);
}