package com.example.projectmanagement;

import java.util.Date;

public class Project {
    int SifProjekta;
    String NazProjekta, OpisProjekta;
    Date DatPocetka, DatZavrsetka;

    public Project(String NazProjekta, String OpisProjekta,
                   Date DatPocetka, Date DatZavrsetka){
        this.setSifProjekta(SifProjekta);
        this.setNazProjekta(NazProjekta);
        this.setOpisProjekta(OpisProjekta);
        this.setDatPocetka(DatPocetka);
        this.setDatZavrsetka(DatZavrsetka);
    }

    // SifProjekta -> getter and setter
    public int getSifProjekta() {
        return this.SifProjekta;
    }
    public void setSifProjekta(int SifProjekta) {
        this.SifProjekta = SifProjekta;
    }

    // NazProjekta -> getter and setter
    public String getNazProjekta() {
        return this.NazProjekta;
    }
    public void setNazProjekta(String NazProjekta) {
        this.NazProjekta = NazProjekta;
    }

    // OpisProjekta -> getter and setter
    public String getOpisProjekta() {
        return this.OpisProjekta;
    }
    public void setOpisProjekta(String OpisProjekta) {
        this.OpisProjekta = OpisProjekta;
    }

    // DatPocetka -> getter and setter
    public Date getDatPocetka() { return this.DatPocetka; }
    public void setDatPocetka(Date DatPocetka) {
        this.DatPocetka = DatPocetka;
    }

    // DatZavrsetka -> getter and setter
    public Date getDatZavrsetka() { return this.DatZavrsetka; }
    public void setDatZavrsetka(Date DatZavrsetka) {
        this.DatZavrsetka = DatZavrsetka;
    }
}
