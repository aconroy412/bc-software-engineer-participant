package com.academy.bank;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankService(scanner);

        while (true) {

            // Menu
            System.out.println("\n===== Bank Management System =====");
            System.out.println("1. Create Customer");
            System.out.println("2. Create Savings Account");
            System.out.println("3. Create Current Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Display Accounts");
            System.out.println("7. Display Customers");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                continue;
            }

            switch (choice) {

                case 1:
                    bankService.createCustomer();
                    break;

                case 2:{

                    int id = 0;
                    boolean validID = false;
                    while (!validID || id < 0) {
                        if (id < 0) {
                            System.out.println("Please enter a positive integer.");
                        }
                        System.out.println("Hello, what should this customer's ID be? : ");
                        try {   
                            id = Integer.parseInt(scanner.nextLine());
                            validID = true;
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Please print a valid number.");
                        }
                    }

                    double balance = 0.0;
                    validID = false;
                    while (!validID || balance < 0) {
                        if (balance < 0.0) {
                            System.out.println("Please enter a positive number.");
                        }
                        System.out.println("Hello, what should this initial balance be? : ");
                        try {   
                            balance = Double.parseDouble(scanner.nextLine());
                            validID = true;
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Please print a valid number.");
                        }
                    }

                    double interest = 0.0;
                    validID = false;
                    while (!validID || interest < 0) {
                        if (interest < 0.0) {
                            System.out.println("Please enter a positive number.");
                        }
                        System.out.println("Hello, what should this interest rate be? : ");
                        try {   
                            interest = Double.parseDouble(scanner.nextLine());
                            validID = true;
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Please print a valid number.");
                        }
                    }

                    bankService.createSavingsAccount(id, balance, interest);
                    break;
                }

                case 3:
                    {
                        int id = 0;
                        boolean validID = false;
                        while (!validID || id < 0) {
                            if (id < 0) {
                                System.out.println("Please enter a positive integer.");
                            }
                            System.out.println("Hello, what should this customer's ID be? : ");
                            try {   
                                id = Integer.parseInt(scanner.nextLine());
                                validID = true;
                            }
                            catch (NumberFormatException e) {
                                System.out.println("Please print a valid number.");
                            }
                        }

                        double balance = 0.0;
                        validID = false;
                        while (!validID || balance < 0) {
                            if (balance < 0.0) {
                                System.out.println("Please enter a positive number.");
                            }
                            System.out.println("Hello, what should this initial balance be? : ");
                            try {   
                                balance = Double.parseDouble(scanner.nextLine());
                                validID = true;
                            }
                            catch (NumberFormatException e) {
                                System.out.println("Please print a valid number.");
                            }
                        }

                        double charge = 0.0;
                        validID = false;
                        while (!validID || charge < 0) {
                            if (charge < 0.0) {
                                System.out.println("Please enter a positive number.");
                            }
                            System.out.println("Hello, what should this charge be? : ");
                            try {   
                                charge = Double.parseDouble(scanner.nextLine());
                                validID = true;
                            }
                            catch (NumberFormatException e) {
                                System.out.println("Please print a valid number.");
                            }
                        }

                        bankService.createCurrentAccount(id, balance, charge);
                        break;
                    }

                case 4:
                    {
                        int account = 0;
                        boolean validID = false;
                        while (!validID || account < 0) {
                            if (account < 0) {
                                System.out.println("Please enter a positive integer.");
                            }
                            System.out.println("Hello, what should this Account Number be? : ");
                            try {   
                                account = Integer.parseInt(scanner.nextLine());
                                validID = true;
                            }
                            catch (NumberFormatException e) {
                                System.out.println("Please print a valid number.");
                            }
                        }

                        double balance = 0.0;
                        validID = false;
                        while (!validID || balance < 0) {
                            if (balance < 0.0) {
                                System.out.println("Please enter a positive number.");
                            }
                            System.out.println("Hello, what should this amount be? : ");
                            try {   
                                balance = Double.parseDouble(scanner.nextLine());
                                validID = true;
                            }
                            catch (NumberFormatException e) {
                                System.out.println("Please print a valid number.");
                            }
                        }

                        bankService.deposit(account, balance);
                        break;
                    }

                case 5:
                    {
                        int account = 0;
                        boolean validID = false;
                        while (!validID || account < 0) {
                            if (account < 0) {
                                System.out.println("Please enter a positive integer.");
                            }
                            System.out.println("Hello, what should this Account Number be? : ");
                            try {   
                                account = Integer.parseInt(scanner.nextLine());
                                validID = true;
                            }
                            catch (NumberFormatException e) {
                                System.out.println("Please print a valid number.");
                            }
                        }

                        double balance = 0.0;
                        validID = false;
                        while (!validID || balance < 0) {
                            if (balance < 0.0) {
                                System.out.println("Please enter a positive number.");
                            }
                            System.out.println("Hello, what should this amount be? : ");
                            try {   
                                balance = Double.parseDouble(scanner.nextLine());
                                validID = true;
                            }
                            catch (NumberFormatException e) {
                                System.out.println("Please print a valid number.");
                            }
                        }

                        bankService.withdraw(account, balance);
                        break;
                    }

                case 6:
                    bankService.displayAccounts();
                    break;

                case 7:
                    bankService.displayCustomers();
                    break;

                case 8:
                    System.out.println("Thank You!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
