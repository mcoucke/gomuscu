package com.app.gomuscu;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.app.gomuscu.entity.Exercice;
import com.app.gomuscu.entity.ExerciceDansHistorique;
import com.app.gomuscu.entity.ExerciceDansSeance;
import com.app.gomuscu.entity.Historique;
import com.app.gomuscu.entity.Journee;
import com.app.gomuscu.entity.Repetition;
import com.app.gomuscu.entity.Seance;

import java.util.Date;
import java.util.List;

@Dao
public interface GoMuscuDao {

    @Insert
    public long[] insertExercices(Exercice... exercices);

    @Insert
    public long[] insertSeances(Seance... seances);

    @Insert
    public long[] insertExercicesDansSeances(ExerciceDansSeance... exercicesDansSeances);

    @Insert
    public long[] insertExercicesDansHistoriques(ExerciceDansHistorique... exerciceDansHistoriques);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertJournees(Journee... journees);

    @Insert
    public long[] insertHistoriques(Historique... historiques);

    @Insert
    public long[] insertRepetitions(Repetition... repetitions);

//    @Delete
//    public void deleteExercicesDansSeances(ExerciceDansSeance... exercicesDansSeances);

    @Delete
    public void deleteJournees(Journee... journees);

    @Query("SELECT * FROM Exercice")
    public LiveData<List<Exercice>> getAllExercices();

    @Query("SELECT * FROM Seance")
    public LiveData<List<Seance>> getAllSeances();

    @Query("SELECT * FROM Journee")
    public LiveData<List<Journee>> getAllJournees();

    @Query("SELECT * FROM Historique")
    public LiveData<List<Historique>> getAllHistoriques();

    @Query("SELECT * FROM ExerciceDansSeance WHERE idSeance = :id_seance")
    public LiveData<List<ExerciceDansSeance>> getAllExercicesDansSeancesById(Integer... id_seance);

    @Query("SELECT * FROM Exercice WHERE nom = :nom")
    public Exercice getExerciceByNom(String... nom);

    @Query("SELECT * FROM Exercice WHERE id = :id")
    public Exercice getExerciceById(Integer... id);

    @Query("SELECT * FROM Seance WHERE id = :id")
    public Seance getSeanceById(Integer... id);

    @Query("SELECT * FROM Historique WHERE id = :id")
    public Historique getHistoriqueById(Integer... id);

    @Query("SELECT * FROM Journee WHERE date = :date")
    public Journee getJourneeByDate(Date... date);

    @Query("SELECT * FROM Journee WHERE date >= :date")
    public LiveData<List<Journee>> getAllJourneesFromDate(Date... date);

    @Query("SELECT * FROM ExerciceDansHistorique WHERE idHistorique = :id")
    public LiveData<List<ExerciceDansHistorique>> getAllExerciceDansHistoriqueById(Integer... id);

    @Query("SELECT COUNT(*) FROM ExerciceDansHistorique")
    public LiveData<Integer> getExerciceDansHistoriqueCount();

    @Query("SELECT * FROM ExerciceDansHistorique WHERE idHistorique = :id_historique AND idExercice = :id_exercice")
    public ExerciceDansHistorique getExerciceDansHistoriqueById(Integer id_historique, Integer id_exercice);

    @Query("SELECT * FROM Repetition WHERE idExerciceDansHistorique = :id_ex_historique")
    public Repetition getRepetitionByIdExercice(Integer... id_ex_historique);

}
