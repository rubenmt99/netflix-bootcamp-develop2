package com.everis.d4i.tutorial.controllers.impl;

import java.util.List;

import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.everis.d4i.tutorial.controllers.ChapterController;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.ChapterService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_CHAPTER)
public class ChapterControllerImpl implements ChapterController {

	@Autowired
	private ChapterService chapterService;

	@Autowired
	private ChapterRepository chapterRepository;

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<List<ChapterRest>> getChaptersByTvShowIdAndSeasonNumber(@PathVariable Long tvShowId,
			@PathVariable short seasonNumber) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				chapterService.getChaptersByTvShowIdAndSeasonNumber(tvShowId, seasonNumber));
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_NUMBER, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<ChapterRest> getChapterByTvShowIdAndSeasonNumberAndChapterNumber(@PathVariable Long tvShowId,
			@PathVariable short seasonNumber, @PathVariable short number) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				chapterService.getChapterByTvShowIdAndSeasonNumberAndChapterNumber(tvShowId, seasonNumber, number));
	}

	@Override
	@PutMapping(value = RestConstants.RESOURCE_ID)
	@PreAuthorize("hasAuthority('ADMIN')")
	public NetflixResponse<ChapterRest> updateChapter(@Valid @RequestBody Chapter chapter, BindingResult result, @PathVariable("id") Long id) throws NetflixException {
		chapter.setId(id);
		if (result.hasErrors()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		ChapterRest chapterRest = chapterService.updateChapter(chapter);
		if (chapterRest == null){
			return new NetflixResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.NOT_FOUND), "No TvShow found");
		}
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				chapterRest);
	}

	@Override
	@DeleteMapping("/{id}")
	public NetflixResponse<ChapterRest> deleteChapter(@PathVariable("id") Long idChapter) throws NetflixException {
		Chapter chapter = chapterRepository.findById(idChapter).orElse(null);
		if (chapter == null) {
			return new NetflixResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.NOT_FOUND), "No chapter found by ID");
		}
		chapterService.deleteChapter(idChapter);
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK);
	}

}
