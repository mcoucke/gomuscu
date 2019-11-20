package com.app.gomuscu;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.gomuscu.entity.Exercice;

import java.util.List;

@Dao
public interface GoMuscuDao {

    @Insert
    public long[] insertExercices(Exercice... exercices);

    @Update
    public void updateExercices(Exercice... exercices);

    @Delete
    public void deleteExercices(Exercice... exercices);

    @Query("SELECT * FROM Exercice")
    public List<Exercice> getAllExercices();

}
