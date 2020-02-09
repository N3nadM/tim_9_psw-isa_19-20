package com.isapsw.Projekat.dto;

public class PregledDTO {
    private Long id;
    private String popust;
    private String lekarId;
    private String salaId;
    private String tipPregledaId;
    private String medSestraId;
    private String datum;

    public PregledDTO(){

    }

    public PregledDTO(Long id, String popust, String lekarId, String salaId, String tipPregledaId, String medSestraId, String datum) {
        this.id = id;
        this.popust = popust;
        this.lekarId = lekarId;
        this.salaId = salaId;
        this.tipPregledaId = tipPregledaId;
        this.medSestraId = medSestraId;
        this.datum = datum;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPopust() {
        return popust;
    }

    public void setPopust(String popust) {
        this.popust = popust;
    }

    public String getLekarId() {
        return lekarId;
    }

    public void setLekarId(String lekarId) {
        this.lekarId = lekarId;
    }

    public String getSalaId() {
        return salaId;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }

    public String getTipPregledaId() {
        return tipPregledaId;
    }

    public void setTipPregledaId(String tipPregledaId) {
        this.tipPregledaId = tipPregledaId;
    }

    public String getMedSestraId() {
        return medSestraId;
    }

    public void setMedSestraId(String medSestraId) {
        this.medSestraId = medSestraId;
    }
}
