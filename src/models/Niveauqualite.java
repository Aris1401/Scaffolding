package models;

public class Niveauqualite {

    int idniveauqualite;
    character varying designation;
    int niveau;

    public void setIdniveauqualite(int idniveauqualite) {
        this.idniveauqualite = idniveauqualite;
    }
    public int getIdniveauqualite() {
        return this.idniveauqualite;
    }
    public void setDesignation(character varying designation) {
        this.designation = designation;
    }
    public character varying getDesignation() {
        return this.designation;
    }
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    public int getNiveau() {
        return this.niveau;
    }

}
