package dev.afcasco.spring6restmvc.mapper;

import dev.afcasco.spring6restmvc.entity.Customer;
import dev.afcasco.spring6restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO dto);
    CustomerDTO customerToCustomerDto(Customer customer);


}
