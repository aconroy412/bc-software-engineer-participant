package com.northstar.crm.controller;

import com.northstar.crm.model.Customer;
import com.northstar.crm.model.CustomerDraft;
import com.northstar.crm.service.CustomerService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping()
  public List<Customer> list() {
    return customerService.list();
  }

  @GetMapping("/{id}")
  public Customer get(@PathVariable String id) {
    return customerService.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Customer create(@RequestBody CustomerDraft draft) {
      return customerService.create(draft);
  }

  @PutMapping("/{id}")
  public Customer update(
          @PathVariable String id,
          @RequestBody CustomerDraft draft
  ) {
      return customerService.update(id, draft);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id) {
      customerService.delete(id);
  }
}
