package hexlet.code.controller;

import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;

import java.sql.SQLException;


public class UrlChecksController {
    public static void check(Context ctx) throws SQLException {
        var urlId = ctx.pathParamAsClass("id", Long.class).get();

        var url = UrlRepository.findById(urlId)
                .orElseThrow(() -> new NotFoundResponse("Некорректный адрес"));
        var name  = url.getName();


        try {
            var response = Unirest.get(name)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                    .asString();

            int statusCode = response.getStatus();
            String body = response.getBody();

            if (statusCode >= 300 && statusCode < 400) {
                String redirectUrl = response.getHeaders().getFirst("Location");
                if (redirectUrl != null && !redirectUrl.isEmpty()) {
                    response = Unirest.get(redirectUrl)
                            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                            .asString();
                    statusCode = response.getStatus();
                    body = response.getBody();
                }
            }

            String h1 = "";
            String title = "";
            String description = "";

            if (body != null && !body.isEmpty()) {
                var doc = Jsoup.parse(body);
                var h1El = doc.selectFirst("h1");
                var descEl = doc.selectFirst("meta[name~=(?i)description]");

                h1 = h1El != null ? h1El.text() : "";
                title = doc.title();
                description = descEl != null ? descEl.attr("content") : "";
            }

            var check = new UrlCheck(statusCode, h1, title, description);
            check.setUrlId(urlId);
            UrlCheckRepository.save(check);

            boolean incomplete = statusCode != 200 || title.isEmpty() || h1.isEmpty() || description.isEmpty();
            if (incomplete) {
                ctx.sessionAttribute("message", "Проверка выполнена, но не удалось получить все данные "
                        + "(код " + statusCode + ")");
            } else {
                ctx.sessionAttribute("message", "Проверка выполнена успешно (код " + statusCode + ")");
            }

        } catch (Exception e) {
            System.out.println("[ERROR] Проверка не выполнена: " + e.getMessage());
            ctx.sessionAttribute("message", "Проверка не пройдена: " + e.getMessage());
        }

        ctx.redirect(NamedRoutes.urlPath(urlId));
    }
}


