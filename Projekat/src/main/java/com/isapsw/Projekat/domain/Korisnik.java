package com.isapsw.Projekat.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Korisnik implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotBlank(message = "Neophodno je uneti ime.")
    @Size(min=2)
    protected String ime;

    @NotBlank(message = "Neophodno je uneti prezime.")
    @Size(min=2)
    protected String prezime;

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

    public Korisnik(Zahtev zahtev) {
        this.ime = zahtev.getIme();
        this.prezime = zahtev.getPrezime();
        this.password = zahtev.getPassword();
        this.email = zahtev.getEmail();
        this.adresa = zahtev.getAdresa();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
