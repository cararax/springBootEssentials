package xyz.carara.springessencials.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xyz.carara.springessencials.domain.Anime;

import java.util.List;

@Service
public class AnimeService {

    private List<Anime> animeList = List.of(new Anime(1L, "Boku No Hero"), new Anime(2L, "Berserk"));

    public List<Anime> listAll() {
        return animeList;
    }

    public Anime findById(long id) {
        return animeList.stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }
}

