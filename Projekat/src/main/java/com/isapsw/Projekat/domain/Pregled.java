package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Pregled extends Pregled_Operacija{
    @NotBlank
    private String tipPregleda;

    private Integer popust;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pacijent_id", updatable = false, nullable = false)
    @JsonIgnore
    private Pacijent pacijent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lekar_id", updatable = false, nullable = false)
    @JsonIgnore
    private Lekar lekar;

    public Pregled() {
        super();
    }

    public String getTipPregleda() {
        return tipPregleda;
    }

    public void setTipPregleda(String tipPregleda) {
        this.tipPregleda = tipPregleda;
    }

    public Integer getPopust() {
        return popust;
    }

    public void setPopust(Integer popust) {
        this.popust = popust;
    }
    
    public Pacijent getPacijent() { return pacijent; }

    public void setPacijent(Pacijent pacijent) { this.pacijent = pacijent; }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }
}