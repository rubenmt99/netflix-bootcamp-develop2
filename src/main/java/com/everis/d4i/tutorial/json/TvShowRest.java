package com.everis.d4i.tutorial.json;

import java.io.Serializable;
import java.time.Year;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TvShowRest implements Serializable {

	private static final long serialVersionUID = 4916713904971425156L;

	private Long id;
	private String name;
	private String shortDescription;
	private String longDescription;
	private Year year;
	private byte recommendedAge;
	private CategoryRest category;
	private String advertising;

	private List<ChapterRest> chapterRest;

	public TvShowRest(Long id, String name, String shortDescription, String longDescription, Year year, byte recommendedAge, String advertising) {
		this.id = id;
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.year = year;
		this.recommendedAge = recommendedAge;
		this.advertising = advertising;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TvShowRest)) return false;
		TvShowRest that = (TvShowRest) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(name, that.name) &&
				Objects.equals(shortDescription, that.shortDescription) &&
				Objects.equals(longDescription, that.longDescription) &&
				Objects.equals(year, that.year) &&
				recommendedAge == that.recommendedAge &&
				Objects.equals(advertising, that.advertising);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, shortDescription, longDescription, year, recommendedAge, advertising);
	}
}
