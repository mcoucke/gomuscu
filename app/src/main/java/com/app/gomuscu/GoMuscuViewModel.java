package com.app.gomuscu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.app.gomuscu.entity.Exercice;

import java.util.List;

public class GoMuscuViewModel extends AndroidViewModel {

    private GoMuscuRepository goMuscuRepository;

    public GoMuscuViewModel(@NonNull Application application) {
        super(application);
        this.goMuscuRepository = new GoMuscuRepository(application);
    }

    public LiveData<List<Exercice>> getAllExercices() {
        return goMuscuRepository.getAllExercices();
    }

    public Long insertExercice(Exercice exercice) {
        return this.goMuscuRepository.insertExercice(exercice);
    }
}
