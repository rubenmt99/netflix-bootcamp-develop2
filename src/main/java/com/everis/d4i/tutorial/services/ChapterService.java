package com.everis.d4i.tutorial.services;

import java.util.List;

import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.TvShowRest;

public interface ChapterService {

	List<ChapterRest> getChaptersByTvShowIdAndSeasonNumber(Long tvShowId, short seasonNumber) throws NetflixException;

	ChapterRest getChapterByTvShowIdAndSeasonNumberAndChapterNumber(Long tvShowId, short seasonNumber,
			short chapterNumber) throws NetflixException;


	ChapterRest updateChapter(Chapter chapter) throws NetflixException;

	void deleteChapter(Long idChapter) throws NetflixException;
}
