package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.CustomActorRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.services.ActorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {


    @Autowired
    private ActorRepository actorRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<ActorRest> getActorsByChapter(Long chapterId) throws NetflixException {
        try{
            return actorRepository.actorsByChapter(chapterId).stream()
                    .map(actors -> modelMapper.map(actors, ActorRest.class)).collect(Collectors.toList());
        }catch (EntityNotFoundException entityNotFoundException){
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }

    @Override
    public List<ActorRest> getAllActors() throws NetflixException {
            return actorRepository.findAll().stream().map(actor -> modelMapper.map(actor, ActorRest.class)).collect(Collectors.toList());

    }

    @Override
    public ActorRest getActorById(Long id) throws NetflixException {
        try {
            return modelMapper.map(actorRepository.findById(id).orElse(null), ActorRest.class);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }

    @Override
    public ActorRest createActor(Actor actor) throws NetflixException {
        try{
            return modelMapper.map(actorRepository.save(actor), ActorRest.class);
        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public void deleteActor(Long idActor) throws NetflixException {
        Actor tvShow = actorRepository.findById(idActor).orElse(null);
        if(tvShow != null){
            try {
                actorRepository.deleteById(idActor);
            }catch (Exception e){
                throw new NotFoundException(e.getMessage());
            }
        }
    }

    @Override
    public ActorRest updateActor(Actor actor) throws NetflixException {
        Actor actor1 = actorRepository.findById(actor.getId()).orElse(null);
        if (actor1 == null){
            return null;
        }

        actor1.setBio(actor.getBio());
        actor1.setGender(actor.getGender());
        actor1.setNationality(actor.getNationality());
        actor1.setName(actor.getName());
        actor1.setBornDate(actor.getBornDate());

        actorRepository.save(actor1);
        return modelMapper.map(actor1,ActorRest.class);
    }

    @Override
    public List<CustomActorRest> customActorSearch(Long id) throws NetflixException {
        List<CustomActorRest> customActors = actorRepository.customActorSearch(id);
        if (customActors.isEmpty()){
            return null;
        }

        return customActors;
    }
}
