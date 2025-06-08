package com.example.myapplication.ui.zadania;

import java.util.List;

public class Zadanie {
    private String przedmiot;
    private String dataDodania;
    private String dataWykonania;
    private Status status;
    private String temat;
    private String opis;
    private List<String> zalaczniki;
    private String id;

    public enum Status {
        PRZYSZLE,
        ZALEGLE,
        UKONCZONE
    }

    public Zadanie(String id, String przedmiot, String dataDodania, String dataWykonania,
                   Status status, String temat, String opis, List<String> zalaczniki) {
        this.id = id;
        this.przedmiot = przedmiot;
        this.dataDodania = dataDodania;
        this.dataWykonania = dataWykonania;
        this.status = status;
        this.temat = temat;
        this.opis = opis;
        this.zalaczniki = zalaczniki;
    }

    public String getId() {
        return id;
    }

    public String getPrzedmiot() {
        return przedmiot;
    }

    public String getDataDodania() {
        return dataDodania;
    }

    public String getDataWykonania() {
        return dataWykonania;
    }

    public Status getStatus() {
        return status;
    }

    public String getTemat() {
        return temat;
    }

    public String getOpis() {
        return opis;
    }

    public List<String> getZalaczniki() {
        return zalaczniki;
    }
}