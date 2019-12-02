package com.app.gomuscu;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.gomuscu.entity.Seance;

public class SeancesFragment extends Fragment {

    private Seance seance;

    public SeancesFragment(Seance seance){
        this.seance = seance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seances, container, false);

        TextView nomSeance = view.findViewById(R.id.tv_nom_seance);
        nomSeance.setText(seance.getNom());

        return view;
    }
}
