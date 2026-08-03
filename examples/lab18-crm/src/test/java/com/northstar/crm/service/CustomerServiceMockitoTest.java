package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.exception.BusinessException;
import com.northstar.crm.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceMockitoTest {

    @Mock
    CustomerRepository repository;

    CustomerValidator validator;
    DefaultCustomerService service;

    @BeforeEach
    void setUp() {
        // Prefer manual ctor wiring over @InjectMocks for clarity
        validator = new CustomerValidator(repository);
        service = new DefaultCustomerService(repository, validator);
    }

    @Test
void activatesProspectUsingStubbedRepository() {
        Customer ravi = new Customer(
            "CUS-1002", "Ravi Singh", "ravi.singh@example.com", null, CustomerStatus.PROSPECT, LocalDateTime.now());

        when(repository.findById("CUS-1002")).thenReturn(Optional.of(ravi));
        when(repository.save(any(Customer.class))).thenAnswer(inv -> inv.getArgument(0));

        Customer result = service.changeStatus(
            "CUS-1002", CustomerStatus.ACTIVE, "lab-request-001");

        assertEquals(CustomerStatus.ACTIVE, result.getStatus());
        verify(repository).findById("CUS-1002");
        verify(repository).save(argThat(c ->
            "CUS-1002".equals(c.getCustomerId()) && c.getStatus() == CustomerStatus.ACTIVE));
        // Prefer explicit verify counts if validator also reads exists*:
        // verify(repository, times(1)).save(...);
    }

    @Test
    void notFoundNeverCallsSave() {
        // TODO: stub empty Optional; assertThrows; verify(repository, never()).save(any())
        when(repository.findById("CUS-9999")).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> {
            service.changeStatus("CUS-9999", CustomerStatus.ACTIVE, "lab-request-001");
        });

        verify(repository).findById("CUS-9999");
        verify(repository, never()).save(any(Customer.class));
    }

    @Test
    void addCustomerCapturesSavedEntity() {
        when(repository.existsById("CUS-1001")).thenReturn(false);
        when(repository.existsByEmail("amina.khan@example.com")).thenReturn(false);
        when(repository.save(any(Customer.class))).thenAnswer(inv -> inv.getArgument(0));

        service.addCustomer(new Customer(
            "CUS-1001", "Amina Khan", "amina.khan@example.com", null, CustomerStatus.ACTIVE, LocalDateTime.now()));

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(repository).save(captor.capture());
        assertEquals("CUS-1001", captor.getValue().getCustomerId());
        assertEquals("Amina Khan", captor.getValue().getFullName());
        assertEquals(CustomerStatus.ACTIVE, captor.getValue().getStatus());
    }
}
