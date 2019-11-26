package com.app.gomuscu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.app.gomuscu.entity.Exercice;
import com.app.gomuscu.entity.ExerciceDansSeance;
import com.app.gomuscu.entity.Journee;
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

    public Long insertJournee(Journee journee) {
        return this.goMuscuRepository.insertJournee(journee);
    }

    public LiveData<List<Exercice>> getAllExercices() {
        return goMuscuRepository.getAllExercices();
    }

    public LiveData<List<Seance>> getAllSeances() {
        return goMuscuRepository.getAllSeances();
    }

    public LiveData<List<ExerciceDansSeance>> getAllExercicesDansSeancesById(int id_seance) {
        return goMuscuRepository.getAllExercicesDansSeancesById(id_seance);
    }

    public Exercice getExerciceByNom(String nom) {
        return goMuscuRepository.getExerciceByNom(nom);
    }

    public Seance getSeanceById(int id) {
        return goMuscuRepository.getSeanceById(id);
    }

    public Journee getJourneeByDate(Date date) {
        return goMuscuRepository.getJourneeByDate(date);
    }

    public LiveData<List<Journee>> getAllJourneesFromDate(Date date) {
        return goMuscuRepository.getAllJourneesFromDate(date);
    }
}
