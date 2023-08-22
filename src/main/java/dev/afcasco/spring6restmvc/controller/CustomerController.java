package dev.afcasco.spring6restmvc.controller;

import dev.afcasco.spring6restmvc.model.CustomerDTO;
import dev.afcasco.spring6restmvc.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = "/api/v1/customer/{customerId}";
    private final CustomerService customerService;

    @GetMapping(CUSTOMER_PATH_ID)
    public CustomerDTO getCustomerById(@PathVariable UUID customerId){
        return customerService.getCustomerById(customerId).orElseThrow(NotFoundException::new);
    }

    /*@GetMapping("{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable UUID customerId){
        Customer customer = customerService.getCustomerById(customerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","api/v1/customer/" + customer.getId());
        return new ResponseEntity<>(customer,headers,HttpStatus.OK);
    }*/

    @GetMapping(CUSTOMER_PATH)
    public List<CustomerDTO> getCustomers(){
        return customerService.listCustomers();
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity handlePost(@RequestBody CustomerDTO customer){
        CustomerDTO newCustomer = customerService.saveCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/customer/" + newCustomer.getId());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<CustomerDTO> updateById(@PathVariable UUID customerId, @RequestBody CustomerDTO customer){
        customerService.updateById(customerId,customer).orElseThrow(NotFoundException::new);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<CustomerDTO> deleteById(@PathVariable UUID customerId){
        if(!customerService.deleteById(customerId)){
            throw new NotFoundException();
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Void> updateCustomerPatchById(@PathVariable UUID customerId, @RequestBody CustomerDTO customer){
        customerService.patchCustomerById(customerId,customer);
        return ResponseEntity.noContent().build();
    }


}
