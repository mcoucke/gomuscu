package com.app.gomuscu;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gomuscu.entity.ExerciceDansHistorique;
import com.app.gomuscu.entity.ExerciceDansSeance;
import com.app.gomuscu.entity.Historique;
import com.app.gomuscu.entity.Journee;
import com.app.gomuscu.entity.Repetition;
import com.app.gomuscu.entity.Seance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DemarrerSeance extends AppCompatActivity {
    private GoMuscuViewModel viewModel;
    private Seance seance;
    private List<ExerciceDansSeance> exerciceDansSeanceList;
    private RecyclerView recyclerView;
    private boolean isGlobalCounting = false;
    private boolean isReposCounting = false;
    private int currentExo = 0;
    private int nbExos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demarrer_seance);

        Intent mIntent = getIntent();
        int id_seance = mIntent.getIntExtra("id_seance", 0);

        this.viewModel = ViewModelProviders.of(this).get(GoMuscuViewModel.class);

        this.seance = this.viewModel.getSeanceById(id_seance);
        if (this.seance == null) {
            finish();
        }

        this.recyclerView = findViewById(R.id.rvListeExos);

        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ExerciceDansSeanceAdapter exerciceDansSeanceAdapter =
                new ExerciceDansSeanceAdapter(new ArrayList<ExerciceDansSeance>(), viewModel);
        this.recyclerView.setAdapter(exerciceDansSeanceAdapter);

        this.viewModel.getAllExercicesDansSeancesById(this.seance.getId()).observe(this, new Observer<List<ExerciceDansSeance>>() {
            @Override
            public void onChanged(List<ExerciceDansSeance> exerciceDansSeances) {
                exerciceDansSeanceAdapter.setData(exerciceDansSeances);
                setExerciceDansSeance(exerciceDansSeances);
                setNbExos(exerciceDansSeances.size());
            }
        });
    }

    public void onClickBtnGlobal(View view) {
        // Cas séance non démarrée
        if (!this.isGlobalCounting) {
            // Reset du chrono
            Chronometer chrono = (Chronometer) findViewById(R.id.chronoGlobal);
            chrono.setBase(SystemClock.elapsedRealtime());
            startChronoGlobal();
            Button buttonGlobal = (Button) findViewById(R.id.seance_btn_global);
            buttonGlobal.setEnabled(false);
            buttonGlobal.setText(R.string.seance_btn_fin);
            ((Button) findViewById(R.id.seance_btn_repos)).setEnabled(true);
            enableView(this.currentExo);
            this.isGlobalCounting = true;
        }
        // Cas fin de séance
        else {
            stopChronoGlobal();
            //Insertions dans la base
            int id_historique = terminerSeance();
            // fermeture de l'activity, lancement voirHistorique
            voirHistorique(id_historique);
        }
    }

    public void onClickBtnRepos(View view) {
        // Cas démarrage du repos
        if (!this.isReposCounting) {
            // Reset du chrono
            Chronometer chrono = (Chronometer) findViewById(R.id.chronoRepos);
            chrono.setBase(SystemClock.elapsedRealtime());
            startChronoRepos();
            ((Button) findViewById(R.id.seance_btn_repos)).setText(R.string.seance_btn_reprise);
            this.isReposCounting = true;
            // Maj liste exercices


        }
        // Cas fin de repos
        else {
            stopChronoRepos();
            Button btn_repos = (Button) findViewById(R.id.seance_btn_repos);
            btn_repos.setText(R.string.seance_btn_repos);
            disableView(this.currentExo);
            this.currentExo += 1;
            if(this.currentExo >= this.nbExos-1) {
                if (this.currentExo == this.nbExos-1) {
                    this.enableView(this.currentExo);
                }
                // dernier exercice
                btn_repos.setEnabled(false);
                ((Button) findViewById(R.id.seance_btn_global)).setEnabled(true);

            } else {
                this.enableView(this.currentExo);
            }
            this.isReposCounting = false;
        }
    }

    /**
     * Insertion dans la base des données de la séance effectuée
     * @return l'id de l'historique créé
     */
    public int terminerSeance() {
        Calendar cal = Calendar.getInstance();
        // Les dates insérées doivent impérativement être définies sans heure/minutes/...
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        Chronometer chronoGlobal = (Chronometer) findViewById(R.id.chronoGlobal);
        long elapsedMillis = SystemClock.elapsedRealtime() - chronoGlobal.getBase();
        int dureeSecondes = (int) elapsedMillis / 1000;
        Historique historique = new Historique(this.seance.getNom(), date, dureeSecondes);
        long id_historique = viewModel.insertHistorique(historique);
        //Insertion par exercice
        for (int i = 0; i < this.nbExos; i++) {
            ExerciceDansHistorique exerciceDansHistorique = new ExerciceDansHistorique(
                    exerciceDansSeanceList.get(i).getIdExercice(), (int) id_historique);
            long id_exoDansSeance = viewModel.insertExerciceDansHistorique(exerciceDansHistorique);

            // insert Repetition
            RecyclerView.ViewHolder viewHolder = this.recyclerView.findViewHolderForAdapterPosition(i);
            if (viewHolder != null) {
                View view = viewHolder.itemView;
                EditText etNbRep = (EditText) view.findViewById(R.id.etNbRepListeExercices);
                EditText etPoids = (EditText) view.findViewById(R.id.etPoidsListeExercices);
                int nbRep = -1;
                int poids = -1;
                if(etNbRep.getText().toString().length() > 0) {
                    nbRep = Integer.valueOf(etNbRep.getText().toString());
                }
                if(etPoids.getText().toString().length() > 0) {
                    poids = Integer.valueOf(etPoids.getText().toString());
                }
                Repetition repet = new Repetition(nbRep, poids, -1, (int) id_exoDansSeance);
                viewModel.insertRepetition(repet);
            }
        }
        // Suppression de la séance planifiée aujourd'hui
        Journee journee = viewModel.getJourneeByDate(date);
        viewModel.deleteJournee(journee);

        return (int) id_historique;
    }

    public void voirHistorique(int id_historique) {
        Intent intent = new Intent(this, BilanSeance.class);
        intent.putExtra("id_historique", id_historique);
        finish();
        startActivity(intent);
    }

    /**
     * Active la ligne correspondant à la position donnée dans le RecyclerView
     * @param position : position dans la liste de l'élément
     */
    public void enableView(int position) {
        RecyclerView.ViewHolder viewHolder = this.recyclerView.findViewHolderForAdapterPosition(position);
        if (viewHolder != null) {
            View view = viewHolder.itemView;
            ((TextView) view.findViewById(R.id.tvNomExo)).setEnabled(true);
            ((EditText) view.findViewById(R.id.etNbRepListeExercices)).setEnabled(true);
            ((EditText) view.findViewById(R.id.etPoidsListeExercices)).setEnabled(true);
        }
    }

    /**
     * Désactive la ligne correspondant à la position donnée dans le RecyclerView
     * @param position : position dans la liste de l'élément
     */
    public void disableView(int position) {
        RecyclerView.ViewHolder viewHolder = this.recyclerView.findViewHolderForAdapterPosition(position);
        if (viewHolder != null) {
            View view = viewHolder.itemView;
            ((TextView) view.findViewById(R.id.tvNomExo)).setEnabled(false);
            ((EditText) view.findViewById(R.id.etNbRepListeExercices)).setEnabled(false);
            ((EditText) view.findViewById(R.id.etPoidsListeExercices)).setEnabled(false);
        }

    }

    public void setNbExos(int nbExos) {
        this.nbExos = nbExos;
    }

    public void setExerciceDansSeance(List<ExerciceDansSeance> exerciceDansSeanceList) {
        this.exerciceDansSeanceList = exerciceDansSeanceList;
    }

    public void startChronoGlobal() {
        ((Chronometer) findViewById(R.id.chronoGlobal)).start();
    }

    public void stopChronoGlobal() {
        ((Chronometer) findViewById(R.id.chronoGlobal)).stop();
    }

    public void startChronoRepos() {
        ((Chronometer) findViewById(R.id.chronoRepos)).start();
    }

    public void stopChronoRepos() {
        ((Chronometer) findViewById(R.id.chronoRepos)).stop();
    }

}
