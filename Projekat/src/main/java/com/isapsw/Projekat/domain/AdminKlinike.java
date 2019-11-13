package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class AdminKlinike {

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

    @NotBlank(message = "Neophodno je uneti password.")
    @Size(min=5)
    private String password;

    @NotBlank(message = "Neophodno je uneti email.")
    @Column(updatable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="klinika_id", updatable = false, nullable = false)
    @JsonIgnore
    private Klinika klinika;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="adminKlinCentra_id", updatable = false, nullable = false)
    @JsonIgnore
    private AdminKlinCentra adminKlinCentra;


    public AdminKlinike(@NotBlank(message = "Neophodno je uneti ime.") @Size(min = 2) String ime, @NotBlank(message = "Neophodno je uneti prezime.") @Size(min = 2) String prezime, @NotBlank(message = "Neophodno je uneti korisnicko ime.") String username, @NotBlank(message = "Neophodno je uneti password.") @Size(min = 5) String password, @NotBlank(message = "Neophodno je uneti email.") String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
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

    public String getPrezime() {
        return prezime;
    }

    public void setIme(String ime) {
        this.ime = ime;
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

    @Override
    public String toString() {
        return "AdminKlinike{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", klinika=" + klinika +
                '}';
    }
}
