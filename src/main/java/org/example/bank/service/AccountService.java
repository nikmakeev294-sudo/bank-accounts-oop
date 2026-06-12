package org.example.bank.service;

import org.example.bank.exception.AccountNotFoundException;
import org.example.bank.model.Account;
import org.example.bank.model.Bank;
import org.example.bank.model.Transaction;
import org.example.bank.model.TransactionType;

public class AccountService {
    private final Bank bank;

    public AccountService(Bank bank) {
        this.bank = bank;
    }

    public void addAccount(Account account) {
        bank.addAccount(account);
    }

    public Account findAccountByNumber(String accountNumber) {
        for (Account account : bank.getAccounts()) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        throw new AccountNotFoundException(accountNumber);
    }

    public void deposit(String accountNumber, double amount) {
        Account account = findAccountByNumber(accountNumber);
        account.deposit(amount);

        bank.addTransaction(new Transaction(
                TransactionType.DEPOSIT,
                null,
                accountNumber,
                amount
        ));
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = findAccountByNumber(accountNumber);
        account.withdraw(amount);

        bank.addTransaction(new Transaction(
                TransactionType.WITHDRAW,
                accountNumber,
                null,
                amount
        ));
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = findAccountByNumber(fromAccountNumber);
        Account toAccount = findAccountByNumber(toAccountNumber);

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        bank.addTransaction(new Transaction(
                TransactionType.TRANSFER,
                fromAccountNumber,
                toAccountNumber,
                amount
        ));
    }
}