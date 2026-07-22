package com.academy.bank;

public abstract class Account {
    private int accountNumber;
    private double balance;
    private Customer customer;

    protected Account(int accountNumber, double balance, Customer customer) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customer = customer;
    }

    // various getters
    public int getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    // setter
    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        // check for negative
        if (amount < 0.0) {
            System.out.println("Error: Cannot deposit a negative amount");

        }

        // add the result
        else {
            this.balance += amount;
        }
    }

    public boolean withdraw (double amount) {
        // get the real withdrawl
        double trueCharge = amount + calculateCharges();

        // check if possible
        if (balance - (trueCharge) >= 0.0) {
            balance -= trueCharge;
            return true;
        }
        else {
            return false;
        }
    }

    // abstract display, can't do it yet since its abstract
    protected abstract void displayAccount();

    double calculateCharges() {
        return 0.0;
    }

    double calculateInterest() {
        return 0.0;
    }

    String getAccountType() {
        return "Account";
    }


}


