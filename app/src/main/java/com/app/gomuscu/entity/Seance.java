package com.app.gomuscu.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Seance {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nom;

    public Seance(String nom) {
        this.nom = nom;
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

    @NonNull
    @Override
    public String toString() {
        return this.nom;
    }
}
