package models;

public class Typetravailleur {

    int idtypetravailleur;
    character varying nom;

    public void setIdtypetravailleur(int idtypetravailleur) {
        this.idtypetravailleur = idtypetravailleur;
    }
    public int getIdtypetravailleur() {
        return this.idtypetravailleur;
    }
    public void setNom(character varying nom) {
        this.nom = nom;
    }
    public character varying getNom() {
        return this.nom;
    }

}
