package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.TvShowService;

@Service
public class TvShowServiceImpl implements TvShowService {

	@Autowired
	private TvShowRepository tvShowRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException {

		return tvShowRepository.findByCategoryId(categoryId).stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRest.class)).collect(Collectors.toList());

	}

	@Override
	public TvShowRest getTvShowById(Long id) throws NetflixException {

		try {
			return modelMapper.map(tvShowRepository.getOne(id), TvShowRest.class);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}

	}

	@Override
	public TvShowRest createTvShow(TvShow tvShow) throws NetflixException {
		try{
			return modelMapper.map(tvShowRepository.save(tvShow), TvShowRest.class);
		}catch (Exception e){
			throw new NotFoundException(e.getMessage());
		}
	}

	@Override
	public TvShowRest addCategory(Long idShow, Long idCategory) throws NetflixException {

		TvShow tvShow1 = tvShowRepository.findById(idShow).orElse(null);
		Category category1 = categoryRepository.findById(idCategory).orElse(null);

		if (tvShow1!= null && category1 != null && !tvShow1.getCategory().contains(category1)){
			tvShow1.getCategory().add(category1);
		}else {
			return null;
		}
		tvShowRepository.save(tvShow1);
		return  modelMapper.map(tvShow1, TvShowRest.class);
	}

	@Override
	public void deleteShow(Long idShow) throws NetflixException {
		TvShow tvShow = tvShowRepository.findById(idShow).orElse(null);
		if(tvShow != null){
			try {
				tvShowRepository.deleteById(idShow);
			}catch (Exception e){
				throw new NotFoundException(e.getMessage());
			}
		}
	}

	@Override
	public TvShowRest updateTvShow(TvShow tvShow) throws NetflixException {
		TvShow tvShow1 = tvShowRepository.findById(tvShow.getId()).orElse(null);
		if(tvShow1 == null){
			return null;
		}
		tvShow1.setName(tvShow.getName());
		tvShow1.setAdvertising(tvShow.getAdvertising());
		/*tvShow1.setSeasons(tvShow.getSeasons());*/
		tvShow1.setLongDescription(tvShow.getLongDescription());
		tvShow1.setShortDescription(tvShow.getShortDescription());
		tvShow1.setRecommendedAge(tvShow.getRecommendedAge());
		tvShow1.setYear(tvShow.getYear());
//		tvShow1.setCategory(tvShow.getCategory());

		tvShowRepository.save(tvShow1);

		return  modelMapper.map(tvShow1, TvShowRest.class);
	}


}
