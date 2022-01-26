package xyz.carara.springessencials.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AnimePostRequestBody {
    @NotEmpty(message = "Anime name can not be empty")
    private String name;
}
