package com.example.myapplication.ui.oceny;

public class Ocena {
    private String przedmiot;
    private String wartosc;
    private String data;

    public Ocena(String przedmiot, String wartosc, String data) {
        this.przedmiot = przedmiot;
        this.wartosc = wartosc;
        this.data = data;
    }

    public String getPrzedmiot() {
        return przedmiot;
    }

    public String getWartosc() {
        return wartosc;
    }

    public String getData() {
        return data;
    }
}

