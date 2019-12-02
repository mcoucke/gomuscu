package com.app.gomuscu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
import com.app.gomuscu.entity.ExerciceDansSeance;
import com.app.gomuscu.entity.Seance;

import java.util.ArrayList;
import java.util.List;

public class CreerSeance extends AppCompatActivity {

    GoMuscuViewModel goMuscuViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_seance);
        goMuscuViewModel = ViewModelProviders.of(this).get(GoMuscuViewModel.class);
    }

    public void onClickSauvegarder(View view){

        EditText edNomSeance = findViewById(R.id.ed_nouvelle_seance);
        String nomSeance = edNomSeance.getText().toString();

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        AjoutExerciceFragment currentFragment;
        int idExoSelected;

        Seance seance = new Seance(nomSeance);
        goMuscuViewModel.insertSeance(seance);

        for (int i=0; i < fragments.size(); ++i){
            currentFragment = (AjoutExerciceFragment) fragments.get(i);
            idExoSelected = currentFragment.getIdExerciceSelected();
            ExerciceDansSeance exerciceDansSeance = new ExerciceDansSeance(seance.getId(), idExoSelected);
            goMuscuViewModel.insertExerciceDansSeance(exerciceDansSeance);
        }

        finish();

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
