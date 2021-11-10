package com.connecttosubhankar.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
@Data  // data will give getter and setters and euuals and hashcode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {

    private UUID id;
    private Integer version;

    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;

    private String beerName;
    private BeerStyleEnum beerStyle;

    private Long upc;
    private Integer quantityOnHand;
    private BigDecimal price;
}
