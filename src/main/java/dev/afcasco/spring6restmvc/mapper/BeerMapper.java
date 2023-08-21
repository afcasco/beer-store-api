package dev.afcasco.spring6restmvc.mapper;

import dev.afcasco.spring6restmvc.entity.Beer;
import dev.afcasco.spring6restmvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
