package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pacijent {
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pacijent")
    private ZdrKarton zdrKarton;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pacijent")
    private List<Pregled> pregledi = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pacijent")
    private List<Operacija> operacije = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="adminKlinCentra_id", updatable = false, nullable = false)
    @JsonIgnore
    private AdminKlinCentra adminKlinCentra;

    public Pacijent() {
    }

    public Pacijent(@NotBlank(message = "Neophodno je uneti ime.") @Size(min = 2) String ime, @NotBlank(message = "Neophodno je uneti prezime.") @Size(min = 2) String prezime, @NotBlank(message = "Neophodno je uneti adresu prebivalista.") String adresa, @NotBlank(message = "Neophodno je uneti grad") String grad, @NotBlank(message = "Neophodno je uneti drzavu.") String drzava, @NotBlank(message = "Neophodno je uneti korisnicko ime.") String username, @NotBlank(message = "Neophodno je uneti email.") String email, @NotBlank(message = "Neophodno je uneti password.") @Size(min = 5) String password, @NotBlank(message = "Neophodno je uneti jedinstveni broj zdravstvenog osiguranika.") String jbzo, Date datum_kreiranja, ZdrKarton zdrKarton, List<Pregled> pregledi, List<Operacija> operacije) {
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.grad = grad;
        this.drzava = drzava;
        this.username = username;
        this.email = email;
        this.password = password;
        this.jbzo = jbzo;
        this.datum_kreiranja = datum_kreiranja;
        this.zdrKarton = zdrKarton;
        this.pregledi = pregledi;
        this.operacije = operacije;
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

    public ZdrKarton getZdrKarton() {
        return zdrKarton;
    }

    public void setZdrKarton(ZdrKarton zdrKarton) {
        this.zdrKarton = zdrKarton;
    }

    public List<Pregled> getPregledi() {
        return pregledi;
    }

    public void setPregledi(List<Pregled> pregledi) {
        this.pregledi = pregledi;
    }

    public List<Operacija> getOperacije() {
        return operacije;
    }

    public void setOperacije(List<Operacija> operacije) {
        this.operacije = operacije;
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
                ", zdrKarton=" + zdrKarton +
                ", pregledi=" + pregledi +
                ", operacije=" + operacije +
                '}';
    }

    @PrePersist
    protected void onCreate(){
        this.datum_kreiranja = new Date();
    }
}
