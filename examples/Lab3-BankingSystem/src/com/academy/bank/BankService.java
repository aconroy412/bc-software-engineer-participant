package com.academy.bank;

import java.util.Scanner;

public class BankService {
    private static final int MAX_CUSTOMERS = 50;
    private static final int MAX_ACCOUNTS = 100;
    private static final int MAX_TRANSACTIONS = 500;

    private final Customer[] customers = new Customer[MAX_CUSTOMERS];
    private final Account[] accounts = new Account[MAX_ACCOUNTS];
    private final Transaction[] transactions = new Transaction[MAX_TRANSACTIONS];

    private int customerCount = 0;
    private int accountCount = 0;
    private int transactionCount = 0;
    private int nextAccountNumber = 10001;
    private int nextTransactionNumber = 1;

    private final Scanner scanner;

    public BankService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void createCustomer() {
        String id = "";
        while (id.isEmpty() || id.isBlank()) {
            System.out.println("Please enter a name for the customer: ");
            id = scanner.nextLine();
        }

        String name = "";
        while (name.isEmpty() || name.isBlank()) {
            System.out.println("Please enter a name for the customer: ");
            name = scanner.nextLine();
        }

        String email= "" ;
        while (email.isEmpty() || name.isBlank()) {
            System.out.println("Please enter an email address: ");
            email = scanner.nextLine();
        }

        String phone = "";
        while (phone.isEmpty() || phone.isBlank())
        {
            System.out.println("Please enter a valid phone number for the customer: ");
            phone = scanner.nextLine();
        }

        customers[customerCount++] = new Customer(id, name, email, phone);
        System.out.println("Customer Created Successfully!");
    }

    public void createSavingsAccount(String id, double balance, double interest) {
        Customer cust = findCustomer(id);
        // check if customer exists
        if (cust == null) {
            System.out.println("Can't find customer by that ID");
            return;
        }

        SavingsAccount created = new SavingsAccount(nextAccountNumber++, balance, cust, interest);

        accounts[accountCount++] = created;
        System.out.println("Successfully created account : " + (accountCount - 1) +  " " + balance + " " + id + " " + interest);
    }

    public void createCurrentAccount(String id, double balance, double charge) {
        Customer cust = findCustomer(id);
        // check if customer exists
        if (cust == null) {
            System.out.println("Can't find customer by that ID");
            return;
        }

        Account created = new CurrentAccount(nextAccountNumber++, balance, cust, charge);

        accounts[accountCount++] = created;
        System.out.println("Successfully created account : " + (accountCount - 1) +  " " + balance + " " + id + " " + charge);
    }

    public void deposit(int accountNumber, double amount) {
        Account acc = null;

        for (int i = 0; i < accountCount; i++) {
            if (accountNumber == accounts[i].getAccountNumber())
                acc = accounts[i];
        }

        if (acc == null) {
            System.out.println("No account by that number found");
            return;
        }

        acc.deposit(amount);

        transactions[transactionCount++] = new Transaction(nextTransactionNumber++, amount, acc.getAccountType(), "today", acc.getAccountNumber());

        System.out.println("Balance updated: " + acc.getBalance());
    }

    public void withdraw(int accountNumber, double amount) {
        Account acc = null;

        for (int i = 0; i < accountCount; i++) {
            if (accountNumber == accounts[i].getAccountNumber())
                acc = accounts[i];
        }

        if (acc == null) {
            System.out.println("No account by that number found");
            return;
        }

        acc.withdraw(amount);

        //update transation
        transactions[transactionCount++] = new Transaction(nextTransactionNumber++, amount, acc.getAccountType(), "today", acc.getAccountNumber());

        System.out.println("Balance updated: " + acc.getBalance() + "Fee: " + acc.calculateCharges());
    }

    public void displayAccounts() {
        for (int i = 0; i < accountCount; i++) {
            accounts[i].displayAccount();
            System.out.println("----------------------------------");
        }
    }

    public void displayCustomers() {
        for (int i = 0; i < customerCount; i++) {
            customers[i].printDetails();
            System.out.println("----------------------------------");
        }
    }

    // helper method
    private Customer findCustomer(String id) {
        // scan for the id O(n)
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getCustomerId().equals(id)){
                return customers[i];
            }
        }

        // return null if not found
        return null;
    }

    
}
