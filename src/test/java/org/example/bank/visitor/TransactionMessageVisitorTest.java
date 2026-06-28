package org.example.bank.visitor;

import org.example.bank.model.Transaction;
import org.example.bank.model.TransactionType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionMessageVisitorTest {
    @Test
    void visitorShouldCreateDepositMessage() {
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, null, "UA001", 300);
        TransactionMessageVisitor visitor = new TransactionMessageVisitor();

        transaction.accept(visitor);

        assertEquals(
                "Deposit operation: account UA001 received 300.00",
                visitor.getMessage()
        );
    }

    @Test
    void visitorShouldCreateWithdrawMessage() {
        Transaction transaction = new Transaction(TransactionType.WITHDRAW, "UA002", null, 1200);
        TransactionMessageVisitor visitor = new TransactionMessageVisitor();

        transaction.accept(visitor);

        assertEquals(
                "Withdraw operation: account UA002 spent 1200.00",
                visitor.getMessage()
        );
    }

    @Test
    void visitorShouldCreateTransferMessage() {
        Transaction transaction = new Transaction(TransactionType.TRANSFER, "UA003", "UA001", 500);
        TransactionMessageVisitor visitor = new TransactionMessageVisitor();

        transaction.accept(visitor);

        assertEquals(
                "Transfer operation: 500.00 from UA003 to UA001",
                visitor.getMessage()
        );
    }
}
