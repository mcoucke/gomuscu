package com.app.gomuscu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.app.gomuscu.entity.Exercice;
import com.app.gomuscu.entity.ExerciceDansHistorique;
import com.app.gomuscu.entity.ExerciceDansSeance;
import com.app.gomuscu.entity.Historique;
import com.app.gomuscu.entity.Journee;
import com.app.gomuscu.entity.Repetition;
import com.app.gomuscu.entity.Seance;

import java.util.Date;
import java.util.List;

public class GoMuscuViewModel extends AndroidViewModel {

    private GoMuscuRepository goMuscuRepository;

    public GoMuscuViewModel(@NonNull Application application) {
        super(application);
        this.goMuscuRepository = new GoMuscuRepository(application);
    }

    public Long insertExercice(Exercice exercice) {
        return this.goMuscuRepository.insertExercice(exercice);
    }

    public Long insertSeance(Seance seance) {
        return this.goMuscuRepository.insertSeance(seance);
    }

    public Long insertExerciceDansSeance(ExerciceDansSeance exerciceDansSeance) {
        return this.goMuscuRepository.insertExerciceDansSeance(exerciceDansSeance);
    }

    public Long insertExerciceDansHistorique(ExerciceDansHistorique exerciceDansHistorique) {
        return this.goMuscuRepository.insertExerciceDansHistorique(exerciceDansHistorique);
    }

    public Long insertJournee(Journee journee) {
        return this.goMuscuRepository.insertJournee(journee);
    }

    public Long insertHistorique(Historique historique) {
        return this.goMuscuRepository.insertHistorique(historique);
    }

    public Long insertRepetition(Repetition repetition) {
        return this.goMuscuRepository.insertRepetition(repetition);
    }

    public Void deleteAllExercicesDansSeanceById(int id_seance) {
        this.goMuscuRepository.deleteAllExercicesDansSeanceById(id_seance);
        return null;
    }

    public Void deleteSeance(Seance seance) {
        this.goMuscuRepository.deleteSeance(seance);
        return null;
    }

    public Void deleteJournee(Journee journee) {
        this.goMuscuRepository.deleteJournee(journee);
        return null;
    }

    public LiveData<List<Exercice>> getAllExercices() {
        return goMuscuRepository.getAllExercices();
    }

    public LiveData<List<Seance>> getAllSeances() {
        return goMuscuRepository.getAllSeances();
    }

    public LiveData<List<Journee>> getAllJournees() {
        return goMuscuRepository.getAllJournees();
    }

    public LiveData<List<Historique>> getAllHistoriques() {
        return goMuscuRepository.getAllHistoriques();
    }

    public LiveData<List<ExerciceDansSeance>> getAllExercicesDansSeancesById(int id_seance) {
        return goMuscuRepository.getAllExercicesDansSeancesById(id_seance);
    }

    public Exercice getExerciceByNom(String nom) {
        return goMuscuRepository.getExerciceByNom(nom);
    }

    public Exercice getExerciceById(int id) {
        return goMuscuRepository.getExerciceById(id);
    }

    public Seance getSeanceById(int id) {
        return goMuscuRepository.getSeanceById(id);
    }

    public Historique getHistoriqueById(int id) {
        return goMuscuRepository.getHistoriqueById(id);
    }

    public Journee getJourneeByDate(Date date) {
        return goMuscuRepository.getJourneeByDate(date);
    }

    public LiveData<List<Journee>> getAllJourneesFromDate(Date date) {
        return goMuscuRepository.getAllJourneesFromDate(date);
    }

    public LiveData<List<ExerciceDansHistorique>> getAllExerciceDansHistoriqueById(int id) {
        return goMuscuRepository.getAllExerciceDansHistoriqueById(id);
    }

    public LiveData<Integer> getExerciceDansHistoriqueCount() {
        return goMuscuRepository.getExerciceDansHistoriqueCount();
    }

    public Seance getSeanceByNom(String nom) {
        return goMuscuRepository.getSeanceByNom(nom);
    }
    
    public ExerciceDansHistorique getExerciceDansHistoriqueById(int id_historique, int id_exercice) {
        return goMuscuRepository.getExerciceDansHistoriqueById(id_historique, id_exercice);
    }

    public Repetition getRepetitionByIdExercice(Integer id) {
        return goMuscuRepository.getRepetitionByIdExercice(id);
    }
}
