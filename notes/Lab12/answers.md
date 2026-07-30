Main data flow after refactor (create / get / update status)
    Caller invokes service API with various fields which service validates input and creates a new entity
    Caller asks service for an existing id in which the service checks the storage and returns accordingly or throws an exception
Trust boundary and where validation lives after cleanup
    THe service owns the validation after cleanup
Success/failure contract (duplicate ID, unknown ID, blank name)
    createCustomer returns customer
    getCustomer returns a customer
    updateStatus returns updated custoemr

    duplicate ID on create throws IllegalStateException
    unknown ID for getCustomer and updateStatus returns CustomerNotFoundException
Stable identity (CUS-1001) vs mutable fields (status, email)
    The identity is unchanging as it is the key for finding the customer later on
Retry/idempotency implications for create vs get
    createCustomer cannot be ran twice or more with the same exact command
    getCustomer can be called as many times as possible
Local in-memory shortcut vs production persistence
    The local storage on the JVM is practicale for this demo, but in a real-world application, we would rather have a robust, managed database to be storing and pulling from for this service.
Logs/evidence for support (lab-request-001)
    Keeps understanding of the project consistent.
Two JVM instances = independent memory (conflict risk)
    Each JVM instance has its own memory, so they could be potentially operating on the same data, without the atomicity of a shared database, the data could be written differently. THis highlights the importance of a shared database
Which SOLID ideas fit this lab’s size, and which are deferred?
    Single Responsibilty should be honored, the others are not applicable for a lab of this small size
Why freezing a before snapshot matters more than “I rewrote it cleanly”?
    It keeps a record of what the old version was doing and evidence that it actually was written better.