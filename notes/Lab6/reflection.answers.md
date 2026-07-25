1. What are the advantages of Streams over loops?
- Process data in a concise, readable fashion.
2. When should Streams be preferred?
- Working with large collections or multiple I/O operations
3. What is the difference between `filter()` and `map()`?
- filter reduces the data by cutting out data not specifying a condition
- map is onto, it ties an object to an arbitrary value
4. Why is `reduce()` useful?
-places a collection into a single value
5. What does `Collectors.groupingBy()` do?
- places data into buckets based ona value specified
6. What is the benefit of using `Optional`?
- It eliminates the risk of a value not being found and giving a silent `Null Pointer Exception`
7. Why are Lambda Expressions more readable?
- There is no boilerplate or unnecessary signatures for basic operations
8. When should method references be used?
- When single operations need to be performed on collections `.map(e -> e.getName())` vs `.map(Employee::getName)`
9. Which stream operation is terminal? Give three examples from your lab.
- .collect in `demonstrateCollectors()` .reduce in `displayReductions()` .count in `displayCounts()`
10. How do Streams improve enterprise Java applications?
- Easily maintainable and scalable with very large datasets
11. (Forward look) How would a future CRM use `filter` / `map` / `groupingBy` on customers the same way this lab uses them on employees—without claiming the CRM is implemented today?
- They could use filter by filtering out what customers are located in what area
- Use the map function to map customers to their most frequented item
- grouping customers by ranges of average time spent on a website.