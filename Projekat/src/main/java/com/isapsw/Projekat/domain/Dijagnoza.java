package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "dijagnoze_pacijenata",
            joinColumns = @JoinColumn(name = "dijagnoza_id"),
            inverseJoinColumns = @JoinColumn(name = "zdrKarton_id"))
    private List<ZdrKarton> zdrKarton = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date datumKreiranja;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="adminKlinCentra_id", updatable = false, nullable = false)
    @JsonIgnore
    private AdminKlinCentra adminKlinCentra;

    @PrePersist
    protected void onCreate(){
        this.datumKreiranja = new Date();
    }

    public Dijagnoza(){
        this.terapija = new ArrayList<>();
    }

    public Dijagnoza(@NotBlank String naziv, @NotBlank @Size(min = 3) String sifra, List<Lek> terapija, Date datumKreiranja, AdminKlinCentra adminKlinCentra) {
        this.naziv = naziv;
        this.sifra = sifra;
        this.terapija = terapija;
        this.datumKreiranja = datumKreiranja;
        this.adminKlinCentra = adminKlinCentra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public List<Lek> getTerapija() {
        return terapija;
    }

    public void setTerapija(List<Lek> terapija) {
        this.terapija = terapija;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public AdminKlinCentra getAdminKlinCentra() {
        return adminKlinCentra;
    }

    public void setAdminKlinCentra(AdminKlinCentra adminKlinCentra) {
        this.adminKlinCentra = adminKlinCentra;
    }
}
