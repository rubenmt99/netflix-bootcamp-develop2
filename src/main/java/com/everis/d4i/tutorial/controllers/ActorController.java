package com.everis.d4i.tutorial.controllers;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.CustomActorRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ActorController {

    NetflixResponse<List<ActorRest>> getActorsByChapter(Long chapterId) throws NetflixException;

    NetflixResponse<List<ActorRest>> getAllActors() throws NetflixException;

    NetflixResponse<ActorRest> getActorById(Long id) throws NetflixException;

    NetflixResponse<ActorRest> createActor(Actor actor , BindingResult result) throws NetflixException;


    NetflixResponse<ActorRest> deleteActor(Long idActor) throws NetflixException;

    NetflixResponse<ActorRest> updateActor(Actor actor , BindingResult result, Long id) throws NetflixException;

    NetflixResponse<CustomActorRest> customActorSearch(Long id) throws NetflixException;
}
