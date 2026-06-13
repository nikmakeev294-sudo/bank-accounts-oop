package org.example.bank.command;

import org.example.bank.service.AccountService;

public class DepositCommand implements BankCommand {
    private final AccountService accountService;
    private final String accountNumber;
    private final double amount;

    public DepositCommand(AccountService accountService, String accountNumber, double amount) {
        this.accountService = accountService;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    @Override
    public void execute() {
        accountService.deposit(accountNumber, amount);
    }
}