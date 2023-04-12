package com.everis.d4i.tutorial.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Year;
import java.util.Date;

@Entity
@Table(name = "awards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "YEAR")
    private Year year;

    private String description;

    private String country;

    @ApiModelProperty(hidden=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tv_shows_id")
    private TvShow tvShow;
}
