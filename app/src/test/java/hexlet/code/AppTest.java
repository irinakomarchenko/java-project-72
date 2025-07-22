package hexlet.code;

import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import hexlet.code.model.Url;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class AppTest {

    private Javalin app;
    private static MockWebServer mockWebServer;
    private static String pageUrl;
    private static String domainName;

    @BeforeAll
    public static void beforeAll() throws IOException {
        mockWebServer = new MockWebServer();
        Path path = Paths.get("src/test/resources/wpage.html").toAbsolutePath().normalize();
        String content = Files.readString(path);

        mockWebServer.enqueue(new MockResponse().setBody(content));
        mockWebServer.start();

        pageUrl = mockWebServer.url("/wpage.html").toString();
        domainName = "http://localhost:" + mockWebServer.getPort();
    }

    @AfterAll
    public static void afterAll() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void setUp() throws Exception {
        app = App.getApp();
    }

    @Test
    void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Page Analyzer");
        });
    }

    @Test
    void testUrlsPageEmptyList() { // camelCase!
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("The list is empty");
        });
    }

    @Test
    void testCreateUrlSuccess() { // camelCase!
        JavalinTest.test(app, (server, client) -> {
            System.out.println("pageUrl: " + pageUrl);

            var response = client.post("/urls", "url=" + pageUrl);
            assertThat(response.code()).isEqualTo(200);

            var all = UrlRepository.getEntities();
            System.out.println("All URLs in DB:");
            all.forEach(url -> System.out.println("[" + url.getName() + "]"));

            var saved = all.stream().map(Url::getName).toList();
            System.out.println("Saved: " + saved);
            assertTrue(saved.contains(all.get(0).getName()));
        });
    }

    @Test
    void testCreateUrlInvalidUrl() { // camelCase!
        JavalinTest.test(app, (server, client) -> {
            client.post("/urls", "url=ututy");
            assertFalse(UrlRepository.existsByName("ututy"));
        });
    }

    @Test
    void testUrlsPagination() {
        JavalinTest.test(app, (server, client) -> {
            client.post("/urls", "url=" + pageUrl);
            assertThat(client.get("/urls?page=1").code()).isEqualTo(200);
            assertThat(client.get("/urls?page=1").body().string()).contains("/urls?page=1");
            assertThat(client.get("/urls?page=99").body().string()).contains("Page not found");
        });
    }

    @Test
    void testUrlCheckSuccess() { // camelCase!
        JavalinTest.test(app, (server, client) -> {
            client.post("/urls", "url=" + pageUrl);

            var all = UrlRepository.getEntities();
            all.forEach(url -> System.out.println("[" + url.getName() + "]"));
            var urlId = all.get(0).getId();

            client.post("/urls/" + urlId + "/checks");

            var check = UrlCheckRepository.find(urlId).get(0);
            assertThat(check.getStatusCode()).isEqualTo(200);
            assertThat(check.getH1()).isEqualTo("Hello, world!");
            assertThat(check.getTitle()).isEqualTo("Checking connection");
            assertThat(check.getDescription()).isEqualTo("Test page");
            assertThat(check.getCreatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
        });
    }

    @Test
    void testUrlCheckFail() {
        JavalinTest.test(app, (server, client) -> {
            var fakeWebsite = "http://localhost:9999";
            client.post("/urls", "url=" + fakeWebsite);

            var urlId = UrlRepository.findByName(fakeWebsite).get().getId();
            client.post("/urls/" + urlId + "/checks");

            assertThat(UrlCheckRepository.find(urlId)).isEmpty();
        });
    }
}
