package xyz.carara.springessencials.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class AnimePutRequestBody {
    //    @NotEmpty(message = "Anime name can not be empty")
    private long id;
    @NotEmpty(message = "Anime name can not be empty")
    @Schema(description = "This is the Anime's' name", example = "Naruto", required = true)
    private String name;
}
