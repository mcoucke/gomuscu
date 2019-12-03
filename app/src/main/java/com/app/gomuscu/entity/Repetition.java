package com.app.gomuscu.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Repetition {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private float poids;
    private int nombre;
    private int dureeSecondes;

    @ForeignKey(entity =
            ExerciceDansHistorique.class,
            parentColumns = "id",
            childColumns = "idExerciceDansHistorique")
    private int idExerciceDansHistorique;

    public Repetition(float poids, int nombre, int dureeSecondes, int idExerciceDansHistorique) {
        this.poids  = poids;
        this.nombre = nombre;
        this.dureeSecondes = dureeSecondes;
        this.idExerciceDansHistorique = idExerciceDansHistorique;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getDureeSecondes() {
        return dureeSecondes;
    }

    public void setDureeSecondes(int dureeSecondes) {
        this.dureeSecondes = dureeSecondes;
    }

    public int getIdExerciceDansHistorique() {
        return idExerciceDansHistorique;
    }

    public void setIdExerciceDansHistorique(int idExerciceDansHistorique) {
        this.idExerciceDansHistorique = idExerciceDansHistorique;
    }
}
