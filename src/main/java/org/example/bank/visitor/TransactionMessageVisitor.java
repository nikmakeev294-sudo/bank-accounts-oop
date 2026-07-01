package org.example.bank.visitor;

import org.example.bank.model.Transaction;

import java.util.Locale;

public class TransactionMessageVisitor implements TransactionVisitor {
    private String message;

    @Override
    public void visitDeposit(Transaction transaction) {
        message = String.format(
                Locale.US,
                "Deposit operation: account %s received %.2f",
                transaction.getToAccountNumber(),
                transaction.getAmount()
        );
    }

    @Override
    public void visitWithdraw(Transaction transaction) {
        message = String.format(
                Locale.US,
                "Withdraw operation: account %s spent %.2f",
                transaction.getFromAccountNumber(),
                transaction.getAmount()
        );
    }

    @Override
    public void visitTransfer(Transaction transaction) {
        message = String.format(
                Locale.US,
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