package xyz.carara.springessencials.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import xyz.carara.springessencials.domain.Anime;

import java.util.List;

@Log4j2
public class Client {
    public static void main(String[] args) {
        //GET
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/1", Anime.class);
        log.info(entity);

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
        log.info(animes);
        //@formatter:off
        //exchange retorna objeto dentro de um ResponseEntity
        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
        //@formatter:on
        log.info(exchange.getBody());

        //POST
        Anime newAnime = Anime.builder().name("novo anime").build();
        Anime newAnimeSaved = new RestTemplate().postForObject("http://localhost:8080/animes/", newAnime, Anime.class);
        log.info("saved anime: {}", newAnimeSaved);

        Anime newAnime2 = Anime.builder().name("new anime").build();
        ResponseEntity<Anime> newAnimeSaved2 = new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.POST,
                new HttpEntity<>(newAnime2, createJsonHeader()),
                Anime.class);
        log.info("saved anime: {}", newAnimeSaved2);

        //PUT
        Anime animeToBeUpdated = newAnimeSaved2.getBody();
        animeToBeUpdated.setName("anime atualizado com put");

        ResponseEntity<Void> animeUpdated = new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.PUT,
                new HttpEntity<>(animeToBeUpdated, createJsonHeader()),
                Void.class);

        log.info(animeUpdated);

        //DELETE
        ResponseEntity<Void> animeDeleted = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                animeToBeUpdated.getId());

        log.info(animeDeleted);
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
