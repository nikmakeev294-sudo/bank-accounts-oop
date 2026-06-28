package org.example.bank.decorator;

import org.example.bank.model.Account;

public abstract class AccountDecorator extends Account {
    protected final Account account;

    public AccountDecorator(Account account) {
        super(account.getAccountNumber(), account.getOwnerName(), account.getBalance());
        this.account = account;
    }

    @Override
    public String getAccountNumber() {
        return account.getAccountNumber();
    }

    @Override
    public String getOwnerName() {
        return account.getOwnerName();
    }

    @Override
    public double getBalance() {
        return account.getBalance();
    }

    @Override
    public void deposit(double amount) {
        account.deposit(amount);
    }

    @Override
    public void withdraw(double amount) {
        account.withdraw(amount);
    }
}