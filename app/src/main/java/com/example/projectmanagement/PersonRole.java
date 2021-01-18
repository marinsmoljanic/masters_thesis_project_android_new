package com.example.projectmanagement;

public class PersonRole {
    int SifProjekta, IdOsobe, IdUloge;
    String DatDodjele;
    public PersonRole(int SifProjekta, int IdOsobe, int IdUloge, String DatDodjele){
        this.setSifProjekta(SifProjekta);
        this.setIdOsobe(IdOsobe);
        this.setIdUloge(IdUloge);
        this.setDatDodjele(DatDodjele);
    }

    // SifProjekta -> getter and setter
    public int getSifProjekta() {
        return this.SifProjekta;
    }
    public void setSifProjekta(int SifProjekta) {
        this.SifProjekta = SifProjekta;
    }

    // IdOsobe -> getter and setter
    public int getIdOsobe() {
        return this.IdOsobe;
    }
    public void setIdOsobe(int IdOsobe) {
        this.IdOsobe = IdOsobe;
    }

    // IdUloge -> getter and setter
    public int getIdUloge() {
        return this.IdUloge;
    }
    public void setIdUloge(int IdUloge) {
        this.IdUloge = IdUloge;
    }

    // DatDodjele -> getter and setter
    public String getDatDodjele() {
        return this.DatDodjele;
    }
    public void setDatDodjele(String DatDodjele) {
        this.DatDodjele = DatDodjele;
    }


}
