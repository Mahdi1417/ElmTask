package com.example.elmtask.controllers;

import com.example.elmtask.domains.Service;
import com.example.elmtask.services.interfaces.IServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
public class ServiceController {
    // dependency injection for the service
    @Autowired
    private IServicesService servicesService;

    @GetMapping("/get")
    public ResponseEntity<Service> getService(@RequestParam(value = "id") Integer id) {
        Service ser = servicesService.getServiceById(id);
        // if there is no service with that id: return not found
        if(ser == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(ser);
    }

    @GetMapping("/customerservices")
    public ResponseEntity<Iterable<Service>> getCustomerServices(@RequestParam(value = "id") Integer id) {
        Iterable<Service> services = servicesService.getCustomerServices(id);
        // if there is no customer with that id: return not found
        if(services == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(services);
    }

    @GetMapping("/getall")
    public Iterable<Service> getAllServices() {

        return servicesService.getAllServices();
    }

    @PostMapping("/add")
    public ResponseEntity<Service> addService(@RequestBody Service service) {
        Service ser = servicesService.saveService(service);
        // if there is no customer with that id: return not found
        if(ser == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(ser);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Service> updateService(@RequestBody Service service, @PathVariable Long id) {
        service.setId(id);
        Service ser = servicesService.updateService(service);
        // if there is no customer with that id: return not found
        if(ser == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(ser);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        servicesService.deleteService(id);
    }
}
