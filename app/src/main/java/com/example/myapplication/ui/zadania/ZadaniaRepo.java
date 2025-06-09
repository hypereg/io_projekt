package com.example.myapplication.ui.zadania;

import java.util.ArrayList;
import java.util.List;

public class ZadaniaRepo {
    private static ZadaniaRepo instance;
    private final List<Zadanie> zadania = new ArrayList<>();

    private ZadaniaRepo() {}

    public static ZadaniaRepo getInstance() {
        if (instance == null) instance = new ZadaniaRepo();
        return instance;
    }

    public void setZadania(List<Zadanie> lista) {
        zadania.clear();
        zadania.addAll(lista);
    }

    public Zadanie getZadanieById(String id) {
        for (Zadanie z : zadania) {
            if (z.getId().equals(id)) return z;
        }
        return null;
    }

    public List<Zadanie> getAll() {
        return new ArrayList<>(zadania);
    }
}

