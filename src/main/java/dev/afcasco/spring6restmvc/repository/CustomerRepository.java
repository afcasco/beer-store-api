package dev.afcasco.spring6restmvc.repository;

import dev.afcasco.spring6restmvc.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
