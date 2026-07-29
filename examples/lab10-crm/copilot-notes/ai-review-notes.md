# AI review notes — Lab 10

## lab10-001 — weak vs strong (entity)
- Date: 7/29/2026
- Weak prompt used: Make a Customer Class from Customer2.java
- Output summary: Made a Customer class with no getter/setters
- Strong prompt used: Can you do getters and setters for all fields here
- Output summary: Correctly implimented the getters and setters for the class
- Decision: accept
- Reason (1 sentence): made the class and all functionality 

## lab10-002 — weak vs strong (addCustomer)
- Date: 7/29/2026
- Weak prompt used: Make a class which adds a customer to CustomerService
- Output summary: Method returned added customer to arraylist but did not check for edge casses
- Strong prompt used: Make addCustomer(Customer customer) return customer added handle missing customer fields.
- Output summary: Returned Customer object that was added and checked for missing Ids before adding to the arraylist.
- Decision: partial
- Reason: I liked the idea that it was going for but I changed the error messages.

## lab10-003 — CustomerStatus / Customer scaffold
- Rejected JPA? yes
- Notes: Copilot did not add JPA elements to CustomerStatus and made a class with an enum and the four required statuses.

## lab10-004 — CustomerService review
- Notes: Github Copilot handled edge cases and made a very concise set of methods. However, it made its own "List all" method and did not return the the list of customers as intended. I had to modify the listAll() method that it created without my knowledge.
