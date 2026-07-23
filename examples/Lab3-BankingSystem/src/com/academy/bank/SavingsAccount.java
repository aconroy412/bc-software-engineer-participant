package com.academy.bank;

public class SavingsAccount extends Account implements Printable{
    private double interestRate;

    public SavingsAccount(int accountNumber, double balance, Customer customer, double interestRate) {
        super(accountNumber, balance, customer);
        this.interestRate = interestRate;
    }

    @Override
    double calculateInterest() {
        return super.getBalance() * interestRate / 100.0;
    }

    @Override
    String getAccountType() {
        return "Savings";
    }

    // implement display account
    @Override
    protected void displayAccount() {
        System.out.printf("%s%nAccount Number : %d%nCustomer : %s%nBalance : %.0f%nInterest Rate : %.0f%%%nInterest : %.0f%n",
        this.getAccountType(),
        super.getAccountNumber(),
        super.getCustomer().getName(),
        super.getBalance(),
        this.calculateInterest(), 
        this.calculateInterest());
    }

    @Override
    public void printDetails() {
        // TODO Auto-generated method stub
        displayAccount();
    }
}
