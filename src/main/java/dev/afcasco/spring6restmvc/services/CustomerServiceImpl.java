package dev.afcasco.spring6restmvc.services;

import dev.afcasco.spring6restmvc.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, Customer> customers;

    public CustomerServiceImpl() {
        this.customers = new HashMap<>();
        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("Alex Fernandez")
                .createdTime(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("Tony Montana")
                .createdTime(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customers.put(customer1.getId(), customer1);
        customers.put(customer2.getId(), customer2);
    }

    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customers.get(id);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Customer savedCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .name(customer.getName())
                .createdTime(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customers.put(savedCustomer.getId(),savedCustomer);
        return savedCustomer;
    }

    @Override
    public void updateById(UUID customerId, Customer customer) {
        Customer existing = customers.get(customerId);
        existing.setName(customer.getName());
        existing.setLastModifiedDate(LocalDateTime.now());
        customers.put(existing.getId(),existing);
    }

    @Override
    public void deleteById(UUID customerId) {
        customers.remove(customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, Customer customer) {
        Customer existing = customers.get(customerId);

        if(StringUtils.hasText(customer.getName())) {
            existing.setName(customer.getName());
            existing.setLastModifiedDate(LocalDateTime.now());
        }
    }
}
