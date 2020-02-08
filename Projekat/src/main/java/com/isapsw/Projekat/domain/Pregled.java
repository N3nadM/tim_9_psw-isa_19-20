package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Pregled extends Pregled_Operacija{

    private Integer popust;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lekar_id", updatable = true, nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Lekar lekar;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name="pacijent_id", updatable = true, nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Pacijent pacijent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="medicinskaSestra_id", updatable = true, nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MedicinskaSestra medicinskaSestra;

    public Pregled() {
        super();
        setStanje(0);
        setVrsta(0);
    }

    @Version
    private Long version;

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

    public MedicinskaSestra getMedicinskaSestra() {
        return medicinskaSestra;
    }

    public void setMedicinskaSestra(MedicinskaSestra medicinskaSestra) {
        this.medicinskaSestra = medicinskaSestra;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
