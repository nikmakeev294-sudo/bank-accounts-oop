package org.example.bank;

import org.example.bank.facade.BankFacade;
import org.example.bank.factory.AccountType;
import org.example.bank.model.Account;
import org.example.bank.model.Transaction;

public class Main {
    public static void main(String[] args) {
        BankFacade bankFacade = new BankFacade();

        System.out.println("Bank: " + bankFacade.getBankName());
        System.out.println();

        bankFacade.createAccount(AccountType.DEBIT, "UA001", "Ivan Petrenko", 1000);
        bankFacade.createCreditAccount("UA002", "Olena Shevchenko", 500, 1000);
        bankFacade.createSavingsAccount("UA003", "Andrii Bondarenko", 2000, 0.05);

        bankFacade.createTermDepositAccount("UA004", "Ivan Petrenko", 1000, 0.06, 6);
        bankFacade.createTermDepositAccount("UA005", "Ivan Petrenko", 1000, 0.12, 12);

        System.out.println("Created accounts:");
        printAccounts(bankFacade);
        System.out.println();

        System.out.println("Operations:");
        bankFacade.deposit("UA001", 300);
        bankFacade.withdraw("UA002", 1200);
        bankFacade.transfer("UA003", "UA001", 500);

        bankFacade.deposit("UA004", 200);
        bankFacade.deposit("UA005", 400);

        System.out.println();
        System.out.println("Applying term deposit interest:");
        bankFacade.applyTermInterest("UA004");
        bankFacade.applyTermInterest("UA005");

        System.out.println();
        System.out.println("Final accounts:");
        printAccounts(bankFacade);

        System.out.println();
        System.out.println("Transaction history:");
        printTransactions(bankFacade);
    }

    private static void printAccounts(BankFacade bankFacade) {
        for (Account account : bankFacade.getAccounts()) {
            System.out.printf(
                    "%s | %s | balance: %.2f%n",
                    account.getAccountNumber(),
                    account.getOwnerName(),
                    account.getBalance()
            );
        }
    }

    private static void printTransactions(BankFacade bankFacade) {
        for (Transaction transaction : bankFacade.getTransactions()) {
            System.out.printf(
                    "%s | from: %s | to: %s | amount: %.2f | time: %s%n",
                    transaction.getType(),
                    transaction.getFromAccountNumber(),
                    transaction.getToAccountNumber(),
                    transaction.getAmount(),
                    transaction.getDateTime()
            );
        }
    }
}