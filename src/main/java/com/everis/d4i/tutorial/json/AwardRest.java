package com.everis.d4i.tutorial.json;

import com.everis.d4i.tutorial.entities.TvShow;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.Year;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AwardRest {


    private Long id;

    private String name;

    private Year year;

    private String description;

    private String country;


}
