package com.isapsw.Projekat.domain;

import com.isapsw.Projekat.domain.MedicinskoOsoblje;

import java.util.ArrayList;
import java.util.List;

public class Lekar extends MedicinskoOsoblje {

    private List<Pregled_Operacija> pregledi_operacije = new ArrayList<>();

    public Lekar(List<Pregled_Operacija> pregledi_operacije) {
        this.pregledi_operacije = pregledi_operacije;
    }

    public List<Pregled_Operacija> getPregledi_operacije() {
        return pregledi_operacije;
    }

    public void setPregledi_operacije(List<Pregled_Operacija> pregledi_operacije) {
        this.pregledi_operacije = pregledi_operacije;
    }
}
