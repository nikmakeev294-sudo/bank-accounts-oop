package org.example.bank;

import org.example.bank.exception.BankException;
import org.example.bank.factory.AccountFactory;
import org.example.bank.factory.AccountType;
import org.example.bank.factory.AccountData;
import org.example.bank.model.Account;
import org.example.bank.model.Bank;
import org.example.bank.model.Transaction;
import org.example.bank.service.AccountService;
import org.example.bank.system.BankSystem;

public class Main {
    public static void main(String[] args) {
        try {
            Bank bank = BankSystem.getInstance().getBank();
            AccountService accountService = new AccountService(bank);
            AccountFactory accountFactory = new AccountFactory();

            accountService.addAccount(accountFactory.createAccount(
                    new AccountData.AccountBuilder()
                            .type(AccountType.DEBIT)
                            .accountNumber("UA001")
                            .ownerName("Ivan Petrenko")
                            .initialBalance(1000)
                            .build()
            ));

            accountService.addAccount(accountFactory.createAccount(
                    new AccountData.AccountBuilder()
                            .type(AccountType.CREDIT)
                            .accountNumber("UA002")
                            .ownerName("Olena Shevchenko")
                            .initialBalance(500)
                            .creditLimit(1000)
                            .build()
            ));

            accountService.addAccount(accountFactory.createAccount(
                    new AccountData.AccountBuilder()
                            .type(AccountType.SAVINGS)
                            .accountNumber("UA003")
                            .ownerName("Andrii Bondarenko")
                            .initialBalance(2000)
                            .interestRate(0.05)
                            .build()
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