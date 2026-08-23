# Lab 37 — PostgreSQL notes

TODO: least-privilege app user; browser never touches DB.


Customer 1 ---- 0..* Account
Customer 1 ---- 0..* Address
Customer 1 ---- 0..* StatusHistory

CASCADE on deleting a customer but restrictwhen deleting an account. an account can't exist without someone to own it but not the other way around