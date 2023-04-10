package com.everis.d4i.tutorial.controllers.impl;

import java.util.List;

import com.everis.d4i.tutorial.entities.TvShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.everis.d4i.tutorial.controllers.TvShowController;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.TvShowService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_TV_SHOW)
public class TvShowControllerImpl implements TvShowController {

	@Autowired
	private TvShowService tvShowService;


	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<List<TvShowRest>> getTvShowsByCategory(@RequestParam Long categoryId)
			throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShowService.getTvShowsByCategory(categoryId));
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<TvShowRest> getTvShowById(@PathVariable Long id) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShowService.getTvShowById(id));
	}

	@Override
	@PostMapping()
	public NetflixResponse<TvShowRest> createShow(@Valid @RequestBody TvShow tvShow, BindingResult result) throws NetflixException {
		if (result.hasErrors()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		TvShowRest tvShow1 = tvShowService.createTvShow(tvShow);
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShow1);
	}

	@Override
	@GetMapping(value = "/addCategory", produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<TvShowRest> addShowCategory(@RequestParam Long idShow, @RequestParam Long idCategory) throws NetflixException {

		TvShowRest tvShow1 = tvShowService.addCategory(idShow,idCategory);
		if(tvShow1 != null){
			return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
					tvShow1);
		}
			return new NetflixResponse<>(CommonConstants.ERROR,String.valueOf(HttpStatus.NOT_FOUND),"Category or show not found");
	}

	@Override
	@DeleteMapping("/{id}")
	public NetflixResponse<TvShowRest> deleteShow(@PathVariable("id") Long idShow) throws NetflixException {
		tvShowService.deleteShow(idShow);
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK);
	}

}
