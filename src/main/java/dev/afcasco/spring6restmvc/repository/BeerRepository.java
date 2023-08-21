package dev.afcasco.spring6restmvc.repository;

import dev.afcasco.spring6restmvc.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
