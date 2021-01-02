package com.dcpear.customer.controller;

import com.dcpear.customer.domain.Customer;
import com.dcpear.customer.domain.Level;
import com.dcpear.customer.repo.CustomerRepository;
import com.dcpear.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.*;


@RestController
@RequestMapping("api")
public class CustomerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
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
        LOGGER.info("add a customer");
        Customer result = customerRepository.save(customer);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/getCustomers")
    public Collection<Customer> getCustomers() {
        LOGGER.info("get all customers");
        return (Collection<Customer>) customerRepository.findAll();
    }

    @GetMapping("/getCustomerById/{id}")
    public Customer getCustomer(@PathVariable("id") Integer id) {
        LOGGER.info("GET /getCustomerById/{}", id);
        return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id Not found"));
    }

    @GetMapping("/getCustomersByName/{lastName}")
    public Collection<Customer> getCustomersByLastName(@PathVariable("lastName") String lastName) {
        return customerRepository.findAllByLastName(lastName);
    }

    @GetMapping("/getCustomersByLevel/{level}")
    public Collection<Customer> getCustomersByLevel(@PathVariable("level") Level level){
        return customerRepository.findAllByLevel(level);
    }

    @GetMapping("/getNumberOf/{level}")
    public long getNumbersByLevel(@PathVariable("level") Level level){
        LOGGER.info("GET /{}/number", level);
      return customerService.getLevelCount(level) ;
    }

    @PatchMapping("/updateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Integer id,
                          @Validated @RequestBody Customer customerDetails) throws ResourceNotFoundException {
        LOGGER.debug("PATCH /updateCustomer/{}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + id));
        customer.setLevel(customerDetails.getLevel());
        final Customer result = customerRepository.save(customer);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public  ResponseEntity<Customer> deleteCustomer(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        LOGGER.debug("DELETE /deleteCustomer/{}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + id));

        customerRepository.delete(customer);
        return ResponseEntity.noContent().build();
    }

    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex exception
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        LOGGER.error("Unable to complete transaction", ex);
        return ex.getMessage();
    }
}
