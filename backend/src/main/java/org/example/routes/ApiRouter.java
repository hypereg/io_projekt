package org.example.routes;

import org.example.controllers.*;
import io.javalin.Javalin;

public class ApiRouter {
    public static void registerRoutes(Javalin app) {
        app.get("/api/uzytkownicy", UserController::getAll);
        app.post("/api/uzytkownicy", UserController::create);
        app.get("/api/uzytkownicy/:id", UserController::getById);
        app.put("/api/uzytkownicy/:id", UserController::update);
        app.delete("/api/uzytkownicy/:id", UserController::delete);

        app.get("/api/sale", SalaController::getAll);
        app.get("/api/zajecia", ZajeciaController::getAll);
        app.get("/api/dokumenty", EdokumentController::getAll);
        app.get("/api/oceny", OcenaController::getAll);

        app.post("/api/login", AuthController::login);
    }
}