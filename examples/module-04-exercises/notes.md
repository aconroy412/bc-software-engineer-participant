## Exercise 1
- Using javap -c, we can see that printPerson and main have their own stack frame.
- There is a single reference to the Person object that we created and invoked using the new keyword.

## Exercise 2
- Data stored on the heap is accessible as long as it has a reference. Though, if there are no references remaining, it is eligible for garbage collection. Us, the user, don't have to manage the memory ourselves but we should remove references to things that we don't need.

## Exercise 3
- The program allocated over 250mb of data over time despite a 64mb maximum heap. The garbage collector reclaimed a lot of space between rounds as some references to the data were not being used and therefore were deleted by the G1 Garbage collector.

## Exercise 4
- I can confirm that it is using G1 and that the pause times are random as we did not set them.

## Exercise 5
- ZGC does not pause constantly and runs continuously. It does not have very many pauses. 

## exercise 6
- loaded RetentionDemo class
  → static CACHE field
  → ArrayList entries
  → byte[] objects
- Data could not reclaim the references held by the CACHE field
- Clearing the Cache frees the memroy and allows it to be reclaimed by the GC. Strong references should be removed.

## exercise 7
| Run | String ms | StringBuilder ms |
| --- | --------- | ---------------- |
| 1 | | 274.18 | 2.42 |
| 2 | | 271.90 | 2.52 |
| 3 | | 276.18 | 4.09 |

- String builder uses a buffer when the regular string object has to remake the same object over and over to add a character to it.