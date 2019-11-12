package com.isapsw.Projekat.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Lekar extends MedicinskoOsoblje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "lekar")
    private List<Pregled> pregledi = new ArrayList<>();

    @ManyToMany(mappedBy = "lekari")
    private List<Operacija> operacije = new ArrayList<>();

    public Lekar(){
        this.pregledi = new ArrayList<>();
        this.operacije = new ArrayList<>();
    }

    public Lekar(List<Pregled> pregledi, List<Operacija> operacije) {
        this.pregledi = pregledi;
        this.operacije = operacije;
    }

    public List<Pregled> getPregledi() {
        return pregledi;
    }

    public void setPregledi(List<Pregled> pregledi) {
        this.pregledi = pregledi;
    }

    public List<Operacija> getOperacije() {
        return operacije;
    }

    public void setOperacije(List<Operacija> operacije) {
        this.operacije = operacije;
    }
}
