package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.repositories.AwardRepository;
import com.everis.d4i.tutorial.services.AwardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AwardServiceImpl implements AwardService {

    @Autowired
    AwardRepository awardRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<AwardRest> getAwardsByTvShow(Long id) throws NetflixException {
        try{
            return awardRepository.findByTvShowId(id).stream()
                    .map(award -> modelMapper.map(award, AwardRest.class)).collect(Collectors.toList());
        }catch (EntityNotFoundException entityNotFoundException){
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }

}
