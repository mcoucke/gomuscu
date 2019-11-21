package com.app.gomuscu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class CreerSeance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_seance);
    }

    public void onClickSauvegarder(View view){

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
