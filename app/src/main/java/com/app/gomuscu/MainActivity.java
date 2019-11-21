package com.app.gomuscu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.gomuscu.entity.Exercice;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoMuscuViewModel goMuscuViewModel = ViewModelProviders.of(this).get(GoMuscuViewModel.class);


        goMuscuViewModel.getAllExercices().observe(this, new Observer<List<Exercice>>() {
            @Override
            public void onChanged(List<Exercice> exercices) {
                afficherExos(exercices);
            }
        });

    }

    public void afficherExos(List<Exercice> exercices) {
        for (int i = 0; i < exercices.size(); i++) {
            System.out.println(exercices.get(i));
        }
    }

    public void onImageClick(View view) {
        System.out.println("click image planning");
    }

    public void onClickEditPlanning(View view) {
        System.out.println("click edit planning");
    }

    public void onClickEditSeances(View view) {
        Intent intent = new Intent(this, EditerSeance.class);
        startActivity(intent);
    }

    public void onClickViewHistorique(View view) {
        System.out.println("click edit planning");
    }
}
