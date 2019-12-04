package com.app.gomuscu;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gomuscu.entity.Historique;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueSeances extends AppCompatActivity {
    private GoMuscuViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historique_seances);

        this.viewModel = ViewModelProviders.of(this).get(GoMuscuViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.rvHistoriqueSeances);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final HistoriqueAdapter historiqueAdapter = new HistoriqueAdapter(new ArrayList<Historique>(), viewModel);
        recyclerView.setAdapter(historiqueAdapter);

        this.viewModel.getAllHistoriques().observe(this, new Observer<List<Historique>>() {
            @Override
            public void onChanged(List<Historique> historiqueSeances) {
                historiqueAdapter.setData(historiqueSeances);
            }
        });
    }

    public void onClickRetour(View view) {
        finish();
    }
}
