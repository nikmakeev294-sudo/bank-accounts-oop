package org.example.bank.visitor;

import org.example.bank.model.Transaction;

public class TransactionMessageVisitor implements TransactionVisitor {
    private String message;

    @Override
    public void visitDeposit(Transaction transaction) {
        message = String.format(
                "Deposit operation: account %s received %.2f",
                transaction.getToAccountNumber(),
                transaction.getAmount()
        );
    }

    @Override
    public void visitWithdraw(Transaction transaction) {
        message = String.format(
                "Withdraw operation: account %s spent %.2f",
                transaction.getFromAccountNumber(),
                transaction.getAmount()
        );
    }

    @Override
    public void visitTransfer(Transaction transaction) {
        message = String.format(
                "Transfer operation: %.2f from %s to %s",
                transaction.getAmount(),
                transaction.getFromAccountNumber(),
                transaction.getToAccountNumber()
        );
    }

    public String getMessage() {
        return message;
    }
}
