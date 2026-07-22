# Banking domain notes

| Entity | Identity | Important attributes | Main responsibility |
| ------ | -------- | -------------------- | ------------------- |
| Customer | customerId | name, email, phone | Maintain customer profile |
| Account | accountNumber | owner, balance, accountType | Protect balance and perform deposits/withdrawals |
| Transaction | transactionId | account, type, amount, timestamp | Record one account operation |



## Relationships

- One Customer can own zero or more Accounts.
- One Account belongs to exactly one Customer.
- One Account can have many Transactions.
- One Transaction belongs to exactly one Account.

## Rules

- An account balance cannot be changed directly from outside Account.
- A deposit amount must be positive.
- A withdrawal cannot exceed the allowed balance.


# Why shouldn't Main decide whether a withdrawl is valid?
- This is related to the concept of abstraction. The user shouldn't know why they can't take money out of their bank, they should just click a button and it should withdrawl it for them or reject their withdrawl.


# Why is there no setBalance() direct access?
- This is to prevent users from modifying the object's contents without going through the object's checklist first. We don't want users to make direct modifications to objects without it going through the object's filters.


## SRP spot-check

The original method could change because the formula changes or because
the output format changes. These are separate responsibilities.

# EX7 
- OCP Account cannot be modified however we can extend the class with a new "Frozen Account" which behaves differently
- LSP The Frozen Account honors all of the expectations of its parent "Account" such as withdrawing even if it doesn't withdraw anything
- ISP Savings accoutn doesn't need any sort of deposit and neither does Frozen, they don't impliment anything they don't need
- DIP The user, main() method, doesn't need to know why these things do or don't withdraw, hwoever they just need to "press the button" so to speak.