package models;

public class Surfaceroutedesignation {

    int idsurfaceroutedesignation;
    numeric minimumsurface;
    numeric maximumsurface;
    character varying designation;
    numeric travailmutilipieur;

    public void setIdsurfaceroutedesignation(int idsurfaceroutedesignation) {
        this.idsurfaceroutedesignation = idsurfaceroutedesignation;
    }
    public int getIdsurfaceroutedesignation() {
        return this.idsurfaceroutedesignation;
    }
    public void setMinimumsurface(numeric minimumsurface) {
        this.minimumsurface = minimumsurface;
    }
    public numeric getMinimumsurface() {
        return this.minimumsurface;
    }
    public void setMaximumsurface(numeric maximumsurface) {
        this.maximumsurface = maximumsurface;
    }
    public numeric getMaximumsurface() {
        return this.maximumsurface;
    }
    public void setDesignation(character varying designation) {
        this.designation = designation;
    }
    public character varying getDesignation() {
        return this.designation;
    }
    public void setTravailmutilipieur(numeric travailmutilipieur) {
        this.travailmutilipieur = travailmutilipieur;
    }
    public numeric getTravailmutilipieur() {
        return this.travailmutilipieur;
    }

}
