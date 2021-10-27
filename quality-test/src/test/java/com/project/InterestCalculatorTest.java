package com.project;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InterestCalculatorTest {
  private InterestCalculator interestCalculator;

  @Before
  public void setup() {
    this.interestCalculator = new InterestCalculator();
  }

  @Test
  public void CalculateInterest() {
    // check for whole numbers
    double amount = 2;
    double result = interestCalculator.CalculateInterest(amount);
    assertEquals(1000, result, 0);

    // check for single digit decimals
    amount = 0.5;
    result = interestCalculator.CalculateInterest(amount);
    assertEquals(250, result, 0);

    // check for double-digit decimals
    amount = 0.02;
    result = interestCalculator.CalculateInterest(amount);
    assertEquals(10, result, 0);

    // check for negatives
    amount = -2;
    result = interestCalculator.CalculateInterest(amount);
    assertEquals(-1000, result, 0);
  }
}
