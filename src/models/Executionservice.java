package models;

public class Executionservice {

    int idexecutionservice;
    int idserviceconstruction;
    numeric quantite;
    timestamp without time zone dateexecution;
    numeric montant;

    public void setIdexecutionservice(int idexecutionservice) {
        this.idexecutionservice = idexecutionservice;
    }
    public int getIdexecutionservice() {
        return this.idexecutionservice;
    }
    public void setIdserviceconstruction(int idserviceconstruction) {
        this.idserviceconstruction = idserviceconstruction;
    }
    public int getIdserviceconstruction() {
        return this.idserviceconstruction;
    }
    public void setQuantite(numeric quantite) {
        this.quantite = quantite;
    }
    public numeric getQuantite() {
        return this.quantite;
    }
    public void setDateexecution(timestamp without time zone dateexecution) {
        this.dateexecution = dateexecution;
    }
    public timestamp without time zone getDateexecution() {
        return this.dateexecution;
    }
    public void setMontant(numeric montant) {
        this.montant = montant;
    }
    public numeric getMontant() {
        return this.montant;
    }

}
