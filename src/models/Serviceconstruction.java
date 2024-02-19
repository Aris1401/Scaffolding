package models;

public class Serviceconstruction {

    int idserviceconstruction;
    numeric surfaceroute;
    int idtyperoute;
    int idniveauqualite;
    numeric prix;

    public void setIdserviceconstruction(int idserviceconstruction) {
        this.idserviceconstruction = idserviceconstruction;
    }
    public int getIdserviceconstruction() {
        return this.idserviceconstruction;
    }
    public void setSurfaceroute(numeric surfaceroute) {
        this.surfaceroute = surfaceroute;
    }
    public numeric getSurfaceroute() {
        return this.surfaceroute;
    }
    public void setIdtyperoute(int idtyperoute) {
        this.idtyperoute = idtyperoute;
    }
    public int getIdtyperoute() {
        return this.idtyperoute;
    }
    public void setIdniveauqualite(int idniveauqualite) {
        this.idniveauqualite = idniveauqualite;
    }
    public int getIdniveauqualite() {
        return this.idniveauqualite;
    }
    public void setPrix(numeric prix) {
        this.prix = prix;
    }
    public numeric getPrix() {
        return this.prix;
    }

}
