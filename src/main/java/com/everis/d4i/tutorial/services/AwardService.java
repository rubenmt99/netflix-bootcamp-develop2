package com.everis.d4i.tutorial.services;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.AwardRest;

import java.util.List;

public interface AwardService {

    List<AwardRest> getAwardsByTvShow(Long id) throws NetflixException;

}
