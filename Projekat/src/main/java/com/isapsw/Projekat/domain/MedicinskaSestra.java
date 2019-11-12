package com.isapsw.Projekat.domain;

import com.isapsw.Projekat.domain.MedicinskoOsoblje;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MedicinskaSestra extends MedicinskoOsoblje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "medicinskaSestra")
    private List<Recept> recepti = new ArrayList<>();

    public MedicinskaSestra(){
        super();
        this.recepti = new ArrayList<>();
    }

    public MedicinskaSestra(List<Recept> recepti) {
        super();
        this.recepti = recepti;
    }

    @Override
    public Long getId() { return id; }

    @Override
    public void setId(Long id) { this.id = id; }

    public List<Recept> getRecepti() { return recepti; }

    public void setRecepti(List<Recept> recepti) { this.recepti = recepti; }
}
