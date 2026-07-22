package com.academy.bank;

public class Transaction {
    private int transactionId;
    private double amount;
    private String type;
    private String date;
    private String accountNumber;

    public Transaction(int transactionId, double amount, String type, String date, String accountNumber) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.accountNumber = accountNumber;
    }

    // display
    public void display() {
        System.out.printf("ID: %d, Amount: %.2f, Type: %s, Date: %s, Number: %.2f",
            this.transactionId, this.amount, this.type, this.date, this.accountNumber
        );
    }


    // getters 

    public int getTransactionId() {
        return this.transactionId;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getType() {
        return this.type;
    }

    public String getDate() {
        return this.date;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }
}
