package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @NotBlank(message = "Neophodno je uneti korisnicko ime.")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Neophodno je uneti email.")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Neophodno je uneti password.")
    private String password;

    public MedicinskoOsoblje() {
    }

    public MedicinskoOsoblje(@NotBlank(message = "Neophodno je uneti ime.") @Size(min = 2) String ime, @NotBlank(message = "Neophodno je uneti prezime.") @Size(min = 2) String prezime, @NotBlank(message = "Neophodno je uneti korisnicko ime.") String username, @NotBlank(message = "Neophodno je uneti email.") String email, @NotBlank(message = "Neophodno je uneti password.") String password, Klinika klinika) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.email = email;
        this.password = password;
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
                '}';
    }
}
