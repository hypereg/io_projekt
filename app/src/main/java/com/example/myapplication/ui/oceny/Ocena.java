package com.example.myapplication.ui.oceny;

public class Ocena {
    private String przedmiot;
    private double wartosc;
    private String data;
    private String title;

    public Ocena(String przedmiot, double wartosc, String data, String title) {
        this.przedmiot = przedmiot;
        this.wartosc = wartosc;
        this.data = data;
        this.title = title;
    }

    public String getPrzedmiot() {
        return przedmiot;
    }

    public double getWartosc() {
        return wartosc;
    }

    public String getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }
}

