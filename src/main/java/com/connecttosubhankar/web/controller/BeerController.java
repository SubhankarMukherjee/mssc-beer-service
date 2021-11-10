package com.connecttosubhankar.web.controller;

import com.connecttosubhankar.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID id)
    {
        //todo IMPL
return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<BeerDto> SaveNewBeer(@RequestBody BeerDto beerDto)
    {
        //todo IMPL
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeer(@PathVariable("beerId") UUID id,@RequestBody BeerDto beerDto)
    {
        //todo IMPL
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity<BeerDto> DeleteById(@PathVariable("beerId") UUID id)
    {
        //todo IMPL
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}


