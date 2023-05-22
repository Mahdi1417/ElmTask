package com.example.elmtask.services.interfaces;

import com.example.elmtask.domains.Customer;

public interface ICustomerService {
    boolean existsById(Integer id);
    Customer getCustomerById(Integer id);
    Customer getCustomerByName(String name);
    Iterable<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(Integer id);
}
