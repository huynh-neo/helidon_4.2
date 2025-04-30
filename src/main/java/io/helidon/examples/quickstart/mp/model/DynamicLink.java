package io.helidon.examples.quickstart.mp.model;

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
    private String url;
    private String description;
}
