package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isapsw.Projekat.dto.OdsustvoDTO;
import com.isapsw.Projekat.dto.ZahtevOdsustvoDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class ZahtevOdsustvo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date datum;

    @NotBlank(message = "Neophodno je uneti opis.")
    private String opis;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name="medicinskaSestra_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MedicinskaSestra medicinskaSestra;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name="lekar_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Lekar lekar;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name="klinika_id", updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Klinika klinika;

    private boolean verified = false;

    public ZahtevOdsustvo() {
    }

    public ZahtevOdsustvo(ZahtevOdsustvoDTO zahtevOdsustvoDTO){
        this.datum = zahtevOdsustvoDTO.getDatum();
        this.opis = zahtevOdsustvoDTO.getOpis();
        this.medicinskaSestra = null;
        this.lekar = null;
        this.klinika = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public MedicinskaSestra getMedicinskaSestra() {
        return medicinskaSestra;
    }

    public void setMedicinskaSestra(MedicinskaSestra medicinskaSestra) {
        this.medicinskaSestra = medicinskaSestra;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
