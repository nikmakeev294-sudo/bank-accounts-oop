package org.example.bank.visitor;

import org.example.bank.model.Transaction;

public interface TransactionVisitor {
    void visitDeposit(Transaction transaction);

    void visitWithdraw(Transaction transaction);

    void visitTransfer(Transaction transaction);
}
