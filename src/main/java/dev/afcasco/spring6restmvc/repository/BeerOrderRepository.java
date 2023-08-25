package dev.afcasco.spring6restmvc.repository;

import dev.afcasco.spring6restmvc.entity.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {

}
