package com.connecttosubhankar.bootstrap;

import com.connecttosubhankar.domain.Beer;
import com.connecttosubhankar.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class BeerLoader implements CommandLineRunner {
//command line runner enables this to run while spring context starts up

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    @Override
    public void run(String... args) throws Exception {
        if(beerRepository.count() == 0)
            loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder().beerName("Heniken")
                    .beerStyle("IPA")
                    .minOnHand(2)
                    .price(new BigDecimal(600.00))
                    .quantityToBrew(2)
                    .upc(BEER_1_UPC)
                    .build());
            beerRepository.save(Beer.builder().beerName("Foster")
                    .beerStyle("IPA")
                    .minOnHand(2)
                    .price(new BigDecimal(70.00))
                    .quantityToBrew(220)
                    .upc(BEER_2_UPC)
                    .build());
            beerRepository.save(Beer.builder().beerName("Rum")
                    .beerStyle("IPA")
                    .minOnHand(2)
                    .price(new BigDecimal(12.00))
                    .quantityToBrew(220)
                    .upc(BEER_3_UPC)
                    .build());
            System.out.println("Loaded Beer" + beerRepository.count());
        }
    }
}
