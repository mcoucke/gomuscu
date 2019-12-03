package com.app.gomuscu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    private GoMuscuViewModel goMuscuViewModel;

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
        final View view = inflater.inflate(R.layout.fragment_seances, container, false);

        goMuscuViewModel = ViewModelProviders.of(this.getActivity()).get(GoMuscuViewModel.class);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setCancelable(true);
                builder.setTitle("Supprimer séance");
                builder.setMessage("Êtes vous sur de vouloir supprimer la séance ?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                goMuscuViewModel.deleteSeance(seance);
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });

        return view;
    }
}
