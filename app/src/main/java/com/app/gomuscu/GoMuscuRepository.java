package com.app.gomuscu;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.app.gomuscu.entity.Exercice;
import com.app.gomuscu.entity.ExerciceDansSeance;
import com.app.gomuscu.entity.Journee;
import com.app.gomuscu.entity.Seance;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GoMuscuRepository {
    private GoMuscuDao dao;


    GoMuscuRepository(Application application) {
        GoMuscuDatabase db = GoMuscuDatabase.getInstance(application);
        this.dao = db.getGoMuscuDao();
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
            return this.dao.insertExercices(exercices);
        }
    }

    public long insertSeance(Seance seance) {
        Long id = null;
        try {
            id = new InsertSeanceAsyncTask(this.dao).execute(seance).get()[0];
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "insertSeance : " + e.getMessage());
        }
        return id;
    }

    private static class InsertSeanceAsyncTask extends AsyncTask<Seance, Void, long[]> {
        private GoMuscuDao dao;

        InsertSeanceAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected long[] doInBackground(Seance... seances) {
            return this.dao.insertSeances(seances);
        }
    }

    public long insertExerciceDansSeance(ExerciceDansSeance exerciceDansSeance) {
        Long id = null;
        try {
            id = new InsertExerciceDansSeanceAsyncTask(this.dao).execute(exerciceDansSeance).get()[0];
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "insertExerciceDansSeance : " + e.getMessage());
        }
        return id;
    }

    private static class InsertExerciceDansSeanceAsyncTask extends AsyncTask<ExerciceDansSeance, Void, long[]> {
        private GoMuscuDao dao;

        InsertExerciceDansSeanceAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected long[] doInBackground(ExerciceDansSeance... exerciceDansSeances) {
            return this.dao.insertExercicesDansSeances(exerciceDansSeances);
        }
    }

    public long insertJournee(Journee journee) {
        Long id = null;
        try {
            id = new InsertJourneeAsyncTask(this.dao).execute(journee).get()[0];
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "insertJournee : " + e.getMessage());
        }
        return id;
    }

    private static class InsertJourneeAsyncTask extends AsyncTask<Journee, Void, long[]> {
        private GoMuscuDao dao;

        InsertJourneeAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected long[] doInBackground(Journee... journees) {
            return this.dao.insertJournees(journees);
        }
    }

    public LiveData<List<Exercice>> getAllExercices() {
        LiveData<List<Exercice>> liste = null;
        try {
            liste = new GetAllExercicesAsyncTask(this.dao).execute().get();
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

    public LiveData<List<Seance>> getAllSeances() {
        LiveData<List<Seance>> liste = null;
        try {
            liste = new getAllSeancesAsyncTask(this.dao).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getAllSeances : " + e.getMessage());
        }
        return liste;
    }

    private static class getAllSeancesAsyncTask extends AsyncTask<Void, Void, LiveData<List<Seance>>> {
        private GoMuscuDao dao;

        getAllSeancesAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected LiveData<List<Seance>> doInBackground(Void... voids) {
            return this.dao.getAllSeances();
        }
    }

    public LiveData<List<Journee>> getAllJournees() {
        LiveData<List<Journee>> liste = null;
        try {
            liste = new getAllJourneesAsyncTask(this.dao).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getAllJournees : " + e.getMessage());
        }
        return liste;
    }

    private static class getAllJourneesAsyncTask extends AsyncTask<Void, Void, LiveData<List<Journee>>> {
        private GoMuscuDao dao;

        getAllJourneesAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected LiveData<List<Journee>> doInBackground(Void... voids) {
            return this.dao.getAllJournees();
        }
    }

    public LiveData<List<ExerciceDansSeance>> getAllExercicesDansSeancesById(int id) {
        LiveData<List<ExerciceDansSeance>> liste = null;
        try {
            liste = new getAllExercicesDansSeancesByIdAsyncTask(this.dao).execute(id).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getAllExercicesDansSeancesBySeanceId : " + e.getMessage());
        }
        return liste;
    }

    private static class getAllExercicesDansSeancesByIdAsyncTask
            extends AsyncTask<Integer, Void, LiveData<List<ExerciceDansSeance>>> {
        private GoMuscuDao dao;

        getAllExercicesDansSeancesByIdAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected LiveData<List<ExerciceDansSeance>> doInBackground(Integer... integers) {
            return this.dao.getAllExercicesDansSeancesById(integers);
        }
    }

    public Exercice getExerciceByNom(String nom) {
        Exercice exercice = null;
        try {
            exercice = new getExerciceByNomAsyncTask(this.dao).execute(nom).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getExerciceByNom : " + e.getMessage());
        }
        return exercice;
    }

    private static class getExerciceByNomAsyncTask extends AsyncTask<String, Void, Exercice> {
        private GoMuscuDao dao;

        getExerciceByNomAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected Exercice doInBackground(String... strings) {
            return this.dao.getExerciceByNom(strings);
        }
    }

    public Journee getJourneeByDate(Date date) {
        Journee journee = null;
        try {
            journee = new getJourneeByDateAsyncTask(this.dao).execute(date).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getJourneeByDate : " + e.getMessage());
        }
        return journee;
    }

    private static class getJourneeByDateAsyncTask extends AsyncTask<Date, Void, Journee> {
        private GoMuscuDao dao;

        getJourneeByDateAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected Journee doInBackground(Date... dates) {
            return this.dao.getJourneeByDate(dates);
        }
    }

}
