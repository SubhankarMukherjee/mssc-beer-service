package com.connecttosubhankar.web.controller;

import com.connecttosubhankar.service.BeerService;
import com.connecttosubhankar.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;   // with lombok Required arg annottaion, andfinal it wills
    //set the dependency

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID id)
    {

return new ResponseEntity<>(beerService.findBeerById(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<BeerDto> SaveNewBeer(@Valid @RequestBody BeerDto beerDto)
    {

        return new ResponseEntity( beerService.saveNewBeer(beerDto),HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeer(@PathVariable("beerId") UUID id,@Valid @RequestBody BeerDto beerDto)
    {

        return new ResponseEntity(beerService.updateBeer(id,beerDto),HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity<BeerDto> DeleteById(@PathVariable("beerId") UUID id)
    {
        beerService.deleteBeerById(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}


