package models;

public class Quantiteconstruction {

    int idquantiteconstruction;
    int idsurfaceroutedesignation;
    int idmateriel;
    numeric quantite;

    public void setIdquantiteconstruction(int idquantiteconstruction) {
        this.idquantiteconstruction = idquantiteconstruction;
    }
    public int getIdquantiteconstruction() {
        return this.idquantiteconstruction;
    }
    public void setIdsurfaceroutedesignation(int idsurfaceroutedesignation) {
        this.idsurfaceroutedesignation = idsurfaceroutedesignation;
    }
    public int getIdsurfaceroutedesignation() {
        return this.idsurfaceroutedesignation;
    }
    public void setIdmateriel(int idmateriel) {
        this.idmateriel = idmateriel;
    }
    public int getIdmateriel() {
        return this.idmateriel;
    }
    public void setQuantite(numeric quantite) {
        this.quantite = quantite;
    }
    public numeric getQuantite() {
        return this.quantite;
    }

}
