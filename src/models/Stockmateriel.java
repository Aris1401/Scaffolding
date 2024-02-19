package models;

public class Stockmateriel {

    int idstockmateriel;
    int idmateriel;
    timestamp without time zone dateenregistrement;
    numeric entree;
    numeric sortie;

    public void setIdstockmateriel(int idstockmateriel) {
        this.idstockmateriel = idstockmateriel;
    }
    public int getIdstockmateriel() {
        return this.idstockmateriel;
    }
    public void setIdmateriel(int idmateriel) {
        this.idmateriel = idmateriel;
    }
    public int getIdmateriel() {
        return this.idmateriel;
    }
    public void setDateenregistrement(timestamp without time zone dateenregistrement) {
        this.dateenregistrement = dateenregistrement;
    }
    public timestamp without time zone getDateenregistrement() {
        return this.dateenregistrement;
    }
    public void setEntree(numeric entree) {
        this.entree = entree;
    }
    public numeric getEntree() {
        return this.entree;
    }
    public void setSortie(numeric sortie) {
        this.sortie = sortie;
    }
    public numeric getSortie() {
        return this.sortie;
    }

}
