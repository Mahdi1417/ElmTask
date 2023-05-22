package com.example.elmtask.services;

import com.example.elmtask.domains.Service;
import com.example.elmtask.repositories.IServiceRepository;
import com.example.elmtask.services.interfaces.ICustomerService;
import com.example.elmtask.services.interfaces.IServicesService;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ServicesService implements IServicesService {

    // dependency injection for the service repo
    @Autowired
    private IServiceRepository serviceRepository;

    // dependency injection for the customer service
    @Autowired
    private ICustomerService customerService;

    /**
     * this method is used to get the service with the input id
     * @param id This is service id
     * @return The service object that is related to the input id, or null in case the id does not exist
     */
    @Override
    public Service getServiceById(Integer id) {
        if(serviceRepository.existsById(id))
            return serviceRepository.findById(id).get();
        return null;
    }

    /**
     * this method is used to get a specific customer services
     * @param id This is the id of the customer
     * @return the customer services, or null in case there is no customer with the provided id
     */
    @Override
    public Iterable<Service> getCustomerServices(Integer id) {
        if(customerService.existsById(id))
            return serviceRepository.findByCustomer_Id(id.longValue());
        return null;
    }

    /**
     * this method is used to get all services
     * @return all services
     */
    @Override
    public Iterable<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    /**
     * this method is used to save new service to a customer
     * @param service the service object
     * @return the service object with its id after adding it to the db
     */
    @Override
    public Service saveService(Service service) {
        if(customerService.existsById(service.getCustomer().getId().intValue()))
            serviceRepository.save(service);
        return null;
    }

    /**
     * this method is used to update the service with the related id
     * if there is no service with the input id, it returns null
     * @param newService the new service object
     * @return the service object after updating, or null in case no service with that id
     */
    @Override
    public Service updateService(Service newService) {
        return serviceRepository.findById(newService.getId().intValue())
                .map(service -> {
                    service.setServiceName(newService.getServiceName());
                    service.setServiceType(newService.getServiceType());
                    return serviceRepository.save(service);
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    /**
     * this method is used to delete the service with the input id
     * @param id the id of the service
     */
    @Override
    public void deleteService(Integer id) {
        serviceRepository.deleteById(id);
    }
}
