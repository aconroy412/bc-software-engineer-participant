## Exercise 1
- Using javap -c, we can see that printPerson and main have their own stack frame.
- There is a single reference to the Person object that we created and invoked using the new keyword.

## Exercise 2
- Data stored on the heap is accessible as long as it has a reference. Though, if there are no references remaining, it is eligible for garbage collection. Us, the user, don't have to manage the memory ourselves but we should remove references to things that we don't need.