# Security questions
1. Which fields untrusted?
-   All of them, they each have to be verified
2. Authority is not enforced here 
3. Email is never in errorReponse


# Reflection
1. Which design decision most affected correctness?
    - Validation was the design that most affected correctness as wihtout it, the api could not give error codes
2. What evidence proves the implementation works?
    - `mvn test`
3. Which failure was hardest to diagnose?
    Maps validation email was the hardest to impliment. For me, it was difficult to figure out what to use since it was not from the exception handler, rather it needed to get the validator.