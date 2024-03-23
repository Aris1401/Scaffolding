package com.scaffolding.test.models;

import jakarta.persistence.*;

@Entity
public class Immobilier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int idImmobilier;

    String nomImmobilier;

    public void setIdImmobilier(int idImmobilier) {
        this.idImmobilier = idImmobilier;
    }
    public int getIdImmobilier() {
        return this.idImmobilier;
    }

    public void setNomImmobilier(String nomImmobilier) {
        this.nomImmobilier = nomImmobilier;
    }
    public String getNomImmobilier() {
        return this.nomImmobilier;
    }

}
