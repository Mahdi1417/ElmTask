package com.example.elmtask.controllers;

import com.example.elmtask.domains.Customer;
import com.example.elmtask.services.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    // dependency injection for the customer service
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity<Customer> getCustomer(@RequestParam(value = "id") Integer id) {
        Customer cus = customerService.getCustomerById(id);
        // if there is no customer with that id: return not found
        if(cus == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(cus);
    }

    @GetMapping("/getall")
    public Iterable<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {

        return customerService.saveCustomer(customer);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        customer.setId(id);
        Customer cus = customerService.updateCustomer(customer);
        // if there is no customer with that id: return not found
        if(cus == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(cus);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
    }
}
