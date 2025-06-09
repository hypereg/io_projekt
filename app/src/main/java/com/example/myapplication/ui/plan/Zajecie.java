package com.example.myapplication.ui.plan;

public class Zajecie {
    private final String nazwa;
    private final String typ;
    private final String prowadzacy;
    private final String poczatek;
    private final String koniec;
    private final String sala;
    private final String data;

    public Zajecie(String nazwa, String typ, String prowadzacy, String poczatek, String koniec, String sala, String data) {
        this.nazwa = nazwa;
        this.typ = typ;
        this.prowadzacy = prowadzacy;
        this.poczatek = poczatek;
        this.koniec = koniec;
        this.sala = sala;
        this.data = data;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getTyp() {
        return typ;
    }

    public String getProwadzacy() {
        return prowadzacy;
    }

    public String getPoczatek() {
        return poczatek;
    }

    public String getKoniec() {
        return koniec;
    }

    public String getSala() {
        return sala;
    }

    public String getData() {
        return data;
    }
}
