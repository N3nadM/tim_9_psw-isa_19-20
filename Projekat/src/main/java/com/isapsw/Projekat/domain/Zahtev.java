package com.isapsw.Projekat.domain;

import javax.persistence.Entity;

@Entity
public class Zahtev extends Pacijent_Zahtev{
    private boolean verified = false;

    public Zahtev() {
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
