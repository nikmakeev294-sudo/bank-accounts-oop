package org.example.bank.model;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final String name;
    private final List<Account> accounts;
    private final List<Transaction> transactions;

    public Bank(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
}