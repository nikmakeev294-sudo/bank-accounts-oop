package org.example.bank.facade;

import org.example.bank.command.BankCommand;
import org.example.bank.command.DepositCommand;
import org.example.bank.command.TransferCommand;
import org.example.bank.command.WithdrawCommand;
import org.example.bank.factory.AccountData;
import org.example.bank.factory.AccountFactory;
import org.example.bank.factory.AccountType;
import org.example.bank.model.Account;
import org.example.bank.model.Bank;
import org.example.bank.model.Transaction;
import org.example.bank.observer.ConsoleTransactionObserver;
import org.example.bank.service.AccountService;
import org.example.bank.strategy.PercentFeeStrategy;
import org.example.bank.system.BankSystem;

import java.util.List;

public class BankFacade {
    private final Bank bank;
    private final AccountService accountService;
    private final AccountFactory accountFactory;

    public BankFacade() {
        this.bank = BankSystem.getInstance().getBank();
        this.accountService = new AccountService(bank, new PercentFeeStrategy(0.01));
        this.accountService.addObserver(new ConsoleTransactionObserver());
        this.accountFactory = new AccountFactory();
    }

    public void createAccount(AccountType type, String accountNumber, String ownerName, double initialBalance) {
        AccountData accountData = new AccountData.AccountBuilder()
                .type(type)
                .accountNumber(accountNumber)
                .ownerName(ownerName)
                .initialBalance(initialBalance)
                .build();

        accountService.addAccount(accountFactory.createAccount(accountData));
    }

    public void createCreditAccount(String accountNumber, String ownerName, double initialBalance, double creditLimit) {
        AccountData accountData = new AccountData.AccountBuilder()
                .type(AccountType.CREDIT)
                .accountNumber(accountNumber)
                .ownerName(ownerName)
                .initialBalance(initialBalance)
                .creditLimit(creditLimit)
                .build();

        accountService.addAccount(accountFactory.createAccount(accountData));
    }

    public void createSavingsAccount(String accountNumber, String ownerName, double initialBalance, double interestRate) {
        AccountData accountData = new AccountData.AccountBuilder()
                .type(AccountType.SAVINGS)
                .accountNumber(accountNumber)
                .ownerName(ownerName)
                .initialBalance(initialBalance)
                .interestRate(interestRate)
                .build();

        accountService.addAccount(accountFactory.createAccount(accountData));
    }

    public void deposit(String accountNumber, double amount) {
        BankCommand command = new DepositCommand(accountService, accountNumber, amount);
        command.execute();
    }

    public void withdraw(String accountNumber, double amount) {
        BankCommand command = new WithdrawCommand(accountService, accountNumber, amount);
        command.execute();
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        BankCommand command = new TransferCommand(accountService, fromAccountNumber, toAccountNumber, amount);
        command.execute();
    }

    public String getBankName() {
        return bank.getName();
    }

    public List<Account> getAccounts() {
        return bank.getAccounts();
    }

    public List<Transaction> getTransactions() {
        return bank.getTransactions();
    }
}