package org.example.routes;

import org.example.controllers.*;
import io.javalin.Javalin;

public class ApiRouter {
    public static void registerRoutes(Javalin app) {
        app.get("/api/uzytkownicy", UserController::getAll);
        app.post("/api/uzytkownicy", UserController::create);
        app.get("/api/uzytkownicy/{id}", UserController::getById);
        app.put("/api/uzytkownicy/{id}", UserController::update);
        app.delete("/api/uzytkownicy/{id}", UserController::delete);

        app.post("/api/sale", SalaController::create);
        app.get("/api/sale", SalaController::getAll);
        app.delete("/api/sale/{id}", SalaController::delete);
        app.get("/api/sale/{id}", SalaController::getById);
        app.put("/api/sale/{id}", SalaController::update);

        app.get("/api/zajecia", ZajeciaController::getAll);

        app.get("/api/edokumenty", EdokumentController::getAll);
        app.get("/edokumenty", EdokumentController::getAll);
        app.post("/edokumenty", EdokumentController::create);
        app.get("/edokumenty/{id}", EdokumentController::getById);
        app.put("/edokumenty/{id}", EdokumentController::update);
        app.delete("/edokumenty/{id}", EdokumentController::delete);
        app.get("/edokumenty/uzytkownik/{uzytkownik_id}", EdokumentController::getByUserId);
        app.get("/edokumenty/search", EdokumentController::searchByTitle);
        app.get("/edokumenty/recent/{days}", EdokumentController::getRecentDocuments);

        app.get("/api/oceny", OcenaController::getAll);
        app.post("/api/oceny", OcenaController::create);
        app.get("/api/oceny/{id}", OcenaController::getById);
        app.put("/api/oceny/{id}", OcenaController::update);
        app.delete("/api/oceny/{id}", OcenaController::delete);
        app.get("/api/oceny/srednia/{student_zajecia_id}", OcenaController::getAverage);
        app.get("/api/oceny/srednia", OcenaController::getOverallAverage);

        app.post("/api/register", AuthController::register);
        app.post("/api/login", AuthController::login);
        app.get("/auth/verify", AuthController::verifyToken);

    }
}