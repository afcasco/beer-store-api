package dev.afcasco.spring6restmvc.repository;

import dev.afcasco.spring6restmvc.entity.Beer;
import dev.afcasco.spring6restmvc.model.BeerStyle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

    List<Beer> findAllBeerByBeerNameIsLikeIgnoreCase(String beerName);

    List<Beer> findAllBeerByBeerStyleIs(BeerStyle beerStyle);

    List<Beer> findAllBeerByBeerNameIsLikeIgnoreCaseAndBeerStyle(String beerName, BeerStyle beerStyle);

}
