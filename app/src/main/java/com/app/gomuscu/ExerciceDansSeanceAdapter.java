package com.app.gomuscu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.app.gomuscu.entity.Exercice;
import com.app.gomuscu.entity.ExerciceDansSeance;

import java.util.List;

public class ExerciceDansSeanceAdapter extends RecyclerView.Adapter<ExerciceDansSeanceAdapter.ViewHolder> {
    private List<ExerciceDansSeance> data;
    private GoMuscuViewModel viewModel;

    public ExerciceDansSeanceAdapter(List<ExerciceDansSeance> data, GoMuscuViewModel viewModel) {
        this.data = data;
        this.viewModel = viewModel;
    }

    public void setData(List<ExerciceDansSeance> exerciceDansSeanceList) {
        this.data = exerciceDansSeanceList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.liste_exercices_row, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Exercice exercice = viewModel.getExerciceById(this.data.get(position).getIdExercice());
        holder.textView.setText(exercice.getNom());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        private EditText etNbReps;
        private EditText etPoids;

        public ViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.tvNomExo);
            this.etNbReps = view.findViewById(R.id.etNbRepListeExercices);
            this.etPoids = view.findViewById(R.id.etPoidsListeExercices);

        }
        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(), "position : " + getLayoutPosition() + " text : " + this.textView.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
