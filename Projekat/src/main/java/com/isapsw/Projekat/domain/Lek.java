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

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "alergije_pacijenata",
            joinColumns = @JoinColumn(name = "lek_id"),
            inverseJoinColumns = @JoinColumn(name = "zdrKarton_id"))
    private List<ZdrKarton> zdrKarton = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "lek")
    private List<Recept> recept = new ArrayList<>();

    private AdminKlinCentra adminKlinCentra;

    public Lek() {
    }

    public List<ZdrKarton> getZdrKarton() {
        return zdrKarton;
    }

    public void setZdrKarton(List<ZdrKarton> zdrKarton) {
        this.zdrKarton = zdrKarton;
    }

    public List<Recept> getRecept() {
        return recept;
    }

    public void setRecept(List<Recept> recept) {
        this.recept = recept;
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



    public AdminKlinCentra getAdminKlinCentra() {
        return adminKlinCentra;
    }

    public void setAdminKlinCentra(AdminKlinCentra adminKlinCentra) {
        this.adminKlinCentra = adminKlinCentra;
    }
}
