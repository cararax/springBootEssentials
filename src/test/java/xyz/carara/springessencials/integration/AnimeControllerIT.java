package xyz.carara.springessencials.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import xyz.carara.springessencials.domain.Anime;
import xyz.carara.springessencials.repository.AnimeRepository;
import xyz.carara.springessencials.util.AnimeCreator;
import xyz.carara.springessencials.wrapper.PageableResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase  //pra usar o banco em memória
class AnimeControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort    //para pegar o valor da porta
    private int port;

    @Autowired
    private AnimeRepository repository;

    @Test
    @DisplayName("list returns list of anime inside page object when successful")
    void list_ReturnsListOfAnimesInsidePageObject_WhenSuccessful() {
        Anime savedAnime = repository.save(AnimeCreator.createAnimeToBeSaved());

        String expectedName = savedAnime.getName();
        
        PageableResponse<Anime> animePage = testRestTemplate.exchange("/animes",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageableResponse<Anime>>() {
                })
                .getBody();

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(animePage.toList().get(0).getName())
                .isEqualTo(expectedName);
    }
}
