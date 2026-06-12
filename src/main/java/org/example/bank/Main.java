package org.example.bank;

import org.example.bank.exception.BankException;
import org.example.bank.model.Account;
import org.example.bank.model.Bank;
import org.example.bank.model.CreditAccount;
import org.example.bank.model.DebitAccount;
import org.example.bank.model.SavingsAccount;
import org.example.bank.service.AccountService;

public class Main {
    public static void main(String[] args) {
        try {
            Bank bank = new Bank("Student Bank");
            AccountService accountService = new AccountService(bank);

            accountService.addAccount(new DebitAccount("UA001", "Ivan Petrenko", 1000));
            accountService.addAccount(new CreditAccount("UA002", "Olena Shevchenko", 500, 1000));
            accountService.addAccount(new SavingsAccount("UA003", "Andrii Bondarenko", 2000, 0.05));

            accountService.deposit("UA001", 300);
            accountService.withdraw("UA002", 1200);
            accountService.transfer("UA003", "UA001", 500);

            System.out.println("Bank: " + bank.getName());

            for (Account account : bank.getAccounts()) {
                printAccount(account);
            }
        } catch (BankException e) {
            System.out.println("Operation error: " + e.getMessage());
        }
    }

    private static void printAccount(Account account) {
        System.out.println(account.getAccountNumber() + " | " +
                account.getOwnerName() + " | balance: " +
                account.getBalance());
    }
}