package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Pregled extends Pregled_Operacija{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tip_pregleda_id", updatable = false, nullable = false)
    @JsonIgnore
    private TipPregleda tipPregleda;

    private Integer popust;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="lekar_id", updatable = false, nullable = false)
    private Lekar lekar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sala_id", updatable = false, nullable = false)
    @JsonIgnore
    private Sala sala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pacijent_id", updatable = false, nullable = false)
    @JsonIgnore
    private Pacijent pacijent;

    public Pregled() {
        super();
    }

    public TipPregleda getTipPregleda() {
        return tipPregleda;
    }

    public void setTipPregleda(TipPregleda tipPregleda) {
        this.tipPregleda = tipPregleda;
    }

    public Integer getPopust() {
        return popust;
    }

    public void setPopust(Integer popust) {
        this.popust = popust;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }
}
