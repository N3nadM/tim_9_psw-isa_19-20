package com.isapsw.Projekat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OdmorDTO {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date datumOd;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date datumDo;
    private String opis;
    private String korisnikId;

    public OdmorDTO() {
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

    public String getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(String korisnikId) {
        this.korisnikId = korisnikId;
    }
}
