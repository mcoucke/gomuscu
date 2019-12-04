package com.app.gomuscu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.gomuscu.entity.Journee;
import com.app.gomuscu.entity.Seance;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CreerJournee extends AppCompatActivity {

    Date dateSeance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_journee);

        GoMuscuViewModel goMuscuViewModel = ViewModelProviders.of(this).get(GoMuscuViewModel.class);
        goMuscuViewModel.getAllSeances().observe(this, new Observer<List<Seance>>() {
            @Override
            public void onChanged(List<Seance> seances) {
                setSpinner(seances);
            }
        });
    }

    public void setSpinner(List<Seance> seances){
        ArrayAdapter<Seance> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, seances);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spin_seance);
        spinner.setAdapter(dataAdapter);
    }

    public void onClickChoisirDate(View view){

        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                dateSeance = calendar.getTime();
                updateLabel();
            }
        };

        new DatePickerDialog(this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    public void updateLabel(){
        EditText editText = findViewById(R.id.et_date);
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText.setText(sdf.format(dateSeance));
    }

    public void onClickPlanSeance(View view){
        Spinner spinner = findViewById(R.id.spin_seance);
        Seance selectedSeance = (Seance) spinner.getSelectedItem();

        Journee journee = new Journee(dateSeance, selectedSeance.getId());
        GoMuscuViewModel goMuscuViewModel = ViewModelProviders.of(this).get(GoMuscuViewModel.class);
        goMuscuViewModel.insertJournee(journee);
        finish();
    }
}
