package org.example;

import io.javalin.Javalin;
import org.example.routes.ApiRouter;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.http.defaultContentType = "application/json";
            config.bundledPlugins.enableCors(cors -> cors.addRule(it -> it.anyHost()));
        }).start(7070);

        ApiRouter.registerRoutes(app);
    }}