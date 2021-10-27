package com.project;

public class SavingsBankAccount {
    private InterestCalculator interestCalculator;

    public SavingsBankAccount(InterestCalculator interestCalculator)
    {
      this.interestCalculator = interestCalculator;
    }

    public double GetBalance(String accountNumber)
    {
      float fee = 1000;
      double currentBalance = 10000;

      double actualBalance = currentBalance + this.interestCalculator.CalculateInterest(currentBalance) - fee;

      return actualBalance;
    }
}

