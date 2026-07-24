# List over Set?
- Lists are good for accessing things but not good for addings things. If you have a static group of items you want in your program, a list is better than a set.
# Why HashSet?
- We have to figure out if the book has a number attached to it first before inserting it again.
## 3 Why map instead of boolean?
- We want to have records of who is borrowing the book rather than it just being borrowed. One data structure instead of two differnet arrays of booleans makes it more concise.
## 4 HashMap vs TreeMap
- Tree map is only used when we need to sort data by some sort of parameter. The hashmaps are used for records as there is no sortable parameter that we need. However counts are sortable and we would like to know htat information and have it readily available.
## Comparable vs Comparator?
- Comparator as there are multiple fields which need to be compared.

## Iteration Style most used in production?
- Iterator, as we have safe removal.

## CRM
- Customer List would be arraylist
- Unique Emails: Hash set
- ID -> Customer Lookup: Hash Map