`map` produced a new list of proposed values; it did not modify the immutable `Employee` records in the source list.


# Why long and not int?
- The counts for these datasets could be so massive, in the tens of millions.

# What does it return if you remove collectors.counting
- It would return a list of employees

## Why did we use a Treemap for Showing?
- We wanted a sorted view of the departments. 