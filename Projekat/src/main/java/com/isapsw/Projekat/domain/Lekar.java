package com.isapsw.Projekat.domain;

import com.isapsw.Projekat.domain.MedicinskoOsoblje;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Lekar extends MedicinskoOsoblje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private List<Pregled_Operacija> pregledi_operacije = new ArrayList<>();

    public Lekar(List<Pregled_Operacija> pregledi_operacije) {
        this.pregledi_operacije = pregledi_operacije;
    }

    public List<Pregled_Operacija> getPregledi_operacije() {
        return pregledi_operacije;
    }

    public void setPregledi_operacije(List<Pregled_Operacija> pregledi_operacije) {
        this.pregledi_operacije = pregledi_operacije;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "lekar")
    private List<Pregled> pregledi = new ArrayList<>();

    @ManyToMany(mappedBy = "lekari")
    private List<Operacija> operacije = new ArrayList<>();
}
