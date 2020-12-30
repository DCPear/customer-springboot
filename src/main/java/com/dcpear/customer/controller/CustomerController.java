package com.dcpear.customer.controller;

import com.dcpear.customer.domain.Customer;
import com.dcpear.customer.repo.CustomerRepository;
import com.dcpear.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("api")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public long getCount() {
        return customerService.getCustomerCount();
    }

    @PostMapping("/addCustomers")
    public ResponseEntity<Customer> createContact(@Validated @RequestBody Customer customer) throws URISyntaxException {
        Customer result = customerRepository.save(customer);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/getCustomers")
    public Collection<Customer> getCustomers() {
        return (Collection<Customer>) customerRepository.findAll();
    }

    @GetMapping("/getCustomerById/{id}")
    public Customer getCustomer(@PathVariable("id") Integer id) {
        return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id Not found"));
    }

    @GetMapping("/getCustomersByName/{lastName}")
    public Collection<Customer> getCustomerLastName(@PathVariable("lastName") String lastName) {
        return (Collection<Customer>) customerRepository.findAllByLastName(lastName);
    }

    @PatchMapping("/updateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Integer id,
                          @Validated @RequestBody Customer customerDetails) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + id));
        customer.setLevel(customerDetails.getLevel());
        final Customer result = customerRepository.save(customer);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public  ResponseEntity<Customer> deleteCustomer(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + id));

        customerRepository.delete(customer);
        return ResponseEntity.noContent().build();
    }
}
