package com.scaffolding.test.models;

import jakarta.persistence.*;

@Entity
public class Fournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int idFournisseur;

    String nomFournisseur;

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }
    public int getIdFournisseur() {
        return this.idFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }
    public String getNomFournisseur() {
        return this.nomFournisseur;
    }

}
