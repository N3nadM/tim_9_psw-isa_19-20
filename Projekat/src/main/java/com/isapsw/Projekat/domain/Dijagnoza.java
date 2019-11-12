package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Dijagnoza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String naziv;

    @NotBlank
    @Size(min=3)
    private String sifra;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dijagnoze")
    private List<Lek> terapija = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date datumKreiranja;

    @PrePersist
    protected void onCreate(){
        this.datumKreiranja = new Date();
    }
}
