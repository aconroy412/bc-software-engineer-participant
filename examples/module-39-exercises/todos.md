# Lab 39 — Fill JPA TODOs

## Step 1 — Paste

Create `notes/lab39-todos.md`:

```java
@Entity
@Table(name = "customer")
class CustomerEntity {
  @Id
  private String customerID;
  @Column(name = "full_name", nullable = false)
  private String fullName;
  @Column(nullable = false)
  private String status;
  @Version
  private long version; // optimistic lock
}

interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
  Page<CustomerEntity> findByStatus(String status, Pagable pageable);
}
```
// application.yml ideas
```yaml
spring.datasource.url: jdbc:postgresql://localhost:5432/northstar
spring.jpa.hibernate.ddl-auto: validate 
spring.flyway.enabled: true
```