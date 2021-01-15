package com.example.projectmanagement;


public class Role {
    int IdUloge;
    String NazUloge;

    public Role(String NazUloge){
        this.setNazUloge(NazUloge);

    }

    // NazUloge -> getter and setter
    public String getNazUloge() {
        return this.NazUloge;
    }
    public void setNazUloge(String NazUloge) {
        this.NazUloge = NazUloge;
    }
}
