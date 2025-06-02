package com.example.myapplication.ui.oceny;

import java.util.List;

public class OcenyPrzedmiot {
    private String nazwaPrzedmiotu;
    private double srednia;
    private List<Ocena> oceny;

    public OcenyPrzedmiot(String nazwaPrzedmiotu, List<Ocena> oceny) {
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
        this.oceny = oceny;
        this.srednia = liczSrednia();
    }

    public String getNazwaPrzedmiotu() {
        return nazwaPrzedmiotu;
    }

    public double getSrednia() {
        return srednia;
    }

    public List<Ocena> getOceny() {
        return oceny;
    }

    private double liczSrednia() {
        if (oceny == null || oceny.isEmpty()) {
            return 0.0;
        }
        double suma = 0.0;
        for (Ocena ocena : oceny) {
            suma += ocena.getWartosc();
        }
        return suma / oceny.size();
    }
}
