package com.example.myapplication.ui.wiadomosc;

public class Wiadomosc {
    private String sender;
    private String title;
    private String message;
    private String typ;
    private String data;

    public Wiadomosc(String sender, String title, String message, String typ, String data) {
        this.sender = sender;
        this.title = title;
        this.message = message;
        this.typ = typ;
        this.data = data;
    }

    public String getSender() {

        return sender;
    }

    public String getTitle() {

        return title;
    }

    public String getMessage() {

        return message;
    }

    public String getTyp() {
        return typ;
    }

    public String getData() {
        return data;
    }
}

