package models;

public class Serviceheuretravail {

    int idserviceheuretravail;
    int idserviceconstruction;
    int idtravailleur;
    numeric heurestravail;
    int nombretravailleur;

    public void setIdserviceheuretravail(int idserviceheuretravail) {
        this.idserviceheuretravail = idserviceheuretravail;
    }
    public int getIdserviceheuretravail() {
        return this.idserviceheuretravail;
    }
    public void setIdserviceconstruction(int idserviceconstruction) {
        this.idserviceconstruction = idserviceconstruction;
    }
    public int getIdserviceconstruction() {
        return this.idserviceconstruction;
    }
    public void setIdtravailleur(int idtravailleur) {
        this.idtravailleur = idtravailleur;
    }
    public int getIdtravailleur() {
        return this.idtravailleur;
    }
    public void setHeurestravail(numeric heurestravail) {
        this.heurestravail = heurestravail;
    }
    public numeric getHeurestravail() {
        return this.heurestravail;
    }
    public void setNombretravailleur(int nombretravailleur) {
        this.nombretravailleur = nombretravailleur;
    }
    public int getNombretravailleur() {
        return this.nombretravailleur;
    }

}
