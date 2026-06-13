package org.example.bank;

import org.example.bank.exception.BankException;
import org.example.bank.facade.BankFacade;
import org.example.bank.factory.AccountType;
import org.example.bank.model.Account;
import org.example.bank.model.Transaction;

public class Main {
    public static void main(String[] args) {
        try {
            BankFacade bankFacade = new BankFacade();

            bankFacade.createAccount(AccountType.DEBIT, "UA001", "Ivan Petrenko", 1000);
            bankFacade.createCreditAccount("UA002", "Olena Shevchenko", 500, 1000);
            bankFacade.createSavingsAccount("UA003", "Andrii Bondarenko", 2000, 0.05);

            bankFacade.deposit("UA001", 300);
            bankFacade.withdraw("UA002", 1200);
            bankFacade.transfer("UA003", "UA001", 500);

            System.out.println("Bank: " + bankFacade.getBankName());
            System.out.println();

            System.out.println("Accounts:");
            for (Account account : bankFacade.getAccounts()) {
                printAccount(account);
            }

            System.out.println();
            System.out.println("Transactions:");
            for (Transaction transaction : bankFacade.getTransactions()) {
                printTransaction(transaction);
            }
        } catch (BankException e) {
            System.out.println("Operation error: " + e.getMessage());
        }
    }

    private static void printAccount(Account account) {
        System.out.printf("%s | %s | balance: %.2f%n",
                account.getAccountNumber(),
                account.getOwnerName(),
                account.getBalance());
    }

    private static void printTransaction(Transaction transaction) {
        System.out.printf("%s | %s | from: %s | to: %s | amount: %.2f%n",
                transaction.getDateTime(),
                transaction.getType(),
                transaction.getFromAccountNumber(),
                transaction.getToAccountNumber(),
                transaction.getAmount());
    }
}