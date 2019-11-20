package com.app.gomuscu.entity;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Journee {
    @PrimaryKey
    private Date date;
    @ForeignKey(entity = Seance.class, parentColumns = "id", childColumns = "idSeance")
    private int idSeance;

    public Journee(Date date, int idSeance) {
        this.date = date;
        this.idSeance = idSeance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }
}
