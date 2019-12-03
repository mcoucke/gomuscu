package com.app.gomuscu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.app.gomuscu.entity.Exercice;

import java.util.ArrayList;
import java.util.List;

public class AjoutExerciceFragment extends Fragment {

    private View view;
    private int selected = 0;

    public AjoutExerciceFragment(){}
    public AjoutExerciceFragment(int selected){
        this.selected = selected;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ajout_exercice, container, false);

        GoMuscuViewModel goMuscuViewModel = ViewModelProviders.of(this).get(GoMuscuViewModel.class);

        goMuscuViewModel.getAllExercices().observe(this, new Observer<List<Exercice>>() {
            @Override
            public void onChanged(List<Exercice> exercices) {
                setSpinner(exercices);
            }
        });

        return view;
    }


    private void setSpinner(List<Exercice> listeExercice){
        ArrayAdapter<Exercice> dataAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, listeExercice);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = view.findViewById(R.id.spinner);
        spinner.setAdapter(dataAdapter);

        spinner.setSelection(selected);
    }

    public int getIdExerciceSelected(){
        Spinner spinner = view.findViewById(R.id.spinner);
        Exercice exo = (Exercice) spinner.getSelectedItem();
        return exo.getId();
    }
}
