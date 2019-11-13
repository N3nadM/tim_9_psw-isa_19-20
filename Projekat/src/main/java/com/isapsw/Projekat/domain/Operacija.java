package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Operacija extends Pregled_Operacija {
    @NotBlank
    private String tipOperacije;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "prisutni_lekari",
            joinColumns = @JoinColumn(name = "operacija_id"),
            inverseJoinColumns = @JoinColumn(name = "lekar_id"))
    List<Lekar> lekari = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sala_id", updatable = false, nullable = false)
    @JsonIgnore
    private Sala sala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pacijent_id", updatable = false, nullable = false)
    @JsonIgnore
    private Pacijent pacijent;

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

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    @Override
    public String toString() {
        return "Operacija{" +
                "tipOperacije='" + tipOperacije + '\'' +
                ", lekari=" + lekari +
                '}';
    }
}
