package com.app.gomuscu.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class ExerciceDansSeance {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ForeignKey(entity = Exercice.class, parentColumns = "id", childColumns = "idExercice")
    private int idExercice;
    @ForeignKey(entity = Seance.class, parentColumns = "id", childColumns = "idSeance")
    private int idSeance;

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

    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }
}
