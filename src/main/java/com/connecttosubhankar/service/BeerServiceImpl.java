package com.connecttosubhankar.service;

import com.connecttosubhankar.domain.Beer;
import com.connecttosubhankar.mapper.BeerMapper;
import com.connecttosubhankar.repositories.BeerRepository;
import com.connecttosubhankar.web.exception.NotFoundException;
import com.connecttosubhankar.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService{
    private final BeerRepository beerRepository;

    private final BeerMapper beerMapper;

    @Override
    public BeerDto findBeerById(UUID id) {
        return beerMapper.convertBeerToBeerDTO(beerRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer savedBeer = beerRepository.save(beerMapper.covertBeerDTOToBeer(beerDto));
        return beerMapper.convertBeerToBeerDTO(savedBeer);
    }

    @Override
    public BeerDto updateBeer(UUID id, BeerDto beerDto) {
        Beer getBeer = beerRepository.findById(id).orElseThrow(NotFoundException::new);

            BeerDto beerDtoTobeSet=beerDto;
            beerDtoTobeSet.setId(getBeer.getId());
        Beer savedBeer = beerRepository.save(beerMapper.covertBeerDTOToBeer(beerDtoTobeSet));
        return beerMapper.convertBeerToBeerDTO(savedBeer);

    }

    @Override
    public void deleteBeerById(UUID id) {

    }
}
