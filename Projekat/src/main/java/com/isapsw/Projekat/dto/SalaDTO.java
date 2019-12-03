package com.isapsw.Projekat.dto;

public class SalaDTO {
    private Long id;
    private String salaIdentifier;
    private Long klinikaId;

    public SalaDTO( String salaIdentifier, Long klinikaId) {
        this.salaIdentifier = salaIdentifier;
        this.klinikaId = klinikaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalaIdentifier() {
        return salaIdentifier;
    }

    public void setSalaIdentifier(String salaIdentifier) {
        this.salaIdentifier = salaIdentifier;
    }

    public Long getKlinikaId() {
        return klinikaId;
    }

    public void setKlinikaId(Long klinikaId) {
        this.klinikaId = klinikaId;
    }
}
