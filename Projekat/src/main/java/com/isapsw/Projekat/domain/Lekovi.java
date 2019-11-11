package com.isapsw.Projekat.domain;

import java.util.HashMap;

public class Lekovi {

    private HashMap<Integer, String> lekovi = new HashMap<>();

    public Lekovi(HashMap<Integer, String> lekovi) {
        this.lekovi = lekovi;
    }

    public HashMap<Integer, String> getLekovi() {
        return lekovi;
    }

    public void setLekovi(HashMap<Integer, String> lekovi) {
        this.lekovi = lekovi;
    }
}
