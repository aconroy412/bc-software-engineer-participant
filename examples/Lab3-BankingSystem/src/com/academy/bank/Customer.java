package com.academy.bank;

public class Customer implements Printable{

    private int customerId;
    private String name;
    private String email;
    private String phone;

    public Customer(int cID, String name, String email, String phone) {
        this.customerId = cID;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }
    

    private void display() {
        System.out.printf("Customer: %s, Email: %s, Phone: %s", this.name, this.email, this.phone);
    }


    public void printDetails() {
        display();
    }
}
