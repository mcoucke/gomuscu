package com.app.gomuscu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        Button boutonModifier = view.findViewById(R.id.btn_modifier);
        boutonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreerSeance.class);
                Bundle bundle = new Bundle();
                bundle.putInt("idSeance", seance.getId());
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });


        Button boutonSupprimer = view.findViewById(R.id.btn_supprimer_seance);
        boutonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
