package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface TvShowController {

	NetflixResponse<List<TvShowRest>> getTvShowsByCategory(Long categoryId) throws NetflixException;

	NetflixResponse<TvShowRest> getTvShowById(Long id) throws NetflixException;

	NetflixResponse<TvShowRest> createShow(TvShow tvShow , BindingResult result) throws NetflixException;

	NetflixResponse<TvShowRest> addShowCategory(Long idShow,  Long idCategory) throws NetflixException;

	NetflixResponse<TvShowRest> deleteShow(Long idShow) throws NetflixException;

	NetflixResponse<TvShowRest> updateTVShow(TvShow tvShow , BindingResult result, Long id) throws NetflixException;

}
