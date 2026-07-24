# Collection choices

| # | Scenario | Need (order / unique / key→value / sorted) | Interface | Implementation | Why |
| - | -------- | ------------------------------------------ | --------- | -------------- | --- |
| 1 | Ordered catalog; duplicate titles allowed | Order Non-Unique| `List` | `ArrayList` | Need fast access |
| 2 | Unique registered book IDs | Unique Non-Sorted | `Set` | `HashSet` | No order Necessary, unique |
| 3 | Book ID → current borrower ID | Key-Value | `Key- Value Pair` | `Hash Map` | Key value with no order |
| 4 | Alphabetically sorted categories | Sorted | `Set` | `TreeSet` | Need sorted categories |
| 5 | Category → count, sorted by category | Sorted Key-Value | `Key-Value Pair` | `Tree Map` | Sorted key value |
| 6 | Checkout history in event order |  | `List` | `Linked List` | Frequent Insertions, keeps insertion Order |