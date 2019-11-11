package com.isapsw.Projekat.domain;

public class Klinika {
    private String naziv;
    private String adresa;
    private String opis;
    //dodati listu slobodnih termina
    //dodati listu lekara
    //dodati listu sala
    //dodati cenovnik


    public String getNaziv() {
        return naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
