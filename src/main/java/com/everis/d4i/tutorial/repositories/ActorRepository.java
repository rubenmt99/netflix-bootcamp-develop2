package com.everis.d4i.tutorial.repositories;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.json.CustomActorRest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query(value = "select a.id, a.bio, a.born_date, a.gender, a.name, a.nationality" +
            " from actors a join chapter_actors ca on a.id = ca.actors_id where ca.chapters_id = :id", nativeQuery = true)
    List<Actor> actorsByChapter(@Param("id") Long id);



    @Query(value = "select t.id as idtv, t.name as nametv, t.*, c.id as idch, c.name as namech, c.*, a.id as idactor, a.* from tv_shows t join seasons s on t.id = s.tv_shows_id join chapters c on s.id = c.season_id" +
            " join chapter_actors ca on c.id = ca.chapters_id join actors a on a.id = ca.actors_id where a.id = :number", nativeQuery = true)
    List<CustomActorRest> customActorSearch(@Param("number") Long number);


}
