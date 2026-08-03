```java
// Invalid create payloads to assert in Lab 14
{ "fullName": "", "status": "PROSPECT" } // blank name
{ "fullName": "Amina Khan", "status": "ACTVE" } // status typo
{ "fullName": "A...A" } // 300 chars, oversized name
// Valid control: { "fullName": "Ravi Shah", "status": "PROSPECT" }
```