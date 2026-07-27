What is the difference between checked and unchecked exceptions?
- Checked exceptions are caught at compile time and are exceptions that can be worked with
- Unchecked sxceptions happen at runtime and crash the program
Why should custom exceptions be used?
- They allow for meaningful errors to be given
What is exception propagation?
- Excpetion propagates up the call stack until it gets handled (by a method or JVM itself)
What is the purpose of finally?
- No matter if an exception is made or not, the finally block executes regardless.
Why is try-with-resources preferred?
- it automatically closes resources
When should throw be used?
- when we want to signal that a problem occurred within the code
When should throws be used?
- in the method signature when the method in question doesn't handle the exception and puts the responsibilty on the caller
Why is logging important in enterprise applications?
- it is important in any application but it is to allow for developers to see the errors being triggered in a concise, separate fashion
What happens if an exception is not handled?
- it propagates up the call stack until it is
How does proper exception handling improve software reliability?
- it allows for meaningful error notes and less program crashes
(Forward look) How would a future CRM map domain exceptions (not found / validation) to API errors using the same boundary-catch + log pattern—without claiming CRM is implemented today?
- A customer not found error and a creditcard validation error