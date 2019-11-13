package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Recept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Value("${some.key:false}")
    private boolean overen;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date datumIzdavanja;

    @NotBlank
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date datumIsticanja;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "recepti_pacijenta",
            joinColumns = @JoinColumn(name = "recept_id"),
            inverseJoinColumns = @JoinColumn(name = "zdrKarton_id"))
    private List<ZdrKarton> zdrKarton = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="medicinskaSestra_id", updatable = false, nullable = false)
    @JsonIgnore
    private MedicinskaSestra medicinskaSestra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="lek_id", updatable = false, nullable = false)
    @JsonIgnore
    private Lek lek;

    @PrePersist
    protected void onCreate(){
        this.datumIzdavanja = new Date();
    }

    public Recept() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lek getLek() {
        return lek;
    }

    public void setLek(Lek lek) {
        this.lek = lek;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Date getDatumIsticanja() {
        return datumIsticanja;
    }

    public void setDatumIsticanja(Date datumIsticanja) {
        this.datumIsticanja = datumIsticanja;
    }

    public List<ZdrKarton> getZdrKarton() {
        return zdrKarton;
    }

    public void setZdrKarton(List<ZdrKarton> zdrKarton) {
        this.zdrKarton = zdrKarton;
    }

    public boolean isOveren() {
        return overen;
    }

    public void setOveren(boolean overen) {
        this.overen = overen;
    }

    public MedicinskaSestra getMedicinskaSestra() {
        return medicinskaSestra;
    }

    public void setMedicinskaSestra(MedicinskaSestra medicinskaSestra) {
        this.medicinskaSestra = medicinskaSestra;
    }
}
