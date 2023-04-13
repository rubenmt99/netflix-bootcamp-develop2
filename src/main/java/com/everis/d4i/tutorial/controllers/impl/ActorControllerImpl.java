package com.everis.d4i.tutorial.controllers.impl;

import com.everis.d4i.tutorial.controllers.ActorController;
import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.CustomActorRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( RestConstants.RESOURCE_ACTOR)
public class ActorControllerImpl implements ActorController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private ActorRepository actorRepository;

    @Override
    @GetMapping(value = "/chapter")
    public NetflixResponse<List<ActorRest>> getActorsByChapter(@RequestParam Long chapterId) throws NetflixException {
        List<ActorRest> actorRest = actorService.getActorsByChapter(chapterId);
        if (actorRest.isEmpty()){
            return new NetflixResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.NOT_FOUND), "No se encontraron actores");
        }

        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorRest);
    }

    @Override
    @GetMapping
    public NetflixResponse<List<ActorRest>> getAllActors() throws NetflixException {
        List<ActorRest> actorRest = actorService.getAllActors();
        if (actorRest.isEmpty()){
            return new NetflixResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.NOT_FOUND), "No se encontraron actores");
        }

        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK, actorRest);
    }

    @Override
    @GetMapping(value = RestConstants.RESOURCE_ID)
    public NetflixResponse<ActorRest> getActorById(@PathVariable Long id) throws NetflixException {
        ActorRest actorRest = actorService.getActorById(id);
        if (actorRest == null){
            return new NetflixResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.NOT_FOUND), "No se encontraron actores");
        }

        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorRest);
    }

    @Override
    @PostMapping(value = "/create")
    public NetflixResponse<ActorRest> createActor(@Valid @RequestBody Actor actor, BindingResult result) throws NetflixException {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        ActorRest actorRest = actorService.createActor(actor);

        if (actorRest == null){
            return new NetflixResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.NOT_FOUND), "No se pudo crear el actor");
        }

        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorRest);

    }

    @Override
    @DeleteMapping(value =RestConstants.RESOURCE_ID)
    public NetflixResponse<ActorRest> deleteActor(@PathVariable("id") Long idActor) throws NetflixException {
        Actor actor = actorRepository.findById(idActor).orElse(null);
        if (actor == null){
            return new NetflixResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.NOT_FOUND), "No Actor found by ID");
        }
        actorService.deleteActor(idActor);
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK);
    }

    @Override
    @PutMapping(value = RestConstants.RESOURCE_ID)
    public NetflixResponse<ActorRest> updateActor(@Valid @RequestBody Actor actor, BindingResult result, @PathVariable Long id) throws NetflixException {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        actor.setId(id);
        ActorRest actorRest = actorService.updateActor(actor);
        if (actorRest == null){
            return new NetflixResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.NOT_FOUND), "No se pudo actualizar el actor");
        }
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK, actorRest);
    }

    @Override
    @GetMapping(value = "/alldata")
    public NetflixResponse<CustomActorRest> customActorSearch(@RequestParam Long id) throws NetflixException {
        CustomActorRest customActorRests = actorService.customActorSearch(id);
        if (customActorRests == null){
            return new NetflixResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.NOT_FOUND), "No data found");
        }

        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                customActorRests);
    }
}
