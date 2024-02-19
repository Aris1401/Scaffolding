package models;

public class V_travailleur {

    int idtravailleur;
    character varying nom;
    character varying prenom;
    int duree;
    int idtypetravailleur;
    character varying typetravailleur;
    timestamp without time zone dateinitiation;
    numeric totaltauxhoraire;

    public void setIdtravailleur(int idtravailleur) {
        this.idtravailleur = idtravailleur;
    }
    public int getIdtravailleur() {
        return this.idtravailleur;
    }
    public void setNom(character varying nom) {
        this.nom = nom;
    }
    public character varying getNom() {
        return this.nom;
    }
    public void setPrenom(character varying prenom) {
        this.prenom = prenom;
    }
    public character varying getPrenom() {
        return this.prenom;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
    public int getDuree() {
        return this.duree;
    }
    public void setIdtypetravailleur(int idtypetravailleur) {
        this.idtypetravailleur = idtypetravailleur;
    }
    public int getIdtypetravailleur() {
        return this.idtypetravailleur;
    }
    public void setTypetravailleur(character varying typetravailleur) {
        this.typetravailleur = typetravailleur;
    }
    public character varying getTypetravailleur() {
        return this.typetravailleur;
    }
    public void setDateinitiation(timestamp without time zone dateinitiation) {
        this.dateinitiation = dateinitiation;
    }
    public timestamp without time zone getDateinitiation() {
        return this.dateinitiation;
    }
    public void setTotaltauxhoraire(numeric totaltauxhoraire) {
        this.totaltauxhoraire = totaltauxhoraire;
    }
    public numeric getTotaltauxhoraire() {
        return this.totaltauxhoraire;
    }

}
