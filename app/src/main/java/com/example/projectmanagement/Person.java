package com.example.projectmanagement;

public class Person {
    String PrezimeOsobe, ImeOsobe, OIB;

    public Person(String PrezimeOsobe, String ImeOsobe, String OIB)
    {
        this.PrezimeOsobe = PrezimeOsobe;
        this.ImeOsobe = ImeOsobe;
        this.OIB = OIB;
    }

    // PrezimeOsobe -> getter and setter
    public String getPrezimeOsobe() {
        return this.PrezimeOsobe;
    }
    public void setPrezimeOsobe(String PrezimeOsobe) {
        this.PrezimeOsobe = PrezimeOsobe;
    }

    // ImeOsobe -> getter and setter
    public String getImeOsobe() {
        return this.ImeOsobe;
    }
    public void setImeOsobe(String ImeOsobe) {
        this.ImeOsobe = ImeOsobe;
    }

    // OIB -> getter and setter
    public String getOIB() {
        return this.OIB;
    }
    public void setOIB(String OIB) {
        this.OIB = PrezimeOsobe;
    }
}