package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@MappedSuperclass
public class Pacijent_Zahtev {
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

    @NotBlank(message = "Neophodno je uneti grad")
    private String grad;

    @NotBlank(message = "Neophodno je uneti drzavu.")
    private String drzava;

    @NotBlank(message = "Neophodno je uneti korisnicko ime.")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Neophodno je uneti email.")
    @Column(updatable = false, unique = true)
    private String email;

    @NotBlank(message = "Neophodno je uneti password.")
    @Size(min=5)
    private String password;

    @NotBlank(message = "Neophodno je uneti jedinstveni broj zdravstvenog osiguranika.")
    @Column(updatable = false, unique = true)
    private String jbzo;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date datum_kreiranja;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getJbzo() {
        return jbzo;
    }

    public void setJbzo(String jbzo) {
        this.jbzo = jbzo;
    }

    public Date getDatum_kreiranja() {
        return datum_kreiranja;
    }

    public void setDatum_kreiranja(Date datum_kreiranja) {
        this.datum_kreiranja = datum_kreiranja;
    }


    @Override
    public String toString() {
        return "Pacijent{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", adresa='" + adresa + '\'' +
                ", grad='" + grad + '\'' +
                ", drzava='" + drzava + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", jbzo='" + jbzo + '\'' +
                ", datum_kreiranja=" + datum_kreiranja +
                '}';
    }

    @PrePersist
    protected void onCreate(){
        this.datum_kreiranja = new Date();
    }
}
