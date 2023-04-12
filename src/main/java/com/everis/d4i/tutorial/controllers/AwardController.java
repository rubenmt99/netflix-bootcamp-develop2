package com.everis.d4i.tutorial.controllers;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

import java.util.List;

public interface AwardController {


    NetflixResponse<List<AwardRest>> getAwardsByShow(Long ShowId) throws NetflixException;

}
