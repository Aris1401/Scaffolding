package models;

public class V_etatdestock {

    int idmateriel;
    character varying designation;
    numeric reste;

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
    public void setReste(numeric reste) {
        this.reste = reste;
    }
    public numeric getReste() {
        return this.reste;
    }

}
