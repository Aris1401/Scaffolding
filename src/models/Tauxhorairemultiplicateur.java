package models;

public class Tauxhorairemultiplicateur {

    int idtauxhoraitemultiplicateur;
    int typetravailleur;
    numeric mutliplicateur;

    public void setIdtauxhoraitemultiplicateur(int idtauxhoraitemultiplicateur) {
        this.idtauxhoraitemultiplicateur = idtauxhoraitemultiplicateur;
    }
    public int getIdtauxhoraitemultiplicateur() {
        return this.idtauxhoraitemultiplicateur;
    }
    public void setTypetravailleur(int typetravailleur) {
        this.typetravailleur = typetravailleur;
    }
    public int getTypetravailleur() {
        return this.typetravailleur;
    }
    public void setMutliplicateur(numeric mutliplicateur) {
        this.mutliplicateur = mutliplicateur;
    }
    public numeric getMutliplicateur() {
        return this.mutliplicateur;
    }

}
