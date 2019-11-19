package com.app.gomuscu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class EditerSeance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editer_seance);
    }

    public void onClickCreerSeance(View view){

    }

    public void onClickRetourMenu(View view){
        finish();
    }
}
