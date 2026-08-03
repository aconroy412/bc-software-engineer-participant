```java
public interface CustomerService {
 Customer findById(String customerId);
 Customer activate(String customerId);
}
public DefaultCustomerService(CustomerRepository repository, CustomerNotifier notifier)
```