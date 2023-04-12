package com.everis.d4i.tutorial.services;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.CustomActorRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorService {

    List<ActorRest> getActorsByChapter(Long chapterId) throws NetflixException;

    List<ActorRest> getAllActors() throws NetflixException;

    ActorRest getActorById(Long id) throws NetflixException;

    ActorRest createActor(Actor actor) throws NetflixException;

    void deleteActor(Long idActor) throws NetflixException;

    ActorRest updateActor(Actor actor) throws NetflixException;

    List<CustomActorRest> customActorSearch(Long id) throws NetflixException;
}
