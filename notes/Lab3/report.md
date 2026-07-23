## SOLID design principles for the project
- This project allowed us to practically demonstrate our understanding of SOLID principles
- For this project, the single responsibility principle was demonstrated with the accounts and transactiosn
- - The transaction class handles transactions, customer handles the customer, and the account class handles only theaccounts
- Open for extension, closed for modification is Exemplified in the Account <--- extends --- SavingsAccount, CurrentAccount
- Liskov Substitution Principle, Account is completely replaced by SavingsAccount and CurrentAccount
- Interface Separation Principle, Only the customer and the extensions of Account need Printable Interface.
- Dependency Inversion Principle, Both Accounts as well as the Customer depend on the Printable interface rather than the printab le interface depending on these classes.