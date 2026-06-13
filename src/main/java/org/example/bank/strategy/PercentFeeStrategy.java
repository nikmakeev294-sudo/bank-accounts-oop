package org.example.bank.strategy;

public class PercentFeeStrategy implements FeeStrategy {
    private final double percent;

    public PercentFeeStrategy(double percent) {
        this.percent = percent;
    }

    @Override
    public double calculateFee(double amount) {
        return amount * percent;
    }
}