package com.example.elmtask.repositories;

import com.example.elmtask.domains.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByName(String name);
    List<Customer> findByName(String name);
    List<Customer> findByMobileNum(String mobileNum);
}
