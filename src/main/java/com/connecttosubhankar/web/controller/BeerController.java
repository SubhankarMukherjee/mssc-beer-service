package com.connecttosubhankar.web.controller;

import com.connecttosubhankar.service.BeerService;
import com.comon.brewery.model.BeerDto;
import com.comon.brewery.model.BeerPagedList;
import com.comon.brewery.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class BeerController {

    private final BeerService beerService;   // with lombok Required arg annottaion, andfinal it wills
    //set the dependency

    private static final Integer DEFAULT_PAGE_NUMBER=0;
    private static final Integer DEFAULT_PAGE_SIZE=5;
    private static boolean DEAFULT_SHOW_INVENTORY=false;

    // Paging Supported list beer method


    @GetMapping(produces ={"application/Json"},path="/beer")
    public ResponseEntity<BeerPagedList> listBeers(

                                @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                @RequestParam(value="pageSize", required=false) Integer pageSize,
                                @RequestParam(value="beerName",required = false) String beerName,
                                @RequestParam(value = "beerStyle",required =false) BeerStyleEnum beerStyle,
                                @RequestParam(value = "showInventoryOnHand",required =false) Boolean showInventoryOnHand){
    if(pageNumber ==null || pageNumber < 0)
    {
        pageNumber=DEFAULT_PAGE_NUMBER;
    }
    if(pageSize ==null || pageSize <1)
    {
    pageSize=DEFAULT_PAGE_SIZE;
    }

        if(showInventoryOnHand!=null)
        {
            if(showInventoryOnHand)
            DEAFULT_SHOW_INVENTORY=true;
        }

        BeerPagedList beerList = beerService.listBeer(beerName, beerStyle, PageRequest.of(pageNumber, pageSize),DEAFULT_SHOW_INVENTORY);
        return new ResponseEntity<>(beerList,HttpStatus.OK);
    }



    @GetMapping("/beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId,
                                               @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand){
        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }
        System.out.println("BeerId:"+ beerId);
        System.out.println("showInventoryOnHand:"+showInventoryOnHand);
        BeerDto getBeerDTO = beerService.findBeerById(beerId, showInventoryOnHand);

        System.out.println("Get Beer:"+ getBeerDTO.toString());

        return new ResponseEntity<>(getBeerDTO, HttpStatus.OK);
    }

    @GetMapping("/beerUpc/{upc}")
    public ResponseEntity<BeerDto> getBeerByUPC(@PathVariable("upc") String upc)
    {


        return new ResponseEntity<>(beerService.findBeerByUPC(upc), HttpStatus.OK);
    }


    @PostMapping("/beer")
    public ResponseEntity<BeerDto> SaveNewBeer(@Valid @RequestBody BeerDto beerDto)
    {

        return new ResponseEntity( beerService.saveNewBeer(beerDto),HttpStatus.CREATED);
    }

    @PutMapping("/beer/{beerId}")
    public ResponseEntity<BeerDto> updateBeer(@PathVariable("beerId") UUID id,@Valid @RequestBody BeerDto beerDto)
    {

        return new ResponseEntity(beerService.updateBeer(id,beerDto),HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/beer/{beerId}")
    public ResponseEntity<BeerDto> DeleteById(@PathVariable("beerId") UUID id)
    {
        beerService.deleteBeerById(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}


