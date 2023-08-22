package dev.afcasco.spring6restmvc.controller;

import dev.afcasco.spring6restmvc.model.BeerDTO;
import dev.afcasco.spring6restmvc.service.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerService beerService;

    @GetMapping(BEER_PATH)
    public List<BeerDTO> listBeers() {
        return beerService.listBeers();
    }


    @GetMapping(BEER_PATH_ID)
    public BeerDTO getBeerById(@PathVariable UUID beerId) {

        log.debug("Get Beer by Id - in controller");

        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity handlePost(@RequestBody BeerDTO beer) {
        BeerDTO savedBeer = beerService.saveNewBeer(beer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", BEER_PATH + "/" + savedBeer.getId());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity updateById(@PathVariable UUID beerId, @RequestBody BeerDTO beer) {
        beerService.updateById(beerId, beer).orElseThrow(NotFoundException::new);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable UUID beerId) {
        if(!beerService.deleteById(beerId)){
            throw new NotFoundException();
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity<Void> updateBeerPatchById(@PathVariable UUID beerId, @RequestBody BeerDTO beer) {
        beerService.patchBeerById(beerId, beer);
        return ResponseEntity.noContent().build();
    }

}
