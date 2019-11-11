package com.isapsw.Projekat.domain;

public class MedicinskoOsoblje {
    private String ime;
    private String prezime;
    private RadniKalendar radniKalendar;

    public RadniKalendar getRadniKalendar() {
        return radniKalendar;
    }

    public void setRadniKalendar(RadniKalendar radniKalendar) {
        this.radniKalendar = radniKalendar;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
