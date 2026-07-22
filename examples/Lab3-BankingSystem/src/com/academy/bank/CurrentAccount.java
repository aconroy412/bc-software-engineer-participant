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
        System.out.printf("Type: %s, Number: %d, Name: %s, Balance: %.2f, Charges: %.2f%n",
            this.getAccountType(), super.getAccountNumber(), super.getCustomer().getName(), super.getBalance(), this.calculateCharges()
         );
    }

    @Override
    public void printDetails() {
        displayAccount();
    }
}
