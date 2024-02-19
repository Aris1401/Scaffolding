package models;

public class V_typeroutematerielnom {

    character varying typeroute;
    character varying materiel;

    public void setTyperoute(character varying typeroute) {
        this.typeroute = typeroute;
    }
    public character varying getTyperoute() {
        return this.typeroute;
    }
    public void setMateriel(character varying materiel) {
        this.materiel = materiel;
    }
    public character varying getMateriel() {
        return this.materiel;
    }

}
