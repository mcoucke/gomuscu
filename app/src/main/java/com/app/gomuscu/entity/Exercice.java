package com.app.gomuscu.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Exercice {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nom;
    private String description;
    private String type;


    public Exercice(String nom, String description, String type) {
        this.nom = nom;
        this.description = description;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        String out = "Exercice : " + this.nom + " - " + this.type + "\n";
        out += this.description;
        return out;
    }
}
