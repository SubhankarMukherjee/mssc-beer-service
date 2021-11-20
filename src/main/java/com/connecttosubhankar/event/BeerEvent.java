package com.connecttosubhankar.event;

import com.connecttosubhankar.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
@AllArgsConstructor
public class BeerEvent implements Serializable {
    private static final long serialVersionUID = 4929258906860027305L;
    private BeerDto beerDto;
}
