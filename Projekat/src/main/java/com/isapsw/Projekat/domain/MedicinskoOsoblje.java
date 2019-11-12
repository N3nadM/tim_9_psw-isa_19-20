package com.isapsw.Projekat.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
public class MedicinskoOsoblje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Neophodno je uneti ime.")
    @Size(min=2)
    private String ime;

    @NotBlank(message = "Neophodno je uneti prezime.")
    @Size(min=2)
    private String prezime;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "medicinskiRadnik")
    private RadniKalendar radniKalendar;


    public RadniKalendar getRadniKalendar() {
        return radniKalendar;
    }

    public void setRadniKalendar(RadniKalendar radniKalendar) {
        this.radniKalendar = radniKalendar;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MedicinskoOsoblje{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", radniKalendar=" + radniKalendar +
                '}';
    }
}
