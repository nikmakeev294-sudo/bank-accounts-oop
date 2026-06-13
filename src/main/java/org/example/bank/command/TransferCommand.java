package org.example.bank.command;

import org.example.bank.service.AccountService;

public class TransferCommand implements BankCommand {
    private final AccountService accountService;
    private final String fromAccountNumber;
    private final String toAccountNumber;
    private final double amount;

    public TransferCommand(AccountService accountService, String fromAccountNumber, String toAccountNumber, double amount) {
        this.accountService = accountService;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
    }

    @Override
    public void execute() {
        accountService.transfer(fromAccountNumber, toAccountNumber, amount);
    }
}