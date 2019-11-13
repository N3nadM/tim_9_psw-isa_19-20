package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Lek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Neophodno je uneti naziv leka.")
    private String naziv;

    @NotBlank(message = "Neophodno je uneti sadrzaj leka.")
    private String sadrzaj;

    @NotBlank(message = "Neophodno je uneti sifru leka.")
    @Size(min=3)
    private String sifra;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "lek_za_dijagnozu",
            joinColumns = @JoinColumn(name = "lek_id"),
            inverseJoinColumns = @JoinColumn(name = "dijagnoza_id"))
    private List<Dijagnoza> dijagnoze = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="recept_id",nullable = false)
    @JsonIgnore
    private Recept recept;

    private AdminKlinCentra adminKlinCentra;

    public Lek() {
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

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public List<Dijagnoza> getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(List<Dijagnoza> dijagnoze) {
        this.dijagnoze = dijagnoze;
    }

    public Recept getRecept() {
        return recept;
    }

    public void setRecept(Recept recept) {
        this.recept = recept;
    }

    public AdminKlinCentra getAdminKlinCentra() {
        return adminKlinCentra;
    }

    public void setAdminKlinCentra(AdminKlinCentra adminKlinCentra) {
        this.adminKlinCentra = adminKlinCentra;
    }
}
