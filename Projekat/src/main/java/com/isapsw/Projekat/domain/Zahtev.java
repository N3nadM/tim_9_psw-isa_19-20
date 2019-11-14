package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Zahtev{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Neophodno je uneti ime.")
    @Size(min=2)
    private String ime;

    @NotBlank(message = "Neophodno je uneti prezime.")
    @Size(min=2)
    private String prezime;

    @NotBlank(message = "Neophodno je uneti adresu prebivalista.")
    private String adresa;

    @NotBlank(message = "Neophodno je uneti email.")
    @Column(updatable = false, unique = true)
    private String email;

    @NotBlank(message = "Neophodno je uneti password.")
    @Size(min=5)
    private String password;

    @Transient
    private String confirmPassword;

    private boolean verified = false;

    public Zahtev() {
        super();
    }

    public Zahtev(@NotBlank(message = "Neophodno je uneti ime.") @Size(min = 2) String ime, @NotBlank(message = "Neophodno je uneti prezime.") @Size(min = 2) String prezime, @NotBlank(message = "Neophodno je uneti adresu prebivalista.") String adresa,@NotBlank(message = "Neophodno je uneti korisnicko ime.") String username, @NotBlank(message = "Neophodno je uneti email.") String email, @NotBlank(message = "Neophodno je uneti password.") @Size(min = 5) String password, boolean verified) {
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.email = email;
        this.password = password;
        this.verified = verified;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
