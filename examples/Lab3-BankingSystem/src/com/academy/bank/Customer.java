package com.academy.bank;

public class Customer implements Printable{

    private String customerId;
    private String name;
    private String email;
    private String phone;

    public Customer(String cID, String name, String email, String phone) {
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

    public String getCustomerId() {
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
        System.out.printf("Customer ID : %s%nName : %s%nEmail : %s%nPhone : %s%n", 
        this.customerId, this.name, this.email, this.phone);
    }


    public void printDetails() {
        display();
    }
}
