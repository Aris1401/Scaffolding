package models;

import java.util.Date;

public class Travailleur {

    int idtravailleur;
    character varying nom;
    character varying prenom;
    Date datenaissance;
    numeric salaire;
    timestamp without time zone dateinitiation;
    numeric tauxhoraire;

    public void setIdtravailleur(int idtravailleur) {
        this.idtravailleur = idtravailleur;
    }
    public int getIdtravailleur() {
        return this.idtravailleur;
    }
    public void setNom(character varying nom) {
        this.nom = nom;
    }
    public character varying getNom() {
        return this.nom;
    }
    public void setPrenom(character varying prenom) {
        this.prenom = prenom;
    }
    public character varying getPrenom() {
        return this.prenom;
    }
    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }
    public Date getDatenaissance() {
        return this.datenaissance;
    }
    public void setSalaire(numeric salaire) {
        this.salaire = salaire;
    }
    public numeric getSalaire() {
        return this.salaire;
    }
    public void setDateinitiation(timestamp without time zone dateinitiation) {
        this.dateinitiation = dateinitiation;
    }
    public timestamp without time zone getDateinitiation() {
        return this.dateinitiation;
    }
    public void setTauxhoraire(numeric tauxhoraire) {
        this.tauxhoraire = tauxhoraire;
    }
    public numeric getTauxhoraire() {
        return this.tauxhoraire;
    }

}
