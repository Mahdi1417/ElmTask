package com.example.elmtask.services;

import com.example.elmtask.domains.Customer;
import com.example.elmtask.repositories.ICustomerRepository;
import com.example.elmtask.services.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    // dependency injection for the customer repo
    @Autowired
    private ICustomerRepository customerRepository;

    /**
     * this method is used to check if
     * customer exists in the db with the input id
     * @param id This is customer id
     * @return bool This is true if customer exists
     */
    @Override
    public boolean existsById(Integer id) {
        return customerRepository.existsById(id);
    }

    /**
     * this method is used to get the customer with the input id
     * @param id This is customer id
     * @return The customer object that is related to the input id, or null in case the id does not exist
     */
    @Override
    public Customer getCustomerById(Integer id) {

        if(customerRepository.existsById(id))
            return customerRepository.findById(id).get();
        return null;
    }

    /**
     * this method is used to get the first customer with the input name
     * It has been added to show the ability of adding other methods to
     * retrieve customers using other fields (not only id field)
     * @param name This is customer name
     * @return Customer The first customer object that is related to the input name, or null in case no customer with that name
     */
    @Override
    public Customer getCustomerByName(String name) {
        if(customerRepository.existsByName(name))
            return customerRepository.findByName(name).get(0);
        return null;
    }

    /**
     * this method is used to get all customers
     * @return All customers
     */
    @Override
    public Iterable<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }

    /**
     * this method is used to save new customer
     * @param customer the customer object
     * @return the customer object with its id after adding it to the db
     */
    @Override
    public Customer saveCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    /**
     * this method is used to update the customer with the related id
     * if there is no customer with the input id, it returns null
     * @param newCustomer the new customer object
     * @return the customer object after updating, or null in case no customer with that id
     */
    @Override
    public Customer updateCustomer(Customer newCustomer) {

        return customerRepository.findById(newCustomer.getId().intValue())
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    customer.setMobileNum(newCustomer.getMobileNum());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    /**
     * this method is used to delete the customer with the input id
     * @param id the id of the customer
     */
    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
