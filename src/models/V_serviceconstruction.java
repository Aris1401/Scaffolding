package models;

public class V_serviceconstruction {

    int idserviceconstruction;
    int idsurfaceroutedesignation;
    character varying designation;
    int idtyperoute;
    int idniveauqualite;
    numeric prix;

    public void setIdserviceconstruction(int idserviceconstruction) {
        this.idserviceconstruction = idserviceconstruction;
    }
    public int getIdserviceconstruction() {
        return this.idserviceconstruction;
    }
    public void setIdsurfaceroutedesignation(int idsurfaceroutedesignation) {
        this.idsurfaceroutedesignation = idsurfaceroutedesignation;
    }
    public int getIdsurfaceroutedesignation() {
        return this.idsurfaceroutedesignation;
    }
    public void setDesignation(character varying designation) {
        this.designation = designation;
    }
    public character varying getDesignation() {
        return this.designation;
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
