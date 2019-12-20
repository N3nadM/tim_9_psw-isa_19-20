package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isapsw.Projekat.dto.OdmorDTO;
import com.isapsw.Projekat.dto.OdsustvoDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Odmor {

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

    public Odmor() {
    }

    public Odmor(OdmorDTO odmorDTO) {
        this.datumOd = odmorDTO.getDatumOd();
        this.datumDo = odmorDTO.getDatumDo();
        this.opis = odmorDTO.getOpis();
        this.medicinskaSestra = null;
        this.lekar = null;
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
}
