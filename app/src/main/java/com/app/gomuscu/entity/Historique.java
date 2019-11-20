package com.app.gomuscu.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Historique {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nom;
    private Date date;
    private int dureeSecondes;

    public Historique(String nom, Date date, int dureeSecondes) {
        this.nom = nom;
        this.date = date;
        this.dureeSecondes = dureeSecondes;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDureeSecondes() {
        return dureeSecondes;
    }

    public void setDureeSecondes(int dureeSecondes) {
        this.dureeSecondes = dureeSecondes;
    }

}
