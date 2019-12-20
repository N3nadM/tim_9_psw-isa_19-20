package com.isapsw.Projekat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OdsustvoDTO {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date datum;
    private String opis;
    private String korisnikId;

    public OdsustvoDTO() {
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

    public String getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(String korisnikId) {
        this.korisnikId = korisnikId;
    }
}
