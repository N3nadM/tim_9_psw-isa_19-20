package com.isapsw.Projekat.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotBlank(message = "Neophodno je uneti ime.")
    @Size(min=2)
    protected String ime;

    @NotBlank(message = "Neophodno je uneti prezime.")
    @Size(min=2)
    protected String prezime;

    @NotBlank(message = "Neophodno je uneti korisnicko ime.")
    @Column(unique = true)
    protected String username;

    @NotBlank(message = "Neophodno je uneti password.")
    @Size(min=5)
    protected String password;

    @NotBlank(message = "Neophodno je uneti email.")
    @Column(updatable = false, unique = true)
    protected String email;

    @NotBlank(message = "Neophodno je uneti adresu.")
    private String adresa;

    public Korisnik() {
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
