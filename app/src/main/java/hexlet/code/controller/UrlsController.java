package hexlet.code.controller;

import hexlet.code.dto.BasePage;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UrlsController {

    public  static void build(Context ctx) {
        String message = ctx.consumeSessionAttribute("message");
        var page = new BasePage(message);
        ctx.render("urls/build.jte", Collections.singletonMap("page", page));

    }

    public static void create(Context ctx) throws SQLException {
        String rawUrl = ctx.formParam("url");
        String name;
        try {
            name = normalizeUrl(rawUrl);
        } catch (IllegalArgumentException | URISyntaxException | MalformedURLException e) {
            ctx.sessionAttribute("message", "Incorrect URL");
            ctx.redirect(NamedRoutes.rootPath());
            return;
        }

        var url = new Url();
        if (UrlRepository.existsByName(name)) {
            ctx.sessionAttribute("message", "URL already exists");
        } else {
            url.setName(name);
            url.setCreatedAt(LocalDateTime.now());
            UrlRepository.save(url);
            ctx.sessionAttribute("message", "URL successfully created");
        }
        ctx.redirect(NamedRoutes.urlsPath());
    }

    public static void index(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        final int itemsPerPage = 10;
        var pageCount = getPage(urls.size(), itemsPerPage);
        int pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(pageCount);
        var page = new UrlsPage();

        if (!urls.isEmpty() && pageNumber > pageCount) {
            throw new NotFoundResponse("Page not found");
        } else if (!urls.isEmpty()) {
            urls = UrlRepository.getEntitiesPerPage(itemsPerPage, pageNumber);

            List<Long> urlIds = urls.stream()
                    .map(Url::getId)
                    .collect(Collectors.toList());
            Map<Long, UrlCheck> latestChecks = UrlCheckRepository.findLatestChecksByUrlIds(urlIds);
            Map<Url, UrlCheck> urlsWithLatestChecks = new HashMap<>();
            for (Url item : urls) {
                urlsWithLatestChecks.put(item, latestChecks.get(item.getId()));
            }
            page.setChecks(urlsWithLatestChecks);
            page.setPageNumber(pageNumber);
        }
        page.setUrls(urls);

        String message = ctx.consumeSessionAttribute("message");
        page.setMessage(message);
        if (pageCount > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, pageCount)
                    .boxed()
                    .collect(Collectors.toList());
            page.setPages(pages);
        }

        ctx.render("urls/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponse("URL not found"));
        var checks = UrlCheckRepository.find(id);

        var page = new UrlPage(url, checks);
        String message = ctx.consumeSessionAttribute("message");
        page.setMessage(message);

        ctx.render("urls/show.jte", Collections.singletonMap("page", page));

    }
    private static String normalizeUrl(String path) throws URISyntaxException, MalformedURLException {
        var newPath = new URI(path).toURL();
        return String.format("%s://%s", newPath.getProtocol(), newPath.getAuthority());
    }

    private static int getPage(int a, int b) {
        return (a % b == 0) ? (a / b) : (a / b + 1);
    }

}

