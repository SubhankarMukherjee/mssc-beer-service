package common.events;

import com.connecttosubhankar.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerEvent implements Serializable {
    private static final long serialVersionUID = 4929258906860027305L;
    private BeerDto beerDto;
}
