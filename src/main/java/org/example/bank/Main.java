package org.example.bank;

import org.example.bank.exception.BankException;
import org.example.bank.model.Account;
import org.example.bank.model.CreditAccount;
import org.example.bank.model.DebitAccount;
import org.example.bank.model.SavingsAccount;

public class Main {
    public static void main(String[] args) {
        try {
            Account debitAccount = new DebitAccount("UA001", "Ivan Petrenko", 1000);
            Account creditAccount = new CreditAccount("UA002", "Olena Shevchenko", 500, 1000);
            SavingsAccount savingsAccount = new SavingsAccount("UA003", "Andrii Bondarenko", 2000, 0.05);

            debitAccount.withdraw(300);
            creditAccount.withdraw(1200);
            savingsAccount.addInterest();

            printAccount(debitAccount);
            printAccount(creditAccount);
            printAccount(savingsAccount);
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