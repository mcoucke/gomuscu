package com.app.gomuscu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gomuscu.entity.ExerciceDansHistorique;
import com.app.gomuscu.entity.Historique;

import java.util.ArrayList;
import java.util.List;

public class BilanSeance extends AppCompatActivity {
    private GoMuscuViewModel viewModel;
    private List<ExerciceDansHistorique> exerciceDansHistoriqueList;
    private RecyclerView recyclerView;
    private Historique historique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bilan_seance);

        Intent mIntent = getIntent();
        int id_historique = mIntent.getIntExtra("id_historique", 0);

        this.viewModel = ViewModelProviders.of(this).get(GoMuscuViewModel.class);

        this.historique = this.viewModel.getHistoriqueById(id_historique);
        if (this.historique == null) {
            finish();
        }

        this.recyclerView = findViewById(R.id.rvBilanListeExos);

        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ExerciceDansHistoriqueAdapter exerciceDansHistoriqueAdapter =
                new ExerciceDansHistoriqueAdapter(
                        new ArrayList<ExerciceDansHistorique>(), viewModel, historique.getId());
        this.recyclerView.setAdapter(exerciceDansHistoriqueAdapter);

        this.viewModel.getAllExerciceDansHistoriqueById(this.historique.getId()).observe(this, new Observer<List<ExerciceDansHistorique>>() {
            @Override
            public void onChanged(List<ExerciceDansHistorique> exerciceDansHistoriques) {
                exerciceDansHistoriqueAdapter.setData(exerciceDansHistoriques);
            }
        });

        int duree_minutes = this.historique.getDureeSecondes() / 60;
        String str_minutes = Integer.toString(duree_minutes);
        if (duree_minutes < 10) {
            str_minutes = "0" + duree_minutes;
        }
        int duree_secondes = this.historique.getDureeSecondes() - duree_minutes*60;
        String str_secondes = Integer.toString(duree_secondes);
        if (duree_secondes < 10) {
            str_secondes = "0" + duree_secondes;
        }
        String text_chrono = str_minutes + ":" + str_secondes;
        ((TextView) findViewById(R.id.tvBilanDureeTotale)).setText(text_chrono);

    }


    public void onClickBtnValider(View view) {
        finish();
    }
}
