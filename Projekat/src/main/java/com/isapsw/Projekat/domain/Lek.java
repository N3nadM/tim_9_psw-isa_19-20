package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isapsw.Projekat.dto.LekDTO;

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

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "lek_za_dijagnozu",
            joinColumns = @JoinColumn(name = "lek_id"),
            inverseJoinColumns = @JoinColumn(name = "dijagnoza_id"))
    @JsonIgnore
    private List<Dijagnoza> dijagnoze = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "alergije_pacijenata",
            joinColumns = @JoinColumn(name = "lek_id"),
            inverseJoinColumns = @JoinColumn(name = "zdrKarton_id"))
    @JsonIgnore
    private List<ZdrKarton> zdrKarton = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lek")
    @JsonIgnore
    private List<Recept> recept = new ArrayList<>();

    public Lek() {
    }

    public Lek(LekDTO lekDTO){
        this.naziv = lekDTO.getNaziv();
        this.sadrzaj = lekDTO.getSadrzaj();
        this.sifra = lekDTO.getSifra();
        this.dijagnoze = new ArrayList<>();
        this.zdrKarton = new ArrayList<>();
        this.recept = new ArrayList<>();
    }

    public Lek(Long id, @NotBlank(message = "Neophodno je uneti naziv leka.") String naziv, @NotBlank(message = "Neophodno je uneti sadrzaj leka.") String sadrzaj, @NotBlank(message = "Neophodno je uneti sifru leka.") @Size(min = 3) String sifra, List<Dijagnoza> dijagnoze, List<ZdrKarton> zdrKarton, List<Recept> recept) {
        this.id = id;
        this.naziv = naziv;
        this.sadrzaj = sadrzaj;
        this.sifra = sifra;
        this.dijagnoze = dijagnoze;
        this.zdrKarton = zdrKarton;
        this.recept = recept;
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

}
