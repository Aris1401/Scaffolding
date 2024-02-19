package models;

import java.util.Date;

public class Societe {

    int idsociete;
    character varying nom;
    int idtypesociete;
    Date dateentree;

    public void setIdsociete(int idsociete) {
        this.idsociete = idsociete;
    }
    public int getIdsociete() {
        return this.idsociete;
    }
    public void setNom(character varying nom) {
        this.nom = nom;
    }
    public character varying getNom() {
        return this.nom;
    }
    public void setIdtypesociete(int idtypesociete) {
        this.idtypesociete = idtypesociete;
    }
    public int getIdtypesociete() {
        return this.idtypesociete;
    }
    public void setDateentree(Date dateentree) {
        this.dateentree = dateentree;
    }
    public Date getDateentree() {
        return this.dateentree;
    }

}
