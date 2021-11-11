package com.connecttosubhankar.domain;

import com.connecttosubhankar.web.model.BeerStyleEnum;
import lombok.*;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.id.UUIDGenerator;
import sun.util.resources.cldr.ne.TimeZoneNames_ne;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID" ,strategy="org.hibernate.id.UUIDGenerator")
    @Column(length = 36,updatable = false,nullable = false,columnDefinition = "varchar")
    private UUID id;
    @Version
    private Integer version;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;
    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    private String beerName;
    private String beerStyle;
    @Column(unique = true)
    private Long upc;
     private BigDecimal price;

     //changed for domain internal stuff

    private Integer minOnHand;
    private Integer quantityToBrew;
}
