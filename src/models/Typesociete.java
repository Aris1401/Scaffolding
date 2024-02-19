package models;

public class Typesociete {

    int idtypesociete;
    character varying designation;

    public void setIdtypesociete(int idtypesociete) {
        this.idtypesociete = idtypesociete;
    }
    public int getIdtypesociete() {
        return this.idtypesociete;
    }
    public void setDesignation(character varying designation) {
        this.designation = designation;
    }
    public character varying getDesignation() {
        return this.designation;
    }

}
