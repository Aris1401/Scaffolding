package com.scaffolding.test.models;

import jakarta.persistence.*;

@Entity
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;

    int taux;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }

    public void setTaux(int taux) {
        this.taux = taux;
    }
    public int getTaux() {
        return this.taux;
    }

}
