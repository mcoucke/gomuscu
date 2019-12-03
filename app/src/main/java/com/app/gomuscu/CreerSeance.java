package com.app.gomuscu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
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
    Seance seance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_seance);
        goMuscuViewModel = ViewModelProviders.of(this).get(GoMuscuViewModel.class);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            int idSeance = bundle.getInt("idSeance");
            seance = goMuscuViewModel.getSeanceById(idSeance);
        }

        if(seance != null){
            EditText edNomSeance = findViewById(R.id.ed_nouvelle_seance);
            edNomSeance.setText(seance.getNom());

            System.out.println(seance.getId());
            goMuscuViewModel.getAllExercicesDansSeancesById(seance.getId()).observe(this, new Observer<List<ExerciceDansSeance>>() {
                @Override
                public void onChanged(List<ExerciceDansSeance> exerciceDansSeances) {
                    System.out.println(exerciceDansSeances.size());
                    creerFragment(exerciceDansSeances);
                }
            });
        }

    }

    public void creerFragment(List<ExerciceDansSeance> exerciceDansSeances){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        for(int i = 0; i < exerciceDansSeances.size(); ++i){
            AjoutExerciceFragment fragment = new AjoutExerciceFragment(exerciceDansSeances.get(i).getIdExercice() - 1);
            fragmentTransaction.add(R.id.lin_lay_exercice, fragment);
        }
        fragmentTransaction.commit();
    }

    public void onClickSauvegarder(View view){

        EditText edNomSeance = findViewById(R.id.ed_nouvelle_seance);
        String nomSeance = edNomSeance.getText().toString();

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        AjoutExerciceFragment currentFragment;
        int idExoSelected;
        long idSeance;

        if(this.seance != null){
            idSeance = this.seance.getId();
            goMuscuViewModel.deleteAllExercicesDansSeanceById((int) idSeance);
        }
        else{
            this.seance = new Seance(nomSeance);
            idSeance = goMuscuViewModel.insertSeance(seance);
        }

        for (int i=0; i < fragments.size(); ++i){
            currentFragment = (AjoutExerciceFragment) fragments.get(i);
            idExoSelected = currentFragment.getIdExerciceSelected();
            ExerciceDansSeance exerciceDansSeance = new ExerciceDansSeance((int) idSeance, idExoSelected);
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
