package com.app.gomuscu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.gomuscu.entity.Exercice;
import com.app.gomuscu.entity.Seance;

import java.util.ArrayList;
import java.util.List;

public class CreerSeance extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_seance);
    }

    public void onClickSauvegarder(View view){

//        EditText edNomSeance = findViewById(R.id.ed_nouvelle_seance);
//        String nomSeance = edNomSeance.getText().toString();
//
//        Seance seance = new Seance(nomSeance);
    }

    public void onClickAnnuler(View view){
        finish();
    }

    public void onClickAjouterExercice(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        AjoutExerciceFragment fragment = new AjoutExerciceFragment();
        fragmentTransaction.add(R.id.lin_lay_exercice, fragment);
        fragmentTransaction.commit();
    }
}
