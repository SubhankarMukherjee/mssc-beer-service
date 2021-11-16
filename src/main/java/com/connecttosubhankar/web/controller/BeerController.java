package com.connecttosubhankar.web.controller;

import com.connecttosubhankar.service.BeerService;
import com.connecttosubhankar.web.model.BeerDto;
import com.connecttosubhankar.web.model.BeerPagedList;
import com.connecttosubhankar.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;   // with lombok Required arg annottaion, andfinal it wills
    //set the dependency

    private static final Integer DEFAULT_PAGE_NUMBER=0;
    private static final Integer DEFAULT_PAGE_SIZE=5;

    // Paging Supported list beer method

    @GetMapping(produces ={"application/Json"})
    public ResponseEntity<BeerPagedList> listBeers(

                                @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                @RequestParam(value="pageSize", required=false) Integer pageSize,
                                @RequestParam(value="beerName",required = false) String beerName,
                                @RequestParam(value = "beerStyle",required =false) BeerStyleEnum beerStyle){
    if(pageNumber ==null || pageNumber < 0)
    {
        pageNumber=DEFAULT_PAGE_NUMBER;
    }
    if(pageSize ==null || pageSize <1)
    {
    pageSize=DEFAULT_PAGE_SIZE;
    }

        BeerPagedList beerList = beerService.listBeer(beerName, beerStyle, PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(beerList,HttpStatus.OK);
    }


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


