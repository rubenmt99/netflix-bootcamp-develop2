package com.everis.d4i.tutorial.services;

import java.util.List;

import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;

public interface TvShowService {

	List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException;

	TvShowRest getTvShowById(Long id) throws NetflixException;

	TvShowRest createTvShow(TvShow tvShow) throws NetflixException;

	TvShowRest addCategory(Long idShow, Long idCategory) throws NetflixException;

	void deleteShow(Long idShow) throws NetflixException;

	TvShowRest updateTvShow(TvShow tvShow) throws NetflixException;
}
