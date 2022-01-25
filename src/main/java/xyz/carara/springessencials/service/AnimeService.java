package xyz.carara.springessencials.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xyz.carara.springessencials.domain.Anime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AnimeService {

    private final static List<Anime> animeList;

    static {
        animeList = new ArrayList<>(List.of(new Anime(1L, "Boku No Hero"), new Anime(2L, "Berserk")));
    }

    public List<Anime> listAll() {
        return animeList;
    }

    public Anime findById(long id) {
        return animeList.stream().filter(anime -> anime.getId().equals(id)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }

    public Anime save(Anime newAnime) {
        newAnime.setId(ThreadLocalRandom.current().nextLong(3, 100000));
        animeList.add(newAnime);
        return newAnime;
    }

    public void delete(long id) {
        animeList.remove(findById(id));
    }

    public void replace(Anime animeUpdated) {
        delete(animeUpdated.getId());
        animeList.add(animeUpdated);
    }
}

