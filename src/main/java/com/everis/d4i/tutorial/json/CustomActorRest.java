package com.everis.d4i.tutorial.json;

import com.everis.d4i.tutorial.entities.Chapter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Year;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomActorRest {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private ActorRest actorRest;
    private List<TvShowRest> tvShowRest;

}


