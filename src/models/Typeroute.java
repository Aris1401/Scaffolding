package models;

public class Typeroute {

    int idtyperoute;
    character varying designation;

    public void setIdtyperoute(int idtyperoute) {
        this.idtyperoute = idtyperoute;
    }
    public int getIdtyperoute() {
        return this.idtyperoute;
    }
    public void setDesignation(character varying designation) {
        this.designation = designation;
    }
    public character varying getDesignation() {
        return this.designation;
    }

}
