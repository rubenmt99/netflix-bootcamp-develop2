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
//@Entity
/*@SqlResultSetMapping(
        name = "CustomActorRestMapping",
        classes = @ConstructorResult(
                targetClass = CustomActorRest.class,
                columns = {
                        @ColumnResult(name = "name_tv", type = String.class),
                        @ColumnResult(name = "advertising", type = String.class),
                        @ColumnResult(name = "long_desc", type = String.class),
                        @ColumnResult(name = "recommended_age", type = Integer.class),
                        @ColumnResult(name = "short_desc", type = String.class),
                        @ColumnResult(name = "year", type = Integer.class),
                        @ColumnResult(name = "name_ch", type = String.class),
                        @ColumnResult(name = "duration", type = Integer.class),
                        @ColumnResult(name = "number", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "bio", type = String.class),
                        @ColumnResult(name = "born_date", type = Date.class),
                        @ColumnResult(name = "gender", type = String.class),
                        @ColumnResult(name = "nationality", type = String.class)
                }
        )
)*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomActorRest {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private ActorRest actorRest;
    private List<TvShowRest> tvShowRest;

//    private List<ChapterRest> chapterRest;




}


