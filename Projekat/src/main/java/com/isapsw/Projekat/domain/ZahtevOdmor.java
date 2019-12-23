package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isapsw.Projekat.dto.OdmorDTO;
import com.isapsw.Projekat.dto.ZahtevOdmorDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class ZahtevOdmor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date datumOd;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date datumDo;

    @NotBlank(message = "Neophodno je uneti opis.")
    private String opis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="medicinskaSestra_id")
    @JsonIgnore
    private MedicinskaSestra medicinskaSestra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lekar_id")
    @JsonIgnore
    private Lekar lekar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="klinika_id", updatable = false)
    @JsonIgnore
    private Klinika klinika;

    private boolean verified = false;

    public ZahtevOdmor() {
    }

    public ZahtevOdmor(ZahtevOdmorDTO zahtevOdmorDTO) {
        this.datumOd = zahtevOdmorDTO.getDatumOd();
        this.datumDo = zahtevOdmorDTO.getDatumDo();
        this.opis = zahtevOdmorDTO.getOpis();
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

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
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
