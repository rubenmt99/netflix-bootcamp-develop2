package com.everis.d4i.tutorial.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Year;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TV_SHOWS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TvShow implements Serializable {

	private static final long serialVersionUID = 4916713904971425156L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SHORT_DESC", nullable = true)
	private String shortDescription;

	@Column(name = "LONG_DESC", nullable = true)
	private String longDescription;

	@Column(name = "YEAR")
	private Year year;

	@Column(name = "RECOMMENDED_AGE")
	private byte recommendedAge;

	@ApiModelProperty(hidden=true)
	@ManyToMany(cascade = CascadeType.DETACH)
	@JoinTable(name = "show_categories",
			joinColumns = @JoinColumn(name = "tv_shows_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> category;

	@Column(name = "ADVERTISING", nullable = true)
	private String advertising;

	@ApiModelProperty(hidden=true)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tvShow")
	private List<Season> seasons;

	@ApiModelProperty(hidden=true)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tvShow")
	private List<Award> awards;

}
