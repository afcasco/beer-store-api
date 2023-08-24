package dev.afcasco.spring6restmvc.repository;

import dev.afcasco.spring6restmvc.entity.Beer;
import dev.afcasco.spring6restmvc.model.BeerStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

    Page<Beer> findAllBeerByBeerNameIsLikeIgnoreCase(String beerName, Pageable pageable);

    Page<Beer> findAllBeerByBeerStyleIs(BeerStyle beerStyle, Pageable pageable);

    Page<Beer> findAllBeerByBeerNameIsLikeIgnoreCaseAndBeerStyle(String beerName, BeerStyle beerStyle, Pageable pageable);

}
