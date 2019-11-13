package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ZdrKarton {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pacijent_id",nullable = false)
    @JsonIgnore
    private Pacijent pacijent;

    private Float dioptrija;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "zdrKarton")
    private List<Lek> alergijaNaLek = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "zdrKarton")
    private List<Dijagnoza> istorijaBolesti = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "zdrKarton")
    private List<Recept> izdatiRecepti = new ArrayList<>();

    private Integer visina;

    private Integer tezina;

    private String krvnaGrupa;



}
