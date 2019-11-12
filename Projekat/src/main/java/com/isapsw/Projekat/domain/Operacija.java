package com.isapsw.Projekat.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Operacija extends Pregled_Operacija {
    @NotBlank
    private String tipOperacije;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "prisutni_lekari",
            joinColumns = @JoinColumn(name = "operacija_id"),
            inverseJoinColumns = @JoinColumn(name = "lekar_id"))
    List<Lekar> lekari = new ArrayList<>();

    public Operacija() {
    }

    public String getTipOperacije() {
        return tipOperacije;
    }

    public void setTipOperacije(String tipOperacije) {
        this.tipOperacije = tipOperacije;
    }

    public List<Lekar> getLekari() {
        return lekari;
    }

    public void setLekari(List<Lekar> lekari) {
        this.lekari = lekari;
    }

    @Override
    public String toString() {
        return "Operacija{" +
                "tipOperacije='" + tipOperacije + '\'' +
                ", lekari=" + lekari +
                '}';
    }
}
