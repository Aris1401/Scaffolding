package com.scaffolding.test.models;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int idCommande;

    Timestamp dateCommande;
    double quantite;

    @ManyToOne
    @JoinColumn(name = "id_fournisseur")
    Fournisseur fournisseur;
    @ManyToOne
    @JoinColumn(name = "id_immobilier")
    Immobilier immobilier;

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }
    public int getIdCommande() {
        return this.idCommande;
    }

    public void setDateCommande(Timestamp dateCommande) {
        this.dateCommande = dateCommande;
    }
    public Timestamp getDateCommande() {
        return this.dateCommande;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    public double getQuantite() {
        return this.quantite;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
    public Fournisseur getFournisseur() {
        return this.fournisseur;
    }
    public void setImmobilier(Immobilier immobilier) {
        this.immobilier = immobilier;
    }
    public Immobilier getImmobilier() {
        return this.immobilier;
    }

}
