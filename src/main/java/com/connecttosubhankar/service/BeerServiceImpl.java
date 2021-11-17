package com.connecttosubhankar.service;

import com.connecttosubhankar.domain.Beer;
import com.connecttosubhankar.mapper.BeerMapper;
import com.connecttosubhankar.repositories.BeerRepository;
import com.connecttosubhankar.web.exception.NotFoundException;
import com.connecttosubhankar.web.model.BeerDto;
import com.connecttosubhankar.web.model.BeerPagedList;
import com.connecttosubhankar.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService{
    private final BeerRepository beerRepository;

    private final BeerMapper beerMapper;


    @Override
    public BeerPagedList listBeer(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest,Boolean showInventory) {

        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if(!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle))
        {
            beerPage=beerRepository.findAllByBeerName(beerName,pageRequest);
        }
        else if(StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle))
        {
            beerPage=beerRepository.findAllByBeerStyle(beerStyle,pageRequest);
        }
        if(!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle))
        {
            beerPage=beerRepository.findAllByBeerNameAndBeerStyle(beerName,beerStyle,pageRequest);
        }
        else
        {
            beerPage=beerRepository.findAll(pageRequest);
        }

if(showInventory)
{
    beerPagedList = new BeerPagedList(beerPage.getContent()
            .stream()
            .map(beerMapper::convertBeerToBeerDTOWithInventory)
            .collect(Collectors.toList())
            ,
            PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
            beerPage.getTotalElements());
    return beerPagedList;
}
else {
    beerPagedList = new BeerPagedList(beerPage.getContent()
            .stream()
            .map(beerMapper::convertBeerToBeerDTOWithoutInventory)
            .collect(Collectors.toList())
            ,
            PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
            beerPage.getTotalElements());
    return beerPagedList;
}
    }

    @Override
    public BeerDto findBeerById(UUID id,Boolean showInventory) {
        if(showInventory)
        return beerMapper.convertBeerToBeerDTOWithInventory(beerRepository.findById(id).orElseThrow(NotFoundException::new));
        else
            return  beerMapper.convertBeerToBeerDTOWithoutInventory(beerRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer savedBeer = beerRepository.save(beerMapper.covertBeerDTOToBeer(beerDto));
        return beerMapper.convertBeerToBeerDTOWithInventory(savedBeer);
    }

    @Override
    public BeerDto updateBeer(UUID id, BeerDto beerDto) {
        Beer getBeer = beerRepository.findById(id).orElseThrow(NotFoundException::new);

            BeerDto beerDtoTobeSet=beerDto;
            beerDtoTobeSet.setId(getBeer.getId());
        Beer savedBeer = beerRepository.save(beerMapper.covertBeerDTOToBeer(beerDtoTobeSet));
        return beerMapper.convertBeerToBeerDTOWithInventory(savedBeer);

    }

    @Override
    public void deleteBeerById(UUID id) {

    }


}
