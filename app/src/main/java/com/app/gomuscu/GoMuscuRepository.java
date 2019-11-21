package com.app.gomuscu;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.app.gomuscu.entity.Exercice;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GoMuscuRepository {
    private GoMuscuDao dao;


    GoMuscuRepository(Application application) {
        GoMuscuDatabase db = GoMuscuDatabase.getInstance(application);
        this.dao = db.getGoMuscuDao();
    }

    public LiveData<List<Exercice>> getAllExercices() {
        LiveData<List<Exercice>> liste = null;
        try {
            liste = new GetAllExercicesAsyncTask(this.dao).execute().get();
            System.out.println("LISTE" + liste);
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getAllExercices : " + e.getMessage());
        }
        return liste;
    }

    private static class GetAllExercicesAsyncTask extends AsyncTask<Void, Void, LiveData<List<Exercice>>> {
        private GoMuscuDao dao;

        GetAllExercicesAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected LiveData<List<Exercice>> doInBackground(Void... voids) {
            return this.dao.getAllExercices();
        }
    }

    public long insertExercice(Exercice exercice) {
        Long id = null;
        try {
            id = new InsertExerciceAsyncTask(this.dao).execute(exercice).get()[0];
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "insertExercice : " + e.getMessage());
        }
        return id;
    }

    private static class InsertExerciceAsyncTask extends AsyncTask<Exercice, Void, long[]> {
        private GoMuscuDao dao;

        InsertExerciceAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected long[] doInBackground(Exercice... exercices) {
            return dao.insertExercices(exercices);
        }
    }


}
