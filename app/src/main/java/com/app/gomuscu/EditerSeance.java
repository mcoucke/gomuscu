package com.app.gomuscu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EditerSeance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editer_seance);
    }

    public void onClickCreerSeance(View view){
        Intent intent = new Intent(this, CreerSeance.class);
        startActivity(intent);
    }

    public void onClickRetourMenu(View view){
        finish();
    }
}
