package xyz.carara.springessencials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.carara.springessencials.domain.Anime;


public interface AnimeRepository extends JpaRepository<Anime, Long> {

}
