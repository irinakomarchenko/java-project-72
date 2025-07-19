package hexlet.code;

import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {public static Javalin getApp() {
    var app = Javalin.create(config -> {
        config.bundledPlugins.enableDevLogging();
    });

    app.get("/", ctx -> {
        log.info("Processing request for /");
        ctx.result("Hello, World!");
    });

       return app;
}

   public static void main(String[] args) {
        log.info("Launching the application Hexlet!");
       int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "7070"));
       getApp().start(port);
    }
}
