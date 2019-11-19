package com.app.gomuscu;

import androidx.appcompat.app.AppCompatActivity;

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
}
