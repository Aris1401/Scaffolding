package models;

public class Materiel {

    int idmateriel;
    character varying designation;
    numeric prix;

    public void setIdmateriel(int idmateriel) {
        this.idmateriel = idmateriel;
    }
    public int getIdmateriel() {
        return this.idmateriel;
    }
    public void setDesignation(character varying designation) {
        this.designation = designation;
    }
    public character varying getDesignation() {
        return this.designation;
    }
    public void setPrix(numeric prix) {
        this.prix = prix;
    }
    public numeric getPrix() {
        return this.prix;
    }

}
