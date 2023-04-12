package com.everis.d4i.tutorial.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ActorRest {


    private Long id;

    private String name;

    private Date bornDate;

    private String nationality;

    private String gender;

    private String bio;


}
