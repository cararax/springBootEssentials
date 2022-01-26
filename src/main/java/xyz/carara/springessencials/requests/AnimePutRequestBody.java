package xyz.carara.springessencials.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AnimePutRequestBody {
    @NotEmpty(message = "Anime name can not be empty")
    private long id;
    @NotEmpty(message = "Anime name can not be empty")
    private String name;
}
