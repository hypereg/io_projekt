package com.example.myapplication.ui.ogloszenie;

public class Ogloszenie {
    private String sender;
    private String title;
    private String message;

    public Ogloszenie(String sender, String title, String message) {
        this.sender = sender;
        this.title = title;
        this.message = message;
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
}

