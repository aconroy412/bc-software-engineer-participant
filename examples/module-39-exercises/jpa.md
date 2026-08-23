| Column | Java field / annotation idea |
| --- | --- |
| customer_id | @Id String customerId |
| full_name | String fullName + @Column |
| status | String or enum status |
| created_at | Instant createdAt |

```java
@Id
private String customerId; // customer_id
@Column(name = "full_name", nullable = false)
private String fullName; // full_name
private String status; // status (or enum)
private Instant createdAt; // created_at
```

```java
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;
    
    @JoinColumn(name = "customer_id", nullable = false)
    @ManyToOne( fetch = FetchType.LAZY)
    private String customerId;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "status", nullable = false)
    private enum status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
}
```

camel case for java object, snake_case for columns names
