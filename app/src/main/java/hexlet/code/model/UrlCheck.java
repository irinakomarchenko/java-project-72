package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UrlCheck {
    private Long id;
    private Long urlId;
    private int statusCode;
    private String h1;
    private String title;
    private String description;
    private LocalDateTime createdAt;

    public UrlCheck(int statusCode, String h1, String title, String description, LocalDateTime createdAt) {
        this.statusCode = statusCode;
        this.h1 = h1;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
    }

    public UrlCheck(int statusCode, LocalDateTime createdAt) {
        this.statusCode = statusCode;
        this.createdAt = createdAt;
    }
}
