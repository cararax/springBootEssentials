package xyz.carara.springessencials.util;

import xyz.carara.springessencials.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved() {
        return Anime.builder()
                .name("Test Anime")
                .build();
    }

    public static Anime createValidAnime() {
        return Anime.builder()
                .id(1L)
                .name("Valid Anime")
                .build();
    }

    public static Anime createValidUpdatedAnime() {
        return Anime.builder()
                .id(1L)
                .name("Valid Anime Updated")
                .build();
    }
}
