package com.app.gomuscu;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

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

    private static class InsertExerciceDansSeanceAsyncTask
            extends AsyncTask<ExerciceDansSeance, Void, long[]> {
        private GoMuscuDao dao;

        InsertExerciceDansSeanceAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected long[] doInBackground(ExerciceDansSeance... exerciceDansSeances) {
            return this.dao.insertExercicesDansSeances(exerciceDansSeances);
        }
    }

    public long insertExerciceDansHistorique(ExerciceDansHistorique exerciceDansHistorique) {
        Long id = null;
        try {
            id = new InsertExerciceDansHistoriqueAsyncTask(this.dao).execute(exerciceDansHistorique).get()[0];
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "insertExerciceDansSeance : " + e.getMessage());
        }
        return id;
    }

    private static class InsertExerciceDansHistoriqueAsyncTask
            extends AsyncTask<ExerciceDansHistorique, Void, long[]> {
        private GoMuscuDao dao;

        InsertExerciceDansHistoriqueAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected long[] doInBackground(ExerciceDansHistorique... exerciceDansHistoriques) {
            return this.dao.insertExercicesDansHistoriques(exerciceDansHistoriques);
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

    public long insertHistorique(Historique historique) {
        Long id = null;
        try {
            id = new InsertHistoriqueAsyncTask(this.dao).execute(historique).get()[0];
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "insertHistorique : " + e.getMessage());
        }
        return id;
    }

    private static class InsertHistoriqueAsyncTask extends AsyncTask<Historique, Void, long[]> {
        private GoMuscuDao dao;

        InsertHistoriqueAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected long[] doInBackground(Historique... historique) {
            return this.dao.insertHistoriques(historique);
        }
    }

    public long insertRepetition(Repetition repetition) {
        Long id = null;
        try {
            id = new InsertRepetitionAsyncTask(this.dao).execute(repetition).get()[0];
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "insertRepetition : " + e.getMessage());
        }
        return id;
    }

    private static class InsertRepetitionAsyncTask extends AsyncTask<Repetition, Void, long[]> {
        private GoMuscuDao dao;

        InsertRepetitionAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected long[] doInBackground(Repetition... repetition) {
            return this.dao.insertRepetitions(repetition);
        }
    }

    public void deleteAllExercicesDansSeanceById(Integer id_seance) {
        new DeleteAllExercicesDansSeanceByIdAsyncTask(this.dao).execute(id_seance);
    }

    private static class DeleteAllExercicesDansSeanceByIdAsyncTask
            extends AsyncTask<Integer, Void, Void> {
        private GoMuscuDao dao;

        DeleteAllExercicesDansSeanceByIdAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            return this.dao.deleteAllExercicesDansSeanceById(integers);
        }
    }

    public void deleteSeance(Seance seance) {
        new DeleteSeanceAsyncTask(this.dao).execute(seance);
    }

    public static class DeleteSeanceAsyncTask extends AsyncTask<Seance, Void, Void> {
        private GoMuscuDao dao;

        DeleteSeanceAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Seance... seances) {
            dao.deleteSeances(seances);
            return null;
        }
    }

    public void deleteJournee(Journee journee) {
        new DeleteJourneeAsyncTask(this.dao).execute(journee);
    }

    public static class DeleteJourneeAsyncTask extends AsyncTask<Journee, Void, Void> {
        private GoMuscuDao dao;

        DeleteJourneeAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Journee... journees) {
            dao.deleteJournees(journees);
            return null;
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

    private static class GetAllExercicesAsyncTask
            extends AsyncTask<Void, Void, LiveData<List<Exercice>>> {
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
            liste = new GetAllSeancesAsyncTask(this.dao).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getAllSeances : " + e.getMessage());
        }
        return liste;
    }

    private static class GetAllSeancesAsyncTask
            extends AsyncTask<Void, Void, LiveData<List<Seance>>> {
        private GoMuscuDao dao;

        GetAllSeancesAsyncTask(GoMuscuDao dao) {
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
            liste = new GetAllJourneesAsyncTask(this.dao).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getAllJournees : " + e.getMessage());
        }
        return liste;
    }

    private static class GetAllJourneesAsyncTask
            extends AsyncTask<Void, Void, LiveData<List<Journee>>> {
        private GoMuscuDao dao;

        GetAllJourneesAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected LiveData<List<Journee>> doInBackground(Void... voids) {
            return this.dao.getAllJournees();
        }
    }

    public LiveData<List<Historique>> getAllHistoriques() {
        LiveData<List<Historique>> liste = null;
        try {
            liste = new GetAllHistoriquesAsyncTask(this.dao).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getAllHistoriques : " + e.getMessage());
        }
        return liste;
    }

    private static class GetAllHistoriquesAsyncTask
            extends AsyncTask<Void, Void, LiveData<List<Historique>>> {
        private GoMuscuDao dao;

        GetAllHistoriquesAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected LiveData<List<Historique>> doInBackground(Void... voids) {
            return this.dao.getAllHistoriques();
        }
    }

    public LiveData<List<ExerciceDansSeance>> getAllExercicesDansSeancesById(int id) {
        LiveData<List<ExerciceDansSeance>> liste = null;
        try {
            liste = new GetAllExercicesDansSeancesByIdAsyncTask(this.dao).execute(id).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getAllExercicesDansSeancesBySeanceId : " + e.getMessage());
        }
        return liste;
    }

    private static class GetAllExercicesDansSeancesByIdAsyncTask
            extends AsyncTask<Integer, Void, LiveData<List<ExerciceDansSeance>>> {
        private GoMuscuDao dao;

        GetAllExercicesDansSeancesByIdAsyncTask(GoMuscuDao dao) {
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
            exercice = new GetExerciceByNomAsyncTask(this.dao).execute(nom).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getExerciceByNom : " + e.getMessage());
        }
        return exercice;
    }

    private static class GetExerciceByNomAsyncTask extends AsyncTask<String, Void, Exercice> {
        private GoMuscuDao dao;

        GetExerciceByNomAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected Exercice doInBackground(String... strings) {
            return this.dao.getExerciceByNom(strings);
        }
    }

    public Exercice getExerciceById(int id) {
        Exercice exercice = null;
        try {
            exercice = new GetExerciceByIdAsyncTask(this.dao).execute(id).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getExerciceById : " + e.getMessage());
        }
        return exercice;
    }

    private static class GetExerciceByIdAsyncTask extends AsyncTask<Integer, Void, Exercice> {
        private GoMuscuDao dao;

        GetExerciceByIdAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected Exercice doInBackground(Integer... integers) {
            return this.dao.getExerciceById(integers);
        }
    }

    public Seance getSeanceById(int id) {
        Seance seance = null;
        try {
            seance = new GetSeanceByIdAsyncTask(this.dao).execute(id).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getSeanceById : " + e.getMessage());
        }
        return seance;
    }

    private static class GetSeanceByIdAsyncTask extends AsyncTask<Integer, Void, Seance> {
        private GoMuscuDao dao;

        GetSeanceByIdAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected Seance doInBackground(Integer... integers) {
            return this.dao.getSeanceById(integers);
        }
    }

    public Journee getJourneeByDate(Date date) {
        Journee journee = null;
        try {
            journee = new GetJourneeByDateAsyncTask(this.dao).execute(date).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getJourneeByDate : " + e.getMessage());
        }
        return journee;
    }

    private static class GetJourneeByDateAsyncTask extends AsyncTask<Date, Void, Journee> {
        private GoMuscuDao dao;

        GetJourneeByDateAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected Journee doInBackground(Date... dates) {
            return this.dao.getJourneeByDate(dates);
        }
    }

    public LiveData<List<Journee>> getAllJourneesFromDate(Date date) {
        LiveData<List<Journee>> journees = null;
        try {
            journees = new GetAllJourneesFromDateAsyncTask(this.dao).execute(date).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getAllJourneesFromDate : " + e.getMessage());
        }
        return journees;
    }

    private static class GetAllJourneesFromDateAsyncTask
            extends AsyncTask<Date, Void, LiveData<List<Journee>>> {
        private GoMuscuDao dao;

        GetAllJourneesFromDateAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected LiveData<List<Journee>> doInBackground(Date... dates) {
            return this.dao.getAllJourneesFromDate(dates);
        }
    }

    public LiveData<List<ExerciceDansHistorique>> getAllExerciceDansHistoriqueById(int id) {
        LiveData<List<ExerciceDansHistorique>> liste = null;
        try {
            liste = new GetAllExerciceDansHistoriqueByIdAsyncTask(this.dao).execute(id).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getAllExerciceDansHistoriqueById : " + e.getMessage());
        }
        return liste;
    }

    private static class GetAllExerciceDansHistoriqueByIdAsyncTask
            extends AsyncTask<Integer, Void, LiveData<List<ExerciceDansHistorique>>> {
        private GoMuscuDao dao;

        GetAllExerciceDansHistoriqueByIdAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected LiveData<List<ExerciceDansHistorique>> doInBackground(Integer... integers) {
            return this.dao.getAllExerciceDansHistoriqueById(integers);
        }
    }

    public LiveData<Integer> getExerciceDansHistoriqueCount() {
        LiveData<Integer> count = null;
        try {
            count = new GetExerciceDansHistoriqueCountAsyncTask(this.dao).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getExerciceDansHistoriqueCount : " + e.getMessage());
        }
        return count;
    }

    private static class GetExerciceDansHistoriqueCountAsyncTask
            extends AsyncTask<Void, Void, LiveData<Integer>> {
        private GoMuscuDao dao;

        GetExerciceDansHistoriqueCountAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected LiveData<Integer> doInBackground(Void... voids) {
            return this.dao.getExerciceDansHistoriqueCount();
        }
    }

    public Seance getSeanceByNom(String nom) {
        Seance seance = null;
        try {
            seance = new GetSeanceByNomAsyncTask(this.dao).execute(nom).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("erreurs", "getSeanceByNom : " + e.getMessage());
        }
        return seance;
    }

    private static class GetSeanceByNomAsyncTask extends AsyncTask<String, Void, Seance> {
        private GoMuscuDao dao;

        GetSeanceByNomAsyncTask(GoMuscuDao dao) {
            this.dao = dao;
        }

        @Override
        protected Seance doInBackground(String... strings) {
            return this.dao.getSeanceByNom(strings);
        }
    }

}
