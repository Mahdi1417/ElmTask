package com.example.elmtask.repositories;

import com.example.elmtask.domains.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IServiceRepository extends JpaRepository<Service, Integer> {
    List<Service> findByCustomer_Id(Long id);
}
