package com.academy.bank;

public class CurrentAccount extends Account implements Printable{
    private double transactionFee;

    public CurrentAccount(int accountNumber, double balance, Customer customer, double transactionFee) {
        super(accountNumber, balance, customer);
        this.transactionFee = transactionFee;
    }

    @Override
    double calculateCharges() {
        return transactionFee;
    }

    @Override
    String getAccountType() {
        return "Current";
    }

    @Override
    protected void displayAccount() {
        System.out.printf("%s%nAccount Number : %d%nCustomer : %s%nBalance : %.0f%nInterest Rate : %.0f%%%nCharge : %.0f%n",
        this.getAccountType(),
        super.getAccountNumber(),
        super.getCustomer().getName(),
        super.getBalance(),
        this.calculateInterest(), 
        this.calculateCharges());
    }

    @Override
    public void printDetails() {
        displayAccount();
    }
}
