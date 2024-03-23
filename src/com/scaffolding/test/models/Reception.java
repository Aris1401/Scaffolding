package com.scaffolding.test.models;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
public class Reception {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int idReception;

    int anneeutilisation;
    Timestamp dateReception;
    double prix;
    double taux;

    @ManyToOne
    @JoinColumn(name = "id_commande")
    Commande commande;

    public void setIdReception(int idReception) {
        this.idReception = idReception;
    }
    public int getIdReception() {
        return this.idReception;
    }

    public void setAnneeutilisation(int anneeutilisation) {
        this.anneeutilisation = anneeutilisation;
    }
    public int getAnneeutilisation() {
        return this.anneeutilisation;
    }
    public void setDateReception(Timestamp dateReception) {
        this.dateReception = dateReception;
    }
    public Timestamp getDateReception() {
        return this.dateReception;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public double getPrix() {
        return this.prix;
    }
    public void setTaux(double taux) {
        this.taux = taux;
    }
    public double getTaux() {
        return this.taux;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    public Commande getCommande() {
        return this.commande;
    }

}
