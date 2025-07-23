package hexlet.code.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class Url {
    private Long id;
    private String name;
    private LocalDateTime createdAt;

    public Url(String name) {
        this.name = name;
    }
}

