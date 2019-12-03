package com.app.gomuscu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.gomuscu.entity.Exercice;
import com.app.gomuscu.entity.Seance;

import java.util.List;

public class EditerSeance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editer_seance);

        GoMuscuViewModel goMuscuViewModel = ViewModelProviders.of(this).get(GoMuscuViewModel.class);
        goMuscuViewModel.getAllSeances().observe(this,  new Observer<List<Seance>>() {
            @Override
            public void onChanged(List<Seance> seances) {
                fillListeSeances(seances);
            }
        });


    }

    public void fillListeSeances(List<Seance> seances){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        System.out.println(seances.size());
        for (int i = 0; i < seances.size(); i++){
            SeancesFragment seancesFragment = new SeancesFragment(seances.get(i));
            fragmentTransaction.add(R.id.lin_lay_seance, seancesFragment);
        }
        fragmentTransaction.commit();
    }

    public void onClickCreerSeance(View view){
        Intent intent = new Intent(this, CreerSeance.class);
        startActivity(intent);
    }

    public void onClickRetourMenu(View view){
        finish();
    }
}
