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
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

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
        Page<Beer> list = beerRepository.findAllBeerByBeerNameIsLikeIgnoreCase("%IPA%", null);
        assertThat(list.getContent().size()).isEqualTo(336);
    }

    @Test
    void testGetBeerListByBeerStyle() {
        Page<Beer> list = beerRepository.findAllBeerByBeerStyleIs(BeerStyle.IPA, null);
        assertThat(list.getContent().size()).isEqualTo(548);
    }

    @Test
    void testGetBeerListByBeerNameAndBeerStyle() {
        Page<Beer> list = beerRepository.findAllBeerByBeerNameIsLikeIgnoreCaseAndBeerStyle("%IPA%",BeerStyle.IPA, null);
        assertThat(list.getContent().size()).isEqualTo(310);
    }


}