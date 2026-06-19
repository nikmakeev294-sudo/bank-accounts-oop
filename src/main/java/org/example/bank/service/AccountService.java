package org.example.bank.service;

import org.example.bank.exception.AccountNotFoundException;
import org.example.bank.model.Account;
import org.example.bank.model.Bank;
import org.example.bank.model.Transaction;
import org.example.bank.model.TransactionType;
import org.example.bank.observer.TransactionObserver;
import org.example.bank.strategy.FeeStrategy;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private final Bank bank;
    private final FeeStrategy feeStrategy;
    private final List<TransactionObserver> observers;

    public AccountService(Bank bank, FeeStrategy feeStrategy) {
        this.bank = bank;
        this.feeStrategy = feeStrategy;
        this.observers = new ArrayList<>();
    }

    public void addObserver(TransactionObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Transaction transaction) {
        for (TransactionObserver observer : observers) {
            observer.update(transaction);
        }
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

        Transaction transaction = new Transaction(
                TransactionType.DEPOSIT,
                null,
                accountNumber,
                amount
        );

        bank.addTransaction(transaction);
        notifyObservers(transaction);
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = findAccountByNumber(accountNumber);
        account.withdraw(amount);

        Transaction transaction = new Transaction(
                TransactionType.WITHDRAW,
                accountNumber,
                null,
                amount
        );

        bank.addTransaction(transaction);
        notifyObservers(transaction);
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = findAccountByNumber(fromAccountNumber);
        Account toAccount = findAccountByNumber(toAccountNumber);

        double fee = feeStrategy.calculateFee(amount);
        double totalAmount = amount + fee;

        fromAccount.withdraw(totalAmount);
        toAccount.deposit(amount);

        Transaction transaction = new Transaction(
                TransactionType.TRANSFER,
                fromAccountNumber,
                toAccountNumber,
                amount
        );

        bank.addTransaction(transaction);
        notifyObservers(transaction);
    }
}