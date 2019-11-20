package com.app.gomuscu.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class ExerciceDansHistorique {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ForeignKey(entity = Exercice.class, parentColumns = "id", childColumns = "idExercice")
    private int idExercice;
    @ForeignKey(entity = Historique.class, parentColumns = "id", childColumns = "idHistorique")
    private int idHistorique;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(int idExercice) {
        this.idExercice = idExercice;
    }

    public int getIdHistorique() {
        return idHistorique;
    }

    public void setIdHistorique(int idHistorique) {
        this.idHistorique = idHistorique;
    }
}
