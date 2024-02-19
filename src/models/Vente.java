package models;

public class Vente {

    int idvente;
    numeric prixvente;
    numeric quantite;
    int idsociete;
    int idserviceconstruction;
    timestamp without time zone datevente;

    public void setIdvente(int idvente) {
        this.idvente = idvente;
    }
    public int getIdvente() {
        return this.idvente;
    }
    public void setPrixvente(numeric prixvente) {
        this.prixvente = prixvente;
    }
    public numeric getPrixvente() {
        return this.prixvente;
    }
    public void setQuantite(numeric quantite) {
        this.quantite = quantite;
    }
    public numeric getQuantite() {
        return this.quantite;
    }
    public void setIdsociete(int idsociete) {
        this.idsociete = idsociete;
    }
    public int getIdsociete() {
        return this.idsociete;
    }
    public void setIdserviceconstruction(int idserviceconstruction) {
        this.idserviceconstruction = idserviceconstruction;
    }
    public int getIdserviceconstruction() {
        return this.idserviceconstruction;
    }
    public void setDatevente(timestamp without time zone datevente) {
        this.datevente = datevente;
    }
    public timestamp without time zone getDatevente() {
        return this.datevente;
    }

}
