package org.example.models;

public class User {
    public int id;
    public String imie;
    public String nazwisko;
    public String email;
    public String haslo;
    public String rola;
    public String numerBetterIndex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public String getNumerBetterIndex() {
        return numerBetterIndex;
    }

    public void setNumerBetterIndex(String numerBetterIndex) {
        this.numerBetterIndex = numerBetterIndex;
    }
}