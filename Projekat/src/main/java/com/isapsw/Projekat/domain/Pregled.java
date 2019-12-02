package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Pregled extends Pregled_Operacija{

    private Integer popust;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lekar_id", updatable = false, nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Lekar lekar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pacijent_id", updatable = false, nullable = false)
    @JsonIgnore
    private Pacijent pacijent;

    public Pregled() {
        super();
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

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }
}
