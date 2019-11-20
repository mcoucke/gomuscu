package com.app.gomuscu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

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

//        Exercice test = new Exercice("pull over", "faire des pull over mon pote", "pecs");
//
//        float oui = goMuscuViewModel.insertExercice(test);
//        System.out.println("TEST INSERT : " + oui);
//
//        List<Exercice> aled = goMuscuViewModel.getAllExercices();
//        System.out.println("LEN EXO : " + aled.size());
//        for (int i = 0; i < aled.size(); i++) {
//            System.out.println(aled.get(i));
//        }
    }

    public void onImageClick(View view) {
        System.out.println("click image planning");
    }

    public void onClickEditPlanning(View view) {
        System.out.println("click edit planning");
    }

    public void onClickEditSeances(View view) {
        System.out.println("click edit planning");
    }

    public void onClickViewHistorique(View view) {
        System.out.println("click edit planning");
    }
}
