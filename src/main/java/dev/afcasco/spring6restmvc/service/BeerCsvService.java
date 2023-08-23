package dev.afcasco.spring6restmvc.service;

import dev.afcasco.spring6restmvc.model.BeerCSVRecord;

import java.io.File;
import java.util.List;

public interface BeerCsvService {

    List<BeerCSVRecord> convertCsv(File file);
}
