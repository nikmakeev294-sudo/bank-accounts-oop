package org.example.bank.system;

import org.example.bank.model.Bank;

public class BankSystem {
    private static BankSystem instance;
    private final Bank bank;

    private BankSystem() {
        this.bank = new Bank("Student Bank");
    }

    public static BankSystem getInstance() {
        if (instance == null) {
            instance = new BankSystem();
        }

        return instance;
    }

    public Bank getBank() {
        return bank;
    }
}