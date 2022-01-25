package xyz.carara.springessencials.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xyz.carara.springessencials.domain.Anime;
import xyz.carara.springessencials.mapper.AnimeMapper;
import xyz.carara.springessencials.repository.AnimeRepository;
import xyz.carara.springessencials.requests.AnimePostRequestBody;
import xyz.carara.springessencials.requests.AnimePutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository repository;

    public List<Anime> listAll() {
        return repository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return repository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void delete(long id) {
        repository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime animeUpdated = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        animeUpdated.setId(savedAnime.getId());
        repository.save(animeUpdated);
    }
}

