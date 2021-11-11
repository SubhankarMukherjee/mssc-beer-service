package com.connecttosubhankar.bootstrap;

import com.connecttosubhankar.domain.Beer;
import com.connecttosubhankar.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {
//command line runner enables this to run while spring context starts up

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder().beerName("Heniken")
                    .beerStyle("IPA")
                    .minOnHand(2)
                    .price(new BigDecimal(600.00))
                    .quantityToBrew(2)
                    .upc(4354835830503L)
                    .build());
            beerRepository.save(Beer.builder().beerName("Foster")
                    .beerStyle("IPA")
                    .minOnHand(2)
                    .price(new BigDecimal(70.00))
                    .quantityToBrew(220)
                    .upc(43548353253L)
                    .build());
            System.out.println("Loaded Beer" + beerRepository.count());
        }
    }
}
