package org.example.bank;

import org.example.bank.exception.BankException;
import org.example.bank.factory.AccountFactory;
import org.example.bank.factory.AccountType;
import org.example.bank.model.Account;
import org.example.bank.model.Bank;
import org.example.bank.model.Transaction;
import org.example.bank.service.AccountService;

public class Main {
    public static void main(String[] args) {
        try {
            Bank bank = new Bank("Student Bank");
            AccountService accountService = new AccountService(bank);
            AccountFactory accountFactory = new AccountFactory();

            accountService.addAccount(accountFactory.createAccount(
                    AccountType.DEBIT,
                    "UA001",
                    "Ivan Petrenko",
                    1000
            ));

            accountService.addAccount(accountFactory.createAccount(
                    AccountType.CREDIT,
                    "UA002",
                    "Olena Shevchenko",
                    500
            ));

            accountService.addAccount(accountFactory.createAccount(
                    AccountType.SAVINGS,
                    "UA003",
                    "Andrii Bondarenko",
                    2000
            ));

            accountService.deposit("UA001", 300);
            accountService.withdraw("UA002", 1200);
            accountService.transfer("UA003", "UA001", 500);

            System.out.println("Bank: " + bank.getName());
            System.out.println();

            System.out.println("Accounts:");
            for (Account account : bank.getAccounts()) {
                printAccount(account);
            }

            System.out.println();
            System.out.println("Transactions:");
            for (Transaction transaction : bank.getTransactions()) {
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