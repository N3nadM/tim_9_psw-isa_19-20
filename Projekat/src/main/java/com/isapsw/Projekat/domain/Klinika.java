package com.isapsw.Projekat.domain;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Klinika {
    private String naziv;
    private String adresa;
    private String opis;
    //dodati listu slobodnih termina
    //dodati listu lekara
    //dodati listu sala
    //dodati cenovnik
    private List<AdminKlinike> adminiKlinike;


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

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "klinika", orphanRemoval = true)
    private List<Sala> sale = new ArrayList<>();
}
