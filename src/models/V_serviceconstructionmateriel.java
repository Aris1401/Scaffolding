package models;

public class V_serviceconstructionmateriel {

    int idserviceconstruction;
    int idsurfaceroutedesignation;
    character varying designationsurface;
    int idtyperoute;
    int idniveauqualite;
    numeric prix;
    int idmateriel;
    character varying designation;
    numeric prixunitairemateriel;
    numeric quantite;

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
    public void setDesignationsurface(character varying designationsurface) {
        this.designationsurface = designationsurface;
    }
    public character varying getDesignationsurface() {
        return this.designationsurface;
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
    public void setPrixunitairemateriel(numeric prixunitairemateriel) {
        this.prixunitairemateriel = prixunitairemateriel;
    }
    public numeric getPrixunitairemateriel() {
        return this.prixunitairemateriel;
    }
    public void setQuantite(numeric quantite) {
        this.quantite = quantite;
    }
    public numeric getQuantite() {
        return this.quantite;
    }

}
