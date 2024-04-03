package com.scaffolding.test.models;

import java.sql.Timestamp;
import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
public class Utilisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int idUtilisation;

    String composant;
    Timestamp dateFinUtilisation;
    Timestamp dateUtilisation;
    String etat;
    String nomProprietaire;
    String nomUtilisateur;
    double prixUnitaire;
    double taux;
    String utilisation;

    @ManyToOne
    @JoinColumn(name = "id_immobilier")
    Immobilier immobilier;

    public void setIdUtilisation(int idUtilisation) {
        this.idUtilisation = idUtilisation;
    }
    public int getIdUtilisation() {
        return this.idUtilisation;
    }

    public void setComposant(String composant) {
        this.composant = composant;
    }
    public String getComposant() {
        return this.composant;
    }
    public void setDateFinUtilisation(Timestamp dateFinUtilisation) {
        this.dateFinUtilisation = dateFinUtilisation;
    }
    public Timestamp getDateFinUtilisation() {
        return this.dateFinUtilisation;
    }
    public void setDateUtilisation(Timestamp dateUtilisation) {
        this.dateUtilisation = dateUtilisation;
    }
    public Timestamp getDateUtilisation() {
        return this.dateUtilisation;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
    public String getEtat() {
        return this.etat;
    }
    public void setNomProprietaire(String nomProprietaire) {
        this.nomProprietaire = nomProprietaire;
    }
    public String getNomProprietaire() {
        return this.nomProprietaire;
    }
    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }
    public String getNomUtilisateur() {
        return this.nomUtilisateur;
    }
    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    public double getPrixUnitaire() {
        return this.prixUnitaire;
    }
    public void setTaux(double taux) {
        this.taux = taux;
    }
    public double getTaux() {
        return this.taux;
    }
    public void setUtilisation(String utilisation) {
        this.utilisation = utilisation;
    }
    public String getUtilisation() {
        return this.utilisation;
    }

    public void setImmobilier(Immobilier immobilier) {
        this.immobilier = immobilier;
    }
    public Immobilier getImmobilier() {
        return this.immobilier;
    }

}
