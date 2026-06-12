package org.example.bank.factory;

public class AccountData {
    private final AccountType type;
    private final String accountNumber;
    private final String ownerName;
    private final double initialBalance;
    private final double creditLimit;
    private final double interestRate;

    private AccountData(AccountBuilder builder) {
        this.type = builder.type;
        this.accountNumber = builder.accountNumber;
        this.ownerName = builder.ownerName;
        this.initialBalance = builder.initialBalance;
        this.creditLimit = builder.creditLimit;
        this.interestRate = builder.interestRate;
    }

    public AccountType getType() {
        return type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public static class AccountBuilder {
        private AccountType type;
        private String accountNumber;
        private String ownerName;
        private double initialBalance;
        private double creditLimit = 1000;
        private double interestRate = 0.05;

        public AccountBuilder type(AccountType type) {
            this.type = type;
            return this;
        }

        public AccountBuilder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public AccountBuilder ownerName(String ownerName) {
            this.ownerName = ownerName;
            return this;
        }

        public AccountBuilder initialBalance(double initialBalance) {
            this.initialBalance = initialBalance;
            return this;
        }

        public AccountBuilder creditLimit(double creditLimit) {
            this.creditLimit = creditLimit;
            return this;
        }

        public AccountBuilder interestRate(double interestRate) {
            this.interestRate = interestRate;
            return this;
        }

        public AccountData build() {
            return new AccountData(this);
        }
    }
}
