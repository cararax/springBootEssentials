package xyz.carara.springessencials.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.carara.springessencials.domain.Anime;
import xyz.carara.springessencials.requests.AnimePostRequestBody;
import xyz.carara.springessencials.requests.AnimePutRequestBody;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);
    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);
}
