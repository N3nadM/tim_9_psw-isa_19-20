package com.isapsw.Projekat.domain;

public class ZdrKarton {

    private Pacijent pacijent;

    public ZdrKarton(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }
}
