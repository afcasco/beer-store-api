package dev.afcasco.spring6restmvc.repository;

import dev.afcasco.spring6restmvc.entity.Beer;
import dev.afcasco.spring6restmvc.entity.BeerOrder;
import dev.afcasco.spring6restmvc.entity.BeerOrderShipment;
import dev.afcasco.spring6restmvc.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
class BeerOrderRepositoryTest {

    @Autowired
    BeerOrderRepository beerOrderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BeerRepository beerRepository;

    Customer testCustomer;
    Beer testBeer;

    @BeforeEach
    void setUp(){
        testCustomer = customerRepository.findAll().get(0);
        testBeer = beerRepository.findAll().get(0);
    }


    @Transactional
    @Test
    void testBeerOrders() {

        /*BeerOrderLine beerOrderLine = BeerOrderLine.builder()

                .beer(testBeer)
                .orderQuantity(2000)
                .build();*/

        BeerOrder beerOrder = BeerOrder.builder()
                .customerRef("Test order")
               // .beerOrderLines(Set.of(beerOrderLine))
                .customer(testCustomer)
                .beerOrderShipment(BeerOrderShipment.builder()
                        .trackingNumber(UUID.randomUUID().toString())
                        .build())
                .build();

        BeerOrder savedBeerOrder = beerOrderRepository.save(beerOrder);

        System.out.println(savedBeerOrder.getCustomerRef());
    }
}