package org.example.bank;

import org.example.bank.exception.BankException;
import org.example.bank.model.Account;

public class Main {
    public static void main(String[] args) {
        try {
            Account account = new Account("UA001", "Ivan Petrenko", 1000);

            System.out.println("Owner: " + account.getOwnerName());
            System.out.println("Account number: " + account.getAccountNumber());
            System.out.println("Initial balance: " + account.getBalance());

            account.deposit(500);
            System.out.println("After deposit: " + account.getBalance());

            account.withdraw(200);
            System.out.println("After withdraw: " + account.getBalance());

            account.withdraw(2000);
        } catch (BankException e) {
            System.out.println("Operation error: " + e.getMessage());
        }
    }
}