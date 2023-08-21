package dev.afcasco.spring6restmvc.controller;

import dev.afcasco.spring6restmvc.model.Beer;
import dev.afcasco.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
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
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    @GetMapping
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }


    @GetMapping( "{beerId}")
    public Beer getBeerById(@PathVariable UUID beerId) {

        log.debug("Get Beer by Id - in controller");

        return beerService.getBeerById(beerId);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody Beer beer){
        Beer savedBeer = beerService.saveNewBeer(beer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location","/api/v1/beer/" + savedBeer.getId());

        return new ResponseEntity(httpHeaders,HttpStatus.CREATED);
    }

    @PutMapping("{beerId}")
    public ResponseEntity updateById(@PathVariable UUID beerId,@RequestBody Beer beer){
        beerService.updateById(beerId,beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{beerId}")
    public ResponseEntity deleteById(@PathVariable UUID beerId){
        beerService.deleteById(beerId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{beerId}")
    public ResponseEntity<Void> updateBeerPatchById(@PathVariable UUID beerId, @RequestBody Beer beer){
        beerService.patchBeerById(beerId,beer);
        return ResponseEntity.noContent().build();
    }

}
