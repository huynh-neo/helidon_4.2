package io.helidon.examples.quickstart.mp.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DynamicLink {
    private long id;
    @NotBlank(message = "URL must not be blank")
    @Size(max = 2048, message = "URL is too long")
    private String url;
    @NotBlank(message = "URL must not be blank")
    @Size(max = 2048, message = "description is too long")
    private String description;
}
