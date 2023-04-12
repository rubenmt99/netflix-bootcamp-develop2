package com.everis.d4i.tutorial.controllers.impl;

import com.everis.d4i.tutorial.controllers.AwardController;
import com.everis.d4i.tutorial.entities.Award;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.AwardService;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RestConstants.RESOURCE_AWARD)
public class AwardControllerImpl implements AwardController {

    @Autowired
    private AwardService awardService;


    @Override
    @GetMapping
    public NetflixResponse<List<AwardRest>> getAwardsByShow(@RequestParam Long showId) throws NetflixException {
        List<AwardRest> awardRest = awardService.getAwardsByTvShow(showId);

        if (awardRest.isEmpty()){
            return new NetflixResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.NOT_FOUND), "No Awards found");
        }
        return new NetflixResponse<>(CommonConstants.OK, String.valueOf(HttpStatus.OK), CommonConstants.OK, awardRest);
    }
}
