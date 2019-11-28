package com.isapsw.Projekat.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isapsw.Projekat.dto.KorisnikDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @NotBlank(message = "Neophodno je uneti grad.")
    private String grad;

    @NotBlank(message = "Neophodno je uneti drzavu.")
    private String drzava;

    @NotBlank(message = "Neophodno je uneti telefon.")
    private String telefon;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<Authority> authorities = new ArrayList<>();

    public Korisnik() {

    }

    public Korisnik(KorisnikDTO korisnikDTO){
        this.ime = korisnikDTO.getIme();
        this.prezime = korisnikDTO.getPrezime();
        this.password = korisnikDTO.getPassword();
        this.email = korisnikDTO.getEmail();
        this.adresa = korisnikDTO.getAdresa();
        this.grad = korisnikDTO.getGrad();
        this.drzava = korisnikDTO.getDrzava();
        this.telefon = korisnikDTO.getTelefon();
    }

    public Korisnik(Zahtev zahtev) {
        this.ime = zahtev.getIme();
        this.prezime = zahtev.getPrezime();
        this.password = zahtev.getPassword();
        this.email = zahtev.getEmail();
        this.adresa = zahtev.getAdresa();
        this.grad = zahtev.getGrad();
        this.drzava = zahtev.getDrzava();
        this.telefon = zahtev.getTelefon();
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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

    public List<Authority> getAuthorityList() {
        return this.authorities;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
