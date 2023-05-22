package com.example.elmtask.services.interfaces;

import com.example.elmtask.domains.Service;

public interface IServicesService {
    Service getServiceById(Integer id);
    Iterable<Service> getCustomerServices(Integer id);
    Iterable<Service> getAllServices();
    Service saveService(Service service);
    Service updateService(Service service);
    void deleteService(Integer id);
}
