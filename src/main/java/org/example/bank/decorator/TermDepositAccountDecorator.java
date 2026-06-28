package org.example.bank.decorator;

import org.example.bank.exception.InvalidAmountException;
import org.example.bank.model.Account;

public class TermDepositAccountDecorator extends AccountDecorator {
    private final int termMonths;
    private final double annualInterestRate;

    public TermDepositAccountDecorator(Account account, int termMonths, double annualInterestRate) {
        super(account);

        if (termMonths <= 0) {
            throw new InvalidAmountException(termMonths);
        }

        if (annualInterestRate < 0) {
            throw new InvalidAmountException(annualInterestRate);
        }

        this.termMonths = termMonths;
        this.annualInterestRate = annualInterestRate;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public double calculateInterestForTerm() {
        return getBalance() * annualInterestRate * termMonths / 12;
    }

    public void addInterestForTerm() {
        double interest = calculateInterestForTerm();
        account.deposit(interest);
    }

    public String getTermMessage() {
        return String.format(
                "Term deposit %s for %d months completed. Interest rate: %.2f%%",
                getAccountNumber(),
                termMonths,
                annualInterestRate * 100
        );
    }
}