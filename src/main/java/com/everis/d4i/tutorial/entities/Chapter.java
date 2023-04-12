package com.everis.d4i.tutorial.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "CHAPTERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter implements Serializable {

	private static final long serialVersionUID = 8725949484031409482L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NUMBER")
	private short number;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DURATION")
	private short duration;

	@ApiModelProperty(hidden=true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEASON_ID", nullable = false)
	private Season season;

	@ApiModelProperty(hidden=true)
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name = "chapter_actors",
			joinColumns = @JoinColumn(name = "chapters_id"),
			inverseJoinColumns = @JoinColumn(name = "actors_id")
	)
	private List<Actor> actors;


}
