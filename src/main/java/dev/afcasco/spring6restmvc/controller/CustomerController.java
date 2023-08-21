package dev.afcasco.spring6restmvc.controller;

import dev.afcasco.spring6restmvc.model.Beer;
import dev.afcasco.spring6restmvc.model.Customer;
import dev.afcasco.spring6restmvc.services.CustomerService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("{customerId}")
    public Customer getCustomerById(@PathVariable UUID customerId){
        return customerService.getCustomerById(customerId);
    }

    /*@GetMapping("{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable UUID customerId){
        Customer customer = customerService.getCustomerById(customerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","api/v1/customer/" + customer.getId());
        return new ResponseEntity<>(customer,headers,HttpStatus.OK);
    }*/

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.listCustomers();
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody Customer customer){
        Customer newCustomer = customerService.saveCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/customer/" + newCustomer.getId());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<Customer> updateById(@PathVariable UUID customerId, @RequestBody Customer customer){
        customerService.updateById(customerId,customer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Customer> deleteById(@PathVariable UUID customerId){
        customerService.deleteById(customerId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{customerId}")
    public ResponseEntity<Void> updateCustomerPatchById(@PathVariable UUID customerId, @RequestBody Customer customer){
        customerService.patchCustomerById(customerId,customer);
        return ResponseEntity.noContent().build();
    }


}
