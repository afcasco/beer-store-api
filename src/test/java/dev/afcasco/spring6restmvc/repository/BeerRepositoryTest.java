package dev.afcasco.spring6restmvc.repository;

import dev.afcasco.spring6restmvc.bootstrap.BootstrapData;
import dev.afcasco.spring6restmvc.entity.Beer;
import dev.afcasco.spring6restmvc.model.BeerStyle;
import dev.afcasco.spring6restmvc.service.BeerCsvServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import({BootstrapData.class, BeerCsvServiceImpl.class})
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeerNameTooLong() {
        assertThrows(ConstraintViolationException.class,()-> {
            beerRepository.save(Beer.builder()
                    .beerName("My Beer".repeat(40))
                    .beerStyle(BeerStyle.ALE)
                    .upc("2324235")
                    .price(new BigDecimal("11.99"))
                    .build());

            beerRepository.flush();
        });
    }

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyle.ALE)
                .upc("2324235")
                .price(new BigDecimal("11.99"))
                .build());

        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }

    @Test
    void testGetBeerListByName() {
        List<Beer> list = beerRepository.findAllBeerByBeerNameIsLikeIgnoreCase("%IPA%");
        assertThat(list.size()).isEqualTo(336);
    }

    @Test
    void testGetBeerListByBeerStyle() {
        List<Beer> list = beerRepository.findAllBeerByBeerStyleIs(BeerStyle.IPA);
        assertThat(list.size()).isEqualTo(548);
    }

    @Test
    void testGetBeerListByBeerNameAndBeerStyle() {
        List<Beer> list = beerRepository.findAllBeerByBeerNameIsLikeIgnoreCaseAndBeerStyle("%IPA%",BeerStyle.IPA);
        assertThat(list.size()).isEqualTo(310);
    }


}