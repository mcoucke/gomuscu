package com.app.gomuscu;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.app.gomuscu.entity.Exercice;
import com.app.gomuscu.entity.ExerciceDansSeance;
import com.app.gomuscu.entity.Journee;
import com.app.gomuscu.entity.Seance;

import java.util.Date;
import java.util.List;

@Dao
public interface GoMuscuDao {

    @Insert
    public long[] insertExercices(Exercice... exercices);

//    @Update
//    public void updateExercices(Exercice... exercices);
//
//    @Delete
//    public void deleteExercices(Exercice... exercices);

    @Insert
    public long[] insertSeances(Seance... seances);

    @Insert
    public long[] insertExercicesDansSeances(ExerciceDansSeance... exercicesDansSeances);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertJournees(Journee... journees);

//    @Delete
//    public void deleteExercicesDansSeances(ExerciceDansSeance... exercicesDansSeances);

    @Query("SELECT * FROM Exercice")
    public LiveData<List<Exercice>> getAllExercices();

    @Query("SELECT * FROM Seance")
    public LiveData<List<Seance>> getAllSeances();

    @Query("SELECT * FROM Journee")
    public LiveData<List<Journee>> getAllJournees();

    @Query("SELECT * FROM ExerciceDansSeance WHERE idSeance = :id_seance")
    public LiveData<List<ExerciceDansSeance>> getAllExercicesDansSeancesById(Integer... id_seance);

    @Query("SELECT * FROM Exercice WHERE nom = :nom")
    public Exercice getExerciceByNom(String... nom);

    @Query("SELECT * FROM Journee WHERE date = :date")
    public Journee getJourneeByDate(Date... date);

}
