package xyz.carara.springessencials.repository;

import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import xyz.carara.springessencials.domain.Anime;
import xyz.carara.springessencials.util.AnimeCreator;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
@Log4j2
class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save persists anime when successful")
    void save_PersistAnime_WhenSuccessful() {
        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
    }

    @Test
    @DisplayName("Save updates anime when successful")
    void save_UpdatesAnime_WhenSuccessful() {
        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        animeSaved.setName("outro nome");
        Anime animeUpdated = this.animeRepository.save(animeSaved);
        log.info(animeUpdated.getName());

        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());
    }

    @Test
    @DisplayName("Delete updates anime when successful")
    void delete_RemovesAnime_WhenSuccessful() {
        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        this.animeRepository.delete(animeSaved);

        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by name returns list of animes when Successful")
    void findByName_ReturnsListOfAnime_WhenSuccessuful() {
        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved ();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        String name = animeSaved.getName();
        List<Anime> animeList = this.animeRepository.findByName(name);

        Assertions.assertThat(animeList).isNotEmpty()
                .contains(animeSaved);
    }

    @Test
    @DisplayName("Find by name returns empty list  when no anime is found")
    void findByName_ReturnsEmptyList_WhenAnimeIsNotFound() {
        List<Anime> animeList = this.animeRepository.findByName("1234567890");
        Assertions.assertThat(animeList).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstrainValidationException when name is empty")
    void save_ThrowsConstrainValidationException_WhenNameIsEmpty() {
        Anime animeToBeSaved = new Anime();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()-> this.animeRepository.save(animeToBeSaved))
                .withMessageContaining("Anime name can not be empty");
    }
}