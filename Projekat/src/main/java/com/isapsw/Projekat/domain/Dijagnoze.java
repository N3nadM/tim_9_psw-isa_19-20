package com.isapsw.Projekat.domain;

import java.util.HashMap;

public class Dijagnoze {
    private HashMap<Integer, String> dijagnoze = new HashMap<>();

    public HashMap<Integer, String> getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(HashMap<Integer, String> dijagnoze) {
        this.dijagnoze = dijagnoze;
    }
}
