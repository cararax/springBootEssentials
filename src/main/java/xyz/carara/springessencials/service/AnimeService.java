package xyz.carara.springessencials.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.carara.springessencials.domain.Anime;
import xyz.carara.springessencials.exception.BadRequestException;
import xyz.carara.springessencials.mapper.AnimeMapper;
import xyz.carara.springessencials.repository.AnimeRepository;
import xyz.carara.springessencials.requests.AnimePostRequestBody;
import xyz.carara.springessencials.requests.AnimePutRequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository repository;

    public Page<Anime> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public List<Anime> listAllNonPageable() {
        return repository.findAll();
    }

    public List<Anime> findByName(String name) {
        return repository.findByName(name);
    }

    public Anime findByIdOrThrowBadRequestException(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
    }

    @Transactional //(rollbackOn = Exception.class)
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

