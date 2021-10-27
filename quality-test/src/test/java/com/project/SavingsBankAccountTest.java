package com.project;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SavingsBankAccountTest {
  private SavingsBankAccount savingsBankAccount;

  @Before
  public void setup() {
    this.savingsBankAccount = new SavingsBankAccount(new InterestCalculator());
  }

  @Test
  public void GetBalance() {
    String accountNumber = "0123456789";

    double result = savingsBankAccount.GetBalance(accountNumber);

    assertEquals(5009000, result, 0);
  }
}
