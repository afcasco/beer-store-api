package dev.afcasco.spring6restmvc.service;

import dev.afcasco.spring6restmvc.model.BeerCSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BeerCsvServiceTest {

    BeerCsvService beerCsvService = new BeerCsvServiceImpl();

    @Test
    void testConvertCsv() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");

        List<BeerCSVRecord> records = beerCsvService.convertCsv(file);

        System.out.println(records.size());

        assertThat(records.size()).isGreaterThan(0);
    }
}