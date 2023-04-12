package com.everis.d4i.tutorial.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.time.Year;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CustomActorRest {


    private Long id;

    private Long idactor;
    private String name;

    private Date bornDate;

    private String nationality;

    private String gender;

    private String bio;

    private Long idtv;
    private String nametv;
    private String shortDescription;
    private String longDescription;
    private Year year;
    private byte recommendedAge;
    private CategoryRest category;
    private String advertising;

    private Long idch;
    private short number;
    private String namech;
    private short duration;

}
