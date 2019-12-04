package com.app.gomuscu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gomuscu.entity.Journee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditerPlanning extends AppCompatActivity {
    private GoMuscuViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editer_planning);

        this.viewModel = ViewModelProviders.of(this).get(GoMuscuViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.rvPlanning);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final JourneeAdapter journeeAdapter = new JourneeAdapter(new ArrayList<Journee>(), viewModel);
        recyclerView.setAdapter(journeeAdapter);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date today = cal.getTime();

        this.viewModel.getAllJourneesFromDate(today).observe(this, new Observer<List<Journee>>() {
            @Override
            public void onChanged(List<Journee> journees) {
                journeeAdapter.setData(journees);
            }
        });


    }

    public void onClickCreerJournee(View view) {
        Intent intent = new Intent(this, CreerJournee.class);
        startActivity(intent);
    }


}
