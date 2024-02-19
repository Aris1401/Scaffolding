package models;

public class V_montantheuredetravail {

    int idserviceconstruction;
    int idtravailleur;
    numeric montant;

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
    public void setMontant(numeric montant) {
        this.montant = montant;
    }
    public numeric getMontant() {
        return this.montant;
    }

}
