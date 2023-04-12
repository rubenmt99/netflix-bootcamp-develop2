package com.everis.d4i.tutorial.repositories;

import com.everis.d4i.tutorial.entities.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {

    List<Award> findByTvShowId(Long id);

}
