package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isapsw.Projekat.dto.OdsustvoDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Odsustvo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date datum;

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

    public Odsustvo() {
    }

    public Odsustvo(OdsustvoDTO odsustvoDTO) {
        this.datum = odsustvoDTO.getDatum();
        this.opis = odsustvoDTO.getOpis();
        this.medicinskaSestra = null;
        this.lekar = null;
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
}
